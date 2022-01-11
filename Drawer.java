package fr.scoffgard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Drawer extends JPanel implements MouseMotionListener {

    private ArrayList triangles = new ArrayList();

    private double xR, yR;
    private String firstAx, secondAx;

    public Camera cam;


    public void paintComponent(Graphics g) {
        // Setting up the window
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Get slider values
        double horizontal = Math.toRadians(xR);
        Matrix3 horizontalTransform = parseAxMatrix(firstAx, horizontal);

        double vertical = Math.toRadians(yR);
        Matrix3 verticalTransform = parseAxMatrix(secondAx, vertical);

        Matrix3 transform = horizontalTransform.multiply(verticalTransform);

        // Set the drawing color
        g2d.setColor(Color.WHITE);

        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        double[] zBuffer = new double[img.getWidth() * img.getHeight()];
        // initialize array with extremely far away depths
        for (int q = 0; q < zBuffer.length; q++) {
            zBuffer[q] = Double.NEGATIVE_INFINITY;
        }

        for (Object o : triangles) {
            Polygon polygon = (Polygon) o;

            Vertex v1 = transform.transform(polygon.v1);
            Vertex v2 = transform.transform(polygon.v2);
            Vertex v3 = transform.transform(polygon.v3);


            v1 = processCoordinates(v1, cam);
            v2 = processCoordinates(v2, cam);
            v3 = processCoordinates(v3, cam);

            // compute rectangular bounds for triangle
            int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
            int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
            int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
            int maxY = (int) Math.min(img.getHeight() - 1,  Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

            double triangleArea = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    double b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
                    double b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
                    double b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
                    if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
                        double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
                        int zIndex = y * img.getWidth() + x;
                        if (zBuffer[zIndex] < depth) {
                            img.setRGB(x, y, polygon.color.getRGB());
                            zBuffer[zIndex] = depth;
                        }
                    }
                }
            }
        }

        g2d.drawImage(img, (int) -xR*(getWidth()/1000), (int) yR*(getHeight()/1000), null);
    }

    public void paintTriangle(Polygon polygon) {
        triangles.add(polygon);
        this.repaint();
    }

    Drawer(String firstAx, String secondAx) {
        this.firstAx = firstAx;
        this.secondAx = secondAx;
        this.addMouseMotionListener(this);
        cam = new Camera(0, 0, 1);
    }

    private Matrix3 parseAxMatrix(String ax, double value) {
        return switch (ax) {
            case "XY" -> Ax.XY(value);
            case "YZ" -> Ax.YZ(value);
            default -> Ax.XZ(value);
        };
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        double x = (e.getX() - (getWidth() / 2d));
        double y = (e.getY() - (getHeight() / 2d));

        xR = (180 * Window.sensibility) * x / getWidth();
        yR = (180 * Window.sensibility) * -y / getHeight();

        this.repaint();
    }

    private Vertex processCoordinates(Vertex v, Camera camera) {
        v.x -= camera.x;
        v.y -= camera.y;
        v.z += camera.z;

        v.x /= v.z == 0 ? 1 : v.z;
        v.x *= getWidth() / 2;
        v.x += getWidth() / 2;

        v.y /= v.z == 0 ? 1 : v.z;
        v.y *= getHeight() / 2;
        v.y += getHeight() / 2;

        return v;
    }
}

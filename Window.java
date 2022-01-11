package fr.scoffgard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window {

    private Drawer renderPanel;

    public static double sensibility = 1;

    public static int height = 800, width = 800;

    Window(String firstAx, String secondAx) {

        renderPanel = new Drawer(firstAx, secondAx);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setTitle("GameEngine");

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        pane.add(renderPanel, BorderLayout.CENTER);


        try {
            Robot r = new Robot();
            r.mouseMove(width / 2, height / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        //frame.getContentPane().setCursor(blankCursor);

        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public void draw(Polygon polygon) {
        renderPanel.paintTriangle(polygon);
    }

    public void draw(Polygon[] polygons) {
        for(Polygon polygon : polygons) {
            renderPanel.paintTriangle(polygon);
        }

    }



}

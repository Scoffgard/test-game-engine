package fr.scoffgard;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Window window = new Window("XZ", "YZ");

        /*window.draw(
                new Triangle(
                        new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(-100, 100, -100),
                        Color.WHITE
                )
        );
        window.draw(
                new Triangle(
                        new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(100, -100, -100),
                        Color.RED
                )
        );

        window.draw(
                new Triangle(
                        new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(100, 100, 100),
                        Color.GREEN
                )
        );
        window.draw(
                new Triangle(
                        new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(-100, -100, 100),
                        Color.BLUE
                )
        );*/

        window.draw(new Square(
                new Vertex(-1, -1, 0),
                new Vertex(1, -1, 0),
                new Vertex(1, 1, 0),
                new Vertex(-1, 1, 0),
                Color.WHITE
        ).polygons);

        window.draw(new Square(
                new Vertex(-0.5, -0.5, 1),
                new Vertex(0.5, -0.5, 1),
                new Vertex(0.5, 0.5, 1),
                new Vertex(-0.5, 0.5, 1),
                Color.RED
        ).polygons);

        /*window.draw(new Square(
                new Vertex(-100, -100, 100),
                new Vertex(-100, -100, -100),
                new Vertex(-100, 100, -100),
                new Vertex(-100, 100, 100),
                Color.RED
        ).triangles);

        window.draw(new Square(
                new Vertex(100, -100, 100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, -100),
                new Vertex(100, 100, 100),
                Color.GREEN
        ).triangles);

        window.draw(new Square(
                new Vertex(-100, -100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, -100),
                new Vertex(-100, 100, -100),
                Color.ORANGE
        ).triangles);

        window.draw(new Square(
                new Vertex(-100, -100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, -100, 100),
                new Vertex(-100, -100, 100),
                Color.BLUE
        ).triangles);

        window.draw(new Square(
                new Vertex(-100, 100, -100),
                new Vertex(100, 100, -100),
                new Vertex(100, 100, 100),
                new Vertex(-100, 100, 100),
                Color.YELLOW
        ).triangles);*/
    }
}

package fr.scoffgard;

import java.awt.*;

public class Square{
    Polygon[] polygons;
    Square(Vertex v1, Vertex v2, Vertex v3, Vertex v4, Color color) {
        polygons = new Polygon[] {
            new Polygon(v1, v2, v3, color),
            new Polygon(v1, v4, v3, color)
        };
    }
}

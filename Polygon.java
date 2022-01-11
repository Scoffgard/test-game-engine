package fr.scoffgard;

import java.awt.*;

public class Polygon {
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Color color;
    Polygon(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
}

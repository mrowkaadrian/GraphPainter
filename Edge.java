/*
 * Program: Aplikacja do tworzenia i modyfikowania prostych grafów przy pomocy GUI (Swing)
 *    Plik: Edge.java
 *
 *   Autor: Adrian Mrówka
 *  Indeks: 234939
 *    Data: grudzień 2018 r.
 */

import java.awt.*;
import java.io.Serializable;

public class Edge implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int s_number;

    private Node node1;
    private Node node2;
    private int number;
    private Color color;

    // ----- constructor -----
    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.color = Color.RED;
        number = s_number++;
    }

    // ----- member functions -----
    public boolean isMouseOver(int mouseX, int mouseY) {

        int centerX = (this.node1.getX()+this.node2.getX())/2;
        int centerY = (this.node1.getY()+this.node2.getY())/2;

        double distance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow((mouseY - centerY), 2));
        double maxDistance = 8d;

        if (distance < maxDistance) {
            return true;
        }
        else
            return false;

    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
    }

    @Override
    public String toString() {
        return ("Krawedz " + number + ": " + this.node1.getNumber() + "->" + this.node2.getNumber() + "   Kolor(#" + String.valueOf(color.getRGB()) + ")");
    }
}

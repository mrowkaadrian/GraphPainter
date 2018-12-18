/*
 * Program: Aplikacja do tworzenia i modyfikowania prostych grafów przy pomocy GUI (Swing)
 *    Plik: Node.java
 *
 *   Autor: Adrian Mrówka
 *  Indeks: 234939
 *    Data: grudzień 2018 r.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int s_number = 0;

	private int x;
	private int y;
	private int r;
	private int number;
	private String coordinates = "";
	private String name = "";

	private Color color;

	public Node(int x, int y, String name) {
		this(x,y);
		this.name = name;
	}

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 10;
		this.color = Color.WHITE;
		number = s_number++;
		updateCoordinates();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getNumber() { return number; }

	public void updateCoordinates() {
		this.coordinates = String.valueOf(this.x) + ", " + String.valueOf(this.y);
	}
	
	public boolean isMouseOver(int mx, int my){
		return (x-mx)*(x-mx)+(y-my)*(y-my)<=r*r;
	}

	void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		g.setColor(Color.BLACK);
		g.drawOval(x-r, y-r, 2*r, 2*r);
		g.drawString(coordinates, x+r+2, y+r);
		g.drawString(name, x, y);
	}
	
	@Override
	public String toString(){
		return (number + "]: (" + x +"," + y + "," + r + ")");
	}
	
}

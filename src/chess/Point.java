package chess;

import java.awt.Color;

public class Point {
	private int x;
	private int y;
	private Color color;
	public static final int DIAMETER = 30;
	public Point(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Color getColor() {
		return color;
	}

}

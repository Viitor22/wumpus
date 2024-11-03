package main.game.map;

public class Point {
	private String id;
	private int positionX;
	private int positionY;
	private double weight;
	
	public Point(int x, int y) {
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return (this.positionX == p.getPositionX() && this.positionY == p.getPositionY());
	}
}

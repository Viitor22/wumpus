package main.game.map;

public class Obstacle {
	private Point points;
	
	public Obstacle(Point coordinates) {
		this.setPoints(coordinates);
	}

	public Point getPoints() {
		return points;
	}

	public void setPoints(Point points) {
		this.points = points;
	}

}

package main.game.map;

import java.util.List;

public class Rock {
	public static String CHARACTER = "R";
	
	private List<Point> points;
	Rock(List<Point> points) {
		this.points = points;
	}
	
	public Point getPoint(int position) {
		return this.points.get(position);
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	public boolean hasConflict(List<Point> points) {
		for (int i = 0; i < this.points.size(); i++) {
			Point rockPoint = this.points.get(i);
			for (int j = 0; j < points.size(); j++) {
				Point p = points.get(j);
				if (rockPoint.getPositionX() == p.getPositionX() && rockPoint.getPositionY() == p.getPositionY()) {
					return true;
				}
			}
		}
		
		return false;
	}
}


package main.strategies;

import java.util.List;

import main.game.map.GameMap;
import main.game.map.Point;

public class ShortestDistance implements Strategy {

	@Override
	public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap map) {
		Point robotLocation = map.getRobotLocation();
		Point closestPoint = null;
		double minDistance = Double.MAX_VALUE;

		for (Point nextPoint : possibleNextStep) {
			double distance = Math.abs(nextPoint.getPositionX() - robotLocation.getPositionX()) +
					Math.abs(nextPoint.getPositionY() - robotLocation.getPositionY());
			if (distance < minDistance) {
				minDistance = distance;
				closestPoint = nextPoint;
			}
		}
		return closestPoint;
	}
}


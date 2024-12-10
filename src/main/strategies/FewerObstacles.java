
package main.strategies;

import java.util.ArrayList;
import java.util.List;

import main.game.map.GameMap;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;


public class FewerObstacles implements Strategy {

	@Override
	public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap map) {
		int minObstacleCount = Integer.MAX_VALUE;
		Point bestPoint = null;

		for (Point nextPoint : possibleNextStep) {
			int obstacleCount = countAdjacentObstacles(nextPoint, map);
			if (obstacleCount < minObstacleCount) {
				minObstacleCount = obstacleCount;
				bestPoint = nextPoint;
			}
		}
		return bestPoint;
	}

	private int countAdjacentObstacles(Point point, GameMap gameMap) {
		int count = 0;
		List<Point> allPoints = new ArrayList<Point>();
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY()));
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY() + 1));
		allPoints.add(new Point(point.getPositionX(), point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX(), point.getPositionY() + 1));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY()));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY() + 1));

		for(int i = 0; i < allPoints.size(); i++) {
			Point currentPoint = allPoints.get(i);
			Point playerPosition = gameMap.getRobotLocation();
			int[] scenarioSize = gameMap.getScenarioSize();
			if(
					!(currentPoint.getPositionX() == playerPosition.getPositionX() && currentPoint.getPositionY() == playerPosition.getPositionY()) ||
					currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0] ||
					currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= scenarioSize[1]
			) {
				continue;
			}else {
				if(gameMap.get(currentPoint).equals(Rock.CHARACTER) || gameMap.get(currentPoint).equals(Monster.CHARACTER)) {
					count++;
				}else {
					if(gameMap.get(currentPoint).equals(TreasureChest.CHARACTER)) {
						count = 0;
					}
				}
			}
		}

		return count;
	}
}

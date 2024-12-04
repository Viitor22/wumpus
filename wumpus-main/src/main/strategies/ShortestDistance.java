package main.strategies;
import java.util.List;

import main.game.map.Map;
import main.game.map.Point;

// Utilizando o algoritmo A* para determinar o caminho mais curto até o tesouro

public class ShortestDistance implements Strategy {

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
        Point robotLocation = map.getRobotLocation();
		Point closestPoint = null;
		double minDistance = Double.MAX_VALUE;

		for (Point nextPoint : possibleNextStep) {
            Point p = nextPoint;
            Point t = robotLocation;
			double distance = AStar(p, t);
			if (distance < minDistance) {
				minDistance = distance;
				closestPoint = nextPoint;
			}
		}
		return closestPoint;
    }

    // private boolean findTreasure(Point p, Map map) {
    //     return map.get(p).equals(TreasureChest.CHARACTER);
    // }

    private double AStar(Point p, Point t) {
        int custo = 0;
        double heuristic = Math.sqrt(Math.pow((p.getPositionX() - t.getPositionX()), 2.0) + Math.pow((p.getPositionY() - t.getPositionY()), 2.0));
        return custo + heuristic;
    }
}

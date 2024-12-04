package main.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import main.game.map.*;

public class FewerObstacles implements Strategy {

    private Set<Point> visitedPoints = new HashSet<>();

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
        Iterator<Point> it = possibleNextStep.iterator();
        int min = Integer.MAX_VALUE;
        Point pointSelected = null;

        while (it.hasNext()) {
            Point p = it.next();

            if (visitedPoints.contains(p)) {
                continue; 
            }

            int count = evaluatePoint(p, map);
            if (count < min) {
                min = count;
                pointSelected = p;
            }
        }

        if (pointSelected != null) {
            visitedPoints.add(pointSelected); 
        }

        return pointSelected;
    }

    private int evaluatePoint(Point p, Map map) {
        int count = 0;
        List<Point> points = new ArrayList<>();
        points.add(new Point(p.getPositionX() - 1, p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() - 1, p.getPositionY()));
        points.add(new Point(p.getPositionX() - 1, p.getPositionY() + 1));
        points.add(new Point(p.getPositionX(), p.getPositionY() - 1));
        points.add(new Point(p.getPositionX(), p.getPositionY() + 1));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY()));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY() + 1));

        int[] scenarioSize = map.getScenarioSize();
        Point robotLocation = map.getRobotLocation();

        for (Point currentPoint : points) {
            if (
                    (currentPoint.getPositionX() == robotLocation.getPositionX() &&
                            currentPoint.getPositionY() == robotLocation.getPositionY()) ||
                            (currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0]) ||
                            (currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= scenarioSize[1])
            ) {
                continue;
            }

            String content = map.get(currentPoint);
            if (content.equals(Rock.CHARACTER) || content.equals(Monster.CHARACTER)) {
                count++;
            } else if (content.equals(TreasureChest.CHARACTER)) {
                count = 0; 
            }
        }

        return count;
    }
}

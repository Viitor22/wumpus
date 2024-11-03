package main.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;

public class FewerObstacles implements Strategy{

	/**
	 * N is the next location
	 * p1 p2 p3
	 * p4 N p5
	 * p6 p7 p8
	 */

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
		Iterator<Point> it = possibleNextStep.iterator();
		int min = Integer.MAX_VALUE;
		Point pointSelected = null;
		while (it.hasNext()){
			Point p = it.next();
			int count = evaluatePoint(p, map);

			if (count < min){
				min = count;
				pointSelected = p;
			}
			if (count == min){
				Sort(possibleNextStep, map);
			}
		}
		return pointSelected;
	}

	private Point Sort(List<Point> possibleNextSteps, Map map) {

			Random random = new Random();
			if (!possibleNextSteps.isEmpty()) {
				int index = random.nextInt(possibleNextSteps.size());
				Point nextStep = possibleNextSteps.get(index);
				if (map.get(nextStep).equals(TreasureChest.CHARACTER)) {
					return nextStep;
				}
				return nextStep;
			}
			return null;
		}

	private int evaluatePoint(Point p, Map map) {
		int count = 0;
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(p.getPositionX() - 1, p.getPositionY() - 1));
		points.add(new Point(p.getPositionX() - 1, p.getPositionY()));
		points.add(new Point(p.getPositionX() - 1, p.getPositionY() + 1));
		points.add(new Point(p.getPositionX(), p.getPositionY() - 1));
		points.add(new Point(p.getPositionX(), p.getPositionY() + 1));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY() - 1));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY()));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY() + 1));

		for(int i = 0; i<points.size(); i++){
			Point currentPoint = points.get(i);
			int [] scenarioSize = map.getScenarioSize();
			if ((currentPoint.getPositionX() < 0 || currentPoint.getPositionX() > scenarioSize[0]) ||
					(currentPoint.getPositionY() < 0 || currentPoint.getPositionY() > scenarioSize[1])) {
				continue;
			} else {
				if (map.get(currentPoint).equals(Rock.CHARACTER) || !map.get(currentPoint).equals("M")){
					count++;
				}else {
					if (!map.get(currentPoint).equals(TreasureChest.CHARACTER)){
						map.openTreasureChest(currentPoint);
						count--;
					}
				}
			}

		}
		return count;
	}



}
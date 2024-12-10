package main.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import main.game.map.GameMap;
import main.game.map.Point;

public class Voting implements Strategy{
	public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap map) {
		ShortestDistance shortestdistance = new ShortestDistance();
		FewerObstacles fewerobstacles = new FewerObstacles();
		Sort sort = new Sort();
		
		Point shortestdistancePoint = shortestdistance.evaluatePossibleNextStep(possibleNextStep, map);
		Point fewerobstaclesPoint = fewerobstacles.evaluatePossibleNextStep(possibleNextStep, map);
		Point sortPoint = sort.evaluatePossibleNextStep(possibleNextStep, map);
		
		List<Point> points = new LinkedList<>();
		points.add(shortestdistancePoint);
		points.add(fewerobstaclesPoint);
		points.add(sortPoint);
		
		HashMap<Point, Integer> votation = new HashMap<>();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			if (votation.get(p) == null) {
				votation.put(p, 1);
			} else {
				votation.put(p, votation.get(p) + 1);
			}
		}
		
		return getMostedVotedPoint(votation);
	}
		
	private Point getMostedVotedPoint(HashMap<Point, Integer> votation) {
		Integer biggestValue = Integer.MIN_VALUE;
		List<Point> mostVotedPoints = new ArrayList<>();
		for (Entry<Point, Integer> entry : votation.entrySet()) {
			if (entry.getValue() > biggestValue && entry.getKey() != null) {
				biggestValue = entry.getValue();
				mostVotedPoints.clear();
				mostVotedPoints.add(entry.getKey());
			} else if (entry.getValue() == biggestValue) {
				mostVotedPoints.add(entry.getKey());
			}
		}
		
		if (mostVotedPoints.size() > 1) {
			Random random = new Random();
			return mostVotedPoints.get(random.nextInt(mostVotedPoints.size()));
		}
		
		return mostVotedPoints.get(0);
	}

	public String getPointId(Point p){
		return p.getPositionX() + "," + p.getPositionY();
	}
}



package main.strategies;

import java.util.List;
import java.util.Random;

import main.game.map.GameMap;
import main.game.map.Point;

public class Sort implements Strategy {

	private Random random;

	public Sort() {
		this.random = new Random();
	}

	@Override
	public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, GameMap map) {
		if (possibleNextSteps.isEmpty()) {
			return null; // No valid moves available
		}
		// Select a random valid next step
		int index = random.nextInt(possibleNextSteps.size());
		return possibleNextSteps.get(index);
	}
}


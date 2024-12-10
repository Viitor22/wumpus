package main.game;

import main.game.map.Point;
import main.game.map.TreasureChest;
import main.game.map.GameMap;
import main.strategies.BinaryTree;
import main.strategies.FewerObstacles;
import main.strategies.ShortestDistance;
import main.strategies.Sort;
import main.strategies.Voting;

public class Game {
	private GameMap map;
	private Player player;

	public Game() {
		this.map = new GameMap(8, 8);
		this.player = new Player(new Sort());
	}

	public int[] run() {
		this.map.print();
		System.out.println();
		int numberOfSteps = 0;
		int fails = 0;
		int sucess = 0;
		while (true) {
			Point nextPoint = this.player.evaluatePossibleNextStep(map);
			if (nextPoint == null) {
				break;
			} else {
				++numberOfSteps;
				String space = this.map.get(nextPoint);
				if (space != null && space.equals(TreasureChest.CHARACTER)) {
					String chestType = this.map.openTreasureChest(nextPoint);
					this.map.print();
					System.out.println("Foram dados " + numberOfSteps + " passos.");
					if (!chestType.equals(TreasureChest.CHEST_EMPTY_CHARACTER)) {
						if (chestType.equals(TreasureChest.CHEST_TRESURE_CHARACTER)) {
							++sucess;
						}
						break;
					} else {
						++fails;
					}
				} else {
					this.map.moveRobot(nextPoint);
				}
			}
			this.map.print();
			System.out.println();
		}
		int[] metrics = {sucess, fails, numberOfSteps};
		return metrics;
	}
}

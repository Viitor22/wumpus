package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;

public class FewerObstacles implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
		int minObstacleCount = Integer.MAX_VALUE;
		Point bestPoint = null;

		for (Point nextPoint : possibleNextStep) {
			int obstacleCount = evaluatePoint(nextPoint, map);
			if (obstacleCount < minObstacleCount) {
				minObstacleCount = obstacleCount;
				bestPoint = nextPoint;
			}
		}
		return bestPoint;
	}

	private int evaluatePoint(Point p, Map map) {
		int count = 0;
		int x = p.getPositionX();
		int y = p.getPositionY();

		int[] scenarioSize = map.getScenarioSize();

		// Lista de posições adjacentes
		Point[] adjacentPoints = {
				new Point(x - 1, y - 1), new Point(x - 1, y), new Point(x - 1, y + 1),
				new Point(x, y - 1), /* Ponto central */ new Point(x, y + 1),
				new Point(x + 1, y - 1), new Point(x + 1, y), new Point(x + 1, y + 1)
		};

		for (Point adjacent : adjacentPoints) {
			// Verifica se o ponto adjacente está dentro dos limites do mapa
			if (adjacent.getPositionX() < 0 || adjacent.getPositionX() >= scenarioSize[0] ||
					adjacent.getPositionY() < 0 || adjacent.getPositionY() >= scenarioSize[1]) {
				continue;
			}

			// Verifica o conteúdo do ponto no mapa
			String content = map.get(adjacent);

			if (content == null || content.equals(Rock.CHARACTER)) {
				count++;
			} else if (content.equals(TreasureChest.CHARACTER)) {
				map.openTreasureChest(adjacent);
				count--;  // Favorece o caminho com tesouro
			}
		}
		return count;
	}
}

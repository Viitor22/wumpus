package main.strategies;

import java.util.List;
import java.util.Random;
import main.game.map.Map;
import main.game.map.Point;
import main.game.map.TreasureChest;

public class Sort implements Strategy {
	/**
	 * N is the next location
	 * p1 p2 p3
	 * p4 N p5
	 * p6 p7 p8
	 */
	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		// Inicializa o gerador de números aleatórios
		Random random = new Random();

		// Seleciona um ponto aleatório das opções disponíveis
		if (!possibleNextSteps.isEmpty()) {
			// Escolhe um índice aleatório da lista de possíveis próximos passos
			int index = random.nextInt(possibleNextSteps.size());

			// Seleciona o ponto correspondente
			Point nextStep = possibleNextSteps.get(index);

			// Verifica se o ponto selecionado é um baú de tesouro
			if (map.get(nextStep).equals(TreasureChest.CHARACTER)) {
				// Retorna o ponto se for um baú de tesouro
				return nextStep;
			}

			// Retorna o ponto selecionado, mesmo que não seja um baú
			return nextStep;
		}

		// Retorna null se não houver próximos passos possíveis
		return null;
	}
}

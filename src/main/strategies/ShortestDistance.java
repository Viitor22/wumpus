package main.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;

// Utilizando o algoritmo A* para determinar o caminho mais curto até o tesouro

public class ShortestDistance implements Strategy {

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
        Iterator<Point> it = possibleNextStep.iterator();
        Point pointSelected = null;

        while (it.hasNext()) {
            Point p = it.next();
            Point t = null;

            // Checa se o ponto contém o tesouro
            if (findTreasure(p, map)) {
                t = p;
            }

            // Só avalia o ponto se o tesouro foi encontrado
            if (t != null) {
                pointSelected = evaluatePoint(p, map, t);
            }
        }
        return pointSelected;
    }

    private Point evaluatePoint(Point p, Map map, Point t) {
        int index = -1; // Define como -1 inicialmente para lidar com a falta de atualização
        double min = Integer.MAX_VALUE;
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

        for (int i = 0; i < points.size(); i++) {
            Point currentPoint = points.get(i);

            // Verifica se o ponto está dentro dos limites do cenário
            if (currentPoint.getPositionX() >= 0 && currentPoint.getPositionX() < scenarioSize[0] &&
                currentPoint.getPositionY() >= 0 && currentPoint.getPositionY() < scenarioSize[1]) {

                // Avalia a distância usando A* e atualiza o índice se necessário
                double distance = AStar(p, t);
                if (distance < min) {
                    min = distance;
                    index = i;
                }

                // Verifica se o ponto é um obstáculo
                if (map.get(currentPoint).equals(Rock.CHARACTER) || !map.get(currentPoint).equals("M")) {
                    continue;
                } else if (map.get(currentPoint).equals(TreasureChest.CHARACTER)) {
                    map.openTreasureChest(currentPoint);
                }
            }
        }

        // Verifica se o índice foi atualizado para retornar um ponto válido
        if (index == -1) {
            throw new IllegalStateException("Nenhum ponto válido encontrado.");
        }

        return points.get(index);
    }

    private boolean findTreasure(Point p, Map map) {
        // Retorna verdadeiro se o ponto é um tesouro
        return map.get(p).equals(TreasureChest.CHARACTER);
    }

    private double AStar(Point p, Point t) {
        int custo = 0;
        double heuristic = Math.sqrt(Math.pow((p.getPositionX() - t.getPositionX()), 2.0) + Math.pow((p.getPositionY() - t.getPositionY()), 2.0));
        return custo + heuristic;
    }
}

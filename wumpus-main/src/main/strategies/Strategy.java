package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Point;

public interface Strategy {
    Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map);
}

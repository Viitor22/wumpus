package main.strategies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.data.structure.NodeTree;
import main.game.map.MapOfTreasure;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;
import main.game.map.GameMap;

public class BinaryTree implements Strategy{
    private GameMap gameMap;
    private NodeTree<String> root;
    private LinkedList<NodeTree<String>> sequenceSelected;
    private boolean pointT;

    public BinaryTree(GameMap map) {
        map.print();
        this.gameMap = map;
        this.pointT = false;
        System.out.println("");
        this.root = new NodeTree<>();
        this.sequenceSelected = new LinkedList<>();

        buildTreeAndCalculatePath(map, 0, 0);
    }

    public void insert(String value) {
        if (this.root.getValue() == null) {
            this.root.setValue(value);
        } else {
            insertInTree(value, this.root);
        }
    }

    public void insertInTree(String value, NodeTree<String> node) {
        if (node.getValue().equals(value)) {
            if (node.getLeft() == null) {
                NodeTree<String> newNode = new NodeTree<String>();
                newNode.setValue(value);
                node.setLeft(newNode);
            } else {
                insertInTree(value, node.getLeft());
            }
        } else {
            if (node.getRight() == null) {
                NodeTree<String> newNode = new NodeTree<String>();
                newNode.setValue(value);
                node.setRight(newNode);
            } else {
                insertInTree(value, node.getRight());
            }
        }
    }

    public boolean findBFS(String value) {
        if (this.root == null) {
            System.out.println("Árvore Vazia");
            return false;
        }
        Queue<NodeTree<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            NodeTree<String> nextNode = queue.poll();
            if (nextNode.getValue().equals(value)) {
                return true;
            }
            if (nextNode.getLeft() != null) {
                queue.add(nextNode.getLeft());
            }
            if (nextNode.getRight() != null) {
                queue.add(nextNode.getRight());
            }
        }
        return false;

    }

    public void buildTreeAndCalculatePath(GameMap map, int i, int j) {
        this.root = buildTreeAndCalculatePath(map.getScenario(), 0, 0);
        this.DFS(this.root);
    }

    public void DFS(NodeTree<String> node) {
        LinkedList<NodeTree<String>> path = new LinkedList<>();

        Object treasureLocation = gameMap.getTreasureLocation();
        Point point = (Point) treasureLocation;

        preOrder(node, point, path);
        this.sequenceSelected = path;

        if (!this.sequenceSelected.isEmpty()) {
            this.sequenceSelected.removeFirst();
        } else {
            System.out.println("Não foi possível remover o primeiro elemento. Pois esta vazio");
        }

    }

    public boolean preOrder(NodeTree<String> node, Point value, LinkedList<NodeTree<String>> path) {
        if (node == null) {
            return false;
        }

        path.add(node);
        if (node.getI() == value.getPositionX() && node.getJ() == value.getPositionY()) {
            return true;
        }

        if (preOrder(node.getLeft(), value, path) || preOrder(node.getRight(), value, path)) {
            return true;
        }

        path.removeLast();
        System.out.println("");
        return false;
    }

    public NodeTree<String> buildTreeAndCalculatePath(String[][] map, int i, int j) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length) {
            return null;
        }

        Object treasureLocation = gameMap.getTreasureLocation();
        Point point = (Point) treasureLocation;

        if (map[i][j].equals(TreasureChest.CHARACTER) && (point.getPositionX() != i || point.getPositionY() != j)) {
            return null;
        }
        if (!map[i][j].equals(Rock.CHARACTER) && !map[i][j].equals(Monster.CHARACTER)) {
            NodeTree<String> newNode = new NodeTree<>(map[i][j], i, j);

            if (map[i][j].equals(MapOfTreasure.CHARACTER)) {
                this.pointT = true;
                newNode.setTreasure(this.gameMap.getTreasureLocation());
            }

            NodeTree<String> leftNode = buildTreeAndCalculatePath(map, i + 1, j);
            NodeTree<String> rightNode = buildTreeAndCalculatePath(map, i, j + 1);

            if (this.pointT && leftNode != null && leftNode.getTreasure() != null) {
                newNode.setLeft(leftNode);
            } else if (this.pointT && rightNode != null && rightNode.getTreasure() != null) {
                newNode.setRight(rightNode);
            } else {
                newNode.setLeft(leftNode);
                newNode.setRight(rightNode);
            }
            return newNode;
        }

        return null;
    }

    public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap gameMap) {
        if (!sequenceSelected.isEmpty()) {
            NodeTree<String> nextPoint = sequenceSelected.remove(0);
            if (nextPoint != null) {
                if (!nextPoint.isNILL()) {
                    return new Point(nextPoint.getI(), nextPoint.getJ());
                }
            }
        }
        return null;
    }
}


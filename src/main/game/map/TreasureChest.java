package main.game.map;

public class TreasureChest extends Obstacle{
	public static final String CHEST_TRESURE_CHARACTER = "F";
	public static final String CHEST_TRAP_CHARACTER = "A";
	public static final String CHEST_EMPTY_CHARACTER = "E";
	public static final String CHARACTER = "T";
	public TreasureChest(Point coordinates) {
		super(coordinates);
	}

}

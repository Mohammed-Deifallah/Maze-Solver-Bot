package environment;

public class Utils {

	public static Action selectAction(int i) {
		switch(i) {
		case 0:
			return Action.NORTH;
		case 1:
			return Action.EAST;
		case 2:
			return Action.SOUTH;
		case 3:
			return Action.WEST;
		}
		return null; 
	}
}

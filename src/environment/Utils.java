package environment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static boolean pathExists(Cell grid[][]) {
		List<Point> stack = new ArrayList<Point>();
		stack.add(grid[0][0].getPosition());
		while(!stack.isEmpty()) {
			Point point = stack.remove(stack.size() - 1);
			if (grid[point.x][point.y].getType() == Type.END) {
				return true;
			}
			// searching for valid neighbors cells
			List<Action> actions = grid[point.x][point.y].getPossibleActions();
			for (int i = 0; i < actions.size(); i++) {
				Action action = actions.get(i);
				stack.add(action.getTransition(grid[point.x][point.y]));
			}
			
		}
		return false;
		
	}
}

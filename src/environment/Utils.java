package environment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

	public static boolean pathExists(Cell grid[][]) {
		List<Point> stack = new ArrayList<Point>();
		stack.add(grid[0][0].getPosition());
		Set<Point> visited = new HashSet<Point>();
		while(!stack.isEmpty()) {
			Point point = stack.remove(stack.size() - 1);
			visited.add(grid[point.x][point.y].getPosition());
			if (grid[point.x][point.y].getType() == Type.END) {
				return true;
			}
			// searching for valid neighbors cells
			List<Action> actions = grid[point.x][point.y].getPossibleActions();
			for (int i = 0; i < actions.size(); i++) {
				Action action = actions.get(i);
				if (!visited.contains(action.getTransition(grid[point.x][point.y]))) {
					stack.add(action.getTransition(grid[point.x][point.y]));
				}
			}
			
		}
		return false;
		
	}
}

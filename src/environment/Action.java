package environment;

import java.awt.Point;

public enum Action {
	EAST, NORTH, WEST, SOUTH;

	public Point getTransition(Cell cell) {
		Point nextCell = new Point(cell.getPosition());
		switch (this) {

		case EAST:
			nextCell.y++;
			break;

		case NORTH:
			nextCell.x--;
			break;

		case WEST:
			nextCell.y--;
			break;

		case SOUTH:
			nextCell.x++;
			break;
		}
		return nextCell;
	}
}

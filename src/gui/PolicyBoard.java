package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import environment.Action;

public class PolicyBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[][] board;
	private int n;

	public PolicyBoard(int n) {
		setLayout(new GridLayout(n, n));
		board = new JTextField[n][n];
		this.n = n;
	}

	public void updateLabels(Action[][] actions) {
		for (int i = 0, x = 20; i < n; i++, x += 50) {
			for (int j = 0, y = 20; j < n; j++, y += 50) {
				board[i][j] = new JTextField();
				board[i][j].setBounds(y, x, 50, 50);
				String act = "-";
				switch (actions[i][j]) {
				case NORTH:
					act = "\u2191";
					break;
				case SOUTH:
					act = "\u2193";
					break;
				case WEST:
					act = "\u2190";
					break;
				case EAST:
					act = "\u2192";
					break;
				case NONE:
					act = "-";
					break;
				}
				board[i][j].setText(act);
				board[i][j].setHorizontalAlignment(JTextField.CENTER);
				board[i][j].setFont(new Font("Hobo Std", Font.BOLD, 14));
				board[i][j].setEditable(false);
				this.add(board[i][j]);
			}
		}
	}
}

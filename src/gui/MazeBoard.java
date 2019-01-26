package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import environment.Grid;

public class MazeBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[][] board;
	private int n;

	public MazeBoard(int n) {
		setLayout(new GridLayout(n, n));
		board = new JTextField[n][n];
		this.n = n;
	}

	public void updateLabels(Grid grid) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		for (int i = 0, x = 20; i < n; i++, x += 50) {
			for (int j = 0, y = 20; j < n; j++, y += 50) {
				board[i][j] = new JTextField();
				board[i][j].setBounds(y, x, 50, 50);
				if (grid.getGrid()[i][j].getType() == environment.Type.BARRIER) {
					board[i][j].setText("B");
				} else if (grid.getGrid()[i][j].getType() == environment.Type.START) {
					board[i][j].setText("S");
				} else if (grid.getGrid()[i][j].getType() == environment.Type.END) {
					board[i][j].setText("T");
				} else {
					board[i][j].setText("O");
				}
				board[i][j].setHorizontalAlignment(JTextField.CENTER);
				board[i][j].setFont(new Font("Hobo Std", Font.BOLD, 14));
				board[i][j].setEditable(false);
				this.add(board[i][j]);
			}
		}
	}
}

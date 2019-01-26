package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValueBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[][] board;
	private int n;

	public ValueBoard(int n) {
		setLayout(new GridLayout(n, n));
		board = new JTextField[n][n];
		this.n = n;
	}

	public void updateLabels(Double[][] values) {
		for (int i = 0, x = 20; i < n; i++, x += 50) {
			for (int j = 0, y = 20; j < n; j++, y += 50) {
				board[i][j] = new JTextField();
				board[i][j].setBounds(y, x, 50, 50);
				board[i][j].setHorizontalAlignment(JTextField.CENTER);
				board[i][j].setFont(new Font("Hobo Std", Font.ITALIC, 14));
				board[i][j].setEditable(false);
				if (values[i][j] != Integer.MIN_VALUE) {
					board[i][j].setText(String.valueOf(values[i][j]));
				} else {
					board[i][j].setText("-");
				}
				this.add(board[i][j]);
			}
		}
	}
}

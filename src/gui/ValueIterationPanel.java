package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import environment.Action;

public class ValueIterationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Double[][]> vals;
	private int index;
	private JButton before, after;
	private JLabel iterationNumber, timeTakenLabel;
	public ValueIterationPanel (List<Double[][]> values, Action[][] actions, long timeTaken) {
		this.setLayout(new FlowLayout());
		PolicyBoard board = new PolicyBoard(actions.length);
		board.updateLabels(actions);
		this.add(board);
		ValueBoard vboard = new ValueBoard(actions.length);
		before = new JButton("<-");
		vals = values;
		index = vals.size() - 1;
		vboard.updateLabels(vals.get(index));
		after = new JButton("->");
		iterationNumber = new JLabel("Iteration " + (index + 1) + " of " + vals.size());
		timeTakenLabel = new JLabel("Time taken : " + String.valueOf(timeTaken) + " ms");
		this.add(vboard);
		JPanel buttonsPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(before);
		buttonsPanel.add(after);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(new JLabel("Value Iteration"));
		infoPanel.add(iterationNumber);
		infoPanel.add(timeTakenLabel);
		this.add(buttonsPanel);
		this.add(infoPanel);
		after.setEnabled(false);
		before.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					vboard.updateLabels(vals.get(index));
					iterationNumber.setText("Iteration " + (index + 1) + " of " + vals.size());
					after.setEnabled(true);
					if (index == 0) {
						before.setEnabled(false);
					}
				}
			}
		});
		after.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (index < vals.size() - 1) {
					index++;
					vboard.updateLabels(vals.get(index));
					iterationNumber.setText("Iteration " + (index + 1) + " of " + vals.size());
					before.setEnabled(true);
					if (index == vals.size() - 1) {
						after.setEnabled(false);
					}
				}
			}
		});
	}

}

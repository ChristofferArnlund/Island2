package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import game.TurnHandler;

public class TurnPanel extends JPanel {
	private JLabel turnsLabel;
	private TurnHandler turnHandler;
	private Overview overview;

	public TurnPanel(TurnHandler turnHandler, Overview overview) {
	this.turnHandler = turnHandler;
	this.overview = overview;
	setLayout(new BorderLayout());
	JPanel SouthTurnPanel = new JPanel();
	add(SouthTurnPanel, BorderLayout.SOUTH);
	SouthTurnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		turnsLabel = new JLabel("Turns: 0");
		SouthTurnPanel.add(turnsLabel);

		JButton nextTurnButton = new JButton("Next Turn");
		SouthTurnPanel.add(nextTurnButton);
		nextTurnButton.setHorizontalAlignment(SwingConstants.TRAILING);
		
//		springLayout.putConstraint(SpringLayout.SOUTH, nextTurnButton, 0, SpringLayout.SOUTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, nextTurnButton, -436, SpringLayout.EAST, frame.getContentPane());
		nextTurnButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				turnHandler.newTurn();
				overview.update();

			}

		}

		);

	}
	public void update(){
		turnsLabel.setText("Turns: " + turnHandler.getCounter());
	}

}

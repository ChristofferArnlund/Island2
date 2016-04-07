package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import Technologies.TechnologyHandler;
import buildings.House;
import buildings.StoneMine;
import buildings.WoodCutterLodge;
import game.BuildingManager;

public class techPanel extends JPanel {

	private JTable techTable;
	private int index;
	private Overview overview;
	private TechnologyHandler th;

	public techPanel(TechnologyHandler th) {
		this.th = th;
		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		techTable = new JTable(new techTable(th).generateModel());
		scrollPane.setViewportView(techTable);
		techTable.getTableHeader().setVisible(false);
		// Sets so the table is not gigantic but fills the viewPort off the
		// scrollpanel instead.
		techTable.setPreferredScrollableViewportSize(techTable.getPreferredSize());
		techTable.setFillsViewportHeight(true);

		JLabel techLabel = new JLabel("Technologies");
		add(techLabel, BorderLayout.NORTH);
		techLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		techLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton buildButton = new JButton("Research");

		add(buildButton, BorderLayout.SOUTH);
		buildButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				index = techTable.getSelectedRow();

				update();
//				overview.update();

			}
		});

	}

	public void update() {
		techTable.setModel(new techTable(th).generateModel());
	}
}

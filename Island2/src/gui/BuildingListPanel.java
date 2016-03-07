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
import javax.swing.SwingConstants;

import buildings.House;
import buildings.StoneMine;
import buildings.WoodCutterLodge;
import game.BuildingManager;

public class BuildingListPanel extends JPanel {

	private JTable buildingTable;
	private int index;
	private BuildingManager buildingManager;
	private Overview overview;

	public BuildingListPanel(BuildingManager buildingManager ,Overview overview) {
		this.buildingManager = buildingManager;
		this.overview = overview;
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		// builds the buildingtable
		buildingTable = new JTable(new BuildingTableGen().generateTable());
		scrollPane.setViewportView(buildingTable);
		// Sets so the table is not gigantic but fills the viewPort off the
		// scrollpanel instead.
		buildingTable.setPreferredScrollableViewportSize(buildingTable.getPreferredSize());
		buildingTable.setFillsViewportHeight(true);
		
		
		// BUILDING LIST

		JLabel buildingListLabel = new JLabel("Buildings");
		add(buildingListLabel, BorderLayout.NORTH);
		buildingListLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingListLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton buildButton = new JButton("Build");

		add(buildButton, BorderLayout.SOUTH);
		buildButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			index = buildingTable.getSelectedRow();
				if (index == 0 || index ==-1) {
					buildingManager.addToQueue(new House());
				}if(index==1){
					buildingManager.addToQueue(new WoodCutterLodge());

				}
				if(index==2){
					buildingManager.addToQueue(new StoneMine());

				}
				
				update();
				overview.update();
				
			}
		});
		
	}

	public void update() {
		buildingTable.setModel(new BuildingTableGen().generateTable());
	}
}

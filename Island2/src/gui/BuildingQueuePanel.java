package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import game.BuildingManager;

public class BuildingQueuePanel extends JPanel {

	private JTable buildingQueueTable;
	private BuildingManager buildingManager;
	public BuildingQueuePanel (BuildingManager buildingManager){
		this.buildingManager = buildingManager;
		setLayout(new BorderLayout());
		// LABEL NORTH
				JLabel buildingQueueLabel = new JLabel("Building Queue");
				add(buildingQueueLabel, BorderLayout.NORTH);
				buildingQueueLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
				buildingQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);

				// TABLE CENTER
				buildingQueueTable = new JTable(new BuildingQueueTable(buildingManager).generateTable());
				buildingQueueTable.setFillsViewportHeight(true);

				JScrollPane buildingQueueScroll = new JScrollPane();
				buildingQueueScroll.setViewportView(buildingQueueTable);
				add(buildingQueueScroll, BorderLayout.CENTER);
		

		
		
		
	}
	public void update(){
		
		buildingQueueTable.setModel(new BuildingQueueTable(buildingManager).generateTable());
	}
}

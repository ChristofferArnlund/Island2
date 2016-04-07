package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import game.ResourceManager;
import game.UpdateResources;

public class ResourcePanel extends JPanel{
	public JTable resourcesTable;
	private ResourceManager resourceManager;
	private UpdateResources updateResources;

	public ResourcePanel(ResourceManager resourceManager,UpdateResources updateResources) {
		this.resourceManager = resourceManager;
		this.updateResources = updateResources;
		setLayout(new BorderLayout());
		
		
		JPanel topResourcePanel = new JPanel();

		JLabel resourcesLabel = new JLabel("Resources");
		topResourcePanel.add(resourcesLabel, BorderLayout.CENTER);
		resourcesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));

		JScrollPane bottomResourcePanel = new JScrollPane();
		add(bottomResourcePanel, BorderLayout.CENTER);
		add(topResourcePanel, BorderLayout.NORTH);
		
		
		
		// RESOURCE TABLE

		resourcesTable = new JTable(new ResourceTableGen(resourceManager,updateResources).generateModel());
		bottomResourcePanel.setViewportView(resourcesTable);
		resourcesTable.setPreferredScrollableViewportSize(resourcesTable.getPreferredSize());
		resourcesTable.setFillsViewportHeight(true);

		resourcesTable.setEnabled(false);
		resourcesTable.setRowSelectionAllowed(false);
		resourcesTable.setShowHorizontalLines(false);
		resourcesTable.setShowGrid(false);
		
	}
	public void update(){
		
		resourcesTable.setModel(new ResourceTableGen(resourceManager,updateResources).generateModel());

	}
}
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.BuildingManager;
import game.ResourceManager;

import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;

public class Overview {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResourceManager resourceManager = new ResourceManager();
					BuildingManager buildingManager = new BuildingManager(resourceManager);
					Overview window = new Overview(resourceManager, buildingManager);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private JTable buildingQueueTable;
	private JTable table_1;

	/**
	 * Create the application.
	 * 
	 * @param resourceManager
	 */
	public Overview(ResourceManager resourceManager,BuildingManager buildingManager) {

		initialize(resourceManager,buildingManager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ResourceManager resourceManager, BuildingManager buildingManager) {
		frame = new JFrame();
		frame.setBounds(100, 100, 973, 722);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JScrollPane resourcesPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, resourcesPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, resourcesPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, resourcesPanel, 310, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, resourcesPanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(resourcesPanel);

		JScrollPane buildingListPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, buildingListPanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingListPanel, 6, SpringLayout.EAST, resourcesPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingListPanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingListPanel, 206, SpringLayout.EAST, resourcesPanel);
		frame.getContentPane().add(buildingListPanel);

		JScrollPane buildingQueuePanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, buildingQueuePanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingQueuePanel, 6, SpringLayout.EAST, buildingListPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingQueuePanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingQueuePanel, 206, SpringLayout.EAST, buildingListPanel);
		frame.getContentPane().add(buildingQueuePanel);

		JScrollPane existingBuildingPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, existingBuildingPanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, existingBuildingPanel, 6, SpringLayout.EAST, buildingQueuePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingBuildingPanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, existingBuildingPanel, 206, SpringLayout.EAST,
				buildingQueuePanel);
		frame.getContentPane().add(existingBuildingPanel);

		JScrollPane peoplePanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, peoplePanel, 6, SpringLayout.SOUTH, resourcesPanel);
		springLayout.putConstraint(SpringLayout.WEST, peoplePanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, peoplePanel, 306, SpringLayout.SOUTH, resourcesPanel);

		JPanel topResourcePanel = new JPanel();
		resourcesPanel.setColumnHeaderView(topResourcePanel);

		JLabel resourcesLabel = new JLabel("Resources");
		topResourcePanel.add(resourcesLabel);
		resourcesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));

		JPanel bottomResourcePanel = new JPanel();
		resourcesPanel.setViewportView(bottomResourcePanel);
		bottomResourcePanel.setLayout(new BorderLayout(0, 0));

		// Loads from the resourcesclass in to the JTable

		ResourceTableGen resourcesTableGen = new ResourceTableGen(resourceManager);
		JTable resourcesTable = resourcesTableGen.generateTable();
		bottomResourcePanel.add(resourcesTable);
		resourcesTable.setEnabled(false);
		resourcesTable.setRowSelectionAllowed(false);
		resourcesTable.setShowHorizontalLines(false);
		resourcesTable.setShowGrid(false);

		springLayout.putConstraint(SpringLayout.EAST, peoplePanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(peoplePanel);

		JScrollPane techPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, techPanel, 6, SpringLayout.SOUTH, buildingListPanel);
		springLayout.putConstraint(SpringLayout.WEST, techPanel, 6, SpringLayout.EAST, peoplePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, techPanel, 306, SpringLayout.SOUTH, buildingListPanel);

		JPanel panel = new JPanel();
		buildingListPanel.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel buildingListLabel = new JLabel("Buildings");
		panel.add(buildingListLabel, BorderLayout.NORTH);
		buildingListLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingListLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton buildButton = new JButton("Build");
		panel.add(buildButton, BorderLayout.SOUTH);

		JPanel middleBuildingPanel = new JPanel();
		panel.add(middleBuildingPanel, BorderLayout.WEST);
		middleBuildingPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		middleBuildingPanel.add(scrollPane, BorderLayout.CENTER);

		// builds the buildingtable
		BuildingTableGen buldingTableGen = new BuildingTableGen();
		JTable buildingTable = buldingTableGen.generateTable();
		scrollPane.setViewportView(buildingTable);
		// Sets so the table is not gigantic but fills the viewPort off the
		// scrollpanel instead.
		buildingTable.setPreferredScrollableViewportSize(buildingTable.getPreferredSize());
		buildingTable.setFillsViewportHeight(true);

		springLayout.putConstraint(SpringLayout.EAST, techPanel, 206, SpringLayout.EAST, peoplePanel);
		frame.getContentPane().add(techPanel);

		JScrollPane existingTechPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, existingTechPanel, 6, SpringLayout.SOUTH, buildingQueuePanel);
		springLayout.putConstraint(SpringLayout.WEST, existingTechPanel, 6, SpringLayout.EAST, techPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingTechPanel, 306, SpringLayout.SOUTH, buildingQueuePanel);

		JPanel panel_1 = new JPanel();
		buildingQueuePanel.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel buildingQueueLabel = new JLabel("building Queue");
		panel_1.add(buildingQueueLabel, BorderLayout.NORTH);
		buildingQueueLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);

		buildingQueueTable = new BuildingQueueTable(buildingManager).generateTable();
		panel_1.add(buildingQueueTable, BorderLayout.CENTER);
		springLayout.putConstraint(SpringLayout.EAST, existingTechPanel, 206, SpringLayout.EAST, techPanel);

		JLabel techLabel = new JLabel("Technologies");
		techLabel.setHorizontalAlignment(SwingConstants.CENTER);
		techLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		techPanel.setColumnHeaderView(techLabel);
		frame.getContentPane().add(existingTechPanel);

		JLabel existingTechLabel = new JLabel("Existing Technologies");
		existingTechLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		existingTechLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingTechPanel.setColumnHeaderView(existingTechLabel);

		JButton nextTurnButton = new JButton("Next Turn");
		springLayout.putConstraint(SpringLayout.SOUTH, nextTurnButton, 0, SpringLayout.SOUTH, peoplePanel);

		JLabel PeopleLabel = new JLabel("People");
		PeopleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		PeopleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		peoplePanel.setColumnHeaderView(PeopleLabel);
		springLayout.putConstraint(SpringLayout.EAST, nextTurnButton, 0, SpringLayout.EAST, existingBuildingPanel);

		JPanel panel_2 = new JPanel();
		existingBuildingPanel.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel existingBuildingsLabel = new JLabel("Existing Buildings");
		existingBuildingsLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		existingBuildingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(existingBuildingsLabel, BorderLayout.NORTH);
		frame.getContentPane().add(nextTurnButton);

	}

}

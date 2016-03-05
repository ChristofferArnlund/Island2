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

import buildings.Building;
import buildings.House;
import game.BuildingManager;
import game.ResourceManager;
import game.TurnHandler;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

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
					TurnHandler turnHandler = new TurnHandler(buildingManager,resourceManager);
					Overview window = new Overview(resourceManager, buildingManager,turnHandler);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private JTable buildingQueueTable;
	private JTable buildingTable;
	private BuildingTableGen buldingTableGen;
	private ResourceTableGen resourcesTableGen;
	private JTable resourcesTable;
	private JPanel buildingQueuePanel;
	private JPanel buildingQueueBorderPanel;
	private JTable existingBuildingTable;

	/**
	 * Create the application.
	 * 
	 * @param resourceManager
	 */
	public Overview(ResourceManager resourceManager, BuildingManager buildingManager, TurnHandler turnHandler) {

		initialize(resourceManager, buildingManager,turnHandler);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param turnHandler 
	 */
	private void initialize(ResourceManager resourceManager, BuildingManager buildingManager, TurnHandler turnHandler) {
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

		
		 // BUILDING QUEUE
		
		buildingQueuePanel = new JPanel();
		buildingQueuePanel.setLayout(new BorderLayout());
		springLayout.putConstraint(SpringLayout.NORTH, buildingQueuePanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingQueuePanel, 6, SpringLayout.EAST, buildingListPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingQueuePanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingQueuePanel, 206, SpringLayout.EAST, buildingListPanel);
		frame.getContentPane().add(buildingQueuePanel);

		JPanel existingBuildingPanel = new JPanel();
		existingBuildingPanel.setLayout(new BorderLayout());
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

		resourcesTable = new JTable(new ResourceTableGen(resourceManager).generateTable());
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

		
		
		// BUILDING LIST
		JPanel panel = new JPanel();
		buildingListPanel.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel buildingListLabel = new JLabel("Buildings");
		panel.add(buildingListLabel, BorderLayout.NORTH);
		buildingListLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingListLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton buildButton = new JButton("Build");

		panel.add(buildButton, BorderLayout.SOUTH);

		buildButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int index = buildingTable.getSelectedRow();
				if (index == 0) {
					
					buildingManager.addToQueue(new House());
					update(buildingManager, resourceManager);
				}
			}
		});

		JPanel middleBuildingPanel = new JPanel();
		panel.add(middleBuildingPanel, BorderLayout.WEST);
		middleBuildingPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		middleBuildingPanel.add(scrollPane, BorderLayout.CENTER);

		// builds the buildingtable
		buildingTable =new JTable(new BuildingTableGen().generateTable());
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

		// BUILDING QUEUE
		
		//LABEL NORTH
		JLabel buildingQueueLabel = new JLabel("building Queue");
		buildingQueuePanel.add(buildingQueueLabel, BorderLayout.NORTH);
		buildingQueueLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		// TABLE CENTER 
		buildingQueueTable = new JTable(new BuildingQueueTable(buildingManager).generateTable());
		
		buildingQueuePanel.add(buildingQueueTable, BorderLayout.CENTER);
		
		
		

		JLabel techLabel = new JLabel("Technologies");
		springLayout.putConstraint(SpringLayout.EAST, existingTechPanel, 206, SpringLayout.EAST, techPanel);
		techLabel.setHorizontalAlignment(SwingConstants.CENTER);
		techLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		techPanel.setColumnHeaderView(techLabel);
		frame.getContentPane().add(existingTechPanel);

		JLabel existingTechLabel = new JLabel("Existing Technologies");
		existingTechLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		existingTechLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingTechPanel.setColumnHeaderView(existingTechLabel);

		JButton nextTurnButton = new JButton("Next Turn");
		nextTurnButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				turnHandler.newTurn();
				update(buildingManager,resourceManager);
			
			}

		}

		);

		springLayout.putConstraint(SpringLayout.SOUTH, nextTurnButton, 0, SpringLayout.SOUTH, peoplePanel);

		JLabel PeopleLabel = new JLabel("People");
		PeopleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		PeopleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		peoplePanel.setColumnHeaderView(PeopleLabel);
		springLayout.putConstraint(SpringLayout.EAST, nextTurnButton, 0, SpringLayout.EAST, existingBuildingPanel);

	
		 // EXISTING BUILDINGS
		// LABEL
		JLabel existingBuildingsLabel = new JLabel("Existing Buildings");
		existingBuildingsLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		existingBuildingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingBuildingPanel.add(existingBuildingsLabel, BorderLayout.NORTH);
		frame.getContentPane().add(nextTurnButton);
		
		//LIST
		existingBuildingTable = new JTable(new ExistingBuildingModel(buildingManager).generateModel());
		
		existingBuildingPanel.add(existingBuildingTable, BorderLayout.CENTER);
		
		
		

	}
	public void update(BuildingManager buildingManager,ResourceManager resourceManager){
		buildingQueueTable.setModel(new BuildingQueueTable(buildingManager).generateTable());
		buildingTable.setModel(new BuildingTableGen().generateTable());
		resourcesTable.setModel(new ResourceTableGen(resourceManager).generateTable());
		existingBuildingTable.setModel(new ExistingBuildingModel(buildingManager).generateModel());

		
	}

}

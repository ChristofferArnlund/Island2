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

import buildings.House;
import game.BuildingManager;
import game.ResourceManager;
import game.TurnHandler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTable resourcesTable;
	private JPanel buildingQueuePanel;
	private JTable existingBuildingTable;
	private JLabel turnsLabel;
	private JTable peopleTable;

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

		JPanel resourcesPanel = new JPanel();
		resourcesPanel.setLayout(new BorderLayout());
		springLayout.putConstraint(SpringLayout.NORTH, resourcesPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, resourcesPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, resourcesPanel, 310, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, resourcesPanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(resourcesPanel);

		JPanel buildingListPanel = new JPanel();
		buildingListPanel.setLayout(new BorderLayout());
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

		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new BorderLayout());
		springLayout.putConstraint(SpringLayout.NORTH, peoplePanel, 6, SpringLayout.SOUTH, resourcesPanel);
		springLayout.putConstraint(SpringLayout.WEST, peoplePanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, peoplePanel, 306, SpringLayout.SOUTH, resourcesPanel);

		JPanel topResourcePanel = new JPanel();

		JLabel resourcesLabel = new JLabel("Resources");
		topResourcePanel.add(resourcesLabel, BorderLayout.CENTER);
		resourcesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));

		JScrollPane bottomResourcePanel = new JScrollPane();
		resourcesPanel.add(bottomResourcePanel,BorderLayout.CENTER);
		resourcesPanel.add(topResourcePanel,BorderLayout.NORTH);

		// RESOURCE TABLE

		resourcesTable = new JTable(new ResourceTableGen(resourceManager).generateTable());
		bottomResourcePanel.setViewportView(resourcesTable);
		resourcesTable.setPreferredScrollableViewportSize(resourcesTable.getPreferredSize());
		resourcesTable.setFillsViewportHeight(true);
		
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


		JLabel buildingListLabel = new JLabel("Buildings");
		buildingListPanel.add(buildingListLabel, BorderLayout.NORTH);
		buildingListLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingListLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton buildButton = new JButton("Build");

		buildingListPanel.add(buildButton, BorderLayout.SOUTH);

		buildButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int index = buildingTable.getSelectedRow();
				if (index == 0) {
					
					buildingManager.addToQueue(new House());
					update(buildingManager, resourceManager,turnHandler);
				}
			}
		});


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buildingListPanel.add(scrollPane, BorderLayout.CENTER);

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
		JLabel buildingQueueLabel = new JLabel("Building Queue");
		buildingQueuePanel.add(buildingQueueLabel, BorderLayout.NORTH);
		buildingQueueLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		// TABLE CENTER 
		buildingQueueTable = new JTable(new BuildingQueueTable(buildingManager).generateTable());
		buildingQueueTable.setFillsViewportHeight(true);
		
		JScrollPane buildingQueueScroll = new JScrollPane();
		buildingQueueScroll.setViewportView(buildingQueueTable);
		buildingQueuePanel.add(buildingQueueScroll, BorderLayout.CENTER);
		
		
		

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

		JLabel PeopleLabel = new JLabel("People");
		PeopleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		PeopleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		peoplePanel.add(PeopleLabel,BorderLayout.NORTH);
		
		JScrollPane peopleScrollPane = new JScrollPane();
		peoplePanel.add(peopleScrollPane, BorderLayout.CENTER);
		
		peopleTable = new JTable(new PeopleTable(buildingManager).generateTable());
		peopleScrollPane.setViewportView(peopleTable);
		
		JPanel southPeoplePanel = new JPanel();
		peoplePanel.add(southPeoplePanel, BorderLayout.SOUTH);
		
		

	
		 // EXISTING BUILDINGS
		// LABEL
		JLabel existingBuildingsLabel = new JLabel("Existing Buildings");
		existingBuildingsLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		existingBuildingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingBuildingPanel.add(existingBuildingsLabel, BorderLayout.NORTH);
		
		//LIST
		existingBuildingTable = new JTable(new ExistingBuildingModel(buildingManager).generateModel());
		existingBuildingTable.setFillsViewportHeight(true);
		
		JScrollPane existingBuildingScroll = new JScrollPane();
		existingBuildingScroll.setViewportView(existingBuildingTable);
		existingBuildingPanel.add(existingBuildingScroll, BorderLayout.CENTER);
		
		JPanel turnPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, turnPanel, 6, SpringLayout.SOUTH, existingBuildingPanel);
		springLayout.putConstraint(SpringLayout.WEST, turnPanel, 6, SpringLayout.EAST, existingTechPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, turnPanel, 306, SpringLayout.SOUTH, existingBuildingPanel);
		
		JPanel southExistingBuildingPanel = new JPanel();
		existingBuildingPanel.add(southExistingBuildingPanel, BorderLayout.SOUTH);
		
		JButton assignPeopleButton = new JButton("Assign");
		southExistingBuildingPanel.add(assignPeopleButton);
		
		JButton unAssingPeopleButton = new JButton("Unassign");
		southExistingBuildingPanel.add(unAssingPeopleButton);
		springLayout.putConstraint(SpringLayout.EAST, turnPanel, 206, SpringLayout.EAST, existingTechPanel);
		frame.getContentPane().add(turnPanel);
				turnPanel.setLayout(new BorderLayout(0, 0));
				
				JPanel SouthTurnPanel = new JPanel();
				turnPanel.add(SouthTurnPanel, BorderLayout.SOUTH);
				SouthTurnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						turnsLabel = new JLabel("Turns: 0");
						SouthTurnPanel.add(turnsLabel);
				
						JButton nextTurnButton = new JButton("Next Turn");
						SouthTurnPanel.add(nextTurnButton);
						nextTurnButton.setHorizontalAlignment(SwingConstants.TRAILING);
						springLayout.putConstraint(SpringLayout.SOUTH, nextTurnButton, 0, SpringLayout.SOUTH, frame.getContentPane());
						springLayout.putConstraint(SpringLayout.EAST, nextTurnButton, -436, SpringLayout.EAST, frame.getContentPane());
				nextTurnButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						turnHandler.newTurn();
						update(buildingManager,resourceManager,turnHandler);
					
					}

				}

				);
		
		
		

	}
	public void update(BuildingManager buildingManager,ResourceManager resourceManager,TurnHandler turnHandler){
		buildingQueueTable.setModel(new BuildingQueueTable(buildingManager).generateTable());
		buildingTable.setModel(new BuildingTableGen().generateTable());
		resourcesTable.setModel(new ResourceTableGen(resourceManager).generateTable());
		existingBuildingTable.setModel(new ExistingBuildingModel(buildingManager).generateModel());
		turnsLabel.setText("Turns: "+turnHandler.getCounter());

		
	}
}

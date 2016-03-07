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
import game.Person;
import game.PersonGenerator;
import game.PersonHandler;
import game.ResourceManager;
import game.TurnHandler;
import game.UpdateResources;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

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
					PersonHandler personHandler = new PersonHandler();
					PersonGenerator pg = new PersonGenerator(buildingManager, personHandler);
					UpdateResources updateResources = new UpdateResources();
					TurnHandler turnHandler = new TurnHandler(buildingManager, resourceManager, pg, updateResources);					
					Overview window = new Overview(resourceManager, buildingManager, turnHandler,personHandler,updateResources);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private BuildingQueuePanel buildingQueuePanel;
	private JLabel turnsLabel;
	private JTable peopleTable;
	private ResourcePanel resourcesPanel;
	private BuildingListPanel buildingListPanel;
	private ExistingBuildingPanel existingBuildingPanel;
	private PeoplePanel peoplePanel;
	private TurnPanel turnPanel;

	/**
	 * Create the application.
	 * 
	 * @param resourceManager
	 * @param personHandler 
	 * @param updateResources 
	 */
	public Overview(ResourceManager resourceManager, BuildingManager buildingManager, TurnHandler turnHandler, PersonHandler personHandler, UpdateResources updateResources) {

		initialize(resourceManager, buildingManager, turnHandler,personHandler,updateResources);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param turnHandler
	 * @param personHandler 
	 * @param updateResources 
	 */
	private void initialize(ResourceManager resourceManager, BuildingManager buildingManager, TurnHandler turnHandler, PersonHandler personHandler, UpdateResources updateResources) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		
		// --  resourcesPanel ---
		resourcesPanel = new ResourcePanel(resourceManager, updateResources);
		springLayout.putConstraint(SpringLayout.NORTH, resourcesPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, resourcesPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, resourcesPanel, 310, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, resourcesPanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(resourcesPanel);

		
		// --  buildingListPanel --
		buildingListPanel = new BuildingListPanel(buildingManager, this);
	
		springLayout.putConstraint(SpringLayout.NORTH, buildingListPanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingListPanel, 6, SpringLayout.EAST, resourcesPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingListPanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingListPanel, 206, SpringLayout.EAST, resourcesPanel);
		frame.getContentPane().add(buildingListPanel);

		// --  buildingQueuePanel --
		buildingQueuePanel = new BuildingQueuePanel(buildingManager);
		springLayout.putConstraint(SpringLayout.NORTH, buildingQueuePanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingQueuePanel, 6, SpringLayout.EAST, buildingListPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingQueuePanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingQueuePanel, 206, SpringLayout.EAST, buildingListPanel);
		frame.getContentPane().add(buildingQueuePanel);

		
		//// --  existingBuildingPanel --
		existingBuildingPanel = new ExistingBuildingPanel(buildingManager, personHandler, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, existingBuildingPanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, existingBuildingPanel, 6, SpringLayout.EAST, buildingQueuePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingBuildingPanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, existingBuildingPanel, 206, SpringLayout.EAST,
				buildingQueuePanel);
		frame.getContentPane().add(existingBuildingPanel);

		//-- People ---
		peoplePanel = new PeoplePanel(personHandler);
		existingBuildingPanel.setPeopleTable(peoplePanel.getPeopleTable());
		springLayout.putConstraint(SpringLayout.NORTH, peoplePanel, 6, SpringLayout.SOUTH, resourcesPanel);
		springLayout.putConstraint(SpringLayout.WEST, peoplePanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, peoplePanel, 306, SpringLayout.SOUTH, resourcesPanel);
		springLayout.putConstraint(SpringLayout.EAST, peoplePanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(peoplePanel);
		


		JScrollPane techPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, techPanel, 6, SpringLayout.SOUTH, buildingListPanel);
		springLayout.putConstraint(SpringLayout.WEST, techPanel, 6, SpringLayout.EAST, peoplePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, techPanel, 306, SpringLayout.SOUTH, buildingListPanel);
		
		springLayout.putConstraint(SpringLayout.EAST, techPanel, 206, SpringLayout.EAST, peoplePanel);
		frame.getContentPane().add(techPanel);

		JScrollPane existingTechPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, existingTechPanel, 6, SpringLayout.SOUTH, buildingQueuePanel);
		springLayout.putConstraint(SpringLayout.WEST, existingTechPanel, 6, SpringLayout.EAST, techPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingTechPanel, 306, SpringLayout.SOUTH, buildingQueuePanel);


		

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



	turnPanel = new TurnPanel(turnHandler, this);
		springLayout.putConstraint(SpringLayout.NORTH, turnPanel, 6, SpringLayout.SOUTH, existingBuildingPanel);
		springLayout.putConstraint(SpringLayout.WEST, turnPanel, 6, SpringLayout.EAST, existingTechPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, turnPanel, 306, SpringLayout.SOUTH, existingBuildingPanel);
		springLayout.putConstraint(SpringLayout.EAST, turnPanel, 206, SpringLayout.EAST, existingTechPanel);
		frame.getContentPane().add(turnPanel);
	

	}

	public void update() {
		resourcesPanel.update();
		buildingListPanel.update();
		buildingQueuePanel.update();
		existingBuildingPanel.update();
		peoplePanel.update();
		turnPanel.update();

	}
}

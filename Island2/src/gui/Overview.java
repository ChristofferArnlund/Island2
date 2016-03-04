package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.ResourceManager;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class Overview {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Overview window = new Overview();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Overview() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		JScrollPane existingBuildingsPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, existingBuildingsPanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, existingBuildingsPanel, 6, SpringLayout.EAST, buildingListPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingBuildingsPanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, existingBuildingsPanel, 206, SpringLayout.EAST,
				buildingListPanel);
		frame.getContentPane().add(existingBuildingsPanel);

		JScrollPane buildingQueuePanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, buildingQueuePanel, 10, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buildingQueuePanel, 6, SpringLayout.EAST, existingBuildingsPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, buildingQueuePanel, 310, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buildingQueuePanel, 206, SpringLayout.EAST,
				existingBuildingsPanel);
		frame.getContentPane().add(buildingQueuePanel);

		JScrollPane peoplePanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, peoplePanel, 6, SpringLayout.SOUTH, resourcesPanel);
		springLayout.putConstraint(SpringLayout.WEST, peoplePanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, peoplePanel, 306, SpringLayout.SOUTH, resourcesPanel);
		// ResourceLIst
		ResourceManager resourceManager = new ResourceManager();

		Object[] data1 = resourceManager.resources.keySet().toArray();
		Object[] data2 = resourceManager.resources.values().toArray();
		Object[][] rowData = new Object[data1.length - 1][data1.length - 1];
		
		for (int x = 0; x < data1.length - 1; x++) {
				rowData[x][0] = data1[x];
				rowData[x][1] = data2[x];

			}

		Object columnNames[] = { "Resource", "Value" };
	
	JPanel panel = new JPanel();
	resourcesPanel.setColumnHeaderView(panel);
	
			JLabel resourcesLabel = new JLabel("Resources");
			panel.add(resourcesLabel);
			resourcesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
			
			JPanel panel_1 = new JPanel();
			resourcesPanel.setViewportView(panel_1);
							panel_1.setLayout(new BorderLayout(0, 0));
					
							JTable resourcesList = new JTable(rowData, columnNames);
							panel_1.add(resourcesList);
							resourcesList.setEnabled(false);
							resourcesList.setRowSelectionAllowed(false);
							resourcesList.setShowHorizontalLines(false);
							resourcesList.setShowGrid(false);
	
		
		
		
		springLayout.putConstraint(SpringLayout.EAST, peoplePanel, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(peoplePanel);

		JScrollPane techPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, techPanel, 6, SpringLayout.SOUTH, buildingListPanel);
		springLayout.putConstraint(SpringLayout.WEST, techPanel, 6, SpringLayout.EAST, peoplePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, techPanel, 306, SpringLayout.SOUTH, buildingListPanel);

		JLabel buildingListLabel = new JLabel("Buildings");
		buildingListLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		buildingListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buildingListPanel.setColumnHeaderView(buildingListLabel);
		springLayout.putConstraint(SpringLayout.EAST, techPanel, 206, SpringLayout.EAST, peoplePanel);
		frame.getContentPane().add(techPanel);

		JScrollPane existingTechPanel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, existingTechPanel, 6, SpringLayout.SOUTH,
				existingBuildingsPanel);
		springLayout.putConstraint(SpringLayout.WEST, existingTechPanel, 6, SpringLayout.EAST, techPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, existingTechPanel, 306, SpringLayout.SOUTH,
				existingBuildingsPanel);

		JLabel existingBuildingsLabel = new JLabel("Existing Buildings");
		existingBuildingsLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		existingBuildingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingBuildingsPanel.setColumnHeaderView(existingBuildingsLabel);
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
		springLayout.putConstraint(SpringLayout.EAST, nextTurnButton, 0, SpringLayout.EAST, buildingQueuePanel);

		JLabel lblNewLabel = new JLabel("Nonexisting");
		buildingQueuePanel.setColumnHeaderView(lblNewLabel);
		frame.getContentPane().add(nextTurnButton);

	}
}

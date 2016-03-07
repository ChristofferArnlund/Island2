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
import javax.swing.SwingConstants;

import game.BuildingManager;
import game.PersonHandler;

public class ExistingBuildingPanel extends JPanel {

	private JTable existingBuildingTable;
	private BuildingManager buildingManager;
	private JTable peopleTable;
	private PersonHandler personHandler;
	private Overview overview;

	public ExistingBuildingPanel(BuildingManager buildingManager, PersonHandler personHandler, Overview overview) {
		this.buildingManager = buildingManager;
		this.personHandler = personHandler;
		this.overview = overview;
		setLayout(new BorderLayout());

		JLabel existingBuildingsLabel = new JLabel("Existing Buildings");
		existingBuildingsLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		existingBuildingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(existingBuildingsLabel, BorderLayout.NORTH);

		// LIST
		existingBuildingTable = new JTable(new ExistingBuildingModel(buildingManager).generateModel());
		existingBuildingTable.setFillsViewportHeight(true);

		JScrollPane existingBuildingScroll = new JScrollPane();
		existingBuildingScroll.setViewportView(existingBuildingTable);
		add(existingBuildingScroll, BorderLayout.CENTER);

		JPanel southExistingBuildingPanel = new JPanel();
		add(southExistingBuildingPanel, BorderLayout.SOUTH);

		// ASSIGN PEOPLE BUTTON
		JButton assignPeopleButton = new JButton("Assign");
		assignPeopleButton
				.setToolTipText("Please mark both the existing building and the person you want to assign/unassign");
		assignPeopleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int eIndex = existingBuildingTable.getSelectedRow();
				int pIndex = peopleTable.getSelectedRow();
				if (pIndex != -1) {
					personHandler.assign(personHandler.getUnassigned(pIndex),
							buildingManager.existingBuildings.get(eIndex));

				}
				overview.update();
			}
		});
		southExistingBuildingPanel.add(assignPeopleButton);

		JButton unAssingPeopleButton = new JButton("Unassign");
		unAssingPeopleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int pIndex = peopleTable.getSelectedRow();
				if (pIndex != -1) {
					personHandler.unassign(personHandler.getAssigned(pIndex));

				}

				overview.update();

			}
		});
		southExistingBuildingPanel.add(unAssingPeopleButton);

	}

	public void update() {

		existingBuildingTable.setModel(new ExistingBuildingModel(buildingManager).generateModel());
	}

	public void setPeopleTable(JTable peopleTable) {
		this.peopleTable = peopleTable;

	}

}

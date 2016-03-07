package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import game.Person;
import game.PersonHandler;

public class PeoplePanel extends JPanel{
	
	private PersonHandler personHandler;
	private JTable peopleTable;

	public PeoplePanel(PersonHandler personHandler){
		this.personHandler = personHandler;
		setLayout(new BorderLayout());

		// PEOPLE PANEL
		JLabel PeopleLabel = new JLabel("People");
		PeopleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		PeopleLabel.setHorizontalAlignment(SwingConstants.CENTER);
add(PeopleLabel, BorderLayout.NORTH);

		JScrollPane peopleScrollPane = new JScrollPane();
		add(peopleScrollPane, BorderLayout.CENTER);

		// PEOPLE TABLE
		personHandler.addUnassigned(new Person("YO"));
		peopleTable = new JTable(new PeopleTable(personHandler).generateTable());
		peopleScrollPane.setViewportView(peopleTable);

		JPanel southPeoplePanel = new JPanel();
		add(southPeoplePanel, BorderLayout.SOUTH);
		
	}
	
	public void update(){
		peopleTable.setModel(new PeopleTable(personHandler).generateTable());
		
	}

	public JTable getPeopleTable() {
		return peopleTable;
	}

}

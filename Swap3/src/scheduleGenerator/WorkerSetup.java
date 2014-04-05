package scheduleGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author schneimd
 */
public class WorkerSetup extends javax.swing.JFrame {

	private ArrayList<Day> days;
	private ArrayList<JPanel> workerTabs;
	private boolean isForced = false;

	/**
	 * Allows for editing of already made workers.
	 * 
	 * @param workers
	 */
	public WorkerSetup(ArrayList<Worker> workers) {
		this.setPreferredSize(new Dimension(425, 450));
		this.workerTabs = new ArrayList<JPanel>();
		initComponents();
		for (int c = 0; c < workers.size(); c++) {
			this.addWorker();
		}

		for (int c = 0; c < workers.size(); c++) {
			JTextField nameArea = (JTextField) this.workerTabs.get(c)
					.getComponent(2);
			nameArea.setText(workers.get(c).getName());
			JTabbedPane daysPane = (JTabbedPane) this.workerTabs.get(c)
					.getComponents()[0];
			for (int i = 0; i < daysPane.getTabCount(); i++) {
				for (int n = 0; n < workers.get(c).getDays().size(); n++) {
					if (daysPane.getTitleAt(i).equals(
							workers.get(c).getDays().get(n).getNameOfDay())) {

						JPanel day = (JPanel) daysPane.getComponent(i);
						JScrollPane pane = (JScrollPane) day.getComponent(0);
						JViewport view = (JViewport) pane.getComponent(0);
						JPanel p = (JPanel) view.getComponent(0);

						for (Component job : p.getComponents()) {
							for (String workerJob : workers.get(c).getDays()
									.get(n).getJobs()) {
								if (((JCheckBox) job).getText().equals(
										workerJob)) {
									((JCheckBox) job).setSelected(true);
								}
							}
						}
					}
				}
			}
		}

	}

	/**
	 * Creates new form WorkerSetup
	 */
	public WorkerSetup() {
		this.setPreferredSize(new Dimension(425, 450));
		this.workerTabs = new ArrayList<JPanel>();
		initComponents();
		addWorker();
	}

	private void addWorker() {
		this.days = Main.getDays();
		javax.swing.JTabbedPane tempWorkerDays = new javax.swing.JTabbedPane();
		javax.swing.JTextField tempWorkerName = new javax.swing.JTextField();
		javax.swing.JPanel tempWorkerTab = new javax.swing.JPanel();

		// Makes a tab for each day and a check box for each job.
		for (Day day : this.days) {
			JCheckBox[] jobs = new JCheckBox[day.getJobs().size()];
			for (int i = 0; i < day.getJobs().size(); i++) {
				jobs[i] = new JCheckBox(day.getJobs().get(i));
			}

			// Put Check Boxes in a scrollPane for dynamics
			JScrollPane tempDayJobPane = new JScrollPane();
			JPanel tempPanel = new JPanel();
			tempPanel.setLayout(new GridLayout(jobs.length, 1));

			for (JCheckBox job : jobs) {
				tempPanel.add(job);
			}
			tempDayJobPane.setViewportView(tempPanel);

			// Label the Pane
			JLabel jobLabel = new JLabel("Preferred Jobs:");

			// Create a tab Panel for the Worker Tab and add the inputs.

			JPanel dayTab = new JPanel();

			// Set veritcal and horizontal layouts.
			javax.swing.GroupLayout sundayTab1Layout = new javax.swing.GroupLayout(
					dayTab);
			dayTab.setLayout(sundayTab1Layout);
			sundayTab1Layout
					.setHorizontalGroup(sundayTab1Layout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									sundayTab1Layout
											.createSequentialGroup()
											.addGap(63, 63, 63)
											.addGroup(
													sundayTab1Layout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING)
															.addComponent(
																	tempDayJobPane,
																	javax.swing.GroupLayout.PREFERRED_SIZE,
																	198,
																	javax.swing.GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	jobLabel))
											.addContainerGap(73,
													Short.MAX_VALUE)));

			sundayTab1Layout
					.setVerticalGroup(sundayTab1Layout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									sundayTab1Layout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(jobLabel)
											.addPreferredGap(
													javax.swing.LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(
													tempDayJobPane,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													179, Short.MAX_VALUE)
											.addContainerGap()));

			tempWorkerDays.addTab(day.getNameOfDay(), dayTab);

		}

		// Add a section for the worker's name
		JLabel workerNameLabel = new JLabel("Worker's Name:");

		javax.swing.GroupLayout workerTab1Layout = new javax.swing.GroupLayout(
				tempWorkerTab);
		tempWorkerTab.setLayout(workerTab1Layout);
		workerTab1Layout
				.setHorizontalGroup(workerTab1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								workerTab1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												workerTab1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																tempWorkerDays)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																workerTab1Layout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				workerNameLabel)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				tempWorkerName,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				150,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(49,
																				49,
																				49)))
										.addContainerGap()));

		// Adds text area and label for name then tab area for days.
		workerTab1Layout
				.setVerticalGroup(workerTab1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								workerTab1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												workerTab1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																workerNameLabel)
														.addComponent(
																tempWorkerName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												tempWorkerDays,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												249,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		// Prevents a nullPointer
		if (this.workerTabs.size() == 0) {
			this.workerTabs.add(tempWorkerTab);
			this.workerTabPanel.addTab("Worker 1", null, tempWorkerTab, "");
		} else {
			this.workerTabs.add(tempWorkerTab);
			this.workerTabPanel.addTab(
					"Worker " + String.valueOf(this.workerTabs.size()), null,
					tempWorkerTab, "");
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		
		// SWAP 1 TEAM 1
		// ADDITIONAL FEATURE:
		// The feature added was a checkbox in the worker setup frame
		// to allow the user toggle between forcing every worker to have at least
		// one job before repeating and scheduling jobs based on preference as 
		// before. 
		// The smell that would have made this implementation much easier is the
		// message chain described below. It was hard to find the right locations to
		// introduce the new checkbox to display nicely on the program.
		/* ##############################################################################
		 * SWAP 2, TEAM 2
		 * ELABORATE
		 * The team who had this for swap 1 added a check box on the view, we change the 
		 * checkbox to a drop down which enable the users to to have more options if they
		 * want some option other than restrict one user has to have at least one job.
		 * ##############################################################################
		 */
		this.workerTabPanel = new javax.swing.JTabbedPane();
		this.addButton = new javax.swing.JButton();
		this.removeButton = new javax.swing.JButton();
		this.nextButton = new javax.swing.JButton();
		this.backButton = new javax.swing.JButton();
//		this.forceAtLeastOneJob = new javax.swing.JCheckBox();
		this.model = new DefaultComboBoxModel();
	    this.model.addElement("At leat one job");
	    this.model.addElement("No restriction");
	    comboBox = new JComboBox(model);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Worker Setup");
		
//		this.forceAtLeastOneJob.setText("Assign every worker to \na job before repeating");

		this.addButton.setText("Add Worker");
		this.addButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		this.removeButton.setText("Remove Worker");
		this.removeButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeButtonActionPerformed(evt);
					}
				});

		this.nextButton.setText("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		this.backButton.setText("Back");
		this.backButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		JScrollPane outside = new JScrollPane();
		outside.setViewportView(this.workerTabPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		// SWAP 1 TEAM 1
		// SMELL: Message Chain
		// A chain of successive calls to functions starts from line 294 all the
		// way to line 337 to set the HorizontalGroup of this layout. This
		// pattern is seen various times within this method and makes it very
		// lengthy and hard to read.
		// A possible solution to this would be to replace the chain with a
		// helper function that gets the information needed so that at this
		// point the line "layout.setHorizontalGroup();" would take care of
		// everything.
		// Doing so would make it easier to read and understand the code in this
		// section of the WorkerSetup and thus ease the process of adding
		// enhancements to it.
		
		ArrayList<javax.swing.JComponent> components = new ArrayList<javax.swing.JComponent>();
		components.add(this.comboBox);
		components.add(this.backButton);
		components.add(this.nextButton);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(106, 106, 106)
								.addComponent(this.backButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										65,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(this.nextButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										65,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						layout.createParallelGroup()
						.addComponent(this.comboBox,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												300,
												javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.addButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		136,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		82,
																		Short.MAX_VALUE)
																.addComponent(
																		this.removeButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		136,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														outside,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														0, Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(outside,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										330,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(this.addButton)
												.addComponent(this.removeButton))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.CENTER)
												.addComponent(this.comboBox))
								.addGroup(
										this.addGroupComponenet(layout, components)
										)
								.addGap(0, 8, Short.MAX_VALUE)));

		pack();
	}
	/* ##############################################################################
	 * SWAP 2, TEAM 2
	 * ADDITIONAL FEATURE
	 * In the smell that the previous team mentioned, that it is really hard to fine
	 * the place to add a new component, so we refactored the adding group component 
	 * into another funciton, which creates more abstraction, also 
	 * ##############################################################################
	 */
	
	public ParallelGroup addGroupComponenet(javax.swing.GroupLayout layout, ArrayList<javax.swing.JComponent> components){
		ParallelGroup group = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
		for(int i = 0; i < components.size(); i++){
			group.addComponent(components.get(i));
		}
		return group;
	}

	/**
	 * @param evt
	 */
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ArrayList<Worker> workers = new ArrayList<Worker>();
		boolean allGood = true;
		for (JPanel tab : this.workerTabs) {
			ArrayList<Day> workerDays = new ArrayList<Day>();
			JTextField nameArea = (JTextField) tab.getComponent(2);
			if (nameArea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"You have not entered a name for every worker.");
				allGood = false;
				break;
			}
			JTabbedPane daysPane = (JTabbedPane) tab.getComponents()[0];
			for (int i = 0; i < daysPane.getTabCount(); i++) {

				JPanel day = (JPanel) daysPane.getComponent(i);

				JScrollPane pane = (JScrollPane) day.getComponent(0);

				JViewport view = (JViewport) pane.getComponent(0);

				JPanel p = (JPanel) view.getComponent(0);

				ArrayList<Object> jobNames = new ArrayList<Object>();

				for (Component job : p.getComponents()) {
					if (((JCheckBox) job).isSelected()) {
						jobNames.add(((JCheckBox) job).getText());
					}
				}
				workerDays.add(new Day(daysPane.getTitleAt(i), jobNames));
			}
			workers.add(new Worker(nameArea.getText(), workerDays));
		}
		System.out.println(comboBox.getSelectedItem());
		if(comboBox.getSelectedItem() == "No restriction"){
			this.isForced = false;
		}else{
			this.isForced = true;
		}
		if (allGood) {
			Main.regenerate(workers, this.isForced);
		}
	}

	/**
	 * @param evt
	 */
	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Main.toggleConfig();
		Main.toggleWorkerSetup();
	}

	/**
	 * @param evt
	 */
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.addWorker();
	}

	/**
	 * @param evt
	 */
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.workerTabs.remove(this.workerTabPanel.getSelectedComponent());
		this.workerTabPanel.remove(this.workerTabPanel.getSelectedIndex());
	}

	private javax.swing.JButton addButton;
	private javax.swing.JButton backButton;
	private javax.swing.JButton nextButton;
	private javax.swing.JButton removeButton;
	private javax.swing.JTabbedPane workerTabPanel;
	private javax.swing.JCheckBox forceAtLeastOneJob;
	private DefaultComboBoxModel model;
	private JComboBox comboBox;
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Group;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 * 
 * @author schneimd
 */
// SWAP 1 TEAM 1
// SMELL: Large Class
// This class is very large with many subparts and methods.
// There are around 60+ private variables in this class
// A solution is to break apart the subparts, between objects like the
// jcheckboxes and jpanels.
// This would allow for additional features/refinements for each subpart and
// make it easier to find the places where they would fit in.

/* ##############################################################################
 * SWAP 2, TEAM 2
 * REFACTORING #4,5, AND 6 FOR LARGE CLASS, DUPLICATE CODE, AND DATA CLUMPS
 * The primary comments are in the DayPanel class.
 * This section deals with the LARGE CLASS smell.
 * ##############################################################################
 */
public class Config extends javax.swing.JFrame {

	private boolean firstSelection = true;
	private int numSelected = 0;
	@SuppressWarnings("rawtypes")

	/**
	 * Used to edit days.
	 * 
	 * @param days
	 */

	// SWAP 1 TEAM 1
	public Config(ArrayList<Day> days) {
		initDyn();
		initComponents();
		// QUALITY CHANGES:(1st Refactoring)
		// i. The smell I got rid of here was duplicate code in a long if-else
		// that created a checkbox and list of jobs for the given day.
		// ii. I refactored the code by extracting the if-else statement into
		// one method, "setUpDays" that this constructor calls with the given
		// list of days.
		// More details on how the method works now are included in a comment
		// within that method.
		setUpDays(days);
	}

	// SWAP 1 TEAM 1
	// QUALITY CHANGES: (1st Refactoring Continued)
	// This method takes the list of days and creates a
	// checkbox and joblist for each day and places it in the appropriate place
	// on the model. Rather than check the day of the week and do this for it's
	// checkbox, the checkboxes for the day were put in a private arraylist
	// "dayCheckList" and this is done for every checkbox in the list.
	private void setUpDays(ArrayList<Day> days) {
		for (int i = 0; i < days.size(); i++) {
			dayPanels[i].getCheckBox().doClick();
			ArrayList<String> jobs = days.get(i).getJobs();
			for (String job : jobs) {
				this.dayPanels[i].getModel().addElement(job);
			}

		}
	}

	/**
	 * Creates new form.
	 */
	public Config() {
		initDyn();
		ArrayList<Day> days = new ArrayList<Day>();

		initComponents();
	}

	/* ##############################################################################
	 * SWAP 2, TEAM 2
	 * REFACTORING #4,5, AND 6 FOR LARGE CLASS, DUPLICATE CODE, AND DATA CLUMPS
	 * The primary comments are in the DayPanel class.
	 * This section deals with the Duplicated Code smell.
	 * ##############################################################################
	 */
	@SuppressWarnings("rawtypes")
	private void initDyn() {
		this.dayPanels = new DayPanel[7];
		for (int i = 0; i < 7; i++) {
			this.dayPanels[i] = new DayPanel(i+1);
			final int j = i;
			this.dayPanels[i].getCheckBox().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (dayPanels[j].getCheckBox().isSelected()) {
						numSelected++;
						if (firstSelection) {
							stretch();
						}
						dayPanels[j].checkActionPerformed();
						
						//TEAM 4 SWAP 3. ENHANCEMENT. SOrts the tabs by day, so that it's easier to read.
						boolean toAdd[] = new boolean[7];
						for(int x = 0; x < 7; x ++)
						{
							if(dayPanels[x].getCheckBox().isSelected())
								toAdd[x] = true;
						}
						dayTabs.removeAll();
						for(int x = 0; x < 7; x ++)
						{
							if(toAdd[x])
							{
								dayTabs.addTab(dayPanels[x].getDayName(), dayPanels[x]);
							}
						}
						
						
					} else {
						numSelected--;
						stretch();
						dayTabs.remove(dayPanels[j]);
					}
				}
			});
		}
	}

	// SWAP 1 TEAM 1
	// SMELL: Long Method
	// This method performs many different actions within the same method. It
	// can be broken up into different method (such as initializeCheckboxes and
	// initializePanel.
	// Splitting it up this way would allow someone to make changes to the
	// checkboxes or panels without having to search for them in the code and
	// without affecting the Config code.
	/* ##############################################################################
	 * SWAP 2, TEAM 2
	 * REFACTORING #7 FOR Long Method
	 * refactored the long method to shorter one
	 * ##############################################################################
	 */
	private void initComponents() {

		this.jPanel1 = new javax.swing.JPanel();
		this.jLabel1 = new javax.swing.JLabel();
		this.nextButton = new javax.swing.JButton();
		this.dayTabs = new javax.swing.JTabbedPane();

		// SWAP 1 TEAM 1
		// QUALITY CHANGES: this list of checkboxes is used in different
		// methods.
		// Since the list
		// is the same, a private array is created.

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Configuration");
		setPreferredSize(new java.awt.Dimension(801, 87));
		setResizable(false);

		// SWAP 1 TEAM 1
		// SMELL: Data Clumps
		// Almost ideantical code is given for all of these checkboxes. They all
		// "hangout" together.
		// Extracting these into their own "DayCheckbox" class would get rid of
		// a lot of duplicate code that is repeated below.
		// By doing so the code in this class would be much shorter and easier
		// to understand while the new Checkbox class would seperate the
		// checkbox responsibilites from this class and make it easier to make
		// changes to those checkboxes within its own class.
		
		/* ##############################################################################
		 * SWAP 2, TEAM 2
		 * REFACTORING #4,5, AND 6 FOR LARGE CLASS, DUPLICATE CODE, AND DATA CLUMPS
		 * The primary comments are in the DayPanel class.
		 * This section deals with the Data Clumps smell.
		 * ##############################################################################
		 */
	
		this.nextButton.setText("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
        		setHorizontalGroup(jPanel1Layout)
        );
        jPanel1Layout.setVerticalGroup(
        		setVerticalGroup(jPanel1Layout)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(this.dayTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.dayTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

		this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

		pack();
	}// </editor-fold>
	// SWAP 1 TEAM 1
	// SMELL: Long Method
	// This method performs many different actions within the same method. It
	// can be broken up into different method (such as initializeCheckboxes and
	// initializePanel.
	// Splitting it up this way would allow someone to make changes to the
	// checkboxes or panels without having to search for them in the code and
	// without affecting the Config code.
	/* ##############################################################################
	 * SWAP 2, TEAM 2
	 * REFACTORING #7 FOR Long Method
	 * this part is dealing with long method smell.
	 * ##############################################################################
	 */
	private Group setHorizontalGroup(javax.swing.GroupLayout jPanel1Layout){
		return jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(this.jLabel1)
            .addGap(18, 18, 18)
            .addComponent(this.dayPanels[0].getCheckBox())
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(this.dayPanels[1].getCheckBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(this.dayPanels[2].getCheckBox())
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(this.dayPanels[3].getCheckBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(this.dayPanels[4].getCheckBox())
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(this.dayPanels[5].getCheckBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(this.dayPanels[6].getCheckBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(this.nextButton)
            .addGap(78, 78, 78));
	}
	
	private Group setVerticalGroup(javax.swing.GroupLayout jPanel1Layout){
		return jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(this.dayPanels[0].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(this.dayPanels[1].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(this.dayPanels[2].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
	                            .addComponent(this.nextButton))
	                        .addComponent(this.dayPanels[3].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(this.dayPanels[4].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createSequentialGroup()
	                            .addComponent(this.jLabel1)
	                            .addGap(0, 0, Short.MAX_VALUE))
	                        .addComponent(this.dayPanels[5].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(this.dayPanels[6].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        )
	                    .addContainerGap());
	}

	/**
	 * @param evt
	 */

	// SWAP 1 TEAM 1
	// SMELL: Duplicate Code
	// The methods contained within line 433 to 1521 all do the same thing -
	// check the action performed for the given day. The code within them is
	// exactly the same with the exception of the name of the week and its
	// position in the model.
	// A possible solution to this would be to condense the methods into one
	// that uses a for loop to assign what should happen on the action performed
	// for each day. This would remove the need to check if it is a particular
	// day.
	// Doing so would make the code in this class a lot shorter and easier to
	// read. It would also make it easier to change the action performed for
	// each day, since they could do so in a few lines from one method, rather
	// than having to add the same lines of code for every method for every day
	// of the week.
	
	/* ##############################################################################
	 * SWAP 2, TEAM 2
	 * REFACTORING #4,5, AND 6 FOR LARGE CLASS, DUPLICATE CODE, AND DATA CLUMPS
	 * The primary comments are in the DayPanel class.
	 * This section deals with the Duplicate Code smell.
	 * ##############################################################################
	 */
	
	/**
	 * @param evt
	 */
	// SWAP 1 TEAM 1
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ArrayList<Day> days = new ArrayList<Day>();
		// QUALITY CHANGE:(2nd Refactoring)
		// i. The smell I got rid of here was duplicate if statements in this
		// method that had to do with creating days and a list of jobs for that
		// day when its checkbox was selected.
		// ii. To solve this I extracted all the if statements that were in this
		// method into a method called "daySelected" that does what is described
		// above for the given list of days without all of those if statements.

		daySelected(days);

		if (days.size() > 0) {
			boolean hasJobs = true;
			int i = 0;
			while (hasJobs && i < days.size()) {
				if (days.get(i).getJobs().size() == 0) {
					hasJobs = false;
				}
				i++;
			}
			if (hasJobs) {
				// SWAP 1 TEAM 1
				// SMELL: Feature Envy
				// This entire block calls main five times.
				// This can be moved into a single method within Main.
				// Doing so would ensure that if someone were to try to
				// add/change any of the methods from Main, they wouldn't have
				// to worry about breaking this class.
				
				// ######################################################################
				// SWAP 2, TEAM 2
				// REFACTORING #2, FOR ENHANCEMENT FROM FEATURE ENVY
				// As suggested, the code block was removed and incorporated into a
				// single method within main.
				// This will make it much easier to make any necessary changes to main,
				// as it will be understood what Config wants to accomplish.
				// ######################################################################
				Main.refresh(this, days);
			} else {
				JOptionPane.showMessageDialog(this,
						"You must have at least one job each day.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not added any days.");
		}
	}

	// QUALITY CHANGE:(2nd Refactoring continued)
	// Here is the actual method. Within it I create a list of
	// the checkboxes, and use java's DateFormatSymbols to get the correct
	// string for the day of the week without having to do a lot of if
	// statements like they were doing before. For each button, if it
	// is selected it will create a day, add its job list in the corresponding
	// place on the model, and add itself to the days list that is passed in.
	private void daySelected(ArrayList<Day> days) {
		String[] daysOfWeek = new DateFormatSymbols().getWeekdays();

		for (int i = 0; i < this.dayPanels.length; i++) {
			if (this.dayPanels[i].getCheckBox().isSelected()) {

				ArrayList<Object> day = new ArrayList<Object>();
				List<Object> jobs = Arrays.asList(this.dayPanels[i].getModel().toArray());
				day.addAll(jobs);
				days.add(new Day(daysOfWeek[i + 1], day));
				Collections.sort(days);
			}
		}
	}

	private void stretch() {
		if (this.numSelected > 0) {
			this.setSize(801, 290);
			this.firstSelection = false;
		} else {
			this.setSize(801, 87);
			this.firstSelection = true;
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Config().setVisible(true);
			}
		});
	}

	private DayPanel[] dayPanels;

	private javax.swing.JTabbedPane dayTabs;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JButton nextButton;
}

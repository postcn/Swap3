package scheduleGenerator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/* ##############################################################################
 * SWAP 2, TEAM 2
 * REFACTORING #4,5, AND 6 FOR LARGE CLASS, DUPLICATE CODE, AND DATA CLUMPS
 * 
 * So this refactor covers A LOT of stuff, knocking out three smells from the
 * Config class. It handles all the gui for a given day in the config screen.
 * This abstraction well more than halved the length of the config class and made
 * it infinitely more readable.
 * 
 * What can be enabled by this change? Any sort of altering of the tab layout can
 * be easily accomplished now since everything is in one place.
 * 
 * ##############################################################################
 */
public class DayPanel extends JPanel {
	private JScrollPane scroll;
	private JButton addButton;
	private JButton deleteButton;
	private JList jobList;
	private JTextField jobNameField;
	private JLabel label;
	private DefaultListModel model;
	private JCheckBox checkBox;
	private int dayOfWeek;
	
	public DayPanel(int dayOfWeek) {
		super();
		this.scroll = new JScrollPane();
		this.scroll.setPreferredSize(new Dimension(185, 150));
		this.jobList = new JList();
		this.jobNameField = new JTextField();
		this.label = new JLabel();
		this.addButton = new JButton();
		this.deleteButton = new JButton();
		
		this.checkBox = new JCheckBox();
		
		this.dayOfWeek = dayOfWeek;
		String name = new DateFormatSymbols().getWeekdays()[this.dayOfWeek];
		this.checkBox.setText(name);
		this.checkBox.setName(name);
	}
	
	public JCheckBox getCheckBox() {
		return this.checkBox;
	}
	
	public DefaultListModel getModel() {
		return model;
	}
	
	public String getDayName() {
		return new DateFormatSymbols().getWeekdays()[dayOfWeek];
	}
	
	public void setModel(DefaultListModel m) {
		this.jobList.setModel(m);
	}
	
	public void checkActionPerformed() {
		model = new DefaultListModel<Object>();
		this.jobList.setModel(model);
		this.scroll.setViewportView(this.jobList);

		this.jobNameField.setColumns(20);

		this.label.setText("Job Name:");

		this.addButton.setText("Add Job");
		this.addButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {
						if (!jobNameField.getText().isEmpty()) {
							model.addElement(jobNameField.getText());
							jobList.setModel(model);
							jobNameField.setText("");
						}
					}
				});

		this.deleteButton.setText("Delete Job");
		this.deleteButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {
						while (!jobList
								.isSelectionEmpty()) {
							int n = jobList
									.getSelectedIndex();
							model.remove(n);
						}

					}
				});

		javax.swing.GroupLayout sundayTabLayout = new javax.swing.GroupLayout(
				this);
		this.setLayout(sundayTabLayout);
		sundayTabLayout
				.setHorizontalGroup(sundayTabLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								sundayTabLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												this.scroll,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												182,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												sundayTabLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																sundayTabLayout
																		.createSequentialGroup()
																		.addComponent(
																				this.label)
																		.addGroup(
																				sundayTabLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								sundayTabLayout
																										.createSequentialGroup()
																										.addGap(14,
																												14,
																												14)
																										.addComponent(
																												this.addButton))
																						.addGroup(
																								sundayTabLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												this.jobNameField,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												100,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addComponent(
																this.deleteButton))
										.addContainerGap(431,
												Short.MAX_VALUE)));
		sundayTabLayout
				.setVerticalGroup(sundayTabLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								sundayTabLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												sundayTabLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																sundayTabLayout
																		.createSequentialGroup()
																		.addGroup(
																				sundayTabLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								this.jobNameField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								this.label))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.addButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				this.deleteButton))
														.addComponent(
																this.scroll,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(25,
												Short.MAX_VALUE)));
	} 
}

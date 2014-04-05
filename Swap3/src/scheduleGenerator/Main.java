package scheduleGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.sun.awt.AWTUtilities;

/**
 * This class handles the interaction of one frame to another as well as
 * handling initialization.
 * 
 * @author Mason Schneider and Orion Martin. Created Oct 8, 2012.
 */
public class Main {

	private static ArrayList<Day> days;
	private static ArrayList<Worker> workers;
	private static File path = new File("schedule_data.ser");
	
	/**
	 * Configures days.
	 */
	static Config config;
	/**
	 * Configures workers.
	 */
	static WorkerSetup wSet;
	/**
	 * Displays schedule.
	 */
	static CalendarGUI cal;
	private static Schedule schedule;

	/**
	 * Program starts here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		path = new File("schedule_data.ser");
		config = new Config();
		
		//Code to open the config file.
		
		try {
			recallConfigFile();
			if(getSchedule() != (null)){
				cal = new CalendarGUI(getSchedule());
				//config.setVisible(true);
				cal.setVisible(true);
			} else{
				config.setVisible(true);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			
		}
	}

	/**
	 * Changes visible of config.
	 * 
	 */
	public static void toggleConfig() {
		config.setVisible(!config.isVisible());
	}

	/**
	 * Changes visible of calendar.
	 * 
	 */
	public static void toggleCalendar() {
		cal.setVisible(!cal.isVisible());
	}

	/**
	 * Changes visible of worker setup.
	 * 
	 */
	public static void toggleWorkerSetup() {
		if (wSet != null) {
			wSet.setVisible(!wSet.isVisible());
		}
	}
	
	// ######################################################################
	// SWAP 2, TEAM 2
	// REFACTORING #2, FOR ENHANCEMENT FROM FEATURE ENVY
	// Main description is within the nextButtonActionPerformed method of
	// the Config class.
	// ######################################################################
	public static void refresh(Config conf, ArrayList<Day> days) {
		
		setDays(days);
		wSet = new WorkerSetup();
		toggleWorkerSetup();
		config = conf;
		toggleConfig();
	}
	
	// ######################################################################
	// SWAP 2, TEAM 3
	// REFACTORING - Feature Envy
	// The feature envy block above was true, but we found more in the next method of workerSetup
	// This feature envy let us add a spinner that shows loading for the calendar.
	// We manually made it stay open for one second since this is instantaneous, but we would
	// Have scheduled multiple threads if it was necessary to actually load components.
	// ######################################################################
	public static void regenerate(ArrayList<Worker> workers, boolean isForced) {
		HTMLGenerator.reset();
		setWorkers(workers);
		setSchedule(new Schedule(Main.getDays(), Main.getWorkers(), isForced));
		dumpConfigFile();
		cal = new CalendarGUI(Main.getSchedule());
		cal.setVisible(false);
		final JDialog dialog = new JDialog(cal);
		dialog.setSize(325,200);
		dialog.setLocationRelativeTo(cal);

		JLabel label = new JLabel();
		ImageIcon ii = new ImageIcon("images/spinner.gif");
		label.setIcon(ii);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,0));
		panel.add(label);
		dialog.add(panel);

		dialog.setVisible(true);
		toggleWorkerSetup();
		
		
		class CloseSpinner extends TimerTask {
		    public void run() {
		    	dialog.dispose();
		    	toggleCalendar();
		    	
		    }
		 }
		Timer timer = new Timer();
		timer.schedule(new CloseSpinner(), 1000);
	}

	/**
	 * Returns the value of the field called 'schedule'.
	 * 
	 * @return Returns the schedule.
	 */
	public static Schedule getSchedule() {
		return Main.schedule;
	}

	/**
	 * Sets the field called 'schedule' to the given value.
	 * 
	 * @param schedule
	 *            The schedule to set.
	 */
	public static void setSchedule(Schedule schedule) {
		Main.schedule = schedule;
	}

	/**
	 * Sets the value of workers.
	 * 
	 * @return workers
	 */
	public static ArrayList<Worker> getWorkers() {
		return workers;
	}

	/**
	 * Sets workers.
	 * 
	 * @param w
	 */
	public static void setWorkers(ArrayList<Worker> w) {
		workers = w;
	}

	/**
	 * Returns the value of the field called 'days'.
	 * 
	 * @return Returns the days.
	 */
	public static ArrayList<Day> getDays() {
		return days;
	}

	/**
	 * Sets the field called 'days' to the given value.
	 * 
	 * @param d
	 */
	public static void setDays(ArrayList<Day> d) {
		days = d;
	}
	
	/**
	 * Dumps data to the file schedule_data.ser.
	 *
	 */
	public static void dumpConfigFile(){
		
		try {
			path.delete();
			path.createNewFile();
			FileOutputStream dumpConfig = new FileOutputStream(path);
			ObjectOutputStream fileStore = new ObjectOutputStream(dumpConfig);
			fileStore.writeObject(days);
			fileStore.writeObject(workers);
			fileStore.writeObject(schedule);
			fileStore.writeObject(HTMLGenerator.getTables());
			fileStore.close();
			dumpConfig.close();
			
			System.out.println("Stored");

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Recalls data from schedule_data.ser.
	 *
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void recallConfigFile() throws ClassNotFoundException, IOException{
		if(path.exists()) {
			FileInputStream recallConfig = new FileInputStream(path);
			ObjectInputStream fileRecall = new ObjectInputStream(recallConfig);
			days = (ArrayList<Day>) fileRecall.readObject();
			workers = (ArrayList<Worker>) fileRecall.readObject();
			schedule = (Schedule) fileRecall.readObject();
			HTMLGenerator.setTables((String)fileRecall.readObject());
			
			fileRecall.close();
			recallConfig.close();
		}
	}
}

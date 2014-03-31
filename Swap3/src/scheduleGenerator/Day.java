package scheduleGenerator;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Day is used to store jobs for a given day.
 * 
 * @author schneimd. Created Oct 15, 2012.
 */
public class Day implements Serializable, Comparable<Day> {

	// SWAP 1 TEAM 1
	// SMELL: Data Class
	// Class consists of two fields - dayOfWeek and jobs and only has one method
	// that is not a getter or a setter for the two fields.
	// A Possible solution would be to pull some of the functionality for days
	// being used in other classes into the Day class.
	// For example, within the Config class, each day is checked to see if there
	// is a job assigned to the day.
	// This check can be done from the day class.This would make it easier to
	// make enhancements to Days for the applications because all of the
	// functionality for them would be in one place-its corresponding class.
	
	/* ######################################################################
	 * SWAP 2, TEAM 2
	 * REFACTORING #3, FOR ENHANCEMENT FROM DATA CLASS
	 * I assert that this class was not useless because it only contained
	 * data, but rather that the data it did contain was the wrong data. To
	 * address both problems, the string representing the day of week was
	 * removed to make way for a Date object that represents fully the
	 * calendar date that the object as a whole is representing.
	 * 
	 * This simple change allows for many improvements all over the code.
	 * For one, I was able to implement the Comparable interface (passing the
	 * comparison onto the underlying dates) which allows for sorting of the
	 * dates in natural order. This can make it WORLDS easier to display or
	 * output the data in a reasonable way since you don't have to worry
	 * about a thousand string comparisons or converting strings to dates.
	 * 
	 * It also allows for the simplification of the schedule class, which
	 * would benefit from being able to directly compare the dates.
	 * ######################################################################
	 */

	private Date date;
	private ArrayList<String> jobs = new ArrayList<String>();;

	/**
	 * Construct a day with a name and jobs.
	 * 
	 * @param name
	 * 
	 * @param jobs
	 */
	// SWAP 1 TEAM 1
	// SMELL: Shotgun Surgery
	// Changing the Day class constructor has a cascading effect and would
	// require changes to also be made in the Config class and WorkerSetup class
	// in the methods that call it.
	// A possible solution to this would be to change the methods in the other
	// classes that it is called in in such a way that they do not depend on
	// this particular constructor.
	// Fixing this smell would allow for enhancements to be made to the Day
	// class without having to worry about breaking any other class in the
	// process.
	//
	/* ######################################################################
	 * SWAP 2, TEAM 2
	 * While this is technically true, that is really not possible to avoid.
	 * That is the nature of constructors, after all.
	 * 
	 * This constructor was changed internally so that the class can still
	 * accept a string, negating the need to perform the shotgun surgery. It
	 * is, however, not ideal and so future refactorings should strive to
	 * remove references to this particular constructor.
	 * ######################################################################
	 */
	@Deprecated
	public Day(String name, ArrayList<Object> jobs) {
		try {
			this.date = new SimpleDateFormat("EEE").parse(name);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Object i : jobs) {
			this.jobs.add((String) i);
		}
	}
	
	public Day(Date day, ArrayList<String> jobs) {
		this.date = day;
		this.jobs = jobs;
	}
	
	public Day(Date day) {
		this.date = day;
	}

	/**
	 * Add one jobName.
	 * 
	 * @param jobName
	 */
	public void addJob(String jobName) {
		this.jobs.add(jobName);
	}

	/**
	 * Set jobs to new jobs.
	 * 
	 * @param jobNames
	 */
	public void setJobs(ArrayList<String> jobNames) {
		this.jobs = jobNames;
	}

	/**
	 * return current jobs.
	 * 
	 * @return jobs
	 */
	public ArrayList<String> getJobs() {
		return this.jobs;
	}
	
	public boolean hasJobs() {
		return !this.jobs.isEmpty();
	}

	/**
	 * Gives the name of this day.
	 * 
	 * @return day of week
	 */
	public String getNameOfDay() {
		return new SimpleDateFormat("EEEE").format(this.date);
	}
	
	public Date getDate() {
		return this.date;
	}

	@Override
	public int compareTo(Day d) {
		return this.date.compareTo(d.getDate());
	}
}

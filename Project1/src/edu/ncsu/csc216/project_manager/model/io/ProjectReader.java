/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.project_manager.model.manager.Project;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Class which reads input files and places projects and user stories into
 * different objects which holds the information
 * 
 * @author Zachary Snowdon
 *
 */
public class ProjectReader {

	/**
	 * String to represent the words for the rejected state
	 */
	private static String rej = "Rejected";

	/**
	 * String to represent the words for the Submitted state
	 */
	private static String sub = "Submitted";

	/**
	 * String to represent the words for the Backlog state
	 */
	private static String bac = "Backlog";

	/**
	 * Reads a project file with a given filename and creates a string with the
	 * project information
	 * 
	 * @param fileName The name of the requested input file with the list of
	 *                 projects
	 * @return projects The ArrayList which holds each project and its contents
	 * @throws FileNotFoundException for the FileInputStream
	 */
	public static ArrayList<Project> readProjectFile(String fileName) {
		Scanner fileReader;
		ArrayList<Project> projects = null;
		Scanner projectRead = null;
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
			projects = new ArrayList<Project>();
			String fullFile = null;
			while (fileReader.hasNextLine()) {
				String addLine = fileReader.nextLine();
				if (fullFile == null) {
					fullFile = addLine;
				} else if (fullFile != null) {
					fullFile = fullFile + "\n" + addLine;
				}
			}
			projectRead = new Scanner(fullFile);
			String project1 = null;
			Scanner newRead = new Scanner(fullFile);
			String firstLine = newRead.nextLine();
			String firstChar = firstLine.substring(0, 1);
			newRead.close();
			projectRead.useDelimiter("\\r?\\n?[#]");
			if ("#".equals(firstChar)) {
				while (projectRead.hasNext()) {
					project1 = projectRead.next();
					projects.add(processProject(project1));
				}
			} else {
				projectRead.close();
			}
			fileReader.close();
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("Unable to load file.");
		} catch (IllegalArgumentException e) {
			projectRead.close();
		}
		return projects;
	}

	/**
	 * Processes each individual project and creates a project object for each one
	 * 
	 * @param fullProject The name of an individual project returned from
	 *                    readProjectFile
	 * @return Project The object which creates an object for each individual
	 *         project.
	 */
	private static Project processProject(String fullProject) {
		Project c;
		Scanner lineReader = new Scanner(fullProject);
		boolean gotName = false;
		String projectName = null;
		String storyData = "";
		String newLine = "";

		while (!gotName) {
			projectName = lineReader.next();
			gotName = true;
		}
		if (gotName) {
			while (lineReader.hasNextLine()) {
				newLine = lineReader.nextLine();
				if (storyData.length() != 0) {
					storyData = storyData + "\n" + newLine;
				}
				if (storyData.length() == 0) {
					storyData = newLine;
				}
			}
		}
		String individualStory = null;
		Scanner storyReader = new Scanner(storyData);
		storyReader.useDelimiter("\\r?\\n?[*]");
		c = new Project(projectName);
		try {
			while (storyReader.hasNext()) {
				individualStory = storyReader.next();
				UserStory f = processUserStory(individualStory);
				c.addUserStory(f);
			}
			storyReader.close();
			lineReader.close();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return c;
	}

	/**
	 * Takes an individual story and creates a UserStory object
	 * 
	 * @param fullUserStory The name of an individual user story returned from
	 *                      process Project
	 * @return UserStory The object created which holds the information about the
	 *         user story
	 */
	@SuppressWarnings("resource")
	private static UserStory processUserStory(String fullUserStory) {
		UserStory c;
		int storyId = 0;
		String state = null;
		String title = null;
		String user = null;
		String action = null;
		String value = null;
		String priority = null;
		String developerId = null;
		String rejectionReason = null;
		String firstLine = null;
		boolean gotFirstLine = false;
		if (!gotFirstLine) {
			Scanner readStory = new Scanner(fullUserStory);
			firstLine = readStory.nextLine().trim();
			gotFirstLine = true;
			Scanner readFirst = new Scanner(firstLine);
			readFirst.useDelimiter(",");
			try {
				try {
					storyId = readFirst.nextInt();
				} catch (InputMismatchException e) {
					throw new IllegalArgumentException();
				}
				state = readFirst.next();
				if (rej.equals(state)) {
					title = readFirst.next();
					rejectionReason = readFirst.next();
					if (readFirst.hasNext() || priority != null) {
						throw new IllegalArgumentException();
					}
					boolean skipLine = false;
					if ("Inappropriate".equals(rejectionReason) || "Duplicate".equals(rejectionReason)
							|| "Infeasible".equals(rejectionReason)) {
						skipLine = true;
					}
					if (!skipLine) {
						throw new IllegalArgumentException();
					}
				} else if (sub.equals(state)) {
					title = readFirst.next();
					if (readFirst.hasNext()) {
						throw new IllegalArgumentException();
					}
				} else if (bac.equals(state)) {
					title = readFirst.next();
					priority = readFirst.next();
					if (readFirst.hasNext()) {
						throw new IllegalArgumentException();
					}
					boolean notRights = false;
					if (readFirst.hasNext() || "High".equals(priority) || "Medium".equals(priority)
							|| "Low".equals(priority)) {
						notRights = true;
					}
					if (!notRights) {
						throw new IllegalArgumentException();
					}
				} else if (!rej.equals(state) || !sub.equals(state) || !bac.equals(state)) {
					title = readFirst.next();
					priority = readFirst.next();
					developerId = readFirst.next();
					if (readFirst.hasNext()) {
						throw new IllegalArgumentException();
					}
					boolean notRight = false;
					if (readFirst.hasNext() || "High".equals(priority) || "Medium".equals(priority)
							|| "Low".equals(priority)) {
						notRight = true;
					}
					if (!notRight) {
						throw new IllegalArgumentException();
					}
				}
				readStory.close();
				readFirst.close();
			} catch (NoSuchElementException e) {
				throw new IllegalArgumentException();
			}
			readStory.close();
		}
		Scanner restText = new Scanner(fullUserStory);
		restText.useDelimiter("\\r?\\n?[-]");
		restText.next();
		try {
			user = restText.next().trim();
			action = restText.next().trim();
			value = restText.next().trim();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		if (user.length() == 0 || action.length() == 0 || value.length() == 0 || restText.hasNext()) {
			throw new IllegalArgumentException();
		}
		restText.close();
		c = new UserStory(storyId, state, title, user, action, value, priority, developerId, rejectionReason);
		return c;
	}

}
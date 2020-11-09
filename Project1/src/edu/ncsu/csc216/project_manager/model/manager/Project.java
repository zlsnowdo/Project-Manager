/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.project_manager.model.command.Command;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Class which creates and edits all project objects
 * 
 * @author Zachary Snowdon
 *
 */
public class Project {

	/**
	 * String which holds the initial projectName for every project
	 */
	private String projectName = null;

	/**
	 * Initialization for an ArrayList of UserStories
	 */
	private ArrayList<UserStory> stories;

	/**
	 * Sends the projectName to project reader to get each projects info and uses
	 * Project() to create a project object
	 * 
	 * @param name The name of the project to be created into an object
	 */
	public Project(String name) {
		setProjectName(name);
		stories = new ArrayList<UserStory>();
	}

	/**
	 * Sets the counter for the UserStory instances to the value of the maximum Id
	 * in the list of UserStorys for the project +1
	 */
	public void setUserStoryId() {
		int maxId = 0;
		for (int i = 0; i < stories.size(); i++) {
			UserStory c1 = stories.get(i);
			int newId = c1.getId();
			if (newId > 0) {
				maxId = newId;
			}
		}
		maxId = maxId + 1;
		UserStory.setCounter(maxId);
	}

	/**
	 * Method to change the current project name and set it to the String newName
	 * 
	 * @param newName The name that is going to be set as the projects new name
	 */
	private void setProjectName(String newName) {
		if (newName == null || newName.length() == 0) {
			throw new IllegalArgumentException("Invalid project name.");
		} else {
			this.projectName = newName;
		}
	}

	/**
	 * Method to get the current name of the project in the project object
	 * 
	 * @return String The string of the current project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Method which uses the information held in each project and creates a user
	 * story object to hold the title, user, action, and value
	 * 
	 * @param title  The title of the individual user story
	 * @param user   The user of the individual user story
	 * @param action The action that each user story has to do
	 * @param value  The value of the individual user story
	 * @return int The Id which says where the user story is in the list
	 */
	public int addUserStory(String title, String user, String action, String value) {
		setUserStoryId();
		int maxId = 0;
		UserStory c = null;
		for (int i = 0; i < stories.size(); i++) {
			UserStory c1 = stories.get(i);
			int newId = c1.getId();
			if (newId > 0) {
				maxId = newId;
			}
		}
		if (stories.size() == 0) {
			c = new UserStory(maxId, "Submitted", title, user, action, value, null, null, null);
		} else if (stories.size() != 0) {
			c = new UserStory(maxId + 1, "Submitted", title, user, action, value, null, null, null);
			maxId++;
		}
		stories.add(c);
		return maxId;
	}

	/**
	 * Adds the user story object to the list in sorted order by Id.
	 * 
	 * @param story The user story object which is to be added to the list
	 */
	public void addUserStory(UserStory story) {
		int storyId = story.getId();
		if(stories.size() > 1) {
			int placementCounter = 0;
			for(int i = 0; i < stories.size(); i++) {
				UserStory getMax = stories.get(i);
				int testId = getMax.getId();
				if(storyId > testId) {
					placementCounter++;
				}
			}
			stories.add(placementCounter, story);
		} else if(stories.size() == 1) {
			UserStory newStory = stories.get(0);
			int newId = newStory.getId();
			if(newId == storyId) {
				throw new IllegalArgumentException("Invalid story Id.");
			}
			stories.add(story);
		} else if (stories.size() == 0) {
			stories.add(story);
		}
	}

	/**
	 * Gets a full list of UserStorys and places it into a list that can be accessed
	 * 
	 * @return stories The list that contains all the UserStorys
	 */
	public ArrayList<UserStory> getUserStories() {
		return stories;
	}

	/**
	 * Uses the Id location to return a specific UserStory object
	 * 
	 * @param id The Id which tells the location of the user story in the list.
	 * @return UserStory The UserStory at the location of the Id
	 */
	public UserStory getUserStoryById(int id) {
		UserStory returnStory = null;
		boolean none = false;
		for (int i = 0; i < stories.size(); i++) {
			UserStory c1 = stories.get(i);
			int newId = c1.getId();
			if (newId == id) {
				returnStory = c1;
				none = true;
			}
		}
		if (!none) {
			returnStory = null;
		}
		return returnStory;
	}

	/**
	 * Finds the UserStory with the given id and update it by passing in the given
	 * Command
	 * 
	 * @param id The id of the UserStory to help locate a specific user story
	 * @param c  The command that is to be acted on to the UserStory
	 */
	public void executeCommand(int id, Command c) {
		for (int i = 0; i < stories.size(); i++) {
			UserStory c1 = stories.get(i);
			int newId = c1.getId();
			if (newId == id) {
				c1.update(c);
			}
		}
	}

	/**
	 * Uses the id to locate and delete a specific UserStory
	 * 
	 * @param id The id of the UserStory to help locate a specific user story
	 */
	public void deleteUserStoryById(int id) {
		for (int i = 0; i < stories.size(); i++) {
			UserStory c1 = stories.get(i);
			int newId = c1.getId();
			if (newId == id) {
				stories.remove(i);
			}
		}
	}

}

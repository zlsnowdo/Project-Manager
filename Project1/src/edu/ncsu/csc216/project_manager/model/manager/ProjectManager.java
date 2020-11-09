/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.project_manager.model.command.Command;
import edu.ncsu.csc216.project_manager.model.io.ProjectReader;
import edu.ncsu.csc216.project_manager.model.io.ProjectWriter;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Class which creates and stores all project and information in them
 * 
 * @author Zachary Snowdon
 *
 */
public class ProjectManager {

	/**
	 * Project object which represents the current project being assessed
	 */
	private Project currentProject;

	/**
	 * ArrayList which holds all of the project objects in the program
	 */
	private ArrayList<Project> projects;

	/**
	 * Singleton instance to show ProjectManager is only used once
	 */
	public static ProjectManager singleton = null;

	private ProjectManager() {
		projects = new ArrayList<Project>();
		currentProject = null;
	}

	/**
	 * Creates and checks singleton for the ProjectManager class
	 * 
	 * @return ProjectManager
	 */
	public static ProjectManager getInstance() {
		if (singleton == null) {
			singleton = new ProjectManager();
		}
		return singleton;
	}

	/**
	 * Uses the ProjectReader to read the given fileName and print the list of
	 * Projects into the specific text file
	 * 
	 * @param fileName The name of the file where the projects will be stored
	 */
	public void saveCurrentProjectToFile(String fileName) {
		if (currentProject == null || currentProject.getUserStories().size() == 0) {
			throw new IllegalArgumentException("Invalid project.");
		} else {
			ProjectWriter.writeProjectToFile(fileName, currentProject);
		}

	}

	/**
	 * Uses the ProjectReader to read the given fileName, The returned list of
	 * Projects are added to the projects field
	 * 
	 * @param fileName The name of the input file which holds the projects and user
	 *                 stories
	 */
	public void loadProjectsFromFile(String fileName) {
		try {
			projects = ProjectReader.readProjectFile(fileName);
			String[] loadedProjects = singleton.getProjectList();
			loadProject(loadedProjects[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a new Project with the given name and adds it to the end of the
	 * projects list
	 * 
	 * @param projectName The name of the project to be added to the list
	 */
	public void createNewProject(String projectName) {
		Project c = new Project(projectName);
		projects.add(c);
		loadProject(projectName);
	}

	/**
	 * Reads through each user story and places its information into a 3D string
	 * array
	 * 
	 * @return String[][] 3D array which holds the location and information for each
	 *         user story
	 */
	public String[][] getUserStoriesAsArray() {
		String[][] storyData = null;
		if (currentProject != null) {
			ArrayList<UserStory> stories = currentProject.getUserStories();
			storyData = new String[stories.size()][4];
			for (int i = 0; i < stories.size(); i++) {
				UserStory c = stories.get(i);
				int storyId = c.getId();
				storyData[i][0] = Integer.toString(storyId);
				storyData[i][1] = c.getState();
				storyData[i][2] = c.getTitle();
				String devId = c.getDeveloperId();
				if (devId == null) {
					storyData[i][3] = "";
				} else if (devId != null) {
					storyData[i][3] = devId;
				}
			}
		}
		return storyData;
	}

	/**
	 * Uses the int id to locate and return a specific UserStory
	 * 
	 * @param id The specific location of the requested UserStory
	 * @return UserStory The UserStory that is at the location of the given id
	 */
	public UserStory getUserStoryById(int id) {
		UserStory c = null;
		if (currentProject != null) {
			c = currentProject.getUserStoryById(id);
		}
		return c;
	}

	/**
	 * Finds and locates a specific UserStory then act a specific command on it
	 * 
	 * @param id The location of a specific UserStory
	 * @param c  A specific command to be acted on the UserStory
	 */
	public void executeCommand(int id, Command c) {
		if (currentProject != null) {
			currentProject.executeCommand(id, c);
		}
	}

	/**
	 * Finds and locates a specific UserStory then deletes it from the UserStory
	 * list
	 * 
	 * @param id The location of a specific UserStory
	 */
	public void deleteUserStoryById(int id) {
		if (currentProject != null) {
			currentProject.deleteUserStoryById(id);
		}
	}

	/**
	 * Uses the input information to create a UserStory and place it in a Project
	 * object
	 * 
	 * @param title  The title of the UserStory to be added to the project
	 * @param user   The user of the UserStory to be added to the project
	 * @param action The action of the UserStory to be added to the project
	 * @param value  The id value of the UserStory to be added to the project
	 */
	public void addUserStoryToProject(String title, String user, String action, String value) {
		if (currentProject != null) {
			currentProject.addUserStory(title, user, action, value);
		}
	}

	/**
	 * Find the Project with the given name in the list, makes it the active or
	 * currentProject, and sets the user story id for that project so that any new
	 * UserStorys added to the project are created with the next correct id.
	 * 
	 * @param projectName The name of the project to be loaded
	 */
	public void loadProject(String projectName) {
		for (int i = 0; i < projects.size(); i++) {
			Project c = projects.get(i);
			String projName = c.getProjectName();
			if (projName.equals(projectName)) {
				currentProject = c;
			}
		}
	}

	/**
	 * Returns the project name for the currentProject
	 * 
	 * @return String The name of the currentProject
	 */
	public String getProjectName() {
		String projectName = null;
		if (currentProject != null) {
			Project c = currentProject;
			projectName = c.getProjectName();
		}
		return projectName;
	}

	/**
	 * Returns a String array of project names in the order they are listed in the
	 * projects list
	 * 
	 * @return String[] A String array which holds all of the projects names
	 */
	public String[] getProjectList() {
		String[] projectList = null;
		if (projects != null) {
			projectList = new String[projects.size()];
			for (int i = 0; i < projects.size(); i++) {
				Project c = projects.get(i);
				String projectName = c.getProjectName();
				projectList[i] = projectName;
			}
		}
		return projectList;
	}

	/**
	 * Clears out the projects list and all data in Projects
	 */
	public void resetProjectManager() {
		singleton = null;
	}

}

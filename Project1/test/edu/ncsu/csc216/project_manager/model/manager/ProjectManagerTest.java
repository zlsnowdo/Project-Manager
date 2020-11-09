/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.command.Command;
import edu.ncsu.csc216.project_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Tests for ProjectManager
 * 
 * @author Zachary Snowdon
 *
 */
public class ProjectManagerTest {

	/** Course name */
	private static final String TITLE = "Load Catalog";

	/** Course title */
	private static final String USER = "student";

	/** Course section */
	private static final String ACTION = "Load a course catalog";

	/** User story value */
	private static final String VALUE = "Plan a schedule for next semester";

	/**
	 * Tests the method getUserStoriesAsArray
	 */
	@Test
	public void testCreateNewProject() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.createNewProject("WolfScheduler");
		String[] projectNames = singleton.getProjectList();
		assertEquals(1, projectNames.length);
		assertEquals("WolfScheduler", projectNames[0]);
		singleton.createNewProject("PackScheduler");
		projectNames = singleton.getProjectList();
		assertEquals(2, projectNames.length);
		assertEquals("PackScheduler", projectNames[1]);
	}

	/**
	 * Tests the loadProject function
	 */
	@Test
	public void testLoadProject() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.resetProjectManager();
		singleton.createNewProject("WolfScheduler");
		singleton.createNewProject("PackScheduler");
		singleton.loadProject("PackScheduler");
		assertEquals("PackScheduler", singleton.getProjectName());
	}

	/**
	 * Tests the addUserStoryToProject function
	 */
	@Test
	public void testAddUserStoryToProject() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.resetProjectManager();
		singleton.createNewProject("WolfScheduler");
		singleton.createNewProject("PackScheduler");
		singleton.loadProject("PackScheduler");
		singleton.addUserStoryToProject(TITLE, USER, ACTION, VALUE);
		UserStory c = singleton.getUserStoryById(0);
		assertEquals("Submitted", c.getState());
	}

	/**
	 * Tests the executeCommand function
	 */
	@Test
	public void testExecuteCommand() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.resetProjectManager();
		singleton.createNewProject("WolfScheduler");
		singleton.createNewProject("PackScheduler");
		singleton.loadProject("PackScheduler");
		singleton.addUserStoryToProject(TITLE, USER, ACTION, VALUE);
		Command c = new Command(CommandValue.BACKLOG, "High");
		singleton.executeCommand(0, c);
		UserStory c1 = singleton.getUserStoryById(0);
		assertEquals("Backlog", c1.getState());
	}

	/**
	 * Tests the getUserStoriesAsArray function
	 */
	@Test
	public void testGetUserStoriesAsArray() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.resetProjectManager();
		singleton.createNewProject("WolfScheduler");
		singleton.createNewProject("PackScheduler");
		singleton.loadProject("PackScheduler");
		singleton.addUserStoryToProject(TITLE, USER, ACTION, VALUE);
		String[][] projectData = singleton.getUserStoriesAsArray();
		assertEquals("Load Catalog", projectData[0][2]);
		assertEquals("0", projectData[0][0]);
		singleton.addUserStoryToProject(TITLE, "New user", "New action", VALUE);
		projectData = singleton.getUserStoriesAsArray();
		assertEquals("Submitted", projectData[1][1]);
		assertEquals("", projectData[1][3]);
	}
	
	/**
	 * Tests the deleteUserStoryById function
	 */
	@Test
	public void testDeleteUserStoryById() {
		ProjectManager singleton = ProjectManager.getInstance();
		singleton.resetProjectManager();
		singleton.createNewProject("WolfScheduler");
		singleton.createNewProject("PackScheduler");
		singleton.loadProject("PackScheduler");
		singleton.addUserStoryToProject(TITLE, USER, ACTION, VALUE);
		singleton.addUserStoryToProject("New Title", "New user", "New action", VALUE);
		String[][] projectData = singleton.getUserStoriesAsArray();
		assertEquals("Load Catalog", projectData[0][2]);
		assertEquals("0", projectData[0][0]);
		singleton.deleteUserStoryById(0);
		projectData = singleton.getUserStoriesAsArray();
		assertEquals("New Title", projectData[0][2]);
		assertEquals("Submitted", projectData[0][1]);
	}

}

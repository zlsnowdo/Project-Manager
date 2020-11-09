/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Test for the Project class
 * @author Zachary Snowdon
 *
 */
public class ProjectTest {

	/** The start StoryId value which is the location of the UserStory */
	private static final int STORYID = 5;
	
	/** String which represents which state the UserStory is in */
	private static final String STATE = "Completed";

	/** Course name */
    private static final String TITLE = "Load Catalog";
    
    /** Course title */
    private static final String USER = "student";
    
    /** Course section */
    private static final String ACTION = "Load a course catalog";
    
    /** User story value */
    private static final String VALUE = "Plan a schedule for next semester";
    
    /** String which represents which level of priority the UserStory has */
    private static final String PRIORITY = "High";
    
    /** String which represents which developer is working on the UserStory */
    private static final String DEVELOPERID = "sesmith5";
    
    /** String which represents what the rejection reason for the UserStory is */
    private static final String REJECTIONREASON = null;
    
	/**
	 * Tests adding a UserStory to the project by entering information
	 */
	@Test
	public void testAddUserStoryWithData() {
		Project c = new Project("WolfScheduler");
		ArrayList<UserStory> stories = new ArrayList<UserStory>();
		assertEquals(0, stories.size());
		c.addUserStory(TITLE, USER, ACTION, VALUE);
		UserStory c1 = c.getUserStoryById(0);
		assertEquals(c1, c.getUserStoryById(0));
		assertEquals("Submitted", c1.getState());
		c.addUserStory("New Title", "New User", "New Action", "New Value");
		UserStory c2 = c.getUserStoryById(1);
		assertEquals("New Title", c2.getTitle());
	}
	
	/**
	 * Tests adding a UserStory to the project by entering a UserStory object
	 */
	@Test
	public void testAddUserStoryWithObject() {
		Project c = new Project("WolfScheduler");
		ArrayList<UserStory> stories = new ArrayList<UserStory>();
		assertEquals(0, stories.size());
		UserStory c1 = new UserStory(STORYID, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		c.addUserStory(c1);
		UserStory c2 = c.getUserStoryById(5);
		assertEquals(c1.getId(), c2.getId());
		c2 = new UserStory(1, "Verifying", "Select Course", "student", "select a course to add to my schedule",
				"plan a schedule for next semester", "Medium", "jctetter", null);
		c.addUserStory(c2);
		stories = c.getUserStories();
		assertEquals(2, stories.size());
		c1 = stories.get(1);
		assertEquals(c2.getPriority(), c1.getPriority());
	}
	
	/**
	 * Tests deleting a UserStory from a project
	 */
	@Test
	public void testDeleteUserStoryById() {
		Project c = new Project("WolfScheduler");
		ArrayList<UserStory> stories = new ArrayList<UserStory>();
		UserStory c1 = new UserStory(0, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		c.addUserStory(c1);
		UserStory c2 = new UserStory(1, "Verifying", "Select Course", "student", "select a course to add to my schedule",
				"plan a schedule for next semester", "Medium", "jctetter", null);
		c.addUserStory(c2);
		stories = c.getUserStories();
		assertEquals(2, stories.size());
		c.deleteUserStoryById(1);
		assertEquals(1, stories.size());
		stories = c.getUserStories();
		c1 = stories.get(0);
		String title = "Load Catalog";
		assertEquals(title, c1.getTitle());
	}
	
	
	
	
	
	
	
	
	
	
}
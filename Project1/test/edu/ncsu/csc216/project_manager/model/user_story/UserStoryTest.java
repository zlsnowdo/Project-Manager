/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.user_story;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.command.Command;
import edu.ncsu.csc216.project_manager.model.command.Command.CommandValue;

/**
 * Tests for UserStory
 * @author Zachary Snowdon
 *
 */
public class UserStoryTest {

	/** The start StoryId value which is the location of the UserStory */
	private static final int STORYID = 0;
	
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
	 * Tests the method which creates a UserStory with all of the possible information
	 */
	@Test
	public void testCreateUserStoryLarge() {
		UserStory c = null;
		try {
			c = new UserStory(STORYID, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
			assertEquals(STORYID, c.getId());
			assertEquals(STATE, c.getState());
			assertEquals(TITLE, c.getTitle());
			assertEquals(USER, c.getUser());
			assertEquals(ACTION, c.getAction());
			assertEquals(VALUE, c.getValue());
			assertEquals(PRIORITY, c.getPriority());
			assertEquals(DEVELOPERID, c.getDeveloperId());
			assertEquals(REJECTIONREASON, c.getRejectionReason());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests the method which creates a UserStory with a smaller amount of information
	 */
	@Test
	public void testCreateUserStorySmall() {
		UserStory c =  null;
		try {
			c = new UserStory(TITLE, USER, ACTION, VALUE);
			assertEquals(0, c.getId());
			assertEquals(TITLE, c.getTitle());
			assertEquals(USER, c.getUser());
			assertEquals(ACTION, c.getAction());
			assertEquals(VALUE, c.getValue());
			assertEquals("Submitted", c.getState());
			assertEquals(null, c.getRejectionReason());
			assertEquals(null, c.getPriority());
			assertEquals(null, c.getDeveloperId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			c = new UserStory(TITLE, USER, ACTION, null);
			fail();
		} catch (IllegalArgumentException e) {
			// Ignore because the test is supposed to catch the exception
		}
	}
	
	/**
	 * Tests invalid UserStorys
	 */
	@Test
	public void testCreateInvalidUserStory() {

		try {
			UserStory c = new UserStory(null, USER, ACTION, VALUE);
			c.getTitle();
			fail();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Method which tests the toString method to ensure it prints the correct string
	 */
	@Test
	public void testToString() {
		/* Testing a UserStory without a rejection reason */
		UserStory c = new UserStory(STORYID, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		String line1 = "* 0,Completed,Load Catalog,High,sesmith5,";
		String line2 = "- student";
		String line3 = "- Load a course catalog";
		String line4 = "- Plan a schedule for next semester";
		String output = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
		assertEquals(output, c.toString());
		
		/* Testing a UserStory in a rejected state */
		c = new UserStory(1, "Rejected", TITLE, USER, ACTION, VALUE, null, null, "Duplicate");
		line1 = "* 1,Rejected,Load Catalog,Duplicate";
		line2 = "- student";
		line3 = "- Load a course catalog";
		line4 = "- Plan a schedule for next semester";
        output = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
		assertEquals(output, c.toString());

		/* Testing a UserStory in a backlog state */
		c = new UserStory(2, "Backlog", TITLE, USER, ACTION, VALUE, PRIORITY, null, null);
		line1 = "* 2,Backlog,Load Catalog,High,";
		line2 = "- student";
		line3 = "- Load a course catalog";
		line4 = "- Plan a schedule for next semester";
		output = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
		assertEquals(output, c.toString());
		
		/* Testing a UserStory in a submitted state */
		c = new UserStory(TITLE, USER, ACTION, VALUE);
		line1 = "* 3,Submitted,Load Catalog,";
		line2 = "- student";
		line3 = "- Load a course catalog";
		line4 = "- Plan a schedule for next semester";
		output = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
		assertEquals(output, c.toString());
	}
	
	/**
	 * Method which tests the update state in the completed method with an invalid command update
	 */
	@Test
	public void testUpdateStateCompletedFail() {
		try {
			UserStory c = new UserStory(STORYID, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
			Command command = new Command(CommandValue.CONFIRM, null);
			assertEquals("Completed", c.getState());
			c.update(command);
			fail();
		} catch (UnsupportedOperationException e) {
			//Ignore because the test is supposed to catch the exception
		}

	}
	
	/**
	 * Method which tests the Update method in the Completed state with a correct command update
	 */
	@Test
	public void testUpdateStateCompleted() {
		UserStory c = new UserStory(STORYID, STATE, TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		Command command = new Command(CommandValue.REOPEN, null);
		assertEquals("Completed", c.getState());
		c.update(command);
		assertEquals("Working", c.getState());
	}
	
	/**
	 * Method which tests the update state method for the backlog state with a correct command update
	 */
	@Test
	public void testUpdateStateBacklog() {
		UserStory c = new UserStory(STORYID, "Backlog", TITLE, USER, ACTION, VALUE, PRIORITY, null, null);
		Command command = new Command(CommandValue.REJECT, "rejected");
		assertEquals("Backlog", c.getState());
		c.update(command);
		assertEquals("Rejected", c.getState());
		
		UserStory c1 = new UserStory(STORYID, "Backlog", TITLE, USER, ACTION, VALUE, PRIORITY, null, null);
		Command commands = new Command(CommandValue.ASSIGN, "zlsnowdo");
		assertEquals("Backlog", c1.getState());
		c1.update(commands);
		assertEquals("Working", c1.getState());
	}
	
	/**
	 * Method which tests the Update method in the Submitted state with a correct command update
	 */
	@Test
	public void testUpdateStateSubmitted() {
		UserStory c = new UserStory(STORYID, "Submitted", TITLE, USER, ACTION, VALUE, null, null, null);
		Command command = new Command(CommandValue.BACKLOG, "back up");
		assertEquals("Submitted", c.getState());
		c.update(command);
		assertEquals("Backlog", c.getState());
		
		UserStory c1 = new UserStory(STORYID, "Submitted", TITLE, USER, ACTION, VALUE, null, null, null);
		Command commands = new Command(CommandValue.REJECT, "Duplicate");
		assertEquals("Submitted", c1.getState());
		c1.update(commands);
		assertEquals("Rejected", c1.getState());
	}
	
	/**
	 * Method which tests the update method in the Working state with a correct command update
	 */
	@Test
	public void testUpdateWorkingState() {
		UserStory c = new UserStory(STORYID, "Working", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, null);
		Command command = new Command(CommandValue.REVIEW, null);
		assertEquals("Working", c.getState());
		c.update(command);
		assertEquals("Verifying", c.getState());
		
		UserStory c1 = new UserStory(STORYID, "Working", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, null);
		Command commands = new Command(CommandValue.REJECT, "Inappropriate");
		assertEquals("Working", c1.getState());
		c1.update(commands);
		assertEquals("Rejected", c1.getState());
		
		UserStory c2 = new UserStory(STORYID, "Working", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, null);
		Command commands1 = new Command(CommandValue.ASSIGN, "zlsnowdo");
		assertEquals("Working", c2.getState());
		c2.update(commands1);
		assertEquals("Working", c2.getState());
		
		UserStory c3 = new UserStory(STORYID, "Working", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, null);
		Command commands2 = new Command(CommandValue.REOPEN, null);
		assertEquals("Working", c3.getState());
		c3.update(commands2);
		assertEquals("Backlog", c3.getState());
	}
	
	/**
	 * Method which tests the update method in the Verifying state with a correct command update.
	 */
	@Test
	public void testUpdateVerifyingState() {
		UserStory c = new UserStory(STORYID, "Verifying", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		Command command = new Command(CommandValue.CONFIRM, null);
		assertEquals("Verifying", c.getState());
		c.update(command);
		assertEquals("Completed", c.getState());
		
		UserStory c1 = new UserStory(STORYID, "Verifying", TITLE, USER, ACTION, VALUE, PRIORITY, DEVELOPERID, REJECTIONREASON);
		Command commands = new Command(CommandValue.REOPEN, null);
		assertEquals("Verifying", c1.getState());
		c1.update(commands);
		assertEquals("Working", c1.getState());
	}
	
	/**
	 * Method which tests the update method in the Rejected state with a correct command update
	 */
	@Test
	public void testUpdateRejectedState() {
		UserStory c = new UserStory(STORYID, "Rejected", TITLE, USER, ACTION, VALUE, null, null, "Duplicate");
		Command command = new Command(CommandValue.RESUBMIT, null);
		assertEquals("Rejected", c.getState());
		c.update(command);
		assertEquals("Submitted", c.getState());
	}
	
	/**
	 * Resets the UserStory counter every time
	 * @throws Exception for if the counter cannot be reset
	 */
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		UserStory.setCounter(0);
	}

}

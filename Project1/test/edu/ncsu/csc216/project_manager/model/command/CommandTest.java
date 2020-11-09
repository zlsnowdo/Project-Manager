package edu.ncsu.csc216.project_manager.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.command.Command.CommandValue;

/**
 * Tests for Command
 * 
 * @author Zachary Snowdon
 *
 */
public class CommandTest {

	/**
	 * Method which tests an invalid null command input
	 */
	@Test
	public void testInvalidCommand() {
		try {
			Command c = new Command(null, "Type");
			assertEquals(null, c.getCommand());
			fail("Invalid command.");
		} catch (IllegalArgumentException e) {
			// Ignore because test is supposed to be caught
		}
	}

	/**
	 * Method with tests an invalid command object with no command information
	 */
	@Test
	public void testInvalidCommandNoInfo() {
		try {
			Command c = new Command(CommandValue.ASSIGN, null);
			assertEquals(null, c.getCommandInformation());
			fail("Commands require additional information.");
		} catch (IllegalArgumentException e) {
			// Ignore because test is supposed to be caught
		}
	}

	/**
	 * Method which tests an invalid command with command information that is not
	 * needed
	 */
	@Test
	public void testInvalidCommandInvalidInfo() {
		try {
			Command c = new Command(CommandValue.REVIEW, "");
			assertEquals(CommandValue.REVIEW, c.getCommand());
			fail("Commands do not require additional information.");
		} catch (IllegalArgumentException e) {
			// Ignore because test is supposed to be caught
		}
	}

	/**
	 * Method which tests the getCommand method which returns the CommandValue type
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(CommandValue.BACKLOG, "Type");
		assertEquals(CommandValue.BACKLOG, c.getCommand());
		c = new Command(CommandValue.REVIEW, null);
		assertEquals(CommandValue.REVIEW, c.getCommand());
	}

	/**
	 * Method which tests the getCommandInformation which returns a string with the
	 * command information
	 */
	@Test
	public void testGetCommandInformation() {
		Command c = new Command(CommandValue.BACKLOG, "Type");
		assertEquals("Type", c.getCommandInformation());
		c = new Command(CommandValue.REVIEW, null);
		assertEquals(null, c.getCommandInformation());
	}

}

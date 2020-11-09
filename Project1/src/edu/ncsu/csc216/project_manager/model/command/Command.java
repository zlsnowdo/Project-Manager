/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.command;

/**
 * Class that creates Command objects which hold the specific command type and
 * the information about the selected command type.
 * 
 * @author Zachary Snowdon
 *
 */
public class Command {

	/**
	 * Empty string which holds the information for the selected command on the GUI
	 */
	private String commandInformation = null;

	/**
	 * Enumeration which holds the command type to act on a story
	 */
	private CommandValue command;

	/**
	 * The list of individual command that each user story can be told to do.
	 * {@link #BACKLOG}
	 * {@link #ASSIGN}
	 * {@link #REVIEW}
	 * {@link #CONFIRM}
	 * {@link #REOPEN}
	 * {@link #REJECT}
	 * {@link #RESUBMIT}
	 */
	public enum CommandValue {
		
		/**
		 * Backlog enum
		 */
		BACKLOG,
		
		/**
		 * Assign enum
		 */
		ASSIGN,
		
		/**
		 * Review enum
		 */
		REVIEW,
		
		/**
		 * Confirm enum
		 */
		CONFIRM,
		
		/**
		 * Reopen enum
		 */
		REOPEN,
		
		/**
		 * Reject enum
		 */
		REJECT,
		
		/**
		 * Resubmit enum
		 */
		RESUBMIT
	};

	/**
	 * Method which creates the Command object which holds the CommandValue enum and
	 * the command string for a user story.
	 * 
	 * @param command            The selected command from the GUI that is listed in
	 *                           the enum
	 * @param commandInformation The information that the user typed in about the
	 *                           command
	 */
	public Command(CommandValue command, String commandInformation) {
		this.command = command;
		this.commandInformation = commandInformation;
		if (command == null) {
			throw new IllegalArgumentException("Invalid command.");
		}
		if ((command == CommandValue.BACKLOG || command == CommandValue.ASSIGN || command == CommandValue.REJECT)
				&& (commandInformation == null || commandInformation.length() == 0)) {
			throw new IllegalArgumentException("Commands require additional information.");
		}
		if ((command == CommandValue.REVIEW || command == CommandValue.CONFIRM || command == CommandValue.REOPEN
				|| command == CommandValue.RESUBMIT) && commandInformation != null) {
			throw new IllegalArgumentException("Commands do not require additional information.");
		}
	}

	/**
	 * Gets the command that was selected by the user in the GUI and returns it.
	 * 
	 * @return CommandValue The command that was selected by the user in the GUI
	 */
	public CommandValue getCommand() {
		return command;
	}

	/**
	 * Gets the user input that they typed about the specific command and returns
	 * it.
	 * 
	 * @return commandInformation The information about the selected command the
	 *         user entered
	 */
	public String getCommandInformation() {
		return commandInformation;
	}

}

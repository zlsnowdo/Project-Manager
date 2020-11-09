/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.user_story;

import edu.ncsu.csc216.project_manager.model.command.Command;
import edu.ncsu.csc216.project_manager.model.command.Command.CommandValue;

/**
 * Class which create UserStory objects and places UserStory info in them
 * 
 * @author Zachary Snowdon
 *
 */
public class UserStory {

	/**
	 * Integer which represents the location of each UserStory
	 */
	private int storyId = 0;

	/**
	 * String which holds the title of each UserStory
	 */
	private String title = null;

	/**
	 * String which holds the user of each UserStory
	 */
	private String user = null;

	/**
	 * String which holds the action of each UserStory
	 */
	private String action = null;

	/**
	 * String which holds the value of each UserStory
	 */
	private String value = null;

	/**
	 * String which holds the priority of each UserStory
	 */
	private String priority = null;

	/**
	 * String which holds the developerId of each UserStory
	 */
	private String developerId = null;

	/**
	 * String which holds the rejection reason of each UserStory
	 */
	private String rejectionReason = null;

	/**
	 * The string which is used to identify the submitted state
	 */
	public static final String SUBMITTED_NAME = "Submitted";

	/**
	 * The string which is used to identify the backlog state
	 */
	public static final String BACKLOG_NAME = "Backlog";

	/**
	 * The string which is used to identify the working state
	 */
	public static final String WORKING_NAME = "Working";

	/**
	 * The string which is used to identify the verifying state
	 */
	public static final String VERIFYING_NAME = "Verifying";

	/**
	 * The string which is used to identify the completed state
	 */
	public static final String COMPLETED_NAME = "Completed";

	/**
	 * The string which is used to identify the rejected state
	 */
	public static final String REJECTED_NAME = "Rejected";

	/**
	 * The string which is used to identify high priority UserStory
	 */
	public static final String HIGH_PRIORITY = "High";

	/**
	 * The string which is used to identify medium priority UserStory
	 */
	public static final String MEDIUM_PRIORITY = "Medium";

	/**
	 * The string which is used to identify low priority UserStory
	 */
	public static final String LOW_PRIORITY = "Low";

	/**
	 * The string which is used to identify the duplicate rejection reason
	 */
	public static final String DUPLICATE_REJECTION = "Duplicate";

	/**
	 * The string which is used to identify the inappropriate rejection reason
	 */
	public static final String INAPPROPRIATE_REJECTION = "Inappropriate";

	/**
	 * The string which is used to identify the infeasible rejection reason
	 */
	public static final String INFEASIBLE_REJECTION = "Infeasible";

	/**
	 * Integer which holds the counter of UserStorys
	 */
	private static int counter = 0;

	/**
	 * Initializing the UserStoryState object named storyState;
	 */
	private UserStoryState storyState;

	/**
	 * Creating a UserStoryState in the BacklogState
	 */
	private UserStoryState backlogState = new BacklogState();

	/**
	 * Creating a UserStoryState in the CompletedState
	 */
	private UserStoryState completedState = new CompletedState();

	/**
	 * Creating a UserStoryState in the SubmittedState
	 */
	private UserStoryState submittedState = new SubmittedState();

	/**
	 * Creating a UserStoryState in the RejectedState
	 */
	private UserStoryState rejectedState = new RejectedState();

	/**
	 * Creating a UserStoryState in the WorkingState
	 */
	private UserStoryState workingState = new WorkingState();

	/**
	 * Creating a UserStoryState in the VerifyingState
	 */
	private UserStoryState verifyingState = new VerifyingState();

	/**
	 * Creates a UserStory object with four string values of information held in it
	 * 
	 * @param title  The title of the UserStory to be created
	 * @param user   The user of the UserStory to be created
	 * @param action The action of the UserStory to be created
	 * @param value  The value of the UserStory to be created
	 */
	public UserStory(String title, String user, String action, String value) {
		if (title == null || user == null || action == null || value == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (title.length() == 0 || user.length() == 0 || action.length() == 0 || value.length() == 0) {
			throw new IllegalArgumentException("Invalid information.");
		}
		setState("Submitted");
		setPriority(null);
		setDeveloperId(null);
		setRejectionReason(null);
		if (counter != 0) {
			incrementCounter();
		}
		storyId = UserStory.counter;
		setTitle(title);
		setUser(user);
		setAction(action);
		setValue(value);
		incrementCounter();
	}

	/**
	 * Creates a UserStory object with 8 string values of information held in it
	 * 
	 * @param storyId         The location of a UserStory in the UserStory list
	 * @param state           The state a UserStory is currently in
	 * @param title           The title of the individual UserStory object
	 * @param user            The user of the individual UserStory object
	 * @param action          The action of the individual UserStory object
	 * @param value           The value of the individual UserStory object
	 * @param priority        The priority of the individual UserStory object
	 * @param developerId     The developerId of the individual UserStory object
	 * @param rejectionReason The rejectionReason of the individual UserStory object
	 */
	public UserStory(int storyId, String state, String title, String user, String action, String value, String priority,
			String developerId, String rejectionReason) {
		if (title == null || user == null || action == null || value == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (title.length() == 0 || user.length() == 0 || action.length() == 0 || value.length() == 0) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if ("Submitted".equals(state) && (priority != null || developerId != null || rejectionReason != null)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if ("Backlog".equals(state) && (developerId != null || rejectionReason != null || priority == null)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (("Working".equals(state) || "Completed".equals(state) || "Verifying".equals(state))
				&& (developerId == null || rejectionReason != null || priority == null)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if("Rejected".equals(state) && (developerId != null || rejectionReason == null || priority != null)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if("Working".equals(state)) {
			boolean skipLine = false;
			if("High".equals(priority) || "Low".equals(priority) || "Medium".equals(priority)) {
				skipLine = true;
			}
			if(!skipLine) {
				throw new IllegalArgumentException("Invalid information.");
			}
		}
		if("Rejected".equals(state)) {
			boolean skipLine = false;
			if("Inappropriate".equals(rejectionReason) || "Duplicate".equals(rejectionReason) || "Infeasible".equals(rejectionReason)) {
				skipLine = true;
			}
			if(!skipLine) {
				throw new IllegalArgumentException("Invalid information.");
			}
		}
		setId(storyId);
		setCounter(storyId);
		setState(state);
		this.title = title;
		this.user = user;
		this.action = action;
		this.value = value;
		this.priority = priority;
		this.developerId = developerId;
		this.rejectionReason = rejectionReason;
	}

	/**
	 * Method which sets the Id of a UserStory to show its location
	 * 
	 * @param id The integer which represents the location of a UserStory
	 */
	private void setId(int id) {
		if (id > counter) {
			incrementCounter();
		}
		this.storyId = id;
	}

	/**
	 * Method which sets the state of a UserStory to see which actions need to be
	 * done
	 * 
	 * @param state The state that is to be set on an individual UserStory
	 */
	private void setState(String state) {
		if (state.equals(SUBMITTED_NAME)) {
			storyState = submittedState;
		}
		if (state.equals(BACKLOG_NAME)) {
			storyState = backlogState;
		}
		if (state.equals(WORKING_NAME)) {
			storyState = workingState;
		}
		if (state.equals(VERIFYING_NAME)) {
			storyState = verifyingState;
		}
		if (state.equals(COMPLETED_NAME)) {
			storyState = completedState;
		}
		if (state.equals(REJECTED_NAME)) {
			storyState = rejectedState;
		}
	}

	/**
	 * Method to return the Id of a UserStory which shows its location
	 * 
	 * @return String Id The location of a UserStory in the list
	 */
	public int getId() {
		return storyId;
	}

	/**
	 * Method to get the state of the current UserStory
	 * 
	 * @return String state The state the specific UserStory is in
	 */
	public String getState() {
		String state = storyState.getStateName();
		return state;
	}

	/**
	 * Method to get the title of the current UserStory
	 * 
	 * @return String title The title of the specific UserStory
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method to set the title of a specific UserStory
	 * 
	 * @param title The string name which is to be set as the UserStory title
	 */
	private void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Method to get the user of a specific UserStory
	 * 
	 * @return user The user of the current UserStory
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Method to set the user of a specific UserStory
	 * 
	 * @param user The user of the current UserStory
	 */
	private void setUser(String user) {
		if (user == null || user.length() == 0) {
			throw new IllegalArgumentException("Invalid user.");
		}
		this.user = user;
	}

	/**
	 * Method to get the action of a specific UserStory
	 * 
	 * @return action The action the UserStory is supposed to do
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Method to set the action of a specific UserStory
	 * 
	 * @param action The action the UserStory is supposed to do
	 */
	private void setAction(String action) {
		if (action == null || action.length() == 0) {
			throw new IllegalArgumentException("Invalid action.");
		}
		this.action = action;
	}

	/**
	 * Method to get the value of a specific UserStory
	 * 
	 * @return value The value of the current UserStory
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Method to set the value of a specific UserStory
	 * 
	 * @param value The value of the current UserStory
	 */
	private void setValue(String value) {
		if (value == null || value.length() == 0) {
			throw new IllegalArgumentException("Invalid value.");
		}
		this.value = value;
	}

	/**
	 * Method to get the priority of a specific UserStory
	 * 
	 * @return priority The priority of the selected UserStory
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Method to set the priority of a specific UserStory
	 * 
	 * @param priority The priority of the selected UserStory
	 */
	private void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Method to get the priority of a specific UserStory
	 * 
	 * @return developerId The Id of the developer working on the UserStory
	 */
	public String getDeveloperId() {
		return developerId;
	}

	/**
	 * Method to set which developer is working on the UserStory
	 * 
	 * @param developerId The Id of the developer working on the UserStory
	 */
	private void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}

	/**
	 * Method to return the reason the rejection button was selected
	 * 
	 * @return rejectionReason The string which says why the UserStory was rejected
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}

	/**
	 * Method to set the reason the rejection button was selected
	 * 
	 * @param rejectionReason The string which says why the UserStory was rejected
	 */
	private void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	/**
	 * Counts how many UserStorys are in the list at a certain time
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Sets the increment counter to a certain value after files are read
	 * 
	 * @param number The number of UserStorys in the file
	 */
	public static void setCounter(int number) {
		counter = number;
	}

	/**
	 * Constructor to create the string which holds the courses details
	 * 
	 * @return name The string value for the courses details
	 */
	public String toString() {
		String state = getState();
		String output = "";
		String line1 = "";
		String line2 = "";
		String line3 = "";
		String line4 = "";
		if ("Completed".equals(state) || "Verifying".equals(state) || "Working".equals(state)) {
			line1 = "* " + getId() + "," + getState() + "," + getTitle() + "," + getPriority() + "," + getDeveloperId()
					+ ",";
			line2 = "- " + getUser();
			line3 = "- " + getAction();
			line4 = "- " + getValue();
		} else if ("Rejected".equals(state)) {
			line1 = "* " + getId() + "," + getState() + "," + getTitle() + "," + getRejectionReason();
			line2 = "- " + getUser();
			line3 = "- " + getAction();
			line4 = "- " + getValue();
		} else if ("Backlog".equals(state)) {
			line1 = "* " + getId() + "," + getState() + "," + getTitle() + "," + getPriority() + ",";
			line2 = "- " + getUser();
			line3 = "- " + getAction();
			line4 = "- " + getValue();
		} else if ("Submitted".equals(state)) {
			line1 = "* " + getId() + "," + getState() + "," + getTitle() + ",";
			line2 = "- " + getUser();
			line3 = "- " + getAction();
			line4 = "- " + getValue();
		}
		output = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
		return output;
	}

	/**
	 * Method to update which command is to be acted on a certain UserStory
	 * 
	 * @param c The command to be acted on a certain UserStory
	 */
	public void update(Command c) {
		String state = getState();
		if (state.equals(SUBMITTED_NAME)) {
			submittedState.updateState(c);
		}
		if (state.equals(BACKLOG_NAME)) {
			backlogState.updateState(c);
		}
		if (state.equals(WORKING_NAME)) {
			workingState.updateState(c);
		}
		if (state.equals(VERIFYING_NAME)) {
			verifyingState.updateState(c);
		}
		if (state.equals(COMPLETED_NAME)) {
			completedState.updateState(c);
		}
		if (state.equals(REJECTED_NAME)) {
			rejectedState.updateState(c);
		}
	}

	/**
	 * Interface for states in the UserStory State Pattern. All concrete user story
	 * states must implement the UserStoryState interface. The UserStoryState
	 * interface should be a private interface of the UserStory class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface UserStoryState {

		/**
		 * Update the UserStory based on the given Command. An
		 * UnsupportedOperationException is throw if the Command is not a valid action
		 * for the given state.
		 * 
		 * @param command Command describing the action that will update the UserStory's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the given state.
		 */
		void updateState(Command command);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

	/**
	 * Inner class which completes tasks for the user stories in the submitted state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class SubmittedState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * submitted state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.BACKLOG) {
				setState("Backlog");
				setPriority(command.getCommandInformation());
			} else if (commands == CommandValue.REJECT) {
				setState("Rejected");
				setRejectionReason(command.getCommandInformation());
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == submittedState) {
				state = "Submitted";
			} else if (storyState == backlogState) {
				state = "Backlog";
			} else if (storyState == rejectedState) {
				state = "Rejected";
			}
			return state;
		}

	}

	/**
	 * Inner class which completes tasks for the user stories in the backlog state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class BacklogState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * backlog state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.ASSIGN) {
				setState("Working");
				setDeveloperId(command.getCommandInformation());
			} else if (commands == CommandValue.REJECT) {
				setState("Rejected");
				setRejectionReason(command.getCommandInformation());
				setPriority(null);
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == backlogState) {
				state = "Backlog";
			} else if (storyState == workingState) {
				state = "Working";
			} else if (storyState == rejectedState) {
				state = "Rejected";
			}
			return state;
		}
	}

	/**
	 * Inner class which completes tasks for the user stories in the completed state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class CompletedState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * completed state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.REOPEN) {
				setState("Working");
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == completedState) {
				state = "Completed";
			} else if (storyState == workingState) {
				state = "Working";
			}
			return state;
		}
	}

	/**
	 * Inner class which completes tasks for the user stories in the rejected state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class RejectedState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * rejected state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.RESUBMIT) {
				setState("Submitted");
				setRejectionReason(null);
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == rejectedState) {
				state = "Rejected";
			} else if (storyState == submittedState) {
				state = "Submitted";
			}
			return state;
		}
	}

	/**
	 * Inner class which completes tasks for the user stories in the verifying state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class VerifyingState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * verifying state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.REOPEN) {
				setState("Working");
			} else if (commands == CommandValue.CONFIRM) {
				setState("Completed");
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == verifyingState) {
				state = "Verifying";
			} else if (storyState == workingState) {
				state = "Working";
			} else if (storyState == completedState) {
				state = "Completed";
			}
			return state;
		}
	}

	/**
	 * Inner class which completes tasks for the user stories in the working state
	 * 
	 * @author Zachary Snowdon
	 *
	 */
	public class WorkingState implements UserStoryState {

		/**
		 * Method which updates the state of a user story which is currently in the
		 * working state
		 * 
		 * @param command The enumeration command which is to be acted upon the user
		 *                story
		 */
		public void updateState(Command command) {
			CommandValue commands = command.getCommand();
			if (commands == CommandValue.REOPEN) {
				setState("Backlog");
				setDeveloperId(null);
			} else if (commands == CommandValue.REVIEW) {
				setState("Verifying");
			} else if (commands == CommandValue.REJECT) {
				setState("Rejected");
				setRejectionReason(command.getCommandInformation());
				setPriority(null);
				setDeveloperId(null);
			} else if (commands == CommandValue.ASSIGN) {
				setState("Working");
				setDeveloperId(command.getCommandInformation());
			} else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Method which gets the name of the new state the UserStory is in
		 * 
		 * @return state The string which represents which state the story is now in
		 */
		public String getStateName() {
			String state = "";
			if (storyState == verifyingState) {
				state = "Verifying";
			} else if (storyState == workingState) {
				state = "Working";
			} else if (storyState == rejectedState) {
				state = "Rejected";
			} else if (storyState == backlogState) {
				state = "Backlog";
			}
			return state;
		}
	}

}

/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.project_manager.model.manager.Project;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Class which places the project and user story objects into an output text
 * file
 * 
 * @author Zachary Snowdon
 *
 */
public class ProjectWriter {

	/**
	 * Uses the ProjectWriter method to get the proper output for a user story then
	 * places the formatted user story into an output file
	 * 
	 * @param fileName The name of the output file where the information is stored
	 * @param c        The project object which holds all of the user stories
	 * @throws FileNotFoundException for file which can not be read by the
	 *                               printStream
	 */
	public static void writeProjectToFile(String fileName, Project c) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			ArrayList<UserStory> stories = new ArrayList<UserStory>();
			stories = c.getUserStories();
			String projectName = c.getProjectName();
			fileWriter.println("# " + projectName);
			for (int i = 0; i < stories.size(); i++) {
				UserStory story = stories.get(i);
				fileWriter.println(story.toString());
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}

	}

}

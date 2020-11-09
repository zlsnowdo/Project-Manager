/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.manager.Project;
import edu.ncsu.csc216.project_manager.model.user_story.UserStory;

/**
 * Tests for ProjectWriter
 * @author Zachary Snowdon
 *
 */
public class ProjectWriterTest {
	
    /**
     * String which represents which file the project should be printed to
     */
	private final String newOutput = "test-files/output.txt";
	
	/**
	 * Tests the ProjectWriter class with a valid output file
	 */
	@Test
	public void testPrintProject() {
		Project c = new Project("WolfScheduler");
		UserStory story = new UserStory(0, "Completed", "Load Catalog", "student", "load a course catalog file",
				"plan a schedule for next semester", "High", "sesmith5", null);
		c.addUserStory(story);
		story = new UserStory(1, "Verifying", "Select Course", "student", "select a course to add to my schedule",
				"plan a schedule for next semester", "Medium", "jctetter", null);
		c.addUserStory(story);
		story = new UserStory(2, "Working", "Add Event", "student", "add a recurring event",
				"schedule extra curricular activities", "Medium", "ignacioxd", null);
		c.addUserStory(story);
		story = new UserStory(3, "Backlog", "Export Schedule", "student", "export my schedule",
				"so it's available when I register for classes", "Low", null, null);
		c.addUserStory(story);
		story = new UserStory(4, "Submitted", "Change Title", "student", "change the title of my schedule",
				"so I can have different schedules to consider", null, null, null);
		c.addUserStory(story);
		story = new UserStory(5, "Rejected", "Post to Piazza", "student", "post my schedule to Piazza for feedback",
				"see which of my friends are interested in taking the same courses", null, null, "Inappropriate");
		c.addUserStory(story);
		ArrayList<UserStory> stories = c.getUserStories();
		assertEquals(6, stories.size());
		ProjectWriter.writeProjectToFile(newOutput, c);
		checkFiles("test-files/output.txt", "test-files/project1.txt");
	}
	
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

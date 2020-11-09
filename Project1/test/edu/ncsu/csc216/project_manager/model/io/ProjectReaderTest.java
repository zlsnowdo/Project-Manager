/**
 * 
 */
package edu.ncsu.csc216.project_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.project_manager.model.manager.Project;

/**
 * Tests for ProjectReader
 * @author Zachary Snowdon
 *
 */
public class ProjectReaderTest {
	
	/**
	 * String which represents where a invalid input file is located
	 */
	private final String invalidTestFile = "test-files/invalid_test_file.txt";
	
	/**
	 * String which represents where a valid input file is located
	 */
	private final String validTestFile = "test-files/project1.txt";
	
	/**
	 * Tests reading an invalid test file input in ProjectReader
	 */
	@Test
	public void testInvalidTestFile() {
		try {
			ProjectReader.readProjectFile(invalidTestFile);
			fail();
		} catch (IllegalArgumentException e) {
			//Ignore because test is supposed to be caught
		}
		
	}
	
	/**
	 * Tests reading a valid test file input in ProjectReader
	 */
	@Test
	public void testValidTestFile() {
		try {
			ArrayList<Project> projects = ProjectReader.readProjectFile(validTestFile);
			assertEquals(1, projects.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
}

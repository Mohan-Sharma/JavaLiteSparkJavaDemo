package org.msharma.domain.facade;

import org.msharma.presentation.dto.StudentDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This interface abstracts the user from the complex business services implementations
 * @author Mohan Sharma Created on 22/01/18.
 */
public interface StudentFacade
{
	/**
	 * This function is used to find a student whose roll_number matches the given roll number.
	 * @param rollNumber
	 * @return student satisfying the criteria
	 */
	StudentDTO findStudentByRollNumber(int rollNumber) throws InvocationTargetException, IllegalAccessException;

	/**
	 * This function is used to find all the students which first_name matches the given firstName.
	 * @param firstName
	 * @return list of student satisfying the criteria
	 */
	List<StudentDTO> findStudentByFirstName(String firstName);

	/**
	 * This function is used to find all the students.
	 * @return list of students
	 */
	List<StudentDTO> findAllStudents() throws SQLException, ClassNotFoundException;

	/**
	 * This function is used to save the Student.
	 * @param student
	 */
	Map<String, Object> save(StudentDTO student);

	/**
	 * This function is used to update the given student data.
	 * @param
	 */
	Map<String, Object> update(StudentDTO student);

	/**
	 * This function returns the count of all the students available.
	 * @return number of students in db.
	 */
	Map<String, Long> count();

	/**
	 * This function returns the count of all the student whose first name matches the given firstName
	 * @param firstName
	 * @return number of students satisfying the criteria in db.
	 */
	Map<String, Object> countByFirstName(String firstName);
}

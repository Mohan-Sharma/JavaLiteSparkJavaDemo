package org.msharma.domain.services;

import org.msharma.domain.model.Student;
import org.msharma.presentation.dto.StudentDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * This interface provides an abstraction for the student business service implementations.
 * @author Mohan Sharma Created on 22/01/18.
 */
public interface StudentService
{
	/**
	 * This function fetches a student from DB whose roll_number matches the given rollNumber.
	 * @param rollNumber
	 * @return student satisfying the criteria
	 */
	StudentDTO findStudentByRollNumber(int rollNumber) throws InvocationTargetException, IllegalAccessException;

	/**
	 * This function fetches the students which first_name matches the given firstName.
	 * @param firstName
	 * @return list of student satisfying the criteria
	 */
	List<StudentDTO> findStudentByFirstName(String firstName);

	/**
	 * This function fetches all the students.
	 * @return
	 */
	List<StudentDTO> findAllStudents() throws SQLException, ClassNotFoundException;

	/**
	 * This function saves the Student model in db.
	 * @param student
	 */
	boolean save(StudentDTO student);

	/**
	 * This function update the student data in db
	 * @param student
	 */
	boolean update(StudentDTO student);

	/**
	 * This function returns the count of all the records.
	 * @return number of students in db.
	 */
	Long count();

	/**
	 * This function returns the count of all the student which satisfy the given criteria.
	 * @param firstName
	 * @return number of students satisfying the criteria in db.
	 */
	Long countByFirstName(String firstName);
}

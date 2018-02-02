package org.msharma.persistence.dao;

import org.msharma.domain.model.Student;
import org.msharma.presentation.dto.StudentDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * This interface demonstrates how we can use JavaLite to interact with the database
 * and abstracting the user from the operations.
 * @author Mohan Sharma Created on 22/01/18.
 */
public interface StudentDao
{
	/**
	 * This function fetches a student from DB whose roll_number matches the given rollNumber.
	 * @param rollNumber
	 * @return student satisfying the criteria
	 */
	Student findStudentByRollNumber(int rollNumber);

	/**
	 * This function fetches the students which first_name matches the given firstName.
	 * @param firstName
	 * @return list of student satisfying the criteria
	 */
	List<Student> findStudentByFirstName(String firstName);

	/**
	 * This function fetches all the students.
	 * @return
	 */
	List<Student> findAllStudents() throws SQLException, ClassNotFoundException;

	/**
	 * This function saves the Student model in db.
	 * @param student
	 */
	boolean save(Student student);

	/**
	 * This function updates the student in db
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

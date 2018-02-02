package org.msharma.persistence.dao.impl;

import com.google.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.javalite.activejdbc.Base;
import org.msharma.domain.model.Student;
import org.msharma.persistence.dao.StudentDao;
import org.msharma.persistence.repository.DataSourceFactory;
import org.msharma.presentation.dto.StudentDTO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * This class implements {@link StudentDao} and provides the way to interact with the DB.
 * @author Mohan Sharma Created on 22/01/18.
 */
public class StudentDaoImpl implements StudentDao
{

	@Inject
	private DataSourceFactory dataSourceFactory;
	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#findStudentByRollNumber(int)
	 */
	@Override
	public Student findStudentByRollNumber(int rollNumber)
	{
		Student student = Student.findFirst("roll_number = ?", rollNumber);
		return student;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#findStudentByFirstName(java.lang.String)
	 */
	@Override
	public List<Student> findStudentByFirstName(String firstName)
	{
		List<Student> students = Student.where("first_name = ?", firstName);
		return students;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#findAllStudents()
	 */
	@Override
	public List<Student> findAllStudents() throws SQLException, ClassNotFoundException
	{
		List<Student> students = Student.findAll();
		return students;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#save(org.msharma.domain.model.Student)
	 */
	@Override
	public boolean save(Student student)
	{
		try
		{
			return student.insert();
		}
		catch (Exception e)
		{
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#update(org.msharma.domain.model.Student)
	 */
	@Override
	public boolean update(StudentDTO studentData)
	{
		try
		{
			Student student = Student.findFirst("roll_number = ?", studentData.getRollNumber());
			student.setFirstName(studentData.getFirstName());
			student.setLastName(studentData.getLastName());
			return student.saveIt();
		}
		catch (Exception e)
		{
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#count()
	 */
	@Override
	public Long count()
	{
		return Student.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#countByFirstName(java.lang.String)
	 */
	@Override
	public Long countByFirstName(String firstName)
	{
		return Student.count("first_name = ?", firstName);
	}
}

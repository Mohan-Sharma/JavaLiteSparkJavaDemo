package org.msharma.persistence.dao.impl;

import com.google.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.msharma.domain.model.Student;
import org.msharma.persistence.dao.StudentDao;

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
	//@Value("${development.driver}")
	private String driver;
	//@Value("${development.username}")
	private String username;
	//@Value("${development.password}")
	private String password;
	//@Value("${development.url}")
	private String url;

	@Inject
	private DataSource dataSource;

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
	public void save(Student student)
	{
		student.saveIt();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#saveAll(java.util.Collection)
	 */
	@Override
	public void saveAll(Collection<Student> students)
	{
		if(CollectionUtils.isNotEmpty(students))
			students.forEach(student -> student.saveIt());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#count()
	 */
	@Override
	public Long count()
	{
		Long count = Student.count();
		return count;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.persistence.dao.StudentDao#countByFirstName(java.lang.String)
	 */
	@Override
	public Long countByFirstName(String firstName)
	{
		Long count = Student.count("first_name = ?", firstName);
		return count;
	}

}

package org.msharma.domain.facade.impl;

import com.google.inject.Inject;
import org.msharma.domain.facade.StudentFacade;
import org.msharma.domain.services.StudentService;
import org.msharma.presentation.dto.StudentDTO;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @author Mohan Sharma Created on 22/01/18.
 */
public class StudentFacadeImpl implements StudentFacade
{
	@Inject
	private StudentService studentService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#findStudentByRollNumber(int)
	 */
	@Override
	public StudentDTO findStudentByRollNumber(int rollNumber) throws InvocationTargetException, IllegalAccessException
	{
		return studentService.findStudentByRollNumber(rollNumber);
	}

	@Override
	public List<StudentDTO> findStudentByFirstName(String firstName)
	{
		return studentService.findStudentByFirstName(firstName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#findAllStudents()
	 */
	@Override
	public List<StudentDTO> findAllStudents() throws SQLException, ClassNotFoundException
	{
		return studentService.findAllStudents();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#save(org.msharma.presentation.dto.StudentDTO)
	 */
	@Override
	public void save(StudentDTO student)
	{
		studentService.save(student);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#saveAll(java.util.Collection)
	 */
	@Override
	public void saveAll(Collection<StudentDTO> students)
	{
		studentService.saveAll(students);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#count()
	 */
	@Override
	public Long count()
	{
		return studentService.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#countByFirstName(java.util.String)
	 */
	@Override
	public Long countByFirstName(String firstName)
	{
		return studentService.countByFirstName(firstName);
	}
}

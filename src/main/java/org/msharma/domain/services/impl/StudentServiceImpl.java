package org.msharma.domain.services.impl;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.msharma.domain.model.Student;
import org.msharma.domain.services.StudentService;
import org.msharma.persistence.dao.StudentDao;
import org.msharma.presentation.dto.StudentDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mohan Sharma Created on 22/01/18.
 */
public class StudentServiceImpl implements StudentService
{
	@Inject
	private StudentDao studentDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#findStudentByRollNumber(int)
	 */
	@Override
	public StudentDTO findStudentByRollNumber(int rollNumber)
	{
		Student student = studentDao.findStudentByRollNumber(rollNumber);
		StudentDTO studentDTO = new StudentDTO();
		if(Objects.nonNull(student))
		{
			try
			{
				BeanUtils.copyProperties(studentDTO, student);
			}
			catch (IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		return studentDTO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#findStudentByFirstName(java.lang.String)
	 */
	@Override
	public List<StudentDTO> findStudentByFirstName(String firstName)
	{
		List<StudentDTO> studentData = Lists.newArrayList();
		List<Student> students = studentDao.findStudentByFirstName(firstName);
		if(CollectionUtils.isNotEmpty(students))
		{
			studentData =
					students
							.stream()
							.map(student -> {
								StudentDTO studentDTO = new StudentDTO();
								try
								{
									BeanUtils.copyProperties( studentDTO, student);
								}
								catch (IllegalAccessException | InvocationTargetException e)
								{
									e.printStackTrace();
								}
								return studentDTO;
							})
							.collect(Collectors.toList());
		}
		return studentData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#findAllStudents()
	 */
	@Override
	public List<StudentDTO> findAllStudents() throws SQLException, ClassNotFoundException
	{
		List<StudentDTO> studentData = Lists.newArrayList();
		List<Student> students = studentDao.findAllStudents();
		if(CollectionUtils.isNotEmpty(students))
		{
			studentData =
					students
							.stream()
							.map(student -> {
								StudentDTO studentDTO = new StudentDTO();
								try
								{
									BeanUtils.copyProperties( studentDTO, student);
								}
								catch (IllegalAccessException | InvocationTargetException e)
								{
									e.printStackTrace();
								}
								return studentDTO;
							})
							.collect(Collectors.toList());
		}
		return studentData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#save(org.msharma.presentation.dto.StudentDTO)
	 */
	@Override
	public boolean save(StudentDTO studentDto)
	{
		Student student = new Student();
		student.setRollNumber(studentDto.getRollNumber());
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		return studentDao.save(student);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#update(org.msharma.presentation.dto.StudentDTO)
	 */
	@Override
	public boolean update(StudentDTO studentData)
	{
		return studentDao.update(studentData);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#count()
	 */
	@Override
	public Long count()
	{
		return studentDao.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.services.StudentService#countByFirstName(java.lang.String)
	 */
	@Override
	public Long countByFirstName(String firstName)
	{
		return studentDao.countByFirstName(firstName);
	}
}

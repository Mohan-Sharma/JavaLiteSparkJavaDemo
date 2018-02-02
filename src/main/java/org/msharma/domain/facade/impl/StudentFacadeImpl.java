package org.msharma.domain.facade.impl;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import org.msharma.domain.facade.StudentFacade;
import org.msharma.domain.services.StudentService;
import org.msharma.presentation.dto.StudentDTO;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> save(StudentDTO student)
	{
		boolean result = studentService.save(student);
		Map<String, Object> resultMap = Maps.newHashMap();
		if(result)
			resultMap.put("success", "successfully saved!");
		else
			resultMap.put("error", "Error saving!");
		resultMap.put("status", result);
		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#update(org.msharma.presentation.dto.StudentDTO)
	 */
	@Override
	public Map<String, Object> update(StudentDTO student)
	{
		boolean result = studentService.update(student);
		Map<String, Object> resultMap = Maps.newHashMap();
		if(result)
			resultMap.put("success", "successfully saved!");
		else
			resultMap.put("error", "Error saving!");
		resultMap.put("status", result);
		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#count()
	 */
	@Override
	public Map<String, Long> count()
	{
		Long count = studentService.count();
		Map countMap = Maps.newHashMap();
		countMap.put("count", count);
		return countMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.msharma.domain.facade.StudentFacade#countByFirstName(java.util.String)
	 */
	@Override
	public Map<String, Object> countByFirstName(String firstName)
	{
		Long count = studentService.countByFirstName(firstName);
		Map<String, Object> countMap = Maps.newHashMap();
		countMap.put("firstName", firstName);
		countMap.put("count", count);
		return countMap;
	}
}

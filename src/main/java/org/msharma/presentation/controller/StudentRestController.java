package org.msharma.presentation.controller;

import com.google.inject.Inject;
import org.msharma.domain.facade.StudentFacade;
import org.msharma.presentation.dto.StudentDTO;
import org.msharma.presentation.util.CustomObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This controller acts as the request handler for all student business service requests.
 * @author Mohan Sharma Created on 22/01/18.
 */
public class StudentRestController
{
	@Inject
	private StudentFacade studentFacade;

	@Inject
	private CustomObjectMapper objectMapper;

	/**
	 * This is the base request handler which is invoked when the user hits "/student". This returns the
	 * basic student view.
	 * @param modelAndView
	 * @return the basic view.
	 */
	public Route getStudentHomePage = (Request request, Response response) -> {
		response.redirect("views/student.html");
		return "";
	};

	/**
	 * This handler returns all the students.
	 * @return list of students
	 */
	public Route getAllStudent = (Request request, Response response) -> {
		return objectMapper.dataToJson(studentFacade.findAllStudents());
	};

	/**
	 * This handler can be used to find a student by roll number
	 * @param rollNumber
	 * @return the fetched student
	 */
	public Route  getStudentByRoll = (Request request, Response response) -> {
		Integer rollNumber = Integer.parseInt(request.queryParams("rollNumber"));
		StudentDTO dto = studentFacade.findStudentByRollNumber(rollNumber);
		return objectMapper.dataToJson(dto);
	};

	/**
	 * This handler can be used to find all the students having given name
	 * @param firstName
	 * @return list of students
	 */
	public Route getAllStudentHavingName = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to save the given student data.
	 * @param studentDTO
	 * @return the response of action
	 */
	public Route save = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to save all the student data.
	 * @param students
	 * @return the response of action
	 */
	public Route update = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to get the count of all the students available
	 * @return list of students
	 */
	public Route count= (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to get the count of all the students having the given name.
	 * @return list of students
	 */
	public Route countByFirstName = (Request request, Response response) -> {
		return null;
	};
}

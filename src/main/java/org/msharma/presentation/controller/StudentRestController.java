package org.msharma.presentation.controller;

import com.google.inject.Inject;
import org.msharma.config.ApplicationBootstrap;
import org.msharma.domain.facade.StudentFacade;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.annotation.Resource;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

/**
 * This controller acts as the request handler for all student business service requests.
 * @author Mohan Sharma Created on 22/01/18.
 */
public class StudentRestController
{
	//@Inject
	private StudentFacade studentFacade;

	/**
	 * This is the base request handler which is invoked when the user hits "/student". This returns the
	 * basic student view.
	 * @param modelAndView
	 * @return the basic view.
	 */
	public static Route getStudentHomePage = (Request request, Response response) -> {
		response.redirect("views/student.html");
		return "";
	};

	/**
	 * This handler returns all the students.
	 * @return list of students
	 */
	public static Route getAllStudent = (Request request, Response response) -> {
		return "";
	};

	/**
	 * This handler can be used to find a student by roll number
	 * @param rollNumber
	 * @return the fetched student
	 */
	public static Route  getStudentByRoll = (Request request, Response response) -> {
		String rollNumber = request.queryParams("rollNumber");
		return rollNumber;
	};

	/**
	 * This handler can be used to find all the students having given name
	 * @param firstName
	 * @return list of students
	 */
	public static Route getAllStudentHavingName = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to save the given student data.
	 * @param studentDTO
	 * @return the response of action
	 */
	public static Route save = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to save all the student data.
	 * @param students
	 * @return the response of action
	 */
	public static Route update = (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to get the count of all the students available
	 * @return list of students
	 */
	public static Route count= (Request request, Response response) -> {
		return null;
	};

	/**
	 * This handler can be used to get the count of all the students having the given name.
	 * @return list of students
	 */
	public static Route countByFirstName = (Request request, Response response) -> {
		return null;
	};
}

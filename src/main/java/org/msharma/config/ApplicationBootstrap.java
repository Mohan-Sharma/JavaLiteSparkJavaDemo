package org.msharma.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.msharma.presentation.controller.CommonController;
import org.msharma.presentation.controller.StudentRestController;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.servlet.SparkFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static spark.Spark.*;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class ApplicationBootstrap
{
	public static void main(String[] args) throws Exception
	{
		staticFiles.location("/static");
		staticFiles.expireTime(600L);

		//before("*",                  addTrailingSlashes);
		before("*",                  handleLocaleChange);
		get("/",          "text/html", CommonController.getHomePage);
		get("/getAppJS",          "text/javascript", CommonController.getAppJS);
		get("/student",          "text/html", StudentRestController.getStudentHomePage);
		get("/allstudents/",          StudentRestController.getAllStudent);
		get("/getStudentByRoll",          StudentRestController.getStudentByRoll);
		get("/getAllStudentHavingName/",          StudentRestController.getAllStudentHavingName);
		get("/count/",          StudentRestController.count);
		get("/countByFirstName/",          StudentRestController.countByFirstName);
		post("/save",          StudentRestController.save);
		post("/update",          StudentRestController.update);

	}

	public static Filter addTrailingSlashes = (Request request, Response response) -> {
		if (!request.pathInfo().endsWith("/")) {
			response.redirect(request.pathInfo() + "/");
		}
	};

	public static Filter handleLocaleChange = (Request request, Response response) -> {
		if (getQueryLocale(request) != null) {
			request.session().attribute("locale", getQueryLocale(request));
			response.redirect(request.pathInfo());
		}
	};

	public static String getQueryLocale(Request request) {
		return request.queryParams("locale");
	}
}

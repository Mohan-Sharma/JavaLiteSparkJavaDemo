package org.msharma.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.lang3.BooleanUtils;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.msharma.domain.facade.StudentFacade;
import org.msharma.persistence.repository.DataSourceFactory;
import org.msharma.presentation.controller.CommonController;
import org.msharma.presentation.controller.StudentRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

import javax.sql.DataSource;

import static spark.Spark.*;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class ApplicationBootstrap
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBootstrap.class);

	public static void main(String[] args) throws Exception
	{
		Injector injector = Guice.createInjector(new CustomModule());
		StudentRestController controller = injector.getInstance(StudentRestController.class);
		DataSourceFactory dataSourceFactory = injector.getInstance(DataSourceFactory.class);
		DataSource dataSource = dataSourceFactory.getDataSource();
		//only for development.
		String projectDir = System.getProperty("user.dir");
		String staticDir = "/src/main/resources/static";
		staticFiles.externalLocation(projectDir + staticDir);
		//staticFiles.location("/static");
		staticFiles.expireTime(600L);

		before("/student/*", (request, response) -> {
			if(BooleanUtils.isFalse(Base.hasConnection()))
			{
				LOGGER.info("Connection Open!");
				Base.open(dataSource);
				Base.openTransaction();
			}
		});
		after("/student/*", (request, response) -> {
			if(BooleanUtils.isTrue(Base.hasConnection()))
			{
				LOGGER.info("Connection Closed!");
				Base.commitTransaction();
				Base.close();
			}
		});

		before("*",                  handleLocaleChange);
		get("/",          "text/html", CommonController.getHomePage);
		get("/favicon.ico",          "text/html", CommonController.getFavicon);
		get("/getAppJS",          "text/javascript", CommonController.getAppJS);
		get("/student",          "text/html", controller.getStudentCommonHomePage);
		get("/student/allstudents",          controller.getAllStudent);
		get("/student/getStudentByRoll",          controller.getStudentByRoll);
		get("/student/getAllStudentHavingName",          controller.getAllStudentHavingName);
		get("/student/count",          controller.count);
		get("/student/countByFirstName",          controller.countByFirstName);
		post("/student/save",          controller.save);
		post("/student/update",          controller.update);
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

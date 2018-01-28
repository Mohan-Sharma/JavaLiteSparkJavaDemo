package org.msharma.presentation.controller;

import org.apache.commons.lang3.StringUtils;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This controller can be used to invoke the route of homepage.
 * @author Mohan Sharma Created on 26/01/18.
 */
public class CommonController
{
	/**
	 * This is the base request handler which is invoked when the user hits "/". This returns the
	 * basic welcome view.
	 * @return the basic view.
	 */
	public static Route getHomePage = (Request request, Response response) -> {
		response.redirect("views/index.html");
		return StringUtils.EMPTY;
	};

	/**
	 * This is the base request handler which is invoked when the user hits "/". This returns the
	 * basic welcome view.
	 * @return the basic view.
	 */
	public static Route getAppJS = (Request request, Response response) -> {
		response.redirect("js/app.js");
		return StringUtils.EMPTY;
	};

	/**
	 * This is the base request handler which is invoked when the user hits "/". This returns the
	 * basic welcome view.
	 * @return the basic view.
	 */
	public static Route getFavicon = (Request request, Response response) -> {
		return StringUtils.EMPTY;
	};
}

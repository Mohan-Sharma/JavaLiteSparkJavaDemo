package org.msharma.persistence.repository.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.msharma.persistence.repository.DataSourceFactory;

import javax.sql.DataSource;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class DefaultDataSourceFactory implements DataSourceFactory
{
	@Inject
	private @Named("development.driver") String driver;
	@Inject
	private @Named("development.url") String url;
	@Inject
	private @Named("development.username") String username;
	@Inject
	private @Named("development.password") String password;

	@Override
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}

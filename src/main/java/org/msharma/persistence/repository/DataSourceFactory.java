package org.msharma.persistence.repository;

import javax.sql.DataSource;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public interface DataSourceFactory
{
	DataSource getDataSource();
}

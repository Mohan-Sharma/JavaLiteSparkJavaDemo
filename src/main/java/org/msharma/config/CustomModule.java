package org.msharma.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import org.msharma.domain.facade.StudentFacade;
import org.msharma.domain.facade.impl.StudentFacadeImpl;
import org.msharma.domain.services.StudentService;
import org.msharma.domain.services.impl.StudentServiceImpl;
import org.msharma.persistence.dao.StudentDao;
import org.msharma.persistence.dao.impl.StudentDaoImpl;
import org.msharma.persistence.repository.DataSourceFactory;
import org.msharma.persistence.repository.impl.DefaultDataSourceFactory;
import org.msharma.presentation.util.CustomObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class CustomModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(StudentFacade.class).to(StudentFacadeImpl.class).in(Scopes.SINGLETON);
		bind(StudentService.class).to(StudentServiceImpl.class).in(Scopes.SINGLETON);
		bind(StudentDao.class).to(StudentDaoImpl.class).in(Scopes.SINGLETON);
		bind(DataSourceFactory.class).to(DefaultDataSourceFactory.class).in(Scopes.SINGLETON);
		bind(CustomObjectMapper.class).in(Scopes.SINGLETON);
		Properties properties = new Properties();
		try
		{
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
			Names.bindProperties(binder(), properties);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

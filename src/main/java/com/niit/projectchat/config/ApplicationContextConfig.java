package com.niit.projectchat.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.projectchat.daoimpl.UserDAOImpl;
import com.niit.projectchat.model.User;


@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("project2");// Schema name
		dataSource.setPassword("snehapatil");
		
		Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		dataSource.setConnectionProperties(connectionProperties);

		return dataSource;
	}
	

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactroy(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		//sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		/*sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(NewsFeeds.class);
		sessionBuilder.addAnnotatedClass(Job.class);*/

		return sessionBuilder.buildSessionFactory();

	}

	@Autowired
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		return properties;
	}


	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAOImpl getUserDAO(SessionFactory sessionFactory) {

		return new UserDAOImpl(sessionFactory);
	}
/*
	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAOImpl getBlogDAO(SessionFactory sessionFactory) {

		return new BlogDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "forumDAO")
	public ForumDAOImpl getForumDAO(SessionFactory sessionFactory) {

		return new ForumDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "newsFeedsDAO")
	public NewsFeedsDAOImpl getNewsFeedsDAO(SessionFactory sessionFactory) {

		return new NewsFeedsDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "jobDAO")
	public JobDAOImpl getJobDAO(SessionFactory sessionFactory) {

		return new JobDAOImpl(sessionFactory);
	}*/
}
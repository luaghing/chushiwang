package org.springframework.samples.mvc.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lys on 2018/10/4.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.springframework.samples.mvc.jpa.repository")
public class DatasourceConfig {

	/**
	 * serverTimezone=UTC
	 * mysql-connect-java 6.0.6 要加上面这个..不知道为啥
	 *
	 * @return datasource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}


	@Bean(value = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("org.springframework.samples.mvc.jpa.entity");
//		bean.setPersistenceUnitName("name");
//		bean.setPersistenceProvider(persistenceProvider());
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.connection.charSet", "UTF-8");
		bean.setJpaPropertyMap(properties);
		return bean;
	}

	@Bean("transactionManager")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setDataSource(dataSource());
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		return manager;
	}

}

package com.stockmarket.stock.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionFactoryFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.convert.SchemaFactory;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

	/*
	 * Use the standard Cassandra driver API to create a
	 * com.datastax.oss.driver.api.core.CqlSession instance.
	 */
	@Primary
	public @Bean CqlSession session() {
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {
			return session;
		}
	}

	@Override
	protected String getKeyspaceName() {
		
		return "stockmarket";
	}
	
//	@Primary
//	@Bean
//	@Qualifier(value = "sessionFactory")
//	  public SessionFactoryFactoryBean sessionFactory(CqlSession session, CassandraConverter converter) {
//
//	    SessionFactoryFactoryBean sessionFactory = new SessionFactoryFactoryBean();
//	    sessionFactory.setSession(session);
//	    sessionFactory.setConverter(converter);
//	    sessionFactory.setSchemaAction(SchemaAction.NONE);
//
//	    return sessionFactory;
//	  }
	
	@Bean
	public CassandraConverter cassandraConverter() {
		return new MappingCassandraConverter();
	}
//
//	@Primary
//	@Bean
//	public CassandraOperations cassandraTemplate(SessionFactory sessionFactory, CassandraConverter convertor) {
//		return new CassandraAdminTemplate(sessionFactory, convertor);
//	}

//
//	@Override
//	protected String getKeyspaceName() {
//		return "stockmarket";
//	}
}
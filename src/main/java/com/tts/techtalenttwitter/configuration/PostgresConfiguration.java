package com.tts.techtalenttwitter.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class PostgresConfiguration {	
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
				
		URI dbUri = new URI(dotenv.get("DATABASE_URL"));
		//The URI class can be used to break apart a URI into various different parts.
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = 
			"jdbc:postgresql://" + 
			dbUri.getHost() + 
			":" + dbUri.getPort() + 
			dbUri.getPath() +
			"?sslmode=require";
					
		
		DataSourceBuilder<? extends DataSource> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver")
						 .url(dbUrl)
						 .username(username)
						 .password(password);
		
		return dataSourceBuilder.build();
	}
	
}

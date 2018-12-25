package com.example.bookstore.dataHandler;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class springDatasourceConfig {

//	@Bean
	public DataSource getDataSource() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
		        .setType(EmbeddedDatabaseType.H2)
		        .build();
	    return dataSource;
	}
}

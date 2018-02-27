package ru.evgeny.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.evgeny.dao.interfaces.MP3Dao;
import ru.evgeny.dao.obj.MP3;


@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(MP3 mp3) {
		String sql = "INSERT INTO mp3 (name, author) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { mp3.getName(), mp3.getAuthor() });
	}

	

	@Override
	public void delete(MP3 mp3) {
		String sql = "DELETE FROM mp3 WHERE name = ? AND  author = ?";
		jdbcTemplate.update(sql, new Object[] {mp3.getName(), mp3.getAuthor()});

	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM mp3 WHERE id = ? ";
		int result = jdbcTemplate.update(sql,id);
	}

	@Override
	public List<MP3> getMP3ListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP3> getMP3ListByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MP3 getMP3byID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(List<MP3> mp3) {
		for(MP3 m: mp3)
			insert(m);
		
	}

	

	
	
	
}

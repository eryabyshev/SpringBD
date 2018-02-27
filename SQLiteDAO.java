package ru.evgeny.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ru.evgeny.dao.interfaces.MP3Dao;
import ru.evgeny.dao.obj.MP3;


@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void insert(MP3 mp3) {
		String sql = "INSERT INTO mp3 (name, author) VALUES (:name, :author)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", mp3.getName());
		parameterSource.addValue("author", mp3.getAuthor());
		jdbcTemplate.update(sql, parameterSource);
	}

	

	@Override
	public void delete(MP3 mp3) {
		String sql = "DELETE FROM mp3 WHERE name = :name AND  author = :author";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", mp3.getName());
		parameterSource.addValue("author", mp3.getAuthor());
		jdbcTemplate.update(sql, parameterSource);

	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM mp3 WHERE id = :id ";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		int result = jdbcTemplate.update(sql,parameterSource);
	}

	@Override
	public List<MP3> getMP3ListByName(String name) {
		String sql = "SELECT * FROM mp3 WHERE UPPER(name) LIKE :name";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", "%" + name.toUpperCase() + "%");
		return jdbcTemplate.query(sql, parameterSource, new MP3RowMapper());
	}

	@Override
	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "SELECT * FROM mp3 WHERE UPPER(author) LIKE :author";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", "%" + author.toUpperCase() + "%");
		return jdbcTemplate.query(sql, parameterSource, new MP3RowMapper());
	}

	@Override
	public MP3 getMP3byID(int id) {
		String sql = "SELECT * FFROM mp3 WHERE id = :id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		return jdbcTemplate.queryForObject(sql, parameterSource, new MP3RowMapper());
	}

	@Override
	public void insert(List<MP3> mp3) {
		for(MP3 m: mp3)
			insert(m);
		
	}
	
	
	public static final class  MP3RowMapper implements RowMapper<MP3> {
		@Override
		public MP3 mapRow(ResultSet arg0, int arg1) throws SQLException {
			MP3 mp3 = new MP3();
			mp3.setName(arg0.getString("name"));
			mp3.setAuthor(arg0.getString("author"));
			return mp3;
		}
		
	}


	@Override
	public int getMP3Count() {
		String sql = "SELECT count(*) FROM mp3";
	
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}
}

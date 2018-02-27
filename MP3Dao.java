package ru.evgeny.dao.interfaces;

import java.util.List;

import ru.evgeny.dao.obj.MP3;

public interface MP3Dao {
	void insert(MP3 mp3);
	
	void delete(MP3 mp3);
	
	void delete(int id);
	
	void insert(List<MP3> mp3);
	
	MP3 getMP3byID(int id);
	
	List<MP3> getMP3ListByName(String name);
	
	List<MP3> getMP3ListByAuthor(String author);
	
	int getMP3Count();
}

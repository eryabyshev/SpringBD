package ru.evgeny.main;



import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.evgeny.dao.impl.SQLiteDAO;
import ru.evgeny.dao.obj.MP3;

public class Start {

	public static void main(String[] args) {
		MP3 mp3 = new MP3();
		
		mp3.setName("New Song1");
		mp3.setAuthor("New Author2");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqLiteDAO = (SQLiteDAO)context.getBean("sqliteDAO");
		
		System.out.println("Count:");
		System.out.println(sqLiteDAO.getMP3Count());
		
		List<MP3> name = sqLiteDAO.getMP3ListByName("a");
		
		for(MP3 a : name) {
			System.out.println(a.getName() + " \t " + a.getAuthor());
		}
		

	}

}

package com.example.demo.Config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Models.BookMark;
import com.example.demo.Services.BookMarkService;

public class InitDb  implements CommandLineRunner{

	@Autowired
	private BookMarkService bookMarkService ;
	@Override
	public void run(String... args) throws Exception {
		bookMarkService.deleteAll(); 
		BookMark b1 = new BookMark(null,"title1","www.title1.com",Instant.now());
		BookMark b2 = new BookMark(null,"title2","www.title2.com",Instant.now());
		BookMark b3 = new BookMark(null,"title3","www.title3.com",Instant.now());
		BookMark b4 = new BookMark(null,"title4","www.title4.com",Instant.now());
		BookMark b5 = new BookMark(null,"title5","www.title5.com",Instant.now());
		BookMark b6 = new BookMark(null,"title6","www.title6.com",Instant.now());
		bookMarkService.AddBook(b1);
		bookMarkService.AddBook(b2);
		bookMarkService.AddBook(b3);
		bookMarkService.AddBook(b4);
		bookMarkService.AddBook(b5);
		bookMarkService.AddBook(b6);
		
	}

}

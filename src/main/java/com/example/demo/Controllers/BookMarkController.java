package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BookMarkDto;
import com.example.demo.Models.BookMark;
import com.example.demo.Services.BookMarkService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/book/") 
@Slf4j
public class BookMarkController {

	@Autowired
	private BookMarkService bookMarkService ;

	
	@GetMapping("All/{page}")
	public BookMarkDto AllBook(@PathVariable  Integer page) {
			log.info("lists of Books");
		return bookMarkService.get_All(page);
	}

	@GetMapping("All")
	public List<BookMark> AllBooks() {
			log.info("lists of Books");
		return bookMarkService.all_Books();
	}

	@PostMapping("Add")
	public BookMark AddBook(@RequestBody BookMark bookMark)
	{
		return bookMarkService.AddBook(bookMark);
	}
}

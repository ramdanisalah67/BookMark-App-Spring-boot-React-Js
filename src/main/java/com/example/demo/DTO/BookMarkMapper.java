package com.example.demo.DTO;

import org.springframework.stereotype.Component;

import com.example.demo.Models.BookMark;

@Component
public class BookMarkMapper {

	
	
	public BookDTO ToDto(BookMark b) {
		BookDTO bk = new BookDTO();
		bk.setId(b.getId());
		bk.setTitle(b.getTitle());
		bk.setUrl(b.getUrl());
		bk.setCreatedAt(b.getCreatedAt());
		return bk ;
	}
}

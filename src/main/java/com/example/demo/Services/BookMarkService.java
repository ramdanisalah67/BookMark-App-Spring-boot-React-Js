package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.BookMarkDto;
import com.example.demo.DTO.BookMarkMapper;
import com.example.demo.Models.BookMark;
import com.example.demo.Repositories.BookMarkRepository;

@Service
public class BookMarkService {

	
	@Autowired
	private BookMarkRepository bookMarkRepository ;
	
	public List<BookMark> all_Books(){
		return bookMarkRepository.findAll();
	}

	public BookMarkDto get_All(Integer page){
		
		int pageNo = page<1 ? 0 : page-1 ;
		Pageable pageable = PageRequest.of(pageNo, 2,Sort.Direction.DESC,"title") ;
		
		return new BookMarkDto( bookMarkRepository.findBookMark(pageable));
	}
	
	public BookMark AddBook(BookMark b) {
		return bookMarkRepository.save(b);
	}
	public void deleteAll() {
		bookMarkRepository.deleteAll();
	}

}

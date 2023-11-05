package com.example.demo.DTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Models.BookMark;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class BookMarkDto {

	
	private List<BookDTO> data ;
	private int totalElements ;
	private int totalPages ;
	private int currentPage ;
	@JsonProperty("isFirst")
	private boolean isFirst ;
	@JsonProperty("isLast")
	private boolean isLAst ;
	private boolean hasNext ;
	private boolean hasPrevious ;
	public BookMarkDto(Page<BookDTO> bookmarkPage)
	{
		this.setData(bookmarkPage.getContent());
		this.setTotalElements(bookmarkPage.getNumberOfElements());
		this.setTotalPages(bookmarkPage.getTotalPages());
		this.setCurrentPage(bookmarkPage.getNumber());
		this.setFirst(bookmarkPage.isFirst());
		this.setLAst(bookmarkPage.isLast());
		this.setHasNext(bookmarkPage.hasNext());
		this.setHasPrevious(bookmarkPage.hasPrevious());
		
	}
	
	
	
	
	
}

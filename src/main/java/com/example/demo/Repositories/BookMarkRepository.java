package com.example.demo.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Models.BookMark;


@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

	@Query("select new com.example.demo.DTO.BookDTO(b.id,b.title,b.url,b.CreatedAt) from BookMark b")
	public Page<BookDTO> findBookMark(Pageable p);
}

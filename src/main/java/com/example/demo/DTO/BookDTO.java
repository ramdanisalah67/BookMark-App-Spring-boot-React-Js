package com.example.demo.DTO;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

	private Long id ;
	private String title ;
	private String url ;
	private Instant CreatedAt ;
	
}

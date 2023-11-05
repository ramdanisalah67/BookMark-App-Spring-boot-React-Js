package com.example.demo.Models;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookMark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long Id ; 
	@Column(nullable = false)
	private String title ;
	@Column(nullable = false) 
	private String url ;
	
	private Instant CreatedAt ;
}
  
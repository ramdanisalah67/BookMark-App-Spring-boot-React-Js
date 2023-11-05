package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MySQLContainer;

import com.example.demo.Controllers.BookMarkController;
import com.example.demo.Models.BookMark;
import com.example.demo.Services.BookMarkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class BookMarkAppApplicationTests {

	@Autowired
	private MockMvc mockMvc ;
	
	private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
	
	@Autowired
	private BookMarkService bookMarkService;
	@DynamicPropertySource
	public static void ConfigureProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		
		dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);

		
	}
	
	@BeforeAll
	public static void beforeAll() {
		mySQLContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		mySQLContainer.stop();
	}
	
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(BookMarkController.class).build();
	}


	
	@Test
	
	public void addManyBooks() {
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
		log.info("size of elements : => "+bookMarkService.all_Books().size());
		
		
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"1,2,3"
	})
	
	public void ShouldGetBookMarks(int pageNo,int totalElements,int totalPages) throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/book/All/"+pageNo)
				
				.accept("application/json")
				.contentType("application/json")
				)
	.andExpect(status().isOk())
	.andExpect(MockMvcResultMatchers.jsonPath("$.totalElements",CoreMatchers.equalTo(totalElements)))
	.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages",CoreMatchers.equalTo(totalPages)));

	
	}
	
	
	
}



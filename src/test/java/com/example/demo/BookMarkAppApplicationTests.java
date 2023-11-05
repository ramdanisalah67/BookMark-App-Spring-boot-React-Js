package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
class BookMarkAppApplicationTests {

	@Autowired
	private MockMvc mockMvc ;
	
	private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
	
	
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
	
	public void getAllBooksTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
					.get("/api/book/All")
					
					.accept("application/json")
					.contentType("application/json")
					)
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"));
		
	}

	@Test	
	
	public void AddBook() throws Exception {
	ObjectMapper obj = new ObjectMapper();
	obj.registerModule(new JavaTimeModule());
	BookMark b = new BookMark(null, "salah-eddine", "www.salah.com", Instant.now());
		mockMvc.perform(MockMvcRequestBuilders
					.post("/api/book/Add")
					.contentType("application/json")
					.content(obj.writeValueAsString(b))

					.accept("application/json")
					)
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		
	}
}



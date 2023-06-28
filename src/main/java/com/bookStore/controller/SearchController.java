package com.bookStore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import com.bookStore.repository.MyBookRepository;

@RestController
public class SearchController {
	
	@Autowired
	private BookRepository brepo;
	@Autowired
	private MyBookRepository mbrepo;
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal){
		List<Book> result = this.brepo.findByNameContaining(query); 
		
		return ResponseEntity.ok(result);
	}
}

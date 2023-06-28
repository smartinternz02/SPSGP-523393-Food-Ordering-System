package com.bookStore.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import java.util.*;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("home")
	public String home2() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	@GetMapping("/register_user")
	public String registerUser() {
		return "registerUser";
	}
	@GetMapping("/ty")
	public String ThankYou() {
		return "Thankyou";
	}
	/*@GetMapping("/login")
	public String Login() {
		return "Login";
	}*/
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Perform validation and authentication logic here
        if (email.equals("m@g.com") && password.equals("password")) {
            // Successful login
            return "redirect:/book_register"; 
        } else {
            // Invalid credentials
            return "redirect:/login?error"; 
        }
	}    
	@PostMapping("/user")
	public String login1(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Perform validation and authentication logic here
        if (email.equals("m@g.c") && password.equals("1234")) {
            // Successful login
            return "redirect:/book_register"; 
        } else {
            // Invalid credentials
            return "redirect:/user?error"; 
        }
	} 
	@GetMapping("/register")
	public String Register() {
		return "register";
	}
	@GetMapping("/avail_books")
	public String Available() {
		return "bookList";
	}
	@GetMapping("/payment_book")
	public String Pay() {
		return "payment";
	}

	@RequestMapping("/login")
    public String showLoginForm() {
        return "login";
    }
	@RequestMapping("/hotel_login")
    public String showLoginFormAd() {
        return "HotelLogin";
    }
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}
	@GetMapping("/available_books2")
	public ModelAndView getAllBook2() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList2","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books2";
	}
	
}

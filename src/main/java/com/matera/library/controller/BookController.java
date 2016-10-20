package com.matera.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.matera.library.functions.ReadFile;
import com.matera.library.model.Book;
import com.matera.library.service.BookService;

@Controller
@RequestMapping("/library")
public class BookController {

	@Autowired
	private BookService bookService;
	private ReadFile read = new ReadFile();

	@RequestMapping("/list")
	public ModelAndView newMV() {
		ModelAndView mv = new ModelAndView("library");
		Book book = new Book();
		mv.addObject("bookList",bookService.save(book, read));
		return mv;
	}
}

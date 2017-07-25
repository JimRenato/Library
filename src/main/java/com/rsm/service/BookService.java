package com.rsm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rsm.functions.ReadFile;
import com.rsm.model.Book;
import com.rsm.repository.Books;

@Service
public class BookService {

	@Autowired
	private Books books;

	public List<Book> save(Book book, ReadFile read) {

		List<Book> listBook = new ArrayList<>();
		listBook = readFile(read);

		for (int i = 0; i < listBook.size(); i++) {
			book.setId(listBook.get(i).getId());
			book.setDescription(listBook.get(i).getDescription());
			book.setPrice(listBook.get(i).getPrice());
			book.setDate(listBook.get(i).getDate());

			try {
				books.save(book);
			} catch (DataIntegrityViolationException e) {
				throw new IllegalArgumentException("Invalid date format!");
			}
		}
		return listBook;
	}

	private List<Book> readFile(ReadFile read) {
		return read.formatData(read.read());
	}

}

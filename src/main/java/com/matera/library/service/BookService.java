package com.matera.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matera.library.functions.ReadFile;
import com.matera.library.model.Book;
import com.matera.library.repository.Books;

@Service
public class BookService {

	@Autowired
	private Books books;

	public List<Book> save(Book book, ReadFile read) {

		List<Book> listBook = new ArrayList<Book>();
		listBook = readFile(read);

		for (int i = 0; i < listBook.size(); i++) {
			book.setId(listBook.get(i).getId());
			book.setDescricao(listBook.get(i).getDescricao());
			book.setPreco(listBook.get(i).getPreco());
			book.setData(listBook.get(i).getData());

			try {
				books.save(book);
			} catch (DataIntegrityViolationException e) {
				throw new IllegalArgumentException("Formato de data inválido!");
			}
		}
		return listBook;
	}

	private List<Book> readFile(ReadFile read) {
		return read.formatData(read.read());
	}

}

package com.matera.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matera.library.model.Book;

public interface Books extends JpaRepository<Book, String> {

}

package com.rsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsm.model.Book;

public interface Books extends JpaRepository<Book, String> {

}

package com.company.Summative2AndriamasinoroMandresy.repositories;

import com.company.Summative2AndriamasinoroMandresy.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthorId(int author_id);

}

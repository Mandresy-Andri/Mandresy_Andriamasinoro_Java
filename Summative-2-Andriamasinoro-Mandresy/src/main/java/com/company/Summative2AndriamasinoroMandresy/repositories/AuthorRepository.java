package com.company.Summative2AndriamasinoroMandresy.repositories;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByFirstName(String firstName);
}

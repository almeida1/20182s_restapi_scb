package com.scb.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.model.Livro;
public interface LivroRepository extends JpaRepository<Livro,String> {

}

package com.scb.scb;


import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.scb.model.Livro;
import com.scb.repository.LivroRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class LivroRepositoryTest {

    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LivroRepository livroRepository;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

  
    @Test
    public void whenFindAll() {
        //given
        Livro livro1 = new Livro();
        livro1.setIsbn("3333");
        livro1.setAutor("Larman");
        livro1.setTitulo("UML e Padrões");
        
        entityManager.persist(livro1);
        entityManager.flush();

        Livro livro2 = new Livro();
        livro2.setIsbn("4444");
        livro2.setAutor("Martin");
        livro2.setTitulo("Código Limpo");
        
        entityManager.persist(livro2);
        entityManager.flush();

        //when
        List<Livro> lista = livroRepository.findAll();

        //then
        assertThat(lista.size()).isEqualTo(2);
        assertThat(lista.get(0)).isEqualTo(livro1);
        assertThat(lista.get(1)).isEqualTo(livro2);
        livroRepository.delete(livro1);
        livroRepository.delete(livro2);
        
    }
    
    public void whenExceptionThrown_thenExpectationSatisfied() {
    	 //given
        Livro livro1 = new Livro();
        livro1.setIsbn("3333");
        livro1.setAutor("Larman");
        livro1.setTitulo("UML e Padrões");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Duplicate entry '3333' for key 'PRIMARY");
    	 entityManager.persist(livro1);
         entityManager.flush();
         
    }
}
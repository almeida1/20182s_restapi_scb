package com.scb.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.model.Livro;
import com.scb.repository.LivroRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value= "API REST Bilbioteca")
@RestController
@RequestMapping("/livro")
public class LivroResources {
	@Autowired // injecao de dependencia
	private LivroRepository er;
	
	@ApiOperation(value=" retorna uma lista de livros")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Livro> listaLivros() {
		Iterable<Livro> listaLivros = er.findAll();
		return listaLivros;
	}
	@ApiOperation(value=" cadastra um livro")
	@PostMapping()
	public Livro CadastraLivro(@RequestBody Livro livro){
		return er.save(livro);
		
	}
	@ApiOperation(value=" deleta um livro")
	@DeleteMapping()
	public Livro deleteLivro (@RequestBody Livro livro){
		er.delete(livro);
		return livro;
	}
}

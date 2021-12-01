package br.org.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/findAllPostagem")
@CrossOrigin(origins="*",allowedHeaders="*")
public class PostagemController {
		
		@Autowired
		private PostagemRepository postagemRepository; // tipo - objeto
		
		@GetMapping
		public ResponseEntity <List<Postagem>> getAll() // uma lista que contem varios objetos
		{ 
			return ResponseEntity.ok(postagemRepository.findAll()); // equilave a select * from tb_postagens
		}
		
		/*@GetMapping
		public ResponseEntity <List<Long>> getId() 
		{ 
			return ResponseEntity.ok(postagemRepository.findById());
		}

		@GetMapping
		public ResponseEntity <List<Postagem>> getByTitulo() 
		{ 
			return ResponseEntity.ok(postagemRepository.getByTitulo());
		}*/
}

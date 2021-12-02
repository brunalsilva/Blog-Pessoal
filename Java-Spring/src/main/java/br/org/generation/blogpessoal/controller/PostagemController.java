package br.org.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins="*",allowedHeaders="*")
public class PostagemController {
		
		@Autowired
		private PostagemRepository postagemRepository; // tipo - objeto
		
		@GetMapping
		public ResponseEntity <List<Postagem>> getAll() // uma lista que contem varios objetos
		{ 
			return ResponseEntity.ok(postagemRepository.findAll()); // equilave a select * from tb_postagens
		}
		
		@GetMapping("/{id}")
		public ResponseEntity <Postagem> getById(@PathVariable Long id) 
		{ 
			return postagemRepository.findById(id)
					.map(resp -> ResponseEntity.ok(resp)) //função lambda
					.orElse(ResponseEntity.notFound().build());
		} // select * from tb_postagens where id = x;
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo)
		{ 
			return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		}
		
		@PostMapping
		public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem)
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		}
		
		@PutMapping
		public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem, Long id) //equivale ao update do MySQL
		{
			boolean checkId = postagemRepository.existsById(id);
			if (checkId = true)
				{
				return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
				}
			else
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postagemRepository.getById(id));
			}
		} 
		
		@DeleteMapping("/{id}")
		public void deletePostagem(@PathVariable Long id)
		{
			boolean checkId = postagemRepository.existsById(id);
			if (checkId = true)
			{
			postagemRepository.deleteById(id);
			}
			else
			{
				/*return ResponseEntity.status(HttpStatus.NOT_FOUND).getById(id));
				return postagemRepository.existsById(id)
						.map(resp -> ResponseEntity.ok(resp)) 
						.orElse(ResponseEntity.notFound().build());*/
			}
		}
}

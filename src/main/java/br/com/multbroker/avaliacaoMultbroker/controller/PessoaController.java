package br.com.multbroker.avaliacaoMultbroker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;
import br.com.multbroker.avaliacaoMultbroker.model.PessoaRepository;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@GetMapping("/pessoa/lista")
	public ResponseEntity<List<Pessoa>> lista() {
		
		if (repository.findAll().isEmpty()) {
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Pessoa>>(repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		
		if (repository.findById(id).isPresent()) {
			return new ResponseEntity<Pessoa>(repository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/pessoa/cria")
	public ResponseEntity<Pessoa> cria(@RequestBody Pessoa novaPessoa) {
		repository.save(novaPessoa);
		return new ResponseEntity<Pessoa>(novaPessoa, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/pessoa/{id}/remove")
	public ResponseEntity<Pessoa> deleta(@PathVariable("id") Long id) {
		
		if (repository.findById(id).isPresent()) {
			repository.delete(repository.findById(id).get());
			
			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/pessoa/{id}/altera")
	public ResponseEntity<Pessoa> atualiza(@PathVariable("id") Long id, @RequestBody Pessoa alteraPessoa) {
		
		if (repository.findById(id).isPresent()) {
			Pessoa pessoa = repository.findById(id).get();
			pessoa.setNome(alteraPessoa.getNome());
			pessoa.setIdade(alteraPessoa.getIdade());
			pessoa.setEmail(alteraPessoa.getEmail());
			
			repository.save(pessoa);
			
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
	}
}
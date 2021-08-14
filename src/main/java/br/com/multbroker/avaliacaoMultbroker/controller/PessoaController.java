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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.multbroker.avaliacaoMultbroker.converter.ConversorPessoa;
import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;
import br.com.multbroker.avaliacaoMultbroker.model.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository; 
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> lista() {
		
		if (repository.findAll().isEmpty()) {
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Pessoa>>(repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		
		if (repository.findById(id).isPresent()) {
			return new ResponseEntity<Pessoa>(repository.findById(id).get(), HttpStatus.OK);
		} 
			
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/busca-por/")
	public ResponseEntity<Pessoa> buscaPorNome(@RequestParam("nome") String nome) {
			
		if (repository.findByNome(nome.toLowerCase()) != null) {			
			return new ResponseEntity<Pessoa>(repository.findByNome(nome.toLowerCase()), HttpStatus.OK);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);

	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cria(@RequestBody Pessoa novaPessoa) {
		Pessoa pessoa = new ConversorPessoa().moveEconverteMinusculo(null, novaPessoa);
		repository.save(pessoa);
		
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> deleta(@PathVariable("id") Long id) {
		
		if (repository.findById(id).isPresent()) {
			repository.delete(repository.findById(id).get());
			
			return new ResponseEntity<Pessoa>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualiza(@PathVariable("id") Long id, @RequestBody Pessoa alteraPessoa) {
		
		if (repository.findById(id).isPresent()) {
			Pessoa pessoa = new ConversorPessoa().moveEconverteMinusculo(repository.findById(id).get(), alteraPessoa);
			
			repository.save(pessoa);
			
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}
}
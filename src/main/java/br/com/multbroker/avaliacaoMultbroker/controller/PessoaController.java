package br.com.multbroker.avaliacaoMultbroker.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.multbroker.avaliacaoMultbroker.converter.ConversorPessoa;
import br.com.multbroker.avaliacaoMultbroker.dao.PessoaDao;
import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@GetMapping("/pessoa/lista")
	public ResponseEntity<List<Pessoa>> lista() {
		
		if (pessoaDao.findAll().isEmpty()) {
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Pessoa>>(pessoaDao.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		
		if (pessoaDao.findById(id) != null) {
			return new ResponseEntity<Pessoa>(pessoaDao.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/pessoa")
	public ResponseEntity<Pessoa> buscaPorNome(@RequestParam("nome") String nome) {
		
		try {
			Pessoa pessoa = pessoaDao.findByName(nome);
			
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
			
		} catch(NoResultException e) {
			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
		}

	}
	
	@Transactional
	@PostMapping("/pessoa/cria")
	public ResponseEntity<Pessoa> cria(@RequestBody Pessoa novaPessoa) {
		Pessoa pessoa = new ConversorPessoa().moveEconverteMinusculo(null, novaPessoa);
		pessoaDao.create(pessoa);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
	}
	
	@Transactional
	@DeleteMapping("/pessoa/{id}/remove")
	public ResponseEntity<Pessoa> deleta(@PathVariable("id") Long id) {
		
		if (pessoaDao.findById(id) != null) {
			pessoaDao.delete(pessoaDao.findById(id));
			
//			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}
	
	@Transactional
	@PutMapping("/pessoa/{id}/altera")
	public ResponseEntity<Pessoa> atualiza(@PathVariable("id") Long id, @RequestBody Pessoa alteraPessoa) {
		
		if (pessoaDao.findById(id) != null) {
			Pessoa pessoa = new ConversorPessoa().moveEconverteMinusculo(pessoaDao.findById(id), alteraPessoa);
			
			pessoaDao.update(pessoa);
			
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
		
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}
}
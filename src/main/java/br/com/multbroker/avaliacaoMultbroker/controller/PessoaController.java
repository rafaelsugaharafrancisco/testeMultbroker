package br.com.multbroker.avaliacaoMultbroker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/pessoa")
	public List<Pessoa> lista() {
		return repository.findAll();
	}
	
	@GetMapping("/pessoa/{id}")
	public Optional<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/pessoa")
	public void cria(@RequestBody Pessoa novaPessoa) {
		repository.save(novaPessoa);
	}
	
	@DeleteMapping("/pessoa/{id}")
	public void deleta(@PathVariable("id") Long id) {
        repository.deleteById(id);
	}
	
	@PutMapping("/pessoa")
	public void atualiza( @RequestBody Pessoa pessoa) {
		
		Optional<Pessoa> buscaPessoa = repository.findById(pessoa.getId());
		
		if(buscaPessoa.isPresent()) {
			Pessoa pessoaEncontrada = buscaPessoa.get();
			pessoaEncontrada.setNome(pessoa.getNome());
			pessoaEncontrada.setIdade(pessoa.getIdade());
			pessoaEncontrada.setEmail(pessoa.getEmail());
			
			repository.save(pessoaEncontrada);
		}
	}
}

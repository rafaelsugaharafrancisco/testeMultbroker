package br.com.multbroker.avaliacaoMultbroker.controller;

import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/pessoa")
	public List<Pessoa> lista() {
		return repository.findAll();
	}
	
	@GetMapping("/pessoa/{id}")
	public Optional<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/pessoa")
	public ResponseEntity<String> cria(@RequestBody Pessoa novaPessoa) {
		repository.save(novaPessoa);
		return new ResponseEntity<String>("Pessoa " + novaPessoa.getNome() + " registrada com sucesso", HttpStatus.OK);
	}
	
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<String> deleta(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity<String>("Pessoa com id " + id + " excluido com sucesso", HttpStatus.OK);
	}
	
	@PutMapping("/pessoa")
	public ResponseEntity<String> atualiza( @RequestBody Pessoa pessoa) {
		
		Optional<Pessoa> buscaPessoa = repository.findById(pessoa.getId());
		
		if(buscaPessoa.isPresent()) {
			Pessoa pessoaEncontrada = buscaPessoa.get();
			pessoaEncontrada.setNome(pessoa.getNome());
			pessoaEncontrada.setIdade(pessoa.getIdade());
			pessoaEncontrada.setEmail(pessoa.getEmail());
			
			repository.save(pessoaEncontrada);
			
			return new ResponseEntity<String>("Pessoa " + pessoaEncontrada.getNome() + " atualizado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Pessoa com id " + pessoa.getId() + " não encontrada", HttpStatus.NOT_FOUND);
		}
		
	}
}

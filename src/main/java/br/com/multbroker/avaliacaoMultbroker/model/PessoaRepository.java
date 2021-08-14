package br.com.multbroker.avaliacaoMultbroker.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	public Optional<Pessoa> findByNome(String nome);

}

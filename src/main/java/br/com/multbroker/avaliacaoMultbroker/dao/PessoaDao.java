package br.com.multbroker.avaliacaoMultbroker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;

@Repository
public class PessoaDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Pessoa> findAll() {
		return em.createQuery("select p from pessoa p", Pessoa.class).getResultList();
	}
	
	public Pessoa findById(Long id) {
		return em.find(Pessoa.class, id);
	}
	
	public Pessoa findByName(String nome) throws NoResultException {
		
		return em.createQuery("select p from pessoa p where p.nome = :nome", Pessoa.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public void create(Pessoa pessoa) {
		em.persist(pessoa);
	}
	
	public Pessoa update(Pessoa pessoa) {
		return em.merge(pessoa);
	}
	
	public void delete(Pessoa pessoa) {
		em.remove(pessoa);
	}
}

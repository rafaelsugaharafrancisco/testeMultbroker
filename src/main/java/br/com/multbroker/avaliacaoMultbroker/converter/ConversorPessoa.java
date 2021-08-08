package br.com.multbroker.avaliacaoMultbroker.converter;

import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;

public class ConversorPessoa {
	
	public Pessoa validaEconverteMinusculo(Pessoa pessoa, Pessoa pessoaRequisicao) {
		
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		
		if (pessoaRequisicao.getIdade()!= null) {
			pessoa.setIdade(pessoaRequisicao.getIdade());
		}
		
		if (pessoaRequisicao.getNome() != null) {
			pessoa.setNome(pessoaRequisicao.getNome().toLowerCase());
		}
		
		if (pessoaRequisicao.getEmail() != null) {
			pessoa.setEmail(pessoaRequisicao.getEmail().toLowerCase());
		}
		
		return pessoa;
	}
}

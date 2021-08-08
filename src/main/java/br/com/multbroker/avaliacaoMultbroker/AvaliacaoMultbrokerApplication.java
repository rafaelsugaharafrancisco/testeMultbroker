package br.com.multbroker.avaliacaoMultbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;

//import br.com.multbroker.avaliacaoMultbroker.model.Pessoa;
//import br.com.multbroker.avaliacaoMultbroker.model.PessoaRepository;

@SpringBootApplication
public class AvaliacaoMultbrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoMultbrokerApplication.class, args);
//		Pessoa rafael = new Pessoa();
//		rafael.setNome("Rafael");
//		rafael.setIdade(37);
//		rafael.setEmail("rafa.s.francisco@gmail.com");
//		
//		Pessoa fulano = new Pessoa();
//		fulano.setNome("Fulano");
//		fulano.setIdade(37);
//		fulano.setEmail("fulano@gmail.com");
//		
//		PessoaRepository repository = ctx.getBean(PessoaRepository.class);
//		repository.save(rafael);
//		repository.save(fulano);
	}

}

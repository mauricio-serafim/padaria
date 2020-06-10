package br.faj.padaria;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.faj.padaria.domain.model.Categoriaproduto;
import br.faj.padaria.domain.model.Permissao;
import br.faj.padaria.domain.model.Unidade;
import br.faj.padaria.domain.model.Usuario;
import br.faj.padaria.persistence.repository.CategoriaprodutoRepository;
import br.faj.padaria.persistence.repository.PermissaoRepository;
import br.faj.padaria.persistence.repository.UnidadeRepository;
import br.faj.padaria.persistence.repository.UsuarioRepository;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private CategoriaprodutoRepository categoriaprodutoRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostConstruct
	public void init() {
		usuarioRepository.save(new Usuario("padaria", "$2a$10$gNM6TT6ezJgmMcS0G8s8mOvOK7Fuyp5Kz.ymW4VAMeNX6MW.y6Nky"));

		Permissao role_cadastro_usuario = new Permissao("ROLE_CADASTRO_USUARIO", "Permite cadastrar novos usuários",
				"ROLE_CADASTRO_USUARIO");
		Permissao role_consulta_usuario = new Permissao("ROLE_CONSULTA_USUARIO", "Permite a consulta de usuários",
				"ROLE_CONSULTA_USUARIO");
		Permissao role_admin = new Permissao("ROLE_ADMIN", "Admimnistrador", "ROLE_ADMIN");
		permissaoRepository.saveAll(Arrays.asList(role_cadastro_usuario, role_consulta_usuario, role_admin));

		Categoriaproduto bebida = new Categoriaproduto("B", "Bebida");
		Categoriaproduto lanche = new Categoriaproduto("L", "Lanche");
		categoriaprodutoRepository.saveAll(Arrays.asList(bebida, lanche));
		
		Unidade kg = new Unidade("kg", "quilograma");
		Unidade un = new Unidade("un", "Unidade");
		unidadeRepository.saveAll(Arrays.asList(kg, un));
	}
}

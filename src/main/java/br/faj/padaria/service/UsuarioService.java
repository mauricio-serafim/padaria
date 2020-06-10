package br.faj.padaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.faj.padaria.domain.model.Grupoacesso;
import br.faj.padaria.domain.model.Permissao;
import br.faj.padaria.domain.model.Usuario;
import br.faj.padaria.domain.security.UsuarioSecurity;
import br.faj.padaria.domain.view.UsuarioView;
import br.faj.padaria.persistence.repository.GrupoacessoRepository;
import br.faj.padaria.persistence.repository.PermissaoRepository;
import br.faj.padaria.persistence.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private GrupoacessoRepository grupoacessoRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario usuario = repository.findByNome(nome);

		if (usuario == null)
			throw new BadCredentialsException("Usuário não encontrado ou senha inválida!");

		if (!usuario.isAtivo())
			throw new DisabledException("Usuário desabilitado!");

		return new UsuarioSecurity(usuario.getNome(), usuario.getSenha(), usuario.isAtivo(),
				this.buscarPermissoesUsuario(usuario));
	}

	private List<GrantedAuthority> buscarPermissoesUsuario(Usuario usuario) {
		List<Grupoacesso> gruposacesso = grupoacessoRepository.findByUsuarios(usuario);
		return this.buscarPermissoesDosGrupos(gruposacesso);
	}

	private List<GrantedAuthority> buscarPermissoesDosGrupos(List<Grupoacesso> gruposacesso) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		for (Grupoacesso grupoacesso : gruposacesso) {

			List<Permissao> lista = permissaoRepository.findByGruposacesso(grupoacesso);

			for (Permissao permissao : lista) {
				auths.add(new SimpleGrantedAuthority(permissao.getNome()));
			}
		}

		return auths;
	}

	/***
	 * SALVA UM NOVO REGISTRO DE USUÁRIO
	 * 
	 * @param usuarioView
	 */
	public void salvarUsuario(UsuarioView usuarioView) {

		Usuario usuario = new Usuario();

		/* SETA O USUÁRIO COMO ATIVO NO SISTEMA */
		usuario.setAtivo(true);

		/* LOGIN DO USUÁRIO */
		usuario.setNome(usuarioView.getNome());

		/* NOME DO USUÁRIO A SER SALVO */
		usuario.setNome(usuarioView.getNome());

		/* Criptografa E INFORMA A SENHA */
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioView.getSenha()));

		/* PEGANDO A LISTA DE GRUPOS SELECIONADOS */
		Grupoacesso grupoacesso = null;
		List<Grupoacesso> grupos = new ArrayList<>();
		for (Long codigoGrupo : usuarioView.getGruposacesso()) {

			if (codigoGrupo != null) {

				/* CONSULTA GRUPO POR ID */
				grupoacesso = grupoacessoRepository.findById(codigoGrupo).orElse(null);

				/* ADICIONA O GRUPO NA LISTA */
				grupos.add(grupoacesso);
			}
		}

		/* SETA A LISTA DE GRUPO DO USUÁRIO */
		usuario.setGruposacesso(grupos);

		/* SALVANDO O REGISTRO */
		this.repository.save(usuario);
	}

	/***
	 * CONSULTA OS USUÁRIOS CADASTRADOS
	 * 
	 * @return
	 */
	public List<UsuarioView> consultarUsuarios() {

		List<UsuarioView> views = new ArrayList<>();

		List<Usuario> entities = this.repository.findAll();

		entities.forEach(usuario -> {

			views.add(new UsuarioView(usuario));
		});

		return views;
	}

	/**
	 * DELETA UM USUÁRIO PELO CÓDIGO
	 */
	public void excluir(Long usuario_id) {

		this.repository.deleteById(usuario_id);
	}

	/***
	 * CONSULTA UM USUÁRIO PELO SEU CÓDIGO
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public UsuarioView consultarUsuario(Long codigoUsuario) {

		Usuario entity = this.repository.findById(codigoUsuario).orElse(null);

		List<Long> gruposacesso = new ArrayList<Long>();

		entity.getGruposacesso().forEach(grupo -> {
			gruposacesso.add(grupo.getId());
		});

		return new UsuarioView(entity);

	}

	/**
	 * ALTERA AS INFORMAÇÕES DO USUÁRIO
	 */
	public void alterarUsuario(UsuarioView view) {

		Usuario enitity = this.repository.findById(view.getId()).orElse(null);

		/* USUÁRIO ATIVO OU INATIVO */
		enitity.setAtivo(view.isAtivo());

		/* LOGIN DO USUÁRIO */
		enitity.setNome(view.getNome());

		/* NOME DO USUÁRIO A SER SALVO */
		enitity.setNome(view.getNome());

		/* CRIPTOGRAMA E INFORMA A SENHA */
		if (!StringUtils.isEmpty(view.getSenha()))
			enitity.setSenha(new BCryptPasswordEncoder().encode(view.getSenha()));

		/* PEGANDO A LISTA DE GRUPOS SELECIONADOS */
		Grupoacesso grupoEntity = null;
		List<Grupoacesso> gruposacesso = new ArrayList<Grupoacesso>();
		for (Long gruposacesso_id : view.getGruposacesso()) {

			if (gruposacesso_id != null) {

				/* CONSULTA GRUPO POR CÓDIGO */
				grupoEntity = grupoacessoRepository.findById(gruposacesso_id).orElse(null);

				/* ADICIONA O GRUPO NA LISTA */
				gruposacesso.add(grupoEntity);
			}
		}

		/* SETA A LISTA DE GRUPO DO USUÁRIO */
		enitity.setGruposacesso(gruposacesso);

		/* SALVANDO ALTERAÇÃO DO REGISTRO */
		this.repository.saveAndFlush(enitity);
	}
}

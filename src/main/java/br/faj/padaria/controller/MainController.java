package br.faj.padaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.faj.padaria.domain.model.Produto;
import br.faj.padaria.persistence.repository.CategoriaprodutoRepository;
import br.faj.padaria.persistence.repository.ProdutoRepository;

@Controller
public class MainController {

	@Autowired
	private CategoriaprodutoRepository categoriaprodutoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	/***
	 * ESSE MÉTODO CARREGA A PÁGINA(index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * 
	 * @return
	 */
	@GetMapping
	public String index() {

		return "index";
	}

	/***
	 * CARREGA À PÁGINA INICIAL DA APLICAÇÃO APÓS EFETUARMOS O LOGIN
	 * 
	 * @return
	 */
	@GetMapping("/home")
	public String home() {

		return "home/index";
	}

	@PostMapping("/home")
	public String home2(@RequestParam String nome, @RequestParam String senha) {
		return "home/index";
	}

	/***
	 * MOSTRA UM PÁGINA COM A MENSAGEM DE ACESSO NEGADO QUANDO O USUÁRIO NÃO TIVER
	 * PERMISSÃO DE ACESSAR UMA DETERMINADA FUNÇÃO DO SISTEMA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/acessoNegado", method = RequestMethod.GET)
	public String acessoNegado() {

		return "acessoNegado";
	}

	@GetMapping("/usuarios")
	public String usuario() {
		return "usuarios/index";
	}

	@GetMapping("/vendas")
	public String vendas() {
		return "vendas/index";
	}

	@GetMapping("/estoque")
	public String estoque() {
		return "estoque/index";
	}

	@ResponseBody
	@GetMapping("/lista")
	public List<Produto> listaproduto() {
		List<Produto> produtos = produtoRepository.findAll();

		return produtos;
	}

	@GetMapping("/habilitavenda/{id}")
	public void habilitavenda(@PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).orElse(null);

		produto.setVendaLiberada(true);
		produtoRepository.save(produto);
	}

	@GetMapping("/desabilitavenda/{id}")
	public void desabilitavenda(@PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).orElse(null);

		produto.setVendaLiberada(false);
		produtoRepository.save(produto);
	}
}

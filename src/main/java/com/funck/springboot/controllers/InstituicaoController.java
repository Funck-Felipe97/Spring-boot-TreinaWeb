package com.funck.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.funck.springboot.entidades.Instituicao;
import com.funck.springboot.repository.InstituicaoRepository;

@Controller
@RequestMapping("/instituicoes")
public class InstituicaoController {

	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("instituicao/index");
		List<Instituicao> instituicoes = this.instituicaoRepository.findAll();
		resultado.addObject("instituicoes", instituicoes);
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("/instituicao/inserir");
		resultado.addObject("instituicao", new Instituicao());
		return resultado;
	}
	
	@PostMapping("/inserir")
	public String inserir(Instituicao instituicao) {
		this.instituicaoRepository.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
}

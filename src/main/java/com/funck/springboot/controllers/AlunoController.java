package com.funck.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.funck.springboot.entidades.Aluno;
import com.funck.springboot.entidades.Instituicao;
import com.funck.springboot.repository.AlunoRepository;
import com.funck.springboot.repository.InstituicaoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("/aluno/index");
		resultado.addObject("alunos", this.alunoRepository.findAll());
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("/aluno/inserir");
		Aluno aluno = new Aluno();
		aluno.setInstituicao(new Instituicao());
		resultado.addObject("aluno", aluno);
		resultado.addObject("instituicoes", this.instituicaoRepository.findAll());
		return resultado;
	}
	
	@PostMapping("/inserir")
	public String inserir(Aluno aluno) {
		this.alunoRepository.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Aluno aluno = this.alunoRepository.getOne(id);
		ModelAndView resultado = new ModelAndView("aluno/editar");
		resultado.addObject("aluno", aluno);
		resultado.addObject("instituicoes", this.instituicaoRepository.findAll());
		return resultado;
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno) {
		this.alunoRepository.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		this.alunoRepository.deleteById(id);
		return "redirect:/alunos/index";
	}
}

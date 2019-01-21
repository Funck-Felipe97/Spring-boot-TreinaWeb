package com.funck.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funck.springboot.entidades.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}

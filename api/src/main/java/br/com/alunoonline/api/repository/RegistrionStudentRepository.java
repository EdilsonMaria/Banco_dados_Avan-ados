package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.RegistrionStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrionStudentRepository extends JpaRepository <RegistrionStudent, Long>{
    List<RegistrionStudent> findByStudentId(Long studentId); //Retornando todas as matriculas pelo Id do aluno
}

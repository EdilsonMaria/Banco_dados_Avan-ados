package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.DisciplinasAlunoResponse;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    public static final double GRADE_AVG_TO_APPROVE = 7.0; //Como a media 7 é um valor constante para nossa regra de negocio.
    //Então criamos uma constante no escorpo global da classe para facilitar no decorrer dp codigo

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void create (MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void updateGrades (Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest) {
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));
        updateStudentGrades(matriculaAluno, atualizarNotasRequest);
        updateStudantStatus(matriculaAluno); //esse trecho de codigo faz a validação se o Id da matricula tem ou não no banco de dados

        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void updateStudentGrades (MatriculaAluno matriculaAluno, AtualizarNotasRequest atualizarNotasRequest) {
        if (atualizarNotasRequest.getGrade1() != null) {
            matriculaAluno.setGrade1(atualizarNotasRequest.getGrade1());
        }

        if (atualizarNotasRequest.getGrade2() != null) {
            matriculaAluno.setGrade2(atualizarNotasRequest.getGrade2());
        }
    }

    public void updateStudantStatus (MatriculaAluno matriculaAluno) {
        Double grade1 = matriculaAluno.getGrade1();
        Double grade2 = matriculaAluno.getGrade2();

        if (grade1 != null && grade2 != null) { //se nota1 for diferente de nulo e nota2 for diferente de nulo, então calcula a media
            double average = (grade1 + grade2) / 2;
            matriculaAluno.setStatus(average >= GRADE_AVG_TO_APPROVE ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }   //codigo acima se trata de um if e else com operadores ternarios, onde chamam o status do Enum com base na media calculada
    }

    public void updateStatusToBreak(Long matriculaAlunoId) {
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));
        if (!MatriculaAlunoStatusEnum.MATRICULADO.equals(matriculaAluno.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "So é possivel trancar uma matricula com o status MATRIULADO");
        }
        changeStatus(matriculaAluno, MatriculaAlunoStatusEnum.TRANCADO);
    }

    public void changeStatus(MatriculaAluno matriculaAluno, MatriculaAlunoStatusEnum matriculaAlunoStatusEnum) {
        matriculaAluno.setStatus(matriculaAlunoStatusEnum);
        matriculaAlunoRepository.save(matriculaAluno);
    }

//    public HistoricoAlunoResponse getHistoricoFromAluno(Long studentId) {
//        List<MatriculaAluno> matriculaDoAluno = matriculaAlunoRepository.findByStudentId(studentId);
//
//        if(matriculaDoAluno.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa aluno não tem matricula");
//        }
//
//        HistoricoAlunoResponse historico = new HistoricoAlunoResponse();
//        historico.setStudentName(matriculaDoAluno.get(0).getStudent().getName());
//        historico.setStudentName(matriculaDoAluno.get(0).getStudent().getEmail());
//
//        List<DisciplinasAlunoResponse> disciplinaList = new ArrayList<>();
//        for (MatriculaAluno matricula : matriculaDoAluno) {
//
//        }
//    }

}

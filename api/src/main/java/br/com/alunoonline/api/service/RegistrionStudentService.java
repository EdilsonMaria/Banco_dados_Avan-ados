package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.DisciplinasAlunoResponse;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.model.RegistrionStudent;
import br.com.alunoonline.api.repository.RegistrionStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrionStudentService {

    public static final double GRADE_AVG_TO_APPROVE = 7.0; //Como a media 7 é um valor constante para nossa regra de negocio.
    //Então criamos uma constante no escorpo global da classe para facilitar no decorrer dp codigo

    @Autowired
    RegistrionStudentRepository registrionStudentRepository;

    public void create (RegistrionStudent registrionStudent) {
        registrionStudent.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        registrionStudentRepository.save(registrionStudent);
    }

    public void updateGrades (Long registrionStudentId, AtualizarNotasRequest atualizarNotasRequest) {
        RegistrionStudent registrionStudent =
                registrionStudentRepository.findById(registrionStudentId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));
        updateStudentGrades(registrionStudent, atualizarNotasRequest);
        updateStudantStatus(registrionStudent); //esse trecho de codigo faz a validação se o Id da matricula tem ou não no banco de dados

        registrionStudentRepository.save(registrionStudent);
    }

    public void updateStudentGrades (RegistrionStudent registrionStudent, AtualizarNotasRequest atualizarNotasRequest) {
        if (atualizarNotasRequest.getGrade1() != null) {
            registrionStudent.setGrade1(atualizarNotasRequest.getGrade1());
        }

        if (atualizarNotasRequest.getGrade2() != null) {
            registrionStudent.setGrade2(atualizarNotasRequest.getGrade2());
        }
    }

    public void updateStudantStatus (RegistrionStudent registrionStudent) {
        Double grade1 = registrionStudent.getGrade1();
        Double grade2 = registrionStudent.getGrade2();

        if (grade1 != null && grade2 != null) { //se nota1 for diferente de nulo e nota2 for diferente de nulo, então calcula a media
            double average = (grade1 + grade2) / 2;
            registrionStudent.setStatus(average >= GRADE_AVG_TO_APPROVE ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }   //codigo acima se trata de um if e else com operadores ternarios, onde chamam o status do Enum com base na media calculada
    }

    public void updateStatusToBreak(Long registrionStudentId) {
        RegistrionStudent registrionStudent =
                registrionStudentRepository.findById(registrionStudentId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));
        if (!MatriculaAlunoStatusEnum.MATRICULADO.equals(registrionStudent.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "So é possivel trancar uma matricula com o status MATRIULADO");
        }
        changeStatus(registrionStudent, MatriculaAlunoStatusEnum.TRANCADO);
    }

    public void changeStatus(RegistrionStudent registrionStudent, MatriculaAlunoStatusEnum matriculaAlunoStatusEnum) {
        registrionStudent.setStatus(matriculaAlunoStatusEnum);
        registrionStudentRepository.save(registrionStudent);
    }

    public HistoricoAlunoResponse getAcademicTranscript(Long studentId) {
        List<RegistrionStudent> registrionOfStudent = registrionStudentRepository.findByStudentId(studentId);

        if(registrionOfStudent.isEmpty()) { //Ve se as matricular que estão no banco de dados esta vazia
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa aluno não tem matricula");
        }

        HistoricoAlunoResponse historic = new HistoricoAlunoResponse();
        historic.setStudentName(registrionOfStudent.get(0).getStudent().getName());
        historic.setStudentName(registrionOfStudent.get(0).getStudent().getEmail());

        List<DisciplinasAlunoResponse> subjetcList = new ArrayList<>();
        for (RegistrionStudent subject : registrionOfStudent) {
            DisciplinasAlunoResponse disciplinasAlunoResponse = new DisciplinasAlunoResponse();
            disciplinasAlunoResponse.setSubjectName(subject.getSubject().getName());
            disciplinasAlunoResponse.setProfessorName(subject.getSubject().getProfessor().getName());
            disciplinasAlunoResponse.setGrade1(subject.getGrade1());
            disciplinasAlunoResponse.setGrade2(subject.getGrade2());

//            Double average = calculateAverage(subject.getGrade1(), subject.getGrade2());
//            disciplinasAlunoResponse.setAvarage(average);
            if (subject.getGrade1() != null && subject.getGrade2() != null) {
                disciplinasAlunoResponse.setAvarage((subject.getGrade1() + subject.getGrade2()) /2.0);
            } else {
                disciplinasAlunoResponse.setAvarage(null);
            }

            disciplinasAlunoResponse.setStatus(subject.getStatus());
            subjetcList.add(disciplinasAlunoResponse);
        }

        historic.setStudentSubjectResponseList(subjetcList);
        return historic;
    }

//    public Double calculateAverage(double grade1, double grade2) {
//        double avg = (grade1 + grade2)/2;
//        return avg;
//    }

}

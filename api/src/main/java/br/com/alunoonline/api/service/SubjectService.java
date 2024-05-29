package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Subject;
import br.com.alunoonline.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public void create(Subject subject) {
        subjectRepository.save(subject); }

    public List<Subject> findAll() {return subjectRepository.findAll();}

    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    public void update(Long id, Subject subject) {
        Optional<Subject> subjectFronDb = findById(id);
        if (subjectFronDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrado");
        }

        Subject subjectUpdated = subjectFronDb.get();

        subjectUpdated.setName(subject.getName());
        subjectUpdated.setProfessor(subject.getProfessor());

        subjectRepository.save(subjectUpdated);
    }

    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> findByProfessorId(Long professorId) {
        return subjectRepository.findByProfessorId(professorId);
    }

}

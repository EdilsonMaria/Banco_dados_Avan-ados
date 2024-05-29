package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.CriarAlunoRequest;
import br.com.alunoonline.api.enums.FinanceiroStatusEnum;
import br.com.alunoonline.api.model.Course;
import br.com.alunoonline.api.model.StudentFinance;
import br.com.alunoonline.api.model.Student;
import br.com.alunoonline.api.repository.CourseRepository;
import br.com.alunoonline.api.repository.StudentFinanceRepository;
import br.com.alunoonline.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository; //fazendo a injeção de dependencia do repositor em service p/ poder se conectar ao BD

    @Autowired
    StudentFinanceRepository studentFinanceRepository;

    @Autowired
    CourseRepository courseRepository;

    public void create(CriarAlunoRequest criarAlunoRequest) {
        Course course = courseRepository.findById(criarAlunoRequest.getCourseId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrada"));
        Student studantSaved = studentRepository.save(
                new Student(
                        null,
                        criarAlunoRequest.getName(),
                        criarAlunoRequest.getEmail(),
                        course
                )
        );
        createFinanceiroInformatio(studantSaved, criarAlunoRequest);
    }

    public List<Student> findAll() { //findAll é para busca todos os alunos
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) { //findById esta buscando apenas um aluno com isso precisa ter parametros pois pode existir ou não
        return studentRepository.findById(id);
    }

    public void update(Long id, Student student) { //puxando o aluno do BD para editar na memoria ram e depois devolver para o BD
        Optional<Student> studentFronDb = findById(id);
        if (studentFronDb.isEmpty()) { //condição paraquando o parametro de aluno for nulo ou não existir no BD
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }

        Student alunoUpdated = studentFronDb.get();

        alunoUpdated.setName(student.getName());
        alunoUpdated.setEmail(student.getEmail());

        studentRepository.save(alunoUpdated);
    }

    public void deleteById(Long id) { //DeleteById siguinifica dizer que vamos deletar pelo usurio, dessa forma o parametro adotado sera sempre o numero do ID
        studentRepository.deleteById(id);
    }

    public void createFinanceiroInformatio(Student student, CriarAlunoRequest criarAlunoRequest) {
        StudentFinance financeiroAluno = new StudentFinance(
                null,
                student,
                criarAlunoRequest.getDiscount(),
                criarAlunoRequest.getDueDate(),
                FinanceiroStatusEnum.EM_DIA
        );

        studentFinanceRepository.save(financeiroAluno); //Esse trecho pega o objeto
        // FinanceiroAluno que esta na memoria ran e trazpara ser salvo no banco de dados
    }

}

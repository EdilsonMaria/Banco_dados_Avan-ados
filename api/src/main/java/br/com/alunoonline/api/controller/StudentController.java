package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.CriarAlunoRequest;
import br.com.alunoonline.api.model.Student;
import br.com.alunoonline.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //fazendo com que o controller utilize a arquitetura rest
@RequestMapping("/student") //fazendo com que tudo que acontecer no frontEnd com /aluno caia nessa class

public class StudentController {
    @Autowired
    StudentService studentService; //fazendo a injeção de dependencia do service em controller

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CriarAlunoRequest criarAlunoRequest) { //convertento o jason que vem do Postman ou insonmia para um arquivo java Aluno
        studentService.create(criarAlunoRequest);
    }

    @GetMapping("/all") //fazendo o mapeamento para quando fizer a requisição http com o /all vir para esse trecho de codigo
    @ResponseStatus(HttpStatus.OK)
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}") //o id fica entre chaves pois é um parametros
    @ResponseStatus(HttpStatus.OK)
    public Optional<Student> findById(@PathVariable Long id) { //como no front end nesse caso insonmia vai ter que busca um parametro // algum numero de id que retorna as informações, precisa coloco pathVariable
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Student student, @PathVariable Long id) {
        studentService.update(id, student);  //devolvendo o aluno atualizado para o BD
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { // o PathVaruable converte o Id de verbo Http para Id de Java
        studentService.deleteById(id); //deletando o aluno do BD
    }

}

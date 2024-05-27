package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //fazendo com que o controller utilize a arquitetura rest
@RequestMapping("/aluno") //fazendo com que tudo que acontecer no frontEnd com /aluno caia nessa class

public class AlunoController {
    @Autowired
    AlunoService alunoService; //fazendo a injeção de dependencia do service em controller

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Aluno aluno) { //convertento o jason que vem do Postman ou insonmia para um arquivo java Aluno
        alunoService.create(aluno);
    }

    @GetMapping("/all") //fazendo o mapeamento para quando fizer a requisição http com o /all vir para esse trecho de codigo
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}") //o id fica entre chaves pois é um parametros
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id) { //como no front end nesse caso insonmia vai ter que busca um parametro // algum numero de id que retorna as informações, precisa coloco pathVariable
        return alunoService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Aluno aluno, @PathVariable Long id) {
        alunoService.update(id, aluno);  //devolvendo o aluno atualizado para o BD
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { // o PathVaruable converte o Id de verbo Http para Id de Java
        alunoService.deleteById(id); //deletando o aluno do BD
    }

}

package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.model.RegistrionStudent;
import br.com.alunoonline.api.service.RegistrionStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrion-student")
public class RegistrionStudentController {

    @Autowired
    RegistrionStudentService registrionStudentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void created(@RequestBody RegistrionStudent registrionStudent)
        { registrionStudentService.create(registrionStudent);
    }

    @PatchMapping("/update-grades/{registrionSrudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGrades(@RequestBody AtualizarNotasRequest atualizarNotasRequest,
                             @PathVariable Long registrionSrudentId) {
        registrionStudentService.updateGrades(registrionSrudentId, atualizarNotasRequest);
    }

    @PatchMapping("/update-status-to-break/{registrionStudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusToBreak(@PathVariable Long registrionStudentId) {
        registrionStudentService.updateStatusToBreak(registrionStudentId);
    }

    @GetMapping("/academic-transcript/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoResponse getAcademicTranscript(@PathVariable Long student_id) {
        return registrionStudentService.getAcademicTranscript(student_id);
    }
}

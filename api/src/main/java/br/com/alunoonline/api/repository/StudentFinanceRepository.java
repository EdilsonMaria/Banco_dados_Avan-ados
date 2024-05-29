package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.StudentFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public interface StudentFinanceRepository extends JpaRepository<StudentFinance, Long> {
}

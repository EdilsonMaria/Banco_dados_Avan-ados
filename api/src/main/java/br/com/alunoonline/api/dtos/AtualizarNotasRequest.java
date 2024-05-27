package br.com.alunoonline.api.dtos; //dtos serve para transferir apenas alguns dados do objeto

import lombok.Data;

@Data
public class AtualizarNotasRequest {
    private Double grade1;
    private Double grade2;
}

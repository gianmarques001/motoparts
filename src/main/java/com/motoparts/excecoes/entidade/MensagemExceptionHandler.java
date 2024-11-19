package com.motoparts.excecoes.entidade;

import java.util.List;

public class MensagemExceptionHandler {

    private Integer codigo;
    private String status;
    private String mensagem;
    private List<ErroObject> erros;

    public MensagemExceptionHandler(Integer codigo, String status, String mensagem, List<ErroObject> erros) {
        this.codigo = codigo;
        this.status = status;
        this.mensagem = mensagem;
        this.erros = erros;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErroObject> getErros() {
        return erros;
    }

    public void setErros(List<ErroObject> erros) {
        this.erros = erros;
    }
}

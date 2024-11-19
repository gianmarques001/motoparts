package com.motoparts.excecoes.entidade;

public class ErroObject {

    private String messagemErro;
    private String campo;



    public ErroObject(String messagemErro, String campo ) {
        this.messagemErro = messagemErro;
        this.campo = campo;

    }

    public String getMessagemErro() {
        return messagemErro;
    }

    public void setMessagemErro(String messagemErro) {
        this.messagemErro = messagemErro;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

}

package br.unicamp.projprat3;

public class VagaAplicada {
    private int id;
    private int idVaga;
    private String emailUsuario;
    private String situacao;

    public VagaAplicada(int id, int idVaga, String emailUsuario, String situacao) {
        this.id = id;
        this.idVaga = idVaga;
        this.emailUsuario = emailUsuario;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}

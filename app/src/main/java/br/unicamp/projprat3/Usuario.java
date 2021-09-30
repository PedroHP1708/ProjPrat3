package br.unicamp.projprat3;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nome;
    private String area;
    private String cpf;
    private String email;
    private String cidade;
    //fotoDePerfil varchar(1000) not null,
    private String senha;
    private String descricao;

    public Usuario(String nome, String cpf, String area, String cidade, String email, String senha)
    {
        this.nome = nome;
        this.area = area;
        this.cpf = cpf;
        this.email = email;
        this.cidade = cidade;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}

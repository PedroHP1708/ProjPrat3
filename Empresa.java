package br.unicamp.projprat3;

import java.io.Serializable;

public class Empresa implements Serializable {

    private String nome;
    private String cnpj;
    private String endereco;
    private String email;
    private String telefone;
    private String fotoDePerfil;
    private String senha;
    private String descricao;

    public Empresa(String nome, String cnpj, String endereco, String telefone, String email, String foto, String senha, String descricao)
    {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.descricao = descricao;
        this.fotoDePerfil = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFotoDePerfil() { return fotoDePerfil; }

    public void setFotoDePerfil(String fotoDePerfil) { this.fotoDePerfil = fotoDePerfil; }

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

    @Override
    public String toString() {
        return "Empresa{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", fotoDePerfil='" + fotoDePerfil + '\'' +
                ", senha='" + senha + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}

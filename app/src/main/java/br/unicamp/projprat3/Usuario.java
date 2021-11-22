package br.unicamp.projprat3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    @SerializedName("email")
    private String email;
    @SerializedName("nome")
    private String nome;
    @SerializedName("area")
    private String area;
    @SerializedName("cpf")
    private String cpf;
    @SerializedName("cidade")
    private String cidade;
    @SerializedName("fotoDePerfil")
    private String fotoDePerfil;
    @SerializedName("senha")
    private String senha;
    @SerializedName("descricao")
    private String descricao;

    public Usuario(String email, String nome, String cpf, String area, String cidade, String foto, String senha, String descricao)
    {
        this.nome = nome;
        this.area = area;
        this.cpf = cpf;
        this.email = email;
        this.cidade = cidade;
        this.fotoDePerfil = foto;
        this.senha = senha;
        this.descricao = descricao;
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
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", area='" + area + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", fotoDePerfil='" + fotoDePerfil + '\'' +
                ", senha='" + senha + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

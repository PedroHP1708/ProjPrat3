package br.unicamp.projprat3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vaga implements Serializable {
    private int id;
    private String emailEmpresa;
    private String titulo;
    private String endereco;
    private String area;
    private int salarioBase;
    private String descricao;

    public Vaga(int id, String emailEmpresa, String titulo, String endereco, String area, int salarioBase, String descricao)
    {
        this.id = id;
        this.emailEmpresa = emailEmpresa;
        this.titulo = titulo;
        this.endereco = endereco;
        this.area = area;
        this.salarioBase = salarioBase;
        this.descricao = descricao;
    }

    public Vaga(String emailEmpresa, String titulo, String endereco, String area, int salarioBase, String descricao)
    {
        this.emailEmpresa = emailEmpresa;
        this.titulo = titulo;
        this.endereco = endereco;
        this.area = area;
        this.salarioBase = salarioBase;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(int salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", emailEmpresa='" + emailEmpresa + '\'' +
                ", titulo='" + titulo + '\'' +
                ", endereco='" + endereco + '\'' +
                ", area='" + area + '\'' +
                ", salarioBase=" + salarioBase +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

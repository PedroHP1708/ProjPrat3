package br.unicamp.projprat3;

public class VagaAInserir {

    private String emailEmpresa;
    private String titulo;
    private String endereco;
    private String area;
    private double salarioBase;
    private String descricao;

    public VagaAInserir(String emailEmpresa, String titulo, String endereco, String area, double salarioBase, String descricao)
    {
        this.emailEmpresa = emailEmpresa;
        this.titulo = titulo;
        this.endereco = endereco;
        this.area = area;
        this.salarioBase = salarioBase;
        this.descricao = descricao;
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

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getDetalhes() {
        return descricao;
    }

    public void setDetalhes(String detalhes) {
        this.descricao = detalhes;
    }

    @Override
    public String toString() {
        return "VagaAInserir{" +
                "emailEmpresa='" + emailEmpresa + '\'' +
                ", titulo='" + titulo + '\'' +
                ", endereco='" + endereco + '\'' +
                ", area='" + area + '\'' +
                ", salarioBase=" + salarioBase +
                ", detalhes='" + descricao + '\'' +
                '}';
    }
}
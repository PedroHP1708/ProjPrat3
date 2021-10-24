package br.unicamp.projprat3;

public class Vaga {
    private int id;
    private String emailEmpresa;
    private String titulo;
    private String endereco;
    private String area;
    private int salarioBase;

    public Vaga(int id, String emailEmpresa, String titulo, String endereco, String area, int salarioBase)
    {
        this.id = id;
        this.emailEmpresa = emailEmpresa;
        this.titulo = titulo;
        this.endereco = endereco;
        this.area = area;
        this.salarioBase = salarioBase;
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
}

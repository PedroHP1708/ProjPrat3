namespace Pratica3_API.Models
{
    public class Vaga
    {
        public int Id { get; set; }

        public string emailEmpresa { get; set; }

        public string Titulo { get; set; }

        public string Endereco { get; set; }

        public string Area { get; set; }

        public string Descricao { get; set;}
        
        public decimal salarioBase { get; set;}

    }
}
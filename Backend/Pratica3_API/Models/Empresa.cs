using System.ComponentModel.DataAnnotations;

namespace Pratica3_API.Models
{
    public class Empresa
    {
        [Key]
        public string Email { get; set; }

        public string Nome { get; set; }

        public string Telefone { get; set; }

        public string Cnpj { get; set;}

        public string Endereco { get; set; }

        public string Senha { get; set; }

        public string fotoDePerfil { get; set;}

        public string Descricao { get; set;}

    }
}
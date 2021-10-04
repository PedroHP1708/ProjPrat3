using System.ComponentModel.DataAnnotations;

namespace Pratica3_API.Models
{
    public class Usuario
    {
        [Key]
        public string Email { get; set; }

        public string Nome { get; set; }

        public string Area { get; set; }

        public string Cpf { get; set;}

        public string Cidade { get; set; }

        public string Senha { get; set; }

        public string fotoDePerfil { get; set;}

        public string Descricao { get; set;}

    }
}
using Microsoft.EntityFrameworkCore;
using Pratica3_API.Models;

namespace Pratica3_API.Data
{
    public class PraticaContext: DbContext
    {
        public PraticaContext(DbContextOptions<PraticaContext> options): base (options)
        {
        }

        public DbSet<Usuario> Usuario {get; set;}
        public DbSet<Empresa> Empresa {get; set;}
        public DbSet<Vaga> Vaga {get; set;}
        public DbSet<VagaAplicada> VagaAplicada {get; set;}
    }
}


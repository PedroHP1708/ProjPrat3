using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Pratica3_API.Data;
using Pratica3_API.Models;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace Pratica3_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]

    public class VagaController : Controller
    {
        private readonly PraticaContext _context;

        public VagaController(PraticaContext context)
        {
            // construtor
            _context = context;
        }

        [HttpGet]
        public ActionResult<List<Vaga>> GetAll() 
        {
            return _context.Vaga.ToList();
        }

        [HttpGet("{VagaId}")]
        public ActionResult<List<Vaga>> Get(int VagaId)
        {
            try
            {
                var result = _context.Vaga.Find(VagaId);

                if (result == null)
                {
                    return NotFound();
                }
                
                return Ok(result);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "Falha no acesso ao banco de dados.");
            }
        }

        [HttpPost]
        public async Task<ActionResult> post(Vaga model)
        {
            string area = model.Area;
            string endereco = model.Endereco;
            string titulo = model.Titulo;
            string descricao = model.Descricao;
            decimal salario = model.salarioBase;
            string email = model.emailEmpresa;
            int id = model.Id; 

            System.Console.WriteLine("Id ->" + id + "\n");
            System.Console.WriteLine("Area ->" + area + "\n");
            System.Console.WriteLine("Endereco ->" + endereco + "\n");
            System.Console.WriteLine("Titulo ->" + titulo + "\n");
            System.Console.WriteLine("Descricao ->" +descricao + "\n");
            System.Console.WriteLine("Salario ->" + salario + "\n");
            System.Console.WriteLine("Email ->" + email + "\n");
            //Console.writeLine()

            try
            {
                _context.Vaga.Add(model);

                if (await _context.SaveChangesAsync() == 1)
                {
                    return Ok();
                }
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError, "Falha no acesso ao banco de dados.");
            }

            // retorna BadRequest se não conseguiu incluir
            return BadRequest();
        }
        
        [HttpDelete("{VagaId}")]
        public async Task<ActionResult> delete(int VagaId)
        {
            try
            {
                //verifica se existe vaga a ser excluído
                var vaga = await _context.Vaga.FindAsync(VagaId);

                if (vaga == null)
                {
                    //método do EF

                    return NotFound();
                }
                
                _context.Remove(vaga);
                await _context.SaveChangesAsync();
                return NoContent();
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"Falhano acesso ao banco de dados.");
            }

            // retorna BadRequest se não conseguiu deletar
            return BadRequest();
        }

        [HttpPut("{VagaId}")]
        public async Task<IActionResult> put(int VagaId, Vaga dadosVagaAlt)
        {
            try {
                //verifica se existe vaga a ser alterado
                var result = await _context.Vaga.FindAsync(VagaId);

                if (VagaId != result.Id)
                {
                    return BadRequest();
                }

                result.Endereco = dadosVagaAlt.Endereco;
                result.emailEmpresa = dadosVagaAlt.emailEmpresa;
                result.Titulo = dadosVagaAlt.Titulo;
                result.salarioBase = dadosVagaAlt.salarioBase;
                result.Area = dadosVagaAlt.Area;
                result.Descricao = dadosVagaAlt.Descricao;

                await _context.SaveChangesAsync();
                return Created($"/api/vaga/{dadosVagaAlt.Id}", dadosVagaAlt);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"Falhano acesso ao banco de dados.");
            }
        }
    }
}

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

    public class EmpresaController : Controller
    {
        private readonly PraticaContext _context;

        public EmpresaController(PraticaContext context)
        {
            // construtor
            _context = context;
        }

        [HttpGet]
        public ActionResult<List<Empresa>> GetAll() 
        {
            return _context.Empresa.ToList();
        }

        [HttpGet("{email}")]
        public ActionResult<List<Empresa>> Get(string email)
        {
            try
            {
                var result = _context.Empresa.Find(email);

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
        public async Task<ActionResult> post(Empresa model)
        {
            try
            {
                _context.Empresa.Add(model);

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
        
        [HttpDelete("{email}")]
        public async Task<ActionResult> delete(string email)
        {
            try
            {
                //verifica se existe Empresa a ser excluído
                var empresa = await _context.Empresa.FindAsync(email);

                if (empresa == null)
                {
                    //método do EF

                    return NotFound();
                }
                
                _context.Remove(empresa);
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

        [HttpPut("{email}")]
        public async Task<IActionResult> put(string email, Empresa dadosEmpresaAlt)
        {
            try {
                //verifica se existe empresa a ser alterado
                var result = await _context.Empresa.FindAsync(email);

                if (email != result.Email)
                {
                    return BadRequest();
                }

                result.Nome = dadosEmpresaAlt.Nome;
                result.Telefone = dadosEmpresaAlt.Telefone;
                result.Cnpj = dadosEmpresaAlt.Cnpj;
                result.Endereco = dadosEmpresaAlt.Endereco;
                result.Senha = dadosEmpresaAlt.Senha;
                result.fotoDePerfil = dadosEmpresaAlt.fotoDePerfil;
                result.Descricao = dadosEmpresaAlt.Descricao;

                await _context.SaveChangesAsync();
                return Created($"/api/empresa/{dadosEmpresaAlt.Email}", dadosEmpresaAlt);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"Falhano acesso ao banco de dados.");
            }
        }
    }
}

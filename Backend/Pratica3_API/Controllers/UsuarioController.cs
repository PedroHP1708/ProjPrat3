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

    public class UsuarioController : Controller
    {
        private readonly PraticaContext _context;

        public UsuarioController(PraticaContext context)
        {
            // construtor
            _context = context;
        }

        [HttpGet]
        public ActionResult<List<Usuario>> GetAll() 
        {
            return _context.Usuario.ToList();
        }

        [HttpGet("{UsuarioId}")]
        public ActionResult<List<Usuario>> Get(int UsuarioId)
        {
            try
            {
                var result = _context.Usuario.Find(UsuarioId);

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
        public async Task<ActionResult> post(Usuario model)
        {
            try
            {
                _context.Usuario.Add(model);

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
        
        [HttpDelete("{UsuarioId}")]
        public async Task<ActionResult> delete(int UsuarioId)
        {
            try
            {
                //verifica se existe usuario a ser excluído
                var usuario = await _context.Usuario.FindAsync(UsuarioId);

                if (usuario == null)
                {
                    //método do EF

                    return NotFound();
                }
                
                _context.Remove(usuario);
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

        [HttpPut("{UsuarioId}")]
        public async Task<IActionResult> put(int UsuarioId, Usuario dadosUsuarioAlt)
        {
            try {
                //verifica se existe usuario a ser alterado
                var result = await _context.Usuario.FindAsync(UsuarioId);

                if (UsuarioId != result.Id)
                {
                    return BadRequest();
                }

                result.Nome = dadosUsuarioAlt.Nome;
                result.Area = dadosUsuarioAlt.Area;
                result.Cpf = dadosUsuarioAlt.Cpf;
                result.Cidade = dadosUsuarioAlt.Cidade;
                result.Email = dadosUsuarioAlt.Email;
                result.Senha = dadosUsuarioAlt.Senha;
                result.fotoDePerfil = dadosUsuarioAlt.fotoDePerfil;
                result.Descricao = dadosUsuarioAlt.Descricao;

                await _context.SaveChangesAsync();
                return Created($"/api/usuario/{dadosUsuarioAlt.Id}", dadosUsuarioAlt);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"Falhano acesso ao banco de dados.");
            }
        }
    }
}
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

    public class VagaAplicadaController : Controller
    {
        private readonly PraticaContext _context;

        public VagaAplicadaController(PraticaContext context)
        {
            // construtor
            _context = context;
        }

        [HttpGet]
        public ActionResult<List<VagaAplicada>> GetAll() 
        {
            return _context.VagaAplicada.ToList();
        }

        [HttpGet("{vaId}")]
        public ActionResult<List<VagaAplicada>> Get(int vaId)
        {
            try
            {
                var result = _context.VagaAplicada.Find(vaId);

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
        public async Task<ActionResult> post(VagaAplicada model)
        {
            try
            {
                _context.VagaAplicada.Add(model);

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
        
        [HttpDelete("{vaId}")]
        public async Task<ActionResult> delete(int vaId)
        {
            try
            {
                //verifica se existe vagaAplicada a ser excluído
                var vaga = await _context.VagaAplicada.FindAsync(vaId);

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

        [HttpPut("{vaId}")]
        public async Task<IActionResult> put(int vaId, VagaAplicada dadosVagaAplicadaAlt)
        {
            try {
                //verifica se existe vagaAplicada a ser alterado
                var result = await _context.VagaAplicada.FindAsync(vaId);

                if (vaId != result.Id)
                {
                    return BadRequest();
                }

                result.idVaga = dadosVagaAplicadaAlt.idVaga;
                result.idUsuario = dadosVagaAplicadaAlt.idUsuario;
                result.Situacao = dadosVagaAplicadaAlt.Situacao;

                await _context.SaveChangesAsync();
                return Created($"/api/vagaAplicada/{dadosVagaAplicadaAlt.Id}", dadosVagaAplicadaAlt);
            }
            catch
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,"Falhano acesso ao banco de dados.");
            }
        }
    }
}
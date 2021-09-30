using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Pratica3_API.Models;
using System;
using Microsoft.AspNetCore.Authorization;
using System.Linq;
using Pratica3_API.Services;
using Pratica3_API.Data;

namespace Pratica3_API.Controllers
{
    [Route("api/[controller]")]

    public class LoginController : ControllerBase
    {
        private readonly PraticaContext _context;
        public LoginController(PraticaContext context)
        {
            // construtor
            _context = context;
        }

        [HttpPost]
        [Route("login")]
        [AllowAnonymous]
        public async Task<ActionResult<dynamic>> Authenticate([FromBody] Usuario usuario)
        {
            //verifica se existe aluno a ser excluído
            var user = _context.Usuario
                .Where(u => u.Nome == usuario.Nome && u.Senha == usuario.Senha)
                .FirstOrDefault();
            
            if (user == null)
                return NotFound(new { message = "Usuário ou senha inválidos" });
            
            var token = TokenService.GenerateToken(user);
            user.Senha = "";
            
            return new
            {
                user = user,
                token = token
            };
        }
    }
}
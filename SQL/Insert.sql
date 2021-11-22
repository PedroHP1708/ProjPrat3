
-- TABELA USUARIO

select * from Usuario

insert into Usuario values   ('ps@gmail.com', 'Pedro Sakai' , 'Enfermagem' , '103.234.987-56', 'Campinas', '-', 'admin', 'Formado técnico...'),
							 ('ribs@gmail.com', 'Yara Ribeiro', 'Informática', '170.850.781-99', 'Sorocaba', '-', 'admin', 'Eu fiz o curso...'),
							 ('pe.perez@yahoo.com', 'Pedro Perez' , 'Educacional', '193.209.117-26', 'Manaus'  , '-', 'admin', 'Estou cursando...')

-- TABELA EMPRESA

select * from Empresa

insert into Empresa values   ('netflix@gmail.com', 'Netflix', '(19)99263-7294', 'XX.XXX.XXX/0001-XX', 'Rua X...', '-', 'net', 'A Netflix é...'        ),
							 ('disney@gmail.com' , 'Disney' , '...'           , 'XX.XXX.XXX/0001-XX', 'Rua Y...', '-', 'dis', 'O grupo Walt Disney...')

-- TABELA VAGA

select * from Vaga

insert into Vaga values ('disney@gmail.com' , 'Analista', 'Rua X...', 'Economica'       , 'Estamos procurando...', 1500.00),
						('netflix@gmail.com', 'Diretor' , 'Rua Y...', 'Cinematrogafica' , 'Buscamos alguem...'   , 2000.00)

-- TABELA vagaAPLICADA

select * from vagaAplicada

insert into vagaAplicada values (1, 'ps@gmail.com', 'Em analise'),
								(1, 'pe.perez@yahoo.com', 'Chamada'),
								(2, 'ribs@gmail.com', 'Recusado'),
								(2, 'ps@gmail.com', 'Em analise')
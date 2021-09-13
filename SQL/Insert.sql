
-- TABELA USUARIO

select * from Usuario

insert into Usuario values   (1, 'Pedro Sakai' , 'Enfermagem' , '103.234.987-56', 'ps@gmail.com'      , 'Campinas', '-', 'admin', 'Formado técnico...'),
							 (2, 'Yara Ribeiro', 'Informática', '170.850.781-99', 'ribs@gmail.com'    ,	'Sorocaba', '-', 'admin', 'Eu fiz o curso...'),
							 (3, 'Pedro Perez' , 'Educacional', '193.209.117-26', 'pe.perez@yahoo.com',	'Manaus'  , '-', 'admin', 'Estou cursando...')

-- TABELA EMPRESA

select * from Empresa

insert into Empresa values   (1, 'Netflix', '(19)99263-7294', 'XX.XXX.XXX/0001-XX', 'netflix@gmail.com', 'Rua X...', '-', 'net', 'A Netflix é...'        ),
							 (2, 'Disney' , '...'           , 'XX.XXX.XXX/0001-XX', 'disney@gmail.com' , 'Rua Y...', '-', 'dis', 'O grupo Walt Disney...')

-- TABELA VAGA

select * from Vaga

insert into Vaga values (1, 2, 'Analista', 'Rua X...', 'Economica'       , 'Estamos procurando...', 1500.00),
						(2, 1, 'Diretor' , 'Rua Y...', 'Cinematrogafica' , 'Buscamos alguem...'   , 2000.00)

-- TABELA vagaAPLICADA

select * from vagaAplicada

insert into vagaAplicada values (1, 1, 1, 'Em analise'),
								(2, 1, 3, 'Chamada'),
								(3, 2, 2, 'Recusado'),
								(4, 2, 1, 'Em analise')
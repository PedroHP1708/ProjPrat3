drop table Usuario

create table Usuario(
  email		   varchar(30)   primary key,
  nome		   varchar(50)   not null,
  area		   varchar(30)   not null,
  cpf		   varchar(14)   not null,
  cidade       varchar(30)   not null,
  fotoDePerfil varchar(1000) not null,
  senha		   varchar(10)   not null,
  descricao	   varchar(500)  null
)

create table Empresa(
  email		   varchar(30) primary key not null,
  nome		   varchar(50)   not null,
  telefone     varchar(20)   not null,
  cnpj		   varchar(20)   not null,
  endereco     varchar(50)   not null,
  fotoDePerfil varchar(1000) not null,
  senha		   varchar(10)   not null,
  descricao	   varchar(500)  null
)

create table Vaga(
  id    int primary key identity(1,1),
  emailEmpresa varchar(30) not null,
  titulo      varchar(20)   not null,
  endereco    varchar(50)   not null,
  area		  varchar(30)   not null,
  descricao	  varchar(500)  not null,
  salarioBase money not null,
  constraint fkEmpresa foreign key(emailEmpresa)
  references Empresa(email)
)

create table vagaAplicada(
  id int primary key identity(1,1),
  idVaga int not null,
  emailUsuario varchar(30) not null,
  situacao varchar(10) not null,
  constraint fkVaga    foreign key(idVaga) references Vaga(id),
  constraint fkUsuario foreign key(emailUsuario) references Usuario(email)
)


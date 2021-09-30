drop table Empresa
create table Usuario(
  id    int primary key identity(1,1),
  nome		   varchar(50)   not null,
  area		   varchar(30)   not null,
  cpf		   varchar(14)   not null,
  email		   varchar(30)   not null,
  cidade       varchar(30)   not null,
  fotoDePerfil varchar(1000) not null,
  senha		   varchar(10)   not null,
  descricao	   varchar(500)  null
)

create table Empresa(
  id    int primary key identity(1,1),
  nome		   varchar(50)   not null,
  telefone     varchar(20)   not null,
  cnpj		   varchar(20)   not null,
  email		   varchar(30)   not null,
  endereco     varchar(50)   not null,
  fotoDePerfil varchar(1000) not null,
  senha		   varchar(10)   not null,
  descricao	   varchar(500)  null
)

create table Vaga(
  id    int primary key identity(1,1),
  idEmpresa int not null,
  titulo      varchar(20)   not null,
  endereco    varchar(50)   not null,
  area		  varchar(30)   not null,
  descricao	  varchar(500)  not null,
  salarioBase money not null,
  constraint fkEmpresa foreign key(idEmpresa)
  references Empresa(id)
)

create table vagaAplicada(
  id int primary key identity(1,1),
  idVaga int not null,
  idUsuario int not null,
  situacao varchar(10) not null,
  constraint fkVaga    foreign key(idVaga) references Vaga(id),
  constraint fkUsuario foreign key(idUsuario) references Usuario(id)
)


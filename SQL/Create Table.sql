
create table Usuario(
  idUsuario    int primary key,
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
  idEmpresa    int primary key,
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
  idVaga    int primary key,
  idEmpresa int not null,
  titulo      varchar(20)   not null,
  endereco    varchar(50)   not null,
  area		  varchar(30)   not null,
  descricao	  varchar(500)  not null,
  salarioBase money not null,
  constraint fkEmpresa foreign key(idEmpresa)
  references Empresa(idEmpresa)
)

create table vagaAplicada(
  idVagaAplicada int primary key,
  idVaga int not null,
  idUsuario int not null,
  situacao varchar(10) not null,
  constraint fkVaga    foreign key(idVaga) references Vaga(idVaga),
  constraint fkUsuario foreign key(idUsuario) references Usuario(idUsuario)
)


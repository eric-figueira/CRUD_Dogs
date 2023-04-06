create table CRUD_Dogs.Cachorro(
  id int identity(1,1) primary key,
  nome varchar(15) not null,
  raca varchar(40) not null,
  idade tinyint not null,
  peso float null,
  porte varchar(15) not null,
  cor varchar(10) not null,
  dono varchar(30) not null,
  cep char(9) check(cep like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') not null,
  numeroCasa smallint not null,
  complemento varchar(15) not null
)

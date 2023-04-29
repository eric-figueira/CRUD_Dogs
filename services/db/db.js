import bdConfig from './dbconfig';
import mysql from "mysql2/promise";

export const getConexao = async () => {
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    const conexao = await mysql.createConnection(bdConfig);
    global.conexao = conexao;
    return conexao;
}

export const estrutureSe = async () => {
    const conexao = await getConexao();

    const sql = "CREATE TABLE IF NOT EXISTS CRUD_Dogs.Cachorro(id int unsigned auto_increment primary key,nome varchar(15) not null,raca varchar(40) not null,idade tinyint unsigned not null,peso float unsigned null,porte varchar(15) not null,cor varchar(10) not null,dono varchar(30) not null,cep char(9) check(cep like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') not null,numeroCasa smallint unsigned not null,complemento varchar(15) not null"

    return await conexao.query(sql);
}
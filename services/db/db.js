const bdConfig = require('./dbconfig');
const mysql = require("mysql2/promise");

const getConexao = async () => {
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    const conexao = await mysql.createConnection(bdConfig);
    global.conexao = conexao;
    return conexao;
}

const estrutureSe = async () => {
    const conexao = await getConexao();

    const sql = "CREATE TABLE IF NOT EXISTS Cachorro(id int unsigned auto_increment primary key,nome varchar(15) not null,raca varchar(40) not null,idade tinyint unsigned not null,peso float unsigned null,porte varchar(15) not null,cor varchar(10) not null,dono varchar(30) not null,cep char(9) not null, numeroCasa smallint unsigned not null,complemento varchar(15) not null"

    return await conexao.query(sql);
}


module.exports = { getConexao, estrutureSe 
}
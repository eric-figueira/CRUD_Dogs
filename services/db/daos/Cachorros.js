const { getConexao } = require('../db');

async function insere(cachorro) {
    const conexao = await getConexao();
    if (conexao == null) return null;

    try {
        const sql = "INSERT INTO CRUD_Dogs.Cachorro (nome, raca, porte, cor, dono, cep, complemento, idade, numeroCasa, peso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        console.log(cachorro.dono)
        console.log(cachorro.nome)
        const dados = [cachorro.nome, cachorro.raca, cachorro.porte, cachorro.cor, cachorro.dono, cachorro.cep, cachorro.complemento, cachorro.idade, cachorro.numeroCasa, cachorro.peso];
        await conexao.query(sql, dados);
        return true;
    }
    catch (erro) {
        console.log(erro);
        return false;
    }
}

async function atualiza(cachorro) {
    const conexao = await getConexao();
    if (conexao == null)
        return null;

    try {
        const sql = "UPDATE CRUD_Dogs.Cachorro SET nome = ?, raca = ?, idade = ?, peso = ?, porte = ?, cor = ?, dono = ?, cep = ?, numeroCasa = ?, complemento = ? WHERE id = ?";
        const dados = [cachorro.nome, cachorro, raca, cachorro.idade, cachorro.peso, cachorro.porte, cachorro.cor, cachorro.dono, cachorro.cep, cachorro.numeroCasa, cachorro.complemento, cachorro.id];
        await conexao.query(sql, dados);
        return true;
    }
    catch (erro) {
        return false;
    }
}

async function deleta(id) {
    const conexao = await getConexao();
    if (conexao == null)
        return null;

    try {
        const sql = "DELETE FROM CRUD_Dogs.Cachorro WHERE id = ?";
        const dados = [id];
        await conexao.query(sql, dados);
        return true;
    }
    catch (erro) {
        return false;
    }
}

async function recuperaUm(id) {
    const conexao = await getConexao();
    if (conexao == null)
        return null;

    try {
        const sql = "SELECT * FROM CRUD_Dogs.Cachorro WHERE id = ?";
        const dados = [id];
        const [linhas] = await conexao.query(sql, dados);
        return linhas;
    }
    catch (erro) {
        return false;
    }
}

async function recuperaTodos() {
    const conexao = await getConexao();
    if (conexao == null)
        return null;

    try {
        const sql = "SELECT * FROM CRUD_Dogs.Cachorro";
        const [linhas] = await conexao.query(sql);
        return linhas;
    }
    catch (erro) {
        return false;
    }
}


module.exports = { insere, atualiza, deleta, recuperaUm, recuperaTodos }

/*
    Esse arquivo tem a única e exclusiva função de tratar das regras
    de negócio, isto é, verificar se as requisições estão de acordo
    e chamar o DAO para fazer a operação no banco de dados 
*/

const Cachorros = require('../db/daos/Cachorros')
const Cachorro = require('../db/dbos/Cachorro')


async function inserir(req, res)
{
  //
}

async function atualizar(req, res)
{
  //
}

async function deletar(req, res)
{
  //
}

async function recuperarUm(req, res)
{
  //
}

async function recuperarTodos(req, res)
{
  //
}

module.exports = { inserir, atualizar, deletar, recuperarUm, recuperarTodos }
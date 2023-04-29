/*
    Esse arquivo tem a única e exclusiva função de tratar das regras
    de negócio, isto é, verificar se as requisições estão de acordo
    e chamar o DAO para fazer a operação no banco de dados 
*/

import Cachorro from '../db/dbos/cachorro';
import { atualiza, deleta, insere, recuperaTodos, recuperaUm } from '../db/daos/cachorros';
import Comunicado from '../models/comunicado';

async function inserir(req, res) {
  if (Object.values(req.body).length != 10 || !req.body.nome || !req.body.raca || !req.body.idade || !req.body.peso || !req.body.porte || !req.body.cor || !req.body.dono || !req.body.cep || !req.body.numeroCasa || !req.body.complemento) {
    const erro = Comunicado.novo('DdI', 'Dados inesperados', 'Não foram fornecidos exatamente as 10 informações esperadas de um cachorro (nome, raça, idade, peso, porte, cor, dono, CEP, número da casa e complemento)').object;
    res.status(422).json(erro);
  }

  let cachorro;
  try {
    cachorro = Cachorro.novoCachorro(0, req.body.nome, req.body.raca, req.body.idade, req.body.peso, req.body.porte, req.body.cor, req.body.dono, req.body.cep, req.body.numeroCasa, req.body.complemento);
  }
  catch {
    const erro = Comunicado.novo('TDE', 'Dados de tipos errados', 'Id, idade e número da casa devem ser números naturais positivos; nome, raça, porte, cor, dono, cep e complemento devem ser textos não vazios; e peso deve ser um número real positivo').object;
    return res.status(422).json(erro);
  }

  const ret = await insere(cachorro);

  if (ret == null) {
    const erro = Comunicado.novo('CBD', 'Sem conexão com o BD', 'Não foi possível estabelecer conexão com o banco de dados').object;
    return res.status(500).json(erro);
  }

  if (ret == false) {
    const erro = Comunicado.novo('CJE', 'Cachorro já existe', 'Já há cachorro cadastrado com o id informado').object;
    return res.status(409).json(erro);
  }

  //if (ret===true)
  //{
  const sucesso = Comunicado.novo('IBS', 'Inclusão bem sucedida', 'O cachorro foi incluído com sucesso').object;
  return res.status(201).json(sucesso);
  //}
}


async function atualizar(req, res) {
  //
}

async function deletar(req, res) {
  //
}

async function recuperarUm(req, res) {
  //
}

async function recuperarTodos(req, res) {
  //
}

export default { inserir, atualizar, deletar, recuperarUm, recuperarTodos }
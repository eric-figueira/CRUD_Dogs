/*
    Esse arquivo tem a única e exclusiva função de tratar das regras
    de negócio, isto é, verificar se as requisições estão de acordo
    e chamar o DAO para fazer a operação no banco de dados 
*/

const Cachorro = require('../db/dbos/cachorro');
const { atualiza, deleta, insere, recuperaTodos, recuperaUm } = require('../db/daos/Cachorros');
const Comunicado = require('../models/comunicado');

async function inserir(req, res) 
{
  if (Object.values(req.body).length != 11
      || !req.body.idCachorro  
      || !req.body.nome  || !req.body.raca 
      || !req.body.idade || !req.body.peso 
      || !req.body.porte || !req.body.cor 
      || !req.body.dono  || !req.body.cep
      || !req.body.numeroCasa || !req.body.complemento) 
  {
    const erro = Comunicado.novo('DdI', 'Dados inesperados', 'Não foram fornecidos exatamente as 10 informações esperadas de um cachorro (nome, raça, idade, peso, porte, cor, dono, CEP, número da casa e complemento)').object;
    return res.status(422).json(erro);
  }

  let cachorro;
  try 
  {
    cachorro = Cachorro.novoCachorro(0, req.body.nome, req.body.raca, req.body.porte, req.body.cor, req.body.dono, req.body.cep, req.body.complemento, req.body.idade, req.body.numeroCasa, req.body.peso);
  }
  catch (err)
  {
    const erro = Comunicado.novo('TDE', 'Dados de tipos errados', 'Id, idade e número da casa devem ser números naturais positivos; nome, raça, porte, cor, dono, cep e complemento devem ser textos não vazios; e peso deve ser um número real positivo').object;
    return res.status(422).json(erro);
  }

  const ret = await insere(cachorro);

  if (ret == null) {
    const erro = Comunicado.novo('CBD', 'Sem conexão com o BD', 'Não foi possível estabelecer conexão com o banco de dados').object;
    return res.status(500).json(erro);
  }

  if (ret == false) {
    console.log("aqui");
    const erro = Comunicado.novo('CJE', 'Cachorro já existe', 'Já há cachorro cadastrado com o id informado').object;
    return res.status(409).json(erro);
  }

  //if (ret===true)
  //{
  const sucesso = Comunicado.novo('IBS', 'Inclusão bem sucedida', 'O cachorro foi incluído com sucesso').object;
  return res.status(201).json(sucesso);
  //}
}

async function atualizar(req, res) 
{
  if (Object.values(req.body).length != 11
      || !req.body.idCachorro
      || !req.body.nome  || !req.body.raca 
      || !req.body.idade || !req.body.peso 
      || !req.body.porte || !req.body.cor 
      || !req.body.dono  || !req.body.cep
      || !req.body.numeroCasa || !req.body.complemento) 
  {
    const erro = Comunicado.novo('DdI', 'Dados inesperados', 'Não foram fornecidos exatamente as 10 informações esperadas de um cachorro (nome, raça, idade, peso, porte, cor, dono, CEP, número da casa e complemento)').object;
    console.log(req.body);
    return res.status(422).json(erro);
  }

  let cachorro;
  try 
  {
    cachorro = Cachorro.novoCachorro(0, req.body.nome, req.body.raca, req.body.porte, req.body.cor, req.body.dono, req.body.cep, req.body.complemento, req.body.idade, req.body.numeroCasa, req.body.peso);
  }
  catch 
  {
    const erro = Comunicado.novo('TDE', 'Dados de tipos errados', 'Id, idade e número da casa devem ser números naturais positivos; nome, raça, porte, cor, dono, cep e complemento devem ser textos não vazios; e peso deve ser um número real positivo').object;
    return res.status(422).json(erro);
  }

  const id = req.params.id
  let ret = await recuperaUm(id);

  if (ret===null)
  {
      const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
      return res.status(500).json(erro);
  }

  if (ret===false)
  {
      const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
      return res.status(409).json(erro);
  }

  if (ret.length==0)
  {
      const erro = Comunicado.novo('CNE','Cachorro inexistente','Não há cachorro cadastrado com o código informado').object;
      return res.status(404).json(erro);
  }

  cachorro.id = Number(id);
  ret = await atualiza(cachorro);

  if (ret===null)
  {
      const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
      return res.status(500).json(erro);
  }

  if (ret===false)
  {
      const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
      return res.status(409).json(erro);
  }

  const sucesso = Comunicado.novo('ABS','Alteração bem sucedida','O cachorro foi atualizado com sucesso').object;
  return res.status(201).json(sucesso);
}

async function deletar(req, res) 
{
  // Passou dados desnecessários
  if (Object.values(req.body).length != 0) 
  {
    const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
    return res.status(422).json(erro);
  }

  const id = req.params.id

  // Testamos se o livro existe antes de tentar remover
  let ret = await recuperaUm(id)

  // Falha no banco
  if (ret === null) 
  {
    const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
    return res.status(500).json(erro);
  }

  // Falha no comando
  if (ret === false) 
  {
    const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
    return res.status(409).json(erro);
  }

  // Livro não existe
  if (ret.length == 0) 
  {
    const erro = Comunicado.novo('CNE','Cachorro inexistente','Não há cachorro cadastrado com o código informado').object;
    return res.status(404).json(erro);
  }
  
  ret = await deleta(id)

  if (ret===null)
  {
      const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
      return res.status(500).json(erro);
  }

  if (ret===false)
  {
      const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
      return res.status(409).json(erro);
  }

  const sucesso = Comunicado.novo('RBS','Remoção bem sucedida','O cachorro foi removido com sucesso').object;
  return res.status(200).json(sucesso);
}

async function recuperarUm(req, res) 
{
  if (Object.values(req.body).length != 0) 
  {
    const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
    return res.status(422).json(erro);
  }

  const id = req.params.id

  const ret = await recuperaUm(id)

  // Falha no banco
  if (ret === null) 
  {
    const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
    return res.status(500).json(erro);
  }

  // Falha no comando
  if (ret === false) 
  {
    const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
    return res.status(409).json(erro);
  }

  return res.status(200).json(ret);
}

async function recuperarTodos(req, res) 
{
  if (Object.values(req.body).length != 0) 
  {
    const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
    return res.status(422).json(erro);
  }

  const ret = await recuperaTodos();

  // Falha no banco
  if (ret === null) 
  {
    const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
    return res.status(500).json(erro);
  }

  // Falha no comando
  if (ret === false) 
  {
    const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
    return res.status(409).json(erro);
  }

  return res.status(200).json(ret);
}

module.exports = { inserir, atualizar, deletar, recuperarUm, recuperarTodos }
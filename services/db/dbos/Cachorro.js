class Cachorro {
  #id
  #nome
  #raca
  #porte
  #cor
  #dono
  #cep
  #complemento
  #idade
  #numeroCasa
  #peso

  constructor(id, nome, raca, porte, cor, dono, cep, complemento, idade, numeroCasa, peso) 
  {
    this.id = id,
    this.nome = nome,
    this.raca = raca,
    this.porte = porte,
    this.cor = cor,
    this.dono = dono,
    this.cep = cep,
    this.complemento = complemento,
    this.idade = idade,
    this.numeroCasa = numeroCasa,
    this.peso = peso
  }

  get id() { return this.#id }
  get nome() { return this.#nome }
  get raca() { return this.#raca }
  get porte() { return this.#porte }
  get cor() { return this.#cor }
  get dono() { return this.#dono }
  get cep() { return this.#cep }
  get complemento() { return this.#complemento }
  get idade() { return this.#idade }
  get numeroCasa() { return this.#numeroCasa }
  get peso() { return this.#peso }

  set id(id) {
    // -1 vai indicar que não há nenhum cachorro na lista, e 0 será a 1a posição
    if (id === undefined || typeof id !== 'number' || isNaN(id) || id !== parseInt(id) || id < -1)
      throw ('Id do Cachorro inválido!')

    this.#id = id
  }

  set nome(nome) {
    if (nome === undefined || typeof nome !== 'string' || nome === "")
      throw ('Nome do Cachorro inválido!')

    if (nome.length > 15)
      nome = nome.substring(0, 15)

    this.#nome = nome
  }

  set raca(raca) {
    if (raca === undefined || typeof raca !== 'string' || raca === "")
      throw ('Raça inválida!')

    if (raca.length > 40)
      raca = raca.substring(0, 40)

    this.#raca = raca
  }

  set porte(porte) {
    if (porte === undefined || typeof porte !== 'string' || porte === "")
      throw ('Porte inválido!')

    if (porte.length > 15)
      porte = porte.substring(0, 15)

    this.#porte = porte
  }

  set cor(cor) {
    if (cor === undefined || typeof cor !== 'string' || cor === "")
      throw ('Cor do Cachorro inválido!')

    if (cor.length > 10)
      cor = cor.substring(0, 10)

    this.#cor = cor
  }

  set dono(dono) {
    if (dono === undefined || typeof dono !== 'string' || dono === "")
      throw ('Nome do Dono do Cachorro inválido!')

    if (dono.length > 30)
      dono = dono.substring(0, 30)

    this.#dono = dono
  }

  set cep(cep) {
    if (cep === undefined || typeof cep !== 'string' || cep === "")
      throw ('CEP da residência do cachorro inválido!')

    if (cep.length > 8)
      cep = cep.substring(0, 8)

    this.#cep = cep
  }

  set complemento(complemento) {
    if (complemento === undefined || typeof complemento !== 'string' || complemento === "")
      throw ('Nome do Dono do Cachorro inválido!')

    if (complemento.length > 15)
      complemento = complemento.substring(0, 15)

    this.#complemento = complemento
  }

  set numeroCasa(numero) {
    if (numero === undefined || typeof numero !== 'number' || isNaN(numero) || numero !== parseInt(numero) || numero < 0)
      throw ('Número da casa do cachorro inválido!')

    this.#numeroCasa = numero
  }

  set idade(idade) {
    if (idade === undefined || typeof idade !== 'number' || isNaN(idade) || idade !== parseInt(idade) || idade < 0)
      throw ('Idade do cachorro inválida!')

    this.#idade = idade
  }

  set peso(peso) {
    if (peso === undefined || typeof peso !== 'number' || isNaN(peso) || peso < 0)
      throw ('Peso do cachorro inválido');

    this.#peso = peso;
  }
}

function novoCachorro (id, nome, raca, porte, cor, dono, cep, complemento, idade, numeroCasa, peso)
{
  try {
    return new Cachorro(id, nome, raca, porte, cor, dono, cep, complemento, idade, numeroCasa, peso)
  }
  catch (erro) {
    throw erro;
  }
}


module.exports = { novoCachorro }

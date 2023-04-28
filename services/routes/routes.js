/*
    Esse arquivo tem a única e exclusiva função de tratar as rotas
    das requisições e chamar as funções correspondentes no controller
*/

const express = require('express')
const router = express.Router()

router.get('/rota', controller.funcao)

module.exports = router
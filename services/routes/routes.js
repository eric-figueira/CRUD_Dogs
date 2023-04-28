/*
    Esse arquivo tem a única e exclusiva função de tratar as rotas
    das requisições e chamar as funções correspondentes no controller
*/

const express = require('express')
const router = express.Router()
const controller = require('../controllers/controller')


router.get('/cachorros',     controller.recuperarTodos)
router.get('/cachorros/:id', controller.recuperarUm)
router.post('/cachorros',    controller.inserir)
router.put('/cachorros/:id', controller.atualizar)
router.put('/cachorros/:id', controller.deletar)


module.exports = router
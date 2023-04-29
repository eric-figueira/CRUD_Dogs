/*
    Esse arquivo tem a única e exclusiva função de tratar as rotas
    das requisições e chamar as funções correspondentes no controller
*/

const Router = require('express');
const router = Router()
const { recuperarTodos, recuperarUm, inserir, atualizar, deletar } = require ('../controllers/controller')


router.get('/cachorros', recuperarTodos)
router.get('/cachorros/:id', recuperarUm)
router.post('/cachorros', inserir)
router.put('/cachorros/:id', atualizar)
router.delete('/cachorros/:id', deletar)


module.exports = router
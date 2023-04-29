/*
    Esse arquivo tem a única e exclusiva função de tratar as rotas
    das requisições e chamar as funções correspondentes no controller
*/

import { Router } from 'express'
const router = Router()
import { recuperarTodos, recuperarUm, inserir, atualizar, deletar } from '../controllers/controller'


router.get('/cachorros', recuperarTodos)
router.get('/cachorros/:id', recuperarUm)
router.post('/cachorros', inserir)
router.put('/cachorros/:id', atualizar)
router.delete('/cachorros/:id', deletar)


export default router
const express = require('express')
const router = express.Router()

router.get('/rota', controller.funcao)

module.exports = router
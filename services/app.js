const express = require('express')
const rotas = require('./routes/routes')

const port = 3000
const app = express()


app.use(express.json())
app.use('/', rotas)


app.listen(port, () => {
  console.log(`Node Server Started on port ${port}`)
})
import express, { json } from 'express'
import rotas from './routes/routes'

const port = 3000
const app = express()


app.use(json())
app.use('/', rotas)


app.listen(port, () => {
  console.log(`Node Server Started on port ${port}`)
})
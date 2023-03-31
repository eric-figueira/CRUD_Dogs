package CRUD.bd;

import CRUD.bd.core.*;
import CRUD.bd.daos.*;

public class BDSQLServer {
    public static final MeuPreparedStatement COMANDO;

    static {
        MeuPreparedStatement comando = null;

        try {
            comando = new MeuPreparedStatement(
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                    "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD22156",
                    "BD22156", "horadecolocareucoloco");
        } catch (Exception erro) {
            System.err.println("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}
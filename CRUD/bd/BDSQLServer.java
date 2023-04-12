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
                    "jdbc:sqlserver://updateme:1433;databasename=updateme",
                    "logon", "password");
        } catch (Exception erro) {
            System.err.println("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}
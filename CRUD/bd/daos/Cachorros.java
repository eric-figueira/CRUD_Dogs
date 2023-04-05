package CRUD.bd.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import CRUD.bd.BDSQLServer;
import CRUD.bd.core.MeuResultSet;
import CRUD.bd.*;
import CRUD.bd.core.*;
import CRUD.bd.dbos.*;

import javax.swing.*;

public class Cachorros {
    public static boolean isCadastrado(int id) throws Exception {
        boolean retorno = false;

        try {
            String sql = "select * from CRUD_Dogs.Cachorro where id = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);;
            BDSQLServer.COMANDO.setInt(1, id);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch(SQLException erro) {
            throw new Exception("DEU ERRO NA HORA DE BUSCAR O CACHORRO!");
        }
        return retorno;
    }

    public static void inserir(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, MALIGNO!");
        try {
            String sql = "insert into CRUD_Dogs.Cachorro (nome, raca, idade, peso, porte, cor, dono, cep, numeroCasa) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, cachorro.getNome());
            BDSQLServer.COMANDO.setString(2, cachorro.getRaca());
            BDSQLServer.COMANDO.setInt(3, cachorro.getIdade());
            BDSQLServer.COMANDO.setFloat(4, cachorro.getPeso());
            BDSQLServer.COMANDO.setString(5, cachorro.getPorte());
            BDSQLServer.COMANDO.setString(6, cachorro.getCor());
            BDSQLServer.COMANDO.setString(7, cachorro.getDono());
            BDSQLServer.COMANDO.setString(8, cachorro.getCep());
            BDSQLServer.COMANDO.setInt(9, cachorro.getNumeroCasa());
            BDSQLServer.COMANDO.setString(10, cachorro.getComplemento());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch(SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("ERRO AO INSERIR CACHORRO! AAAAA");
        }
    }

    public static void atualizar(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, PESTE!");
        if (!isCadastrado(cachorro.getIdCachorro()))
            throw new Exception("CACHORRO NÃO ESTÁ CADASTRADO, PESTE!");
        try {
            String sql = "update CRUD_Dogs.Cachorro set nome = ?, raca = ?, idade = ?, peso = ?, porte = ?, cor = ?, " +
                    "dono = ?, cep = ?, numeroCasa = ? where id = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, cachorro.getNome());
            BDSQLServer.COMANDO.setString(2, cachorro.getRaca());
            BDSQLServer.COMANDO.setInt(3, cachorro.getIdade());
            BDSQLServer.COMANDO.setFloat(4, cachorro.getPeso());
            BDSQLServer.COMANDO.setString(5, cachorro.getPorte());
            BDSQLServer.COMANDO.setString(6, cachorro.getCor());
            BDSQLServer.COMANDO.setString(7, cachorro.getDono());
            BDSQLServer.COMANDO.setString(8, cachorro.getCep());
            BDSQLServer.COMANDO.setInt(9, cachorro.getNumeroCasa());
            BDSQLServer.COMANDO.setInt(10, cachorro.getIdCachorro());
            BDSQLServer.COMANDO.setString(11, cachorro.getComplemento());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("ERRO AO ATUALIZAR CACHORRO! AAAAA");
        }
    }

    public static void excluir(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, CRIANÇA!");
        if (!isCadastrado(cachorro.getIdCachorro()))
            throw new Exception("CACHORRO NÃO ESTÁ CADASTRADO, CRIANÇA!");
        try {
            String sql = "delete from CRUD_Dogs.Cachorro where id = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, cachorro.getIdCachorro());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("ERRO AO EXCLUIR CACHORRO! AI QUE ÓDIO!");
        }
    }

    public static Cachorro getCachorro(int idCachorro) throws Exception {
        Cachorro cachorro = null;

        try {
            String sql = "select * from CRUD_Dogs.Cachorro where id = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, idCachorro);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            if (!resultado.first())
                throw new Exception("CACHORRO NÃO ESTÁ CADASTRADO, PESTE!");

            cachorro = new Cachorro(idCachorro,
                    resultado.getString("nome"),
                    resultado.getString("raca"),
                    resultado.getShort("idade"),
                    resultado.getFloat("peso"),
                    resultado.getString("porte"),
                    resultado.getString("cor"),
                    resultado.getString("dono"),
                    resultado.getString("cep"),
                    resultado.getShort("numeroCasa"),
                    resultado.getString("complemento"));
        }
        catch (SQLException erro) {
            throw new Exception("ERRO AO BUSCAR POR CACHORRO! AAAAAA");
        }
        return cachorro;
    }

    public static MeuResultSet getCachorros() throws Exception {
        MeuResultSet resultado = null;
        try {
            String sql = "select * from CRUD_Dogs.Cachorro";

            BDSQLServer.COMANDO.prepareStatement (sql);
            resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro) {
            throw new Exception("ERRO AO BUSCAR OS CACHORROS! AAAAA");
        }
        return resultado;
    }

    public static ArrayList<Cachorro> getArrayListCachorros() throws Exception {
        ArrayList<Cachorro> lista = new ArrayList<>();
        try {
            String sql = "select * from CRUD_Dogs.Cachorro";

            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            while (resultado.next()) {
                Cachorro cachorro = new Cachorro(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("raca"),
                    resultado.getShort("idade"),
                    resultado.getFloat("peso"),
                    resultado.getString("porte"),
                    resultado.getString("cor"),
                    resultado.getString("dono"),
                    resultado.getString("cep"),
                    resultado.getShort("numeroCasa"),
                    resultado.getString("complemento"));
                lista.add(cachorro);
            }
        }
        catch (SQLException erro) {
            throw new Exception("ERRO AO BUSCAR OS CACHORROS! AI QUE ÓDIO!");
        }
        return lista;
    }
}

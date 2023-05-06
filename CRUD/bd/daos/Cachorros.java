package CRUD.bd.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import CRUD.API.ClienteWS;
import CRUD.bd.dbos.Cachorro;

public class Cachorros {
    public static boolean isCadastrado(int id) throws Exception {
        boolean retorno = false;
        try {
            Cachorro c = (Cachorro) ClienteWS.getObjeto(Cachorro.class, "http://localhost:3000/cachorros/", id + "");
            if (c != null)
                retorno = true;
        }
        catch(Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return retorno;
    }

    public static void inserir(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, MALIGNO!");
        try {
            try {
                ClienteWS.postObjeto(Cachorro.class, cachorro,"http://localhost:3000/cachorros");
            }
            catch(Exception erro) {
                throw new Exception(erro.getMessage());
            }
        }
        catch(Exception erro) {
            throw new Exception("ERRO AO INSERIR CACHORRO! AAAAA");
        }
    }

    public static void atualizar(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, PESTE!");
        if (!isCadastrado(cachorro.getIdCachorro()))
            throw new Exception("CACHORRO NÃO ESTÁ CADASTRADO, PESTE!");
        try {
            ClienteWS.putObjeto(cachorro, Cachorro.class, "http://localhost:3000/cachorros", cachorro.getIdCachorro() + "");
        }
        catch (Exception erro) {
            throw new Exception("ERRO AO ATUALIZAR CACHORRO! AAAAA");
        }
    }

    public static void excluir(Cachorro cachorro) throws Exception {
        if (cachorro == null)
            throw new Exception("CACHORRO É NULO, CRIANÇA!");
        if (!isCadastrado(cachorro.getIdCachorro()))
            throw new Exception("CACHORRO NÃO ESTÁ CADASTRADO, CRIANÇA!");
        try {
            ClienteWS.deleteObjeto(Cachorro.class, "http://localhost:3000/cachorros", cachorro.getIdCachorro() + "");
        }
        catch (Exception erro) {
            throw new Exception("ERRO AO EXCLUIR CACHORRO! AI QUE ÓDIO!");
        }
    }

    public static ArrayList<LinkedHashMap> getCachorro(int idCachorro) throws Exception {
        ArrayList<LinkedHashMap> cachorro = null;

        try {
            cachorro = (ArrayList<LinkedHashMap>) ClienteWS.getObjeto(ArrayList.class, "http://localhost:3000/cachorros", idCachorro + "");
            System.out.println(cachorro);
        }
        catch (Exception erro) {
            throw new Exception("ERRO AO BUSCAR POR CACHORRO! AAAAAA");
        }
        return cachorro;
    }

    public static ArrayList<LinkedHashMap> getCachorros() throws Exception {
        ArrayList<LinkedHashMap> cachorros = null;
        try {
            cachorros = (ArrayList<LinkedHashMap>) ClienteWS.getObjetos(ArrayList.class, "http://localhost:3000/cachorros");
        }
        catch (Exception erro) {
            throw new Exception("ERRO AO BUSCAR OS CACHORROS! AAAAA");
        }
        return cachorros;
    }
}

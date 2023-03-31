package CRUD;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Janela extends JFrame {

    // Labels
    protected JLabel lbTitulo        = new JLabel("CRUD Cachorros"),
                     lbIdCachorro    = new JLabel("Id Cachorro"),
                     lbNomeCachorro  = new JLabel("Nome do Cachorro"),
                     lbRaca          = new JLabel("Raça"),
                     lbPorte         = new JLabel("Porte"),
                     lbCor           = new JLabel("Cor"),
                     lbNomeDono      = new JLabel("Nome do Dono"),
                     lbCep           = new JLabel("CEP"),
                     lbIdadeCachorro = new JLabel("Idade do Cachorro"),
                     lbNumeroCasa    = new JLabel("Número Casa"),
                     lbPeso          = new JLabel("Peso do Cachorro");

    // Botões
    protected JButton btnInserir   = new JButton("Inserir"),
                      btnBuscar    = new JButton("Buscar"),
                      btnDeletar   = new JButton("Deletar"),
                      btnAtualizar = new JButton("Atualizar"),
                      btnSalvar    = new JButton("Salvar"),
                      btnProximo   = new JButton(">"),
                      btnAnterior  = new JButton("<");

    // TextBoxes
    protected JTextField txtIdCachorro    = new JTextField(),
                         txtNomeCachorro  = new JTextField(),
                         txtRaca          = new JTextField(),
                         txtPorte         = new JTextField(),
                         txtCor           = new JTextField(),
                         txtNomeDono      = new JTextField(),
                         txtCep           = new JTextField(),
                         txtIdadeCachorro = new JTextField(),
                         txtNumeroCasa    = new JTextField(),
                         txtPeso          = new JTextField();

    public Janela() {

        super("CRUD Cachorros");


    }
}

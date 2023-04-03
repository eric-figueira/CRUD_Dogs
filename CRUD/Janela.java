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


        // JPanel geral onde todos os outros JPanel vão estar
        JPanel Cachorro = new JPanel();
        GridLayout grdCachorro = new GridLayout(4, 1);
        Cachorro.setLayout(grdCachorro);


            // Navbar onde vão ficar os botões CRUD
            JPanel navBotoes = new JPanel();
            FlowLayout flwBotoes = new FlowLayout();
            navBotoes.setLayout(flwBotoes);

            navBotoes.add(btnInserir);
            navBotoes.add(btnBuscar);
            navBotoes.add(btnAtualizar);
            navBotoes.add(btnDeletar);


            // Navbar onde vão ficar os botões Anterior e Próximo Cachorro
            JPanel navProxAnt = new JPanel();
            FlowLayout flwProxAnt = new FlowLayout();
            navProxAnt.setLayout(flwProxAnt);

            navProxAnt.add(btnAnterior);
            navProxAnt.add(btnProximo);


            // Grid onde vão ficar os labels e textboxes do Cachorro
            JPanel dgCachorro = new JPanel();
            GridLayout grdInfoCachorro = new GridLayout(10 , 2);
            dgCachorro.setLayout(grdInfoCachorro);

            // [label, textbox]
            dgCachorro.add(lbIdCachorro);
            dgCachorro.add(txtIdCachorro);

            dgCachorro.add(lbNomeCachorro);
            dgCachorro.add(txtNomeCachorro);

            dgCachorro.add(lbRaca);
            dgCachorro.add(txtRaca);

            dgCachorro.add(lbPorte);
            dgCachorro.add(txtPorte);

            dgCachorro.add(lbCor);
            dgCachorro.add(txtCor);

            dgCachorro.add(lbNomeDono);
            dgCachorro.add(txtNomeDono);

            dgCachorro.add(lbCep);
            dgCachorro.add(txtCep);

            dgCachorro.add(lbIdadeCachorro);
            dgCachorro.add(txtIdadeCachorro);

            dgCachorro.add(lbNumeroCasa);
            dgCachorro.add(txtNumeroCasa);

            dgCachorro.add(lbPeso);
            dgCachorro.add(txtPeso);



        Cachorro.add(lbTitulo);
        Cachorro.add(navBotoes);
        Cachorro.add(navProxAnt);
        Cachorro.add(dgCachorro);

    }
}

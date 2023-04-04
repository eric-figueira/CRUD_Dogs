package CRUD;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Janela extends JFrame {

    protected enum Operacao {
        INSERINDO, DELETANDO, ATUALIZANDO, BUSCANDO, NAVEGANDO
    }
    protected Operacao operacaoAtual;

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
                     lbPeso          = new JLabel("Peso do Cachorro"),
                     lbMensagem      = new JLabel("Mensagem: ");

    // Botões
    protected JButton btnInserir   = new JButton("Inserir"),
                      btnBuscar    = new JButton("Buscar"),
                      btnDeletar   = new JButton("Deletar"),
                      btnAtualizar = new JButton("Atualizar"),
                      btnSalvar    = new JButton("Salvar"),
                      btnCancelar  = new JButton("Cancelar"),
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


        // Navbar onde vão ficar os botões CRUD
        JPanel navBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        navBotoes.setLayout(flwBotoes);

        navBotoes.add(btnInserir);
        navBotoes.add(btnBuscar);
        navBotoes.add(btnAtualizar);
        navBotoes.add(btnDeletar);


        // JPanel geral onde todos os outros JPanel vão estar
        JPanel Cachorro = new JPanel();
        Cachorro.setLayout(new GridLayout(1, 1));

            // Grid onde vão ficar os labels e textboxes do Cachorro
            JPanel dgCachorro = new JPanel();
            GridLayout grdInfoCachorro = new GridLayout(11 , 2);
            dgCachorro.setLayout(grdInfoCachorro);

            dgCachorro.add(btnAnterior);
            dgCachorro.add(btnProximo);

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


        Cachorro.add(dgCachorro);

        Container ctnForm = this.getContentPane();
        ctnForm.setLayout(new BorderLayout());

        JPanel dgBottom = new JPanel();
        dgBottom.setLayout(new GridLayout(2, 2));

        dgBottom.add(btnSalvar);
        dgBottom.add(btnCancelar);
        dgBottom.add(lbMensagem);

        ctnForm.add(navBotoes, BorderLayout.NORTH);
        ctnForm.add(Cachorro, BorderLayout.CENTER);
        ctnForm.add(dgBottom, BorderLayout.SOUTH);

        this.setSize(450, 500);
        this.setVisible(true);
    }

    private void LimparCampos() {
        txtIdCachorro.setText("");
        txtNomeCachorro.setText("");
        txtRaca.setText("");
        txtPorte.setText("");
        txtCor.setText("");
        txtNomeDono.setText("");
        txtCep.setText("");
        txtIdadeCachorro.setText("");
        txtNumeroCasa.setText("");
        txtPeso.setText("");
    }

    private void VerificarHabilitacaoControles() {
        // Habilitamos todos os controles para desabilitar nas situações oportunas
        txtIdCachorro.setEditable(true);
        txtNomeCachorro.setEditable(true);
        txtRaca.setEditable(true);
        txtPorte.setEditable(true);
        txtCor.setEditable(true);
        txtNomeDono.setEditable(true);
        txtCep.setEditable(true);
        txtIdadeCachorro.setEditable(true);
        txtNumeroCasa.setEditable(true);
        txtPeso.setEditable(true);

        btnInserir.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnDeletar.setEnabled(true);
        btnAtualizar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnProximo.setEnabled(false); // ?
        btnAnterior.setEnabled(false);

        btnSalvar.setText("Salvar"); // Setamos para o texto padrão
        lbMensagem.setText("Mensagem: "); // Setamos para a mensagem padrão


        if (this.operacaoAtual != Operacao.NAVEGANDO)
        {
            // Isso não é condicional, pois em qualquer operação que não seja Navegar os botões estarão desabilitados
            // pois o usuário já erá clicado em algum deles
            btnInserir.setEnabled(false);
            btnBuscar.setEnabled(false);
            btnDeletar.setEnabled(false);
            btnAtualizar.setEnabled(false);


            if (this.operacaoAtual == Operacao.INSERINDO || this.operacaoAtual == Operacao.ATUALIZANDO) {
                // Digitará tudo, menos o IdCachorro (nas duas operações)
                txtIdCachorro.setEditable(false);

                // Para sair da operação de inserir ou atualizar, basta clicar no botão Cancelar, os outros
                // botões já estarão desabilitados

                if (this.operacaoAtual == Operacao.INSERINDO) {
                    this.LimparCampos();
                    btnSalvar.setText("Inserir");
                    lbMensagem.setText("Mensagem: Digite os dados e clique em Inserir. Ou clique em Cancelar para cancelar a operação");
                } else {
                    btnSalvar.setText("Atualizar");
                    lbMensagem.setText("Mensagem: Digite os dados e clique em Atualizar. Ou clique em Cancelar para cancelar a operação");
                }

            } else if (this.operacaoAtual == Operacao.BUSCANDO || this.operacaoAtual == Operacao.DELETANDO) {
                // Digitará apenas o IdCachorro, o resto estará desabilitado para digitar
                txtNomeCachorro.setEditable(false);
                txtRaca.setEditable(false);
                txtPorte.setEditable(false);
                txtCor.setEditable(false);
                txtNomeDono.setEditable(false);
                txtCep.setEditable(false);
                txtIdadeCachorro.setEditable(false);
                txtNumeroCasa.setEditable(false);
                txtPeso.setEditable(false);

                if (this.operacaoAtual == Operacao.BUSCANDO) {
                    btnSalvar.setText("Buscar");
                    lbMensagem.setText("Mensagem: Digite o Id e clique em Buscar. Ou clique em Cancelar para cancelar a operação");
                }
            }
        }
            // Para o deletar, vai fazer ele apenas clicar em deletar ou vai ter que digitar o id
            // Aqui teria algo para o Navegando?
    }

    protected class Insercao implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.INSERINDO;

        }
    }
}

package CRUD;

import CRUD.API.ClienteWS;
import CRUD.API.Logradouro;

import CRUD.bd.daos.Cachorros;
import CRUD.bd.dbos.Cachorro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.util.*;


public class Janela extends JFrame {

    protected enum Operacao {
        INSERINDO, DELETANDO, ATUALIZANDO, BUSCANDO, NAVEGANDO
    }
    protected Operacao operacaoAtual;

    // Lista de Cachorros
    ArrayList<Cachorro> listaCachorros = new ArrayList<>();
    protected int posicaoCachorroAtual;

    // Labels
    protected JLabel lbIdCachorro    = new JLabel("Id Cachorro"),
                     lbNomeCachorro  = new JLabel("Nome do Cachorro"),
                     lbRaca          = new JLabel("Raça"),
                     lbPorte         = new JLabel("Porte"),
                     lbCor           = new JLabel("Cor"),
                     lbNomeDono      = new JLabel("Nome do Dono"),
                     lbCep           = new JLabel("CEP"),
                     lbIdadeCachorro = new JLabel("Idade do Cachorro"),
                     lbNumeroCasa    = new JLabel("Número"),
                     lbPeso          = new JLabel("Peso do Cachorro (Kg)"),
                     lbComplemento   = new JLabel("Complemento"),
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

    // Inputs
    protected JTextField txtIdCachorro    = new JTextField(),
                         txtNomeCachorro  = new JTextField(),
                         txtRaca          = new JTextField(),
                         txtCor           = new JTextField(),
                         txtNomeDono      = new JTextField(),
                         txtComplemento   = new JTextField();

    protected JFormattedTextField txtCep; // textbox com formatação (máscara)

    // input number
    protected JSpinner spIdadeCachorro = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1)),
                       spNumero        = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1)),
                       spPeso          = new JSpinner(new SpinnerNumberModel(0, 0, 110, 0.1));

    String[] portes = { "Mini", "Pequeno", "Médio", "Grande", "Gigante" };
    protected JComboBox<String> cbPorte = new JComboBox<>(portes); // combobox para auxiliar o usuário

    // JList onde serão exibidos os dados do CEP
    protected JTextArea txtLogradouro = new JTextArea();


    public Logradouro retornarLogradouro(String cep) throws Exception {
        try {
            return (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep/", cep);
        } catch (Exception ex) {
            throw new Exception("ERRO AO RETORNAR LOGRADOURO!");
        }
    }

    public void updateArrayListCachorros() {
        try { this.listaCachorros = Cachorros.getArrayListCachorros(); }
        catch (Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "OCORREU UM ERRO!", JOptionPane.ERROR_MESSAGE); }
    }

    public Janela() {

        super("CRUD Cachorros");

        try {
            // Setando a máscara para o CEP
            MaskFormatter mascaraCEP = new MaskFormatter("##.###-###");
            txtCep = new JFormattedTextField(mascaraCEP);
        }
        catch (Exception ignored) { }

        this.txtLogradouro.setEditable(false); // O usuário não poderá alterar os dados de endereço NUNCA
        this.txtLogradouro.setLineWrap(true);
        this.txtLogradouro.setFont(new Font("Courier New",
                                            this.txtLogradouro.getFont().getStyle(), 18));

        // Setando tratadores de eventos para os botões
        btnInserir.addActionListener(new Inserir());
        btnAtualizar.addActionListener(new Atualizar());
        btnBuscar.addActionListener(new Buscar());
        btnDeletar.addActionListener(new Deletar());
        btnProximo.addActionListener(new PassarParaProximo());
        btnAnterior.addActionListener(new PassarParaAnterior());
        btnSalvar.addActionListener(new Salvar());
        btnCancelar.addActionListener(new Cancelar());
        txtCep.addFocusListener(new MostrarLogradouro());


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
        Cachorro.setLayout(new GridLayout(1, 2));

            // Grid onde vão ficar os labels e textboxes do Cachorro
            JPanel dgCachorro = new JPanel();

            GridLayout grdInfoCachorro = new GridLayout(12 , 2);
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
            dgCachorro.add(cbPorte);

            dgCachorro.add(lbCor);
            dgCachorro.add(txtCor);

            dgCachorro.add(lbNomeDono);
            dgCachorro.add(txtNomeDono);

            dgCachorro.add(lbIdadeCachorro);
            dgCachorro.add(spIdadeCachorro);

            dgCachorro.add(lbPeso);
            dgCachorro.add(spPeso);

            dgCachorro.add(lbCep);
            dgCachorro.add(txtCep);

            dgCachorro.add(lbComplemento);
            dgCachorro.add(txtComplemento);

            dgCachorro.add(lbNumeroCasa);
            dgCachorro.add(spNumero);


        Cachorro.add(dgCachorro);
        Cachorro.add(txtLogradouro);

        Container ctnForm = this.getContentPane();
        ctnForm.setLayout(new BorderLayout());

        JPanel dgBottom = new JPanel();
        dgBottom.setLayout(new GridLayout(2, 1));
        JPanel dgButtons = new JPanel();
        dgButtons.setLayout(new GridLayout(1, 2));

        dgButtons.add(btnSalvar);
        dgButtons.add(btnCancelar);

        dgBottom.add(dgButtons);
        dgBottom.add(lbMensagem);

        ctnForm.add(navBotoes, BorderLayout.NORTH);
        ctnForm.add(Cachorro, BorderLayout.CENTER);
        ctnForm.add(dgBottom, BorderLayout.SOUTH);

        // Fazer get dos cachorros e setar a posição para 0
        updateArrayListCachorros();
        if (listaCachorros.isEmpty()) posicaoCachorroAtual = -1; // Nenhum cachorro na lista
        else posicaoCachorroAtual = 0; // Estamos no primeiro cachorro da lista

        this.operacaoAtual = Operacao.NAVEGANDO;
        this.VerificarHabilitacaoControles();

        this.addWindowListener(new FechamentoDeJanela());

        this.setSize(750, 500);
        this.setVisible(true);
    }

    private void LimparCampos() {
        txtIdCachorro   .setText("");
        txtNomeCachorro .setText("");
        txtRaca         .setText("");
        cbPorte         .setSelectedIndex(0);
        txtCor          .setText("");
        txtNomeDono     .setText("");
        spIdadeCachorro .setValue(0);
        spPeso          .setValue(0);
        txtCep          .setText("");
        spNumero        .setValue(0);
        txtComplemento  .setText("");
        txtLogradouro   .setText("");
    }

    private void VerificarPosicaoCachorroEPreencherCampos() {
        // Método que será responsável por ler a posição atual do cachorro e colocar os seus dados nos campos
        // além de testar se os botoes Proximo e Anterior deverão ser (des)habilitados

        if (operacaoAtual == Operacao.NAVEGANDO)
        {
            if (this.listaCachorros.isEmpty())
            {
                // Lista vazia, não pode navegar
                btnProximo.setEnabled(false);
                btnAnterior.setEnabled(false);
                // Também não lemos nenhum dado

                // Caso acabamos de deletar o único cachorro da lista, precisamos limpar a tela
                LimparCampos();
            }
            else
            {
                btnProximo.setEnabled(true);
                btnAnterior.setEnabled(true);

                if (posicaoCachorroAtual == listaCachorros.size() - 1) {
                    // Se estiver na última posição, não pode ir para o próximo
                    btnProximo.setEnabled(false);
                }
                if (posicaoCachorroAtual == 0) {
                    // Se estiver na primeira posição, não pode ir para o anterior
                    btnAnterior.setEnabled(false);
                }

                // Pegar as informações do cachorro nessa posição
                try
                {
                    // Precisamos pegar o cachorro a partir do ArrayList, e nao do banco de dados, pois
                    // não necessariamente haverá um cachorro com o id 0, 1, 2
                    Cachorro r = listaCachorros.get(posicaoCachorroAtual);

                    txtIdCachorro   .setText(r.getIdCachorro() + "");
                    txtNomeCachorro .setText(r.getNome());
                    txtRaca         .setText(r.getRaca());
                    cbPorte         .setSelectedItem(r.getPorte());
                    txtCor          .setText(r.getCor());
                    txtNomeDono     .setText(r.getDono());
                    txtCep          .setText(r.getCep());
                    txtComplemento  .setText(r.getComplemento());
                    spIdadeCachorro .setValue(r.getIdade());
                    spNumero        .setValue(r.getNumeroCasa());
                    spPeso          .setValue(r.getPeso());

                    // Mostrar logradouro no textarea
                    // Por algum motivo, o CEP era salvo com espaço no final, o que fazia a requisição da api retornar erro
                    MostrarLogradouro(r.getCep().trim());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO AO ACESSAR CACHORRO!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            // Não navega, portanto, temos que desabilitar os botões de navegação
            btnProximo.setEnabled(false);
            btnAnterior.setEnabled(false);
        }
    }

    private void VerificarHabilitacaoControles() {
        // Habilitamos todos os controles para desabilitar nas situações oportunas
        boolean podeHabilitar = operacaoAtual != Operacao.NAVEGANDO;

        txtIdCachorro   .setEditable(podeHabilitar);
        txtNomeCachorro .setEditable(podeHabilitar);
        txtRaca         .setEditable(podeHabilitar);
        cbPorte         .setEnabled (podeHabilitar);
        txtCor          .setEditable(podeHabilitar);
        txtNomeDono     .setEditable(podeHabilitar);
        spIdadeCachorro .setEnabled (podeHabilitar);
        spPeso          .setEnabled (podeHabilitar);
        txtCep          .setEditable(podeHabilitar);
        spNumero        .setEnabled (podeHabilitar);
        txtComplemento  .setEditable(podeHabilitar);

        btnInserir  .setEnabled(!podeHabilitar);

        // caso não haja cachorros ainda cadastrados, o usuário não pode realizar nenhuma ação exceto incluir
        boolean pode = !listaCachorros.isEmpty();
        btnBuscar   .setEnabled(pode);
        btnDeletar  .setEnabled(pode);
        btnAtualizar.setEnabled(pode);

        btnSalvar   .setEnabled(podeHabilitar);
        btnCancelar .setEnabled(podeHabilitar);

        // btnProximo e btnAnterior vão estar a ser setados pelo método VerificarPosicaoCachorroEPreencherCampos
        VerificarPosicaoCachorroEPreencherCampos();

        btnSalvar.setText("Salvar"); // Setamos para o texto padrão
        lbMensagem.setText("Mensagem: "); // Setamos para a mensagem padrão


        if (this.operacaoAtual != Operacao.NAVEGANDO)
        {
            // como o usuário já selecionou uma opção, para voltar a navegação ele terá de clicar em cancelar,
            // então impedimos o mesmo de realizar as outras operações
            btnInserir.setEnabled(false);
            btnBuscar.setEnabled(false);
            btnAtualizar.setEnabled(false);
            btnDeletar.setEnabled(false);

            if (this.operacaoAtual == Operacao.INSERINDO || this.operacaoAtual == Operacao.ATUALIZANDO) {
                // Digitará tudo, menos o IdCachorro (nas duas operações)
                txtIdCachorro   .setEditable(false);
                txtNomeCachorro .setEditable(true);
                txtRaca         .setEditable(true);
                cbPorte         .setEnabled (true);
                txtCor          .setEditable(true);
                txtNomeDono     .setEditable(true);
                spIdadeCachorro .setEnabled (true);
                spPeso          .setEnabled (true);
                txtCep          .setEditable(true);
                spNumero        .setEnabled (true);
                txtComplemento  .setEditable(true);

                // Para sair da operação de inserir ou atualizar, basta clicar no botão Cancelar, os outros
                // botões já estarão desabilitados

                if (this.operacaoAtual == Operacao.INSERINDO) {
                    btnSalvar.setText("Inserir");
                    lbMensagem.setText("Mensagem: Digite os dados e clique em Inserir. Ou clique em Cancelar para cancelar a operação");
                }
                else {
                    btnSalvar.setText("Atualizar");
                    lbMensagem.setText("Mensagem: Digite os dados e clique em Atualizar. Ou clique em Cancelar para cancelar a operação");
                }

            } else if (this.operacaoAtual == Operacao.BUSCANDO || this.operacaoAtual == Operacao.DELETANDO) {
                // Digitará apenas o IdCachorro (na Busca), o resto estará desabilitado para digitar
                txtIdCachorro   .setEditable(true);
                txtNomeCachorro .setEditable(false);
                txtRaca         .setEditable(false);
                cbPorte         .setEnabled (false);
                txtCor          .setEditable(false);
                txtNomeDono     .setEditable(false);
                spIdadeCachorro .setEnabled (false);
                spPeso          .setEnabled (false);
                txtCep          .setEditable(false);
                spNumero        .setEnabled (false);
                txtComplemento  .setEditable(false);

                if (this.operacaoAtual == Operacao.BUSCANDO) {
                    btnSalvar.setText("Buscar");
                    lbMensagem.setText("Mensagem: Digite o Id e clique em Buscar. Ou clique em Cancelar para cancelar a operação");
                }
                else {
                    txtIdCachorro.setEditable(false);
                    btnSalvar.setText("Deletar");
                    lbMensagem.setText("Mensagem: Clique em Deletar para confirmar deleção. Ou clique em Cancelar para cancelar a operação");
                }
            }
        }
    }

    protected static class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    protected class Salvar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try
            {
                if (operacaoAtual != Operacao.BUSCANDO)
                {
                    String cep = txtCep.getText().substring(0, 2) + txtCep.getText().substring(3, 6)
                            + txtCep.getText().substring(7, 10);
                    Cachorro cachorro = new Cachorro(-1, txtNomeCachorro.getText(), txtRaca.getText(),
                            Short.parseShort(spIdadeCachorro.getValue().toString()),
                            Float.parseFloat(spPeso.getValue().toString()), cbPorte.getSelectedItem() + "",
                            txtCor.getText(), txtNomeDono.getText(), cep,
                            Short.parseShort(spNumero.getValue().toString()), txtComplemento.getText());

                    if (operacaoAtual == Operacao.INSERINDO) {
                        Cachorros.inserir(cachorro);

                        JOptionPane.showMessageDialog(null, "Inserção feita com êxito!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                        // O ArrayList de cachorros deve ser atualizado
                        updateArrayListCachorros();

                        // Como acabamos de inserir no banco de dados, o cachorro inserido será o último da lista, e ao setar
                        // posicaoCachorroAtual para o tamanho da lista - 1, estaremos carregando os dados do último cachorro
                        posicaoCachorroAtual = listaCachorros.size() - 1;
                    } else if (operacaoAtual == Operacao.ATUALIZANDO) {
                        cachorro.setIdCachorro(Integer.parseInt(txtIdCachorro.getText()));
                        Cachorros.atualizar(cachorro);

                        JOptionPane.showMessageDialog(null, "Atualização feita com êxito!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                        // O ArrayList de cachorros deve ser atualizado
                        updateArrayListCachorros();
                    } else if (operacaoAtual == Operacao.DELETANDO) {
                        cachorro.setIdCachorro(Integer.parseInt(txtIdCachorro.getText()));
                        Cachorros.excluir(cachorro);

                        JOptionPane.showMessageDialog(null, "Deleção feita com êxito!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                        // Se tem 1 elemento na lista, a posição tem que ir para -1 pois nao ha cachorro ha ser mostrado
                        if (listaCachorros.size() == 1)
                            posicaoCachorroAtual = -1;
                        else {
                            // Se estiver numa posicao maior que 0, decrementamos 1, pois caso esteja na 1a
                            // posicao, permanecemos ali, pois quando o arraylist for atualizado, os dados serao
                            // "movidos" 1 posicao anterior, portanto nao precisamos mudar a posicao quando estiver 1a
                            if (posicaoCachorroAtual > 0)
                                posicaoCachorroAtual -= 1;
                        }

                        // O ArrayList de cachorros deve ser atualizado
                        updateArrayListCachorros();
                    }
                }
                // Foi necessário fazer essa divisão, pois ao clicar em Buscar, lá em cima tentava instanciar um cachorro,
                // mas pelo fato de não ter os outros dados exceto o Id, não dava certo
                else
                {
                    if (txtIdCachorro.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(
                                null, "Preencha todos os campos corretamente!", "Erro!",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Cachorro c = Cachorros.getCachorro(Integer.parseInt(txtIdCachorro.getText()));
                    posicaoCachorroAtual = listaCachorros.indexOf(c);
                }

                // Depois que o usuário fez o que tinha que fazer, tem que voltar para o modo de navegação
                operacaoAtual = Operacao.NAVEGANDO;
                VerificarHabilitacaoControles();
            }
            catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO AO CONCLUIR OPERAÇÃO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected class Inserir implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.INSERINDO;
            VerificarHabilitacaoControles();

            // Precisamos limpar a tela para o usuário colocar os dados do cachorro
            LimparCampos();
        }
    }

    protected class Atualizar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.ATUALIZANDO;
            VerificarHabilitacaoControles();

            // Não limpamos a tela, pois o usuário pode não querer mudar todos os dados
        }
    }

    protected class Buscar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.BUSCANDO;
            VerificarHabilitacaoControles();

            // Os campos serão limpos para que, quando o usuário digitar o Id, o resto das informações sejam mostradas
            LimparCampos();
        }
    }

    protected class Deletar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.DELETANDO;
            VerificarHabilitacaoControles();
        }
    }

    protected class Cancelar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operacaoAtual = Operacao.NAVEGANDO;

            // O usuário tinha (ou não) dados da tela, decidiu fazer alguma operação, os campos foram
            // habilitados e limpos, mas o usuário decidiu cancelar a operação, precisamos recolocar
            // os dados que antes estavam na tela. O método abaixo chama o método que faz isso

            // Antes de colocar os dados novamente, precisamos limpar os que o usuário colocara anteriormente
            LimparCampos();

            VerificarHabilitacaoControles();
        }
    }

    protected class PassarParaProximo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            posicaoCachorroAtual++;
            VerificarPosicaoCachorroEPreencherCampos();
        }
    }

    protected class PassarParaAnterior implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            posicaoCachorroAtual--;
            VerificarPosicaoCachorroEPreencherCampos();
        }
    }

    public void MostrarLogradouro(String cep) {
        try
        {
            Logradouro l = retornarLogradouro(cep);

            // Caso já tenha algo escrito no textArea, precisamos limpá-lo antes de escrever o novo dado
            txtLogradouro.setText("");
            txtLogradouro.append(l.toString());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Verifique se digitou o CEP corretamente ou se está conectado devidamente à rede",
                    "ERRO AO RETORNAR LOGRADOURO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected class MostrarLogradouro extends FocusAdapter {
        public void focusLost(FocusEvent e) {
            if (operacaoAtual == Operacao.INSERINDO || operacaoAtual == Operacao.ATUALIZANDO) {
                String cep = txtCep.getText().substring(0, 2) + txtCep.getText().substring(3, 6)
                        + txtCep.getText().substring(7, 10);
                MostrarLogradouro(cep);
            }
        }
    }
}
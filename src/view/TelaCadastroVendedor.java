package view;

import contoller.VendedorController;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.bean.Vendedor;

/**
 *
 * @author joseo
 */
public class TelaCadastroVendedor extends javax.swing.JInternalFrame {

    private javax.swing.table.DefaultTableModel tabelaModelo;
    private VendedorController vController;
    private Vendedor vSelecionado = new Vendedor();
    private boolean podeEditar = false;

    /**
     * Creates new form cadastroDeLivros
     */
    public TelaCadastroVendedor() {
        vController = new VendedorController();
        CriarTabelaModelo();
        initComponents();
        vController.listarTodos(tabelaModelo);
        limparCampos();
    }

    public void CriarTabelaModelo() {

        tabelaModelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null}
                },
                new String[]{
                    "Código", "CPF", "Nome", "Endereço", "Status"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        };
    }

    public void preencherSelecionado(ListSelectionEvent e) {
        int linha = tabelaDeVendedores.getSelectedRow();
        try {
            int id = Integer.parseInt(tabelaModelo.getValueAt(linha, 0).toString());
            String CPF = tabelaModelo.getValueAt(linha, 1).toString();
            String nome = tabelaModelo.getValueAt(linha, 2).toString();
            String endereco = tabelaModelo.getValueAt(linha, 3).toString();
            boolean status = tabelaModelo.getValueAt(linha, 4).toString().equals("1 - Ativo");

            this.preencherVendedor(vSelecionado, id, CPF, nome, endereco, status);

            this.preencherCampos();
            this.habilitarCamposEdicao();
        } catch (Exception erro) {
            this.limparCampos();
        }
    }

    public void preencherVendedor(Vendedor v, int id, String CPF, String nome, String endereco, boolean status) {
        if (CPF != null && nome != null && endereco != null) {
            v.setId(id);
            v.setCPF(CPF);
            v.setNome(nome);
            v.setEndereco(endereco);
            v.setStatus(status);
        } else {
            this.limparCampos();
        }
    }

    public void preencherCampos() {
        CPFText.setText(vSelecionado.getCPF());
        nomeText.setText(vSelecionado.getNome());
        enderecoText.setText(vSelecionado.getEndereco());
        String status_index = vSelecionado.isStatus() ? "1 - Ativo" : "2 - Inativo";
        statusLista.getModel().setSelectedItem(status_index);
    }

    public void limparCampos() {
        vSelecionado = new Vendedor(); // Limpar vendedor selecionado

        CPFText.setText("");
        nomeText.setText("");
        enderecoText.setText("");
        String status_index = "2 - Inativo";
        statusLista.getModel().setSelectedItem(status_index);

        tabelaDeVendedores.getSelectionModel().clearSelection();
        this.desabilitarCamposEdicao();
    }
    
    public void desabilitarCamposEdicao() {
        this.CPFText.setEnabled(false);
        this.nomeText.setEnabled(false);
        this.enderecoText.setEnabled(false);
        this.statusLista.setEnabled(false);
        
        this.podeEditar = false;
    }
    
    public void habilitarCamposEdicao() {
        this.CPFText.setEnabled(true);
        this.nomeText.setEnabled(true);
        this.enderecoText.setEnabled(true);
        this.statusLista.setEnabled(true);
        
        this.podeEditar = true;
    }

    // Selecionar linhas na tabela
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDeVendedores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CPFText = new javax.swing.JTextField();
        nomeText = new javax.swing.JTextField();
        enderecoText = new javax.swing.JTextField();
        statusLista = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnProcurar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadasro de Vendores");

        tabelaDeVendedores.setModel(tabelaModelo);
        tabelaDeVendedores.getTableHeader().setReorderingAllowed(false);
        tabelaDeVendedores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                preencherSelecionado(e);
            }
        });
        jScrollPane1.setViewportView(tabelaDeVendedores);

        jLabel1.setText("CPF");

        jLabel2.setText("NOME");

        jLabel3.setText("ENDEREÇO");

        jLabel4.setText("STATUS CADASTRO");

        CPFText.setText("000.000.000-00");

        nomeText.setText("Nome Completo");

        enderecoText.setText("Rua, Numero, Cidade - UF");

        statusLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Ativo", "0 - Inativo" }));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnProcurar.setText("Procurar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CPFText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomeText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(enderecoText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(statusLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNovo)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CPFText, enderecoText, nomeText, statusLista});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExcluir, btnNovo, btnProcurar, btnSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(CPFText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(nomeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(enderecoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(statusLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExcluir, btnNovo, btnProcurar, btnSalvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        if (!this.podeEditar) {
            JOptionPane.showMessageDialog(this, "Selecione um vendor na tabela ou clique em NOVO.\nÉ preciso preencher todos os campos.");
            return;
        }
        
        int id = vSelecionado.getId();
        String CPF = CPFText.getText();
        String nome = nomeText.getText();
        String endereco = enderecoText.getText();
        boolean status = statusLista.getSelectedItem().toString().equals("1 - Ativo");

        this.preencherVendedor(vSelecionado, id, CPF, nome, endereco, status);

        if (vSelecionado != null && !(CPF.equals("") || nome.equals("") || endereco.equals("") )) {
            if (vSelecionado.getId() != 0) {
                // atualizar
                vController.salvar(tabelaModelo, vSelecionado, false);
            } else {
                // criar novo
                vController.salvar(tabelaModelo, vSelecionado, true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "É preciso preencher todos os campos.");
        }

        limparCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (vSelecionado == null) {
            JOptionPane.showMessageDialog(this, "O Vendedor selecionado não existe no banco de dados.\nTente selecionar um vendedor da tabela abaixo." );
        } else {
            vController.excluir(tabelaModelo, vSelecionado);
        }

        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        // TODO add your handling code here:
        Object[] possibilities = {"Todos", "Pelo Código", "Pelo CPF"};
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o tipo de busca\n"
                + "que deseja efetuar",
                "Buscar Vendedor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilities,
                possibilities[0]);
        
        escolha = escolha == null ? "" : escolha;

        switch (escolha) {
            case "Pelo Código":
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Código do Vendedor: "));
                vController.listarPorId(tabelaModelo, id);
                break;
            case "Pelo CPF":
                 String CPF = JOptionPane.showInputDialog(this, "CPF do Vendedor: ");
                vController.listarPorCPF(tabelaModelo, CPF);
                break;
            default:
                vController.listarTodos(tabelaModelo);
        }
    }//GEN-LAST:event_btnProcurarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        this.habilitarCamposEdicao();
        this.CPFText.requestFocus();
    }//GEN-LAST:event_btnNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CPFText;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField enderecoText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeText;
    private javax.swing.JComboBox<String> statusLista;
    private javax.swing.JTable tabelaDeVendedores;
    // End of variables declaration//GEN-END:variables

}

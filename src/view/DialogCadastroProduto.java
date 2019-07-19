/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.CadastroProdutoControl;
import entities.Categoria;
import entities.Fornecedor;
import entities.Produto;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

/**
 *
 * @author erick
 */
public class DialogCadastroProduto extends javax.swing.JDialog implements MouseListener, KeyListener {
    
    CadastroProdutoControl control;

    /**
     * Creates new form DialogCadastroProduto
     * @param produto produto a alterar, null se for cadastrar
     */
    public DialogCadastroProduto(java.awt.Frame parent, boolean modal, Produto produto) {
        super(parent, modal);
        control = new CadastroProdutoControl(this, (TelaPrincipal) parent, produto);
        initComponents();
        control.configurarComboBoxs();
        control.verificaAlteracao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfPrecoCompra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfPrecoVenda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbFornecedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfCodigoBarra = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produto");
        setIconImage(new ImageIcon(System.getProperty("user.dir") + "/src/resources/teste1.2.png").getImage());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cadastro Produto");

        jLabel2.setText("Nome do Produto");

        jLabel3.setText("Preço de Compra");

        tfPrecoCompra.addKeyListener(this);

        jLabel4.setText("Preço de Venda");

        tfPrecoVenda.addKeyListener(this);

        jLabel5.setText("Fornecedor");

        jLabel6.setText("Categoria");

        jButton1.setText("+");
        jButton1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButton1.addMouseListener(this);

        jButton2.setText("+");
        jButton2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButton2.addMouseListener(this);

        btCadastrar.setText("Cadastrar");
        btCadastrar.addMouseListener(this);

        btCancelar.setText("Cancelar");
        btCancelar.addMouseListener(this);

        jLabel7.setText("Código de Barras");

        tfCodigoBarra.addKeyListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfCodigoBarra)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCancelar)
                        .addGap(110, 110, 110))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbFornecedor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfPrecoCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPrecoVenda)
                            .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(tfCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(2, 2, 2)
                                .addComponent(tfPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(cbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCadastrar)
                    .addComponent(btCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Code for dispatching events from components to event handlers.

    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == tfPrecoCompra) {
            DialogCadastroProduto.this.tfPrecoCompraKeyPressed(evt);
        }
        else if (evt.getSource() == tfPrecoVenda) {
            DialogCadastroProduto.this.tfPrecoVendaKeyPressed(evt);
        }
        else if (evt.getSource() == tfCodigoBarra) {
            DialogCadastroProduto.this.tfCodigoBarraKeyPressed(evt);
        }
    }

    public void keyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == tfPrecoCompra) {
            DialogCadastroProduto.this.tfPrecoCompraKeyReleased(evt);
        }
        else if (evt.getSource() == tfPrecoVenda) {
            DialogCadastroProduto.this.tfPrecoVendaKeyReleased(evt);
        }
    }

    public void keyTyped(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == tfPrecoCompra) {
            DialogCadastroProduto.this.tfPrecoCompraKeyTyped(evt);
        }
        else if (evt.getSource() == tfPrecoVenda) {
            DialogCadastroProduto.this.tfPrecoVendaKeyTyped(evt);
        }
        else if (evt.getSource() == tfCodigoBarra) {
            DialogCadastroProduto.this.tfCodigoBarraKeyTyped(evt);
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == jButton1) {
            DialogCadastroProduto.this.jButton1MouseClicked(evt);
        }
        else if (evt.getSource() == jButton2) {
            DialogCadastroProduto.this.jButton2MouseClicked(evt);
        }
        else if (evt.getSource() == btCadastrar) {
            DialogCadastroProduto.this.btCadastrarMouseClicked(evt);
        }
        else if (evt.getSource() == btCancelar) {
            DialogCadastroProduto.this.btCancelarMouseClicked(evt);
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        control.cadastrarFornecedorAction();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        control.cadastrarCategoriaAction();
    }//GEN-LAST:event_jButton2MouseClicked

    private void btCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCadastrarMouseClicked
        control.cadastrarAction();
    }//GEN-LAST:event_btCadastrarMouseClicked

    private void tfCodigoBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoBarraKeyPressed
        control.salvaKeyCode(evt);
    }//GEN-LAST:event_tfCodigoBarraKeyPressed

    private void tfCodigoBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoBarraKeyTyped
        control.filtroCampoNumerico(evt);
    }//GEN-LAST:event_tfCodigoBarraKeyTyped

    private void tfPrecoCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoCompraKeyPressed
        control.salvaKeyCode(evt);
    }//GEN-LAST:event_tfPrecoCompraKeyPressed

    private void tfPrecoCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoCompraKeyTyped
        control.filtroCampoMonetario(evt);
    }//GEN-LAST:event_tfPrecoCompraKeyTyped

    private void tfPrecoCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoCompraKeyReleased
        control.validaCampoMonetario(getTfPrecoCompra());
    }//GEN-LAST:event_tfPrecoCompraKeyReleased

    private void tfPrecoVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoVendaKeyPressed
        control.salvaKeyCode(evt);
    }//GEN-LAST:event_tfPrecoVendaKeyPressed

    private void tfPrecoVendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoVendaKeyTyped
        control.filtroCampoMonetario(evt);
    }//GEN-LAST:event_tfPrecoVendaKeyTyped

    private void tfPrecoVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecoVendaKeyReleased
        control.validaCampoMonetario(getTfPrecoVenda());
    }//GEN-LAST:event_tfPrecoVendaKeyReleased

    private void btCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCancelarMouseClicked
        control.cancelarAction();
    }//GEN-LAST:event_btCancelarMouseClicked

    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DialogCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DialogCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DialogCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DialogCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DialogCadastroProduto dialog = new DialogCadastroProduto(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    public JComboBox<Categoria> getCbCategoria() {
        return cbCategoria;
    }

    public void setCbCategoria(JComboBox<Categoria> cbCategoria) {
        this.cbCategoria = cbCategoria;
    }

    public JComboBox<Fornecedor> getCbFornecedor() {
        return cbFornecedor;
    }

    public void setCbFornecedor(JComboBox<Fornecedor> cbFornecedor) {
        this.cbFornecedor = cbFornecedor;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JTextField getTfCodigoBarra() {
        return tfCodigoBarra;
    }

    public void setTfCodigoBarra(JTextField tfCodigoBarra) {
        this.tfCodigoBarra = tfCodigoBarra;
    }

    public JTextField getTfNome() {
        return tfNome;
    }

    public void setTfNome(JTextField tfNome) {
        this.tfNome = tfNome;
    }

    public JTextField getTfPrecoCompra() {
        return tfPrecoCompra;
    }

    public void setTfPrecoCompra(JTextField tfPrecoCompra) {
        this.tfPrecoCompra = tfPrecoCompra;
    }

    public JTextField getTfPrecoVenda() {
        return tfPrecoVenda;
    }

    public void setTfPrecoVenda(JTextField tfPrecoVenda) {
        this.tfPrecoVenda = tfPrecoVenda;
    }

    public JButton getBtCadastrar() {
        return btCadastrar;
    }

    public void setBtCadastrar(JButton btCadastrar) {
        this.btCadastrar = btCadastrar;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public void setBtCancelar(JButton btCancelar) {
        this.btCancelar = btCancelar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox<Categoria> cbCategoria;
    private javax.swing.JComboBox<Fornecedor> cbFornecedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField tfCodigoBarra;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPrecoCompra;
    private javax.swing.JTextField tfPrecoVenda;
    // End of variables declaration//GEN-END:variables
}
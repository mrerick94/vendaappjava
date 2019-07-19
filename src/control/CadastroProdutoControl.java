/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import entities.Categoria;
import entities.Fornecedor;
import entities.Produto;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import util.Validacao;
import view.DialogCadastroCategoria;
import view.DialogCadastroProduto;
import view.DialogCadastroUsuario;
import view.ModuloFornecedores;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class CadastroProdutoControl {
    DialogCadastroProduto dialog;
    TelaPrincipal framePrincipal;
    DaoGenerico<Fornecedor> daoFornecedor;
    DaoGenerico<Categoria> daoCategoria;
    DaoGenerico<Produto> daoProduto;
    List<Categoria> listaCategoria;
    List<Fornecedor> listaFornecedor;
    DialogCadastroUsuario dialogFornecedor;
    DialogCadastroCategoria dialogCategoria;
    Produto produto;
    private int keycode;

    public CadastroProdutoControl(DialogCadastroProduto dialog, TelaPrincipal framePrincipal, Produto produto) {
        this.dialog = dialog;
        this.framePrincipal = framePrincipal;
        this.produto = produto;
        this.daoCategoria = new DaoGenerico<>();
        this.daoFornecedor = new DaoGenerico<>();
        this.daoProduto = new DaoGenerico<>();
    }
    
    public void configurarComboBoxs() {
        listaCategoria = daoCategoria.getList(Categoria.class);
        dialog.getCbCategoria().setModel(new DefaultComboBoxModel<>(listaCategoria.toArray(new Categoria[0])));
        listaFornecedor = daoFornecedor.getList(Fornecedor.class);
        dialog.getCbFornecedor().setModel(new DefaultComboBoxModel<>(listaFornecedor.toArray(new Fornecedor[0])));
    }
    
    public void cadastrarFornecedorAction() {
        dialogFornecedor = new DialogCadastroUsuario(framePrincipal, true, ModuloFornecedores.getInstancia(framePrincipal), null);
        dialogFornecedor.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!dialogFornecedor.isShowing()) {
                    configurarComboBoxs();
                }
            }
        });
        dialogFornecedor.setVisible(true);
    }
    
    public void cadastrarCategoriaAction() {
        dialogCategoria = new DialogCadastroCategoria(framePrincipal, true);
        dialogCategoria.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!dialogCategoria.isShowing()) {
                    configurarComboBoxs();
                }
            }
        });
        dialogCategoria.setVisible(true);
    }
    
    //Verifica se recebeu um produto para alterar
    public void verificaAlteracao() {
        if (produto != null) {
            dialog.getTfNome().setText(produto.getNome());
            dialog.getTfCodigoBarra().setText(produto.getCodigoBarra());
            dialog.getTfPrecoCompra().setText(produto.getPrecoCompra().toString());
            dialog.getTfPrecoVenda().setText(produto.getPrecoVenda().toString());
            dialog.getCbCategoria().setSelectedItem(produto.getCategoriaId());
            dialog.getCbFornecedor().setSelectedItem(produto.getFornecedorId());
        }
    }
    
    public void cadastrarAction() {
        if (validacaoAntesSalvar()) {
            if (produto == null) {
                Produto produto = new Produto();
                produto.setCodigoBarra(dialog.getTfCodigoBarra().getText());
                produto.setNome(dialog.getTfNome().getText());
                produto.setPrecoCompra(Double.parseDouble(dialog.getTfPrecoCompra().getText()));
                produto.setPrecoVenda(Double.parseDouble(dialog.getTfPrecoVenda().getText()));
                produto.setCategoriaId((Categoria) dialog.getCbCategoria().getSelectedItem());
                produto.setFornecedorId((Fornecedor) dialog.getCbFornecedor().getSelectedItem());
                produto.setEstoque(0d);
                produto.setAtivo(true);
                daoProduto.saveOrUpdate(produto);
                dialog.dispose();
            } else {
                produto.setCodigoBarra(dialog.getTfCodigoBarra().getText());
                produto.setNome(dialog.getTfNome().getText());
                produto.setPrecoCompra(Double.parseDouble(dialog.getTfPrecoCompra().getText()));
                produto.setPrecoVenda(Double.parseDouble(dialog.getTfPrecoVenda().getText()));
                produto.setCategoriaId((Categoria) dialog.getCbCategoria().getSelectedItem());
                produto.setFornecedorId((Fornecedor) dialog.getCbFornecedor().getSelectedItem());
                daoProduto.saveOrUpdate(produto);
                dialog.dispose();
            }
        }
    }
    
    public void salvaKeyCode(java.awt.event.KeyEvent evt) {
        keycode = evt.getKeyCode();
    }
    
    public void filtroCampoMonetario(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoMonetario(evt)) {
            evt.consume();
        }
    }
    
    public void validaCampoMonetario(JTextField campo) {
        if (!Validacao.validaCampoMonetario(campo.getText())) {
            campo.setBackground(new Color(255, 102, 102));
            campo.setVisible(true);
            keycode = 0;
        } else {
            campo.setBackground(new Color(255, 255, 255));
            campo.setVisible(true);
            keycode = 0;
        }
    }
    
    public void filtroCampoNumerico(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoNumerico(evt)) {
            evt.consume();
            keycode = 0;
        }
    }
    
    public void cancelarAction() {
        dialog.dispose();
    }
    
    public boolean validacaoAntesSalvar() {
        boolean tudoCerto = true;
        if (dialog.getTfNome().getText().length() == 0 | dialog.getTfNome().getText().trim().length() == 0) {
            dialog.getTfNome().setBackground(new Color(255, 102, 102));
            dialog.getTfNome().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getTfNome().setBackground(new Color(255, 255, 255));
            dialog.getTfNome().setVisible(true);
        }
        if (dialog.getTfCodigoBarra().getText().length() == 0) {
            dialog.getTfCodigoBarra().setBackground(new Color(255, 102, 102));
            dialog.getTfCodigoBarra().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getTfCodigoBarra().setBackground(new Color(255, 255, 255));
            dialog.getTfCodigoBarra().setVisible(true);
        }
        if (dialog.getTfPrecoCompra().getText().length() == 0) {
            dialog.getTfPrecoCompra().setBackground(new Color(255, 102, 102));
            dialog.getTfPrecoCompra().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getTfPrecoCompra().setBackground(new Color(255, 255, 255));
            dialog.getTfPrecoCompra().setVisible(true);
        }
        if (dialog.getTfPrecoVenda().getText().length() == 0) {
            dialog.getTfPrecoVenda().setBackground(new Color(255, 102, 102));
            dialog.getTfPrecoVenda().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getTfPrecoVenda().setBackground(new Color(255, 255, 255));
            dialog.getTfPrecoVenda().setVisible(true);
        }
        if (dialog.getCbCategoria().getSelectedIndex() == -1) {
            dialog.getCbCategoria().setBackground(new Color(255, 102, 102));
            dialog.getCbCategoria().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getCbCategoria().setBackground(new Color(255, 255, 255));
            dialog.getCbCategoria().setVisible(true);
        }
        if (dialog.getCbFornecedor().getSelectedIndex() == -1) {
            dialog.getCbFornecedor().setBackground(new Color(255, 102, 102));
            dialog.getCbFornecedor().setVisible(true);
            tudoCerto = false;
        } else {
            dialog.getCbFornecedor().setBackground(new Color(255, 255, 255));
            dialog.getCbFornecedor().setVisible(true);
        }
        return tudoCerto;
    }
}

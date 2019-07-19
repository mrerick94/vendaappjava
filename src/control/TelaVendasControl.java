/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import dao.ItemVendaDao;
import dao.ProdutoDao;
import entities.Produto;
import entities.VendaItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import tableModel.TelaVendasTableModel;
import util.GerarPDF;
import util.Validacao;
import view.TelaPrincipal;
import view.TelaVendas;

/**
 *
 * @author erick
 */
public class TelaVendasControl {
    TelaPrincipal framePrincipal;
    TelaVendas telaVenda;
    List<VendaItem> listaVendas;
    ProdutoDao daoProduto;
    ItemVendaDao itemVendaDao;
    DaoGenerico<VendaItem> daoGenericoVendaItem;
    Produto produto;
    private int keycode;

    public TelaVendasControl(TelaPrincipal framePrincipal, TelaVendas telaVenda, Produto produto) {
        this.framePrincipal = framePrincipal;
        this.telaVenda = telaVenda;
        this.produto = produto;
        daoProduto = new ProdutoDao();
        daoGenericoVendaItem = new DaoGenerico<>();
        itemVendaDao = new ItemVendaDao();
        listaVendas = new ArrayList<>();
        telaVenda.getTblVendas().setModel(new TelaVendasTableModel(listaVendas));
        telaVenda.getDfIntervaloDataInicio().setMaxSelectableDate(new Date());
        telaVenda.getDfIntervaloDataInicio().setMinSelectableDate(new Date(630720000000l));
        telaVenda.getDfIntervaloDataFim().setMaxSelectableDate(new Date());
        telaVenda.getDfIntervaloDataFim().setMinSelectableDate(new Date(630720000000l));
    }
    
    public void inicializarProduto() {
        if (produto != null) {
            telaVenda.getTfCodigoBarra().setText(produto.getCodigoBarra());
        }
    }
    
    public void aplicarFiltrosAction() {
        if (telaVenda.getTfCodigoBarra().getText().length() > 0) {
            produto = daoProduto.findByCodigoBarra(telaVenda.getTfCodigoBarra().getText());
            if (produto != null) {
                listaVendas = itemVendaDao.findByProduto(produto);
                atualizarTabela();
            } else {
                System.out.println("produto nao encontrado");
            }
        } else {
            listaVendas = daoGenericoVendaItem.getList(VendaItem.class);
            atualizarTabela();
        }
    }
    
    public void atualizarTabela() {
        ((TelaVendasTableModel) telaVenda.getTblVendas().getModel()).setListaVendaItem(listaVendas);
    }
    
    public void zerarAction() {
        telaVenda.getTfCodigoBarra().setText("");
        telaVenda.getDfIntervaloDataFim().setDate(null);
        telaVenda.getDfIntervaloDataInicio().setDate(null);
        telaVenda.getTfNomeProduto().setText("");
        telaVenda.getCbCategoria().setSelectedIndex(-1);
    }
    
    public void gerarPdf() {
        GerarPDF.gerarRelatorioVendas(listaVendas);
    }
    
    public void salvaKeyCode(java.awt.event.KeyEvent evt) {
        keycode = evt.getKeyCode();
    }
    
    public void filtroCampoNumerico(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoNumerico(evt)) {
            evt.consume();
        }
    }
    
    public void validaCampoData(JTextField campo) {
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
}

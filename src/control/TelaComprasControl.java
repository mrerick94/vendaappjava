/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import dao.ItemCompraDao;
import dao.ProdutoDao;
import entities.Compra;
import entities.CompraItem;
import entities.Produto;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextField;
import tableModel.TelaComprasTableModel;
import util.GerarPDF;
import util.Validacao;
import view.DialogComprarProduto;
import view.TelaCompras;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class TelaComprasControl {

    TelaPrincipal framePrincipal;
    TelaCompras telaCompra;
    List<CompraItem> listaCompras;
    ProdutoDao daoProduto;
    ItemCompraDao itemCompraDao;
    DaoGenerico<CompraItem> daoGenericoCompraItem;
    DaoGenerico<Compra> daoGenericoCompra;
    Produto produto;
    Double auxiliar;
    DaoGenerico<Produto> daoGenericoProduto;
    private int keycode;

    public TelaComprasControl(TelaPrincipal framePrincipal, TelaCompras telaCompra, Produto produto) {
        this.framePrincipal = framePrincipal;
        this.telaCompra = telaCompra;
        this.produto = produto;
        this.daoGenericoProduto = new DaoGenerico<>();
        daoGenericoCompra = new DaoGenerico<>();
        daoProduto = new ProdutoDao();
        daoGenericoCompraItem = new DaoGenerico<>();
        itemCompraDao = new ItemCompraDao();
        listaCompras = new ArrayList<>();
        telaCompra.getTblCompras().setModel(new TelaComprasTableModel(listaCompras));
        telaCompra.getDfIntervaloDataInicio().setMaxSelectableDate(new Date());
        telaCompra.getDfIntervaloDataInicio().setMinSelectableDate(new Date(630720000000l));
        telaCompra.getDfIntervaloDataFim().setMaxSelectableDate(new Date());
        telaCompra.getDfIntervaloDataFim().setMinSelectableDate(new Date(630720000000l));
    }

    public void inicializarProduto() {
        if (produto != null) {
            telaCompra.getTfCodigoProduto().setText(produto.getCodigoBarra());
        }
    }

    public void aplicarFiltrosAction() {
        if (telaCompra.getTfCodigoProduto().getText().length() > 0) {
            produto = daoProduto.findByCodigoBarra(telaCompra.getTfCodigoProduto().getText());
            if (produto != null) {
                listaCompras = itemCompraDao.findByProduto(produto);
                atualizarTabela();
            } else {
                System.out.println("produto nao encontrado");
            }
        } else {
            listaCompras = daoGenericoCompraItem.getList(CompraItem.class);
            atualizarTabela();
        }
    }

    public void atualizarTabela() {
        ((TelaComprasTableModel) telaCompra.getTblCompras().getModel()).setListaCompraItem(listaCompras);
    }

    public void zerarAction() {
        telaCompra.getTfCodigoProduto().setText("");
        telaCompra.getDfIntervaloDataInicio().setDate(null);
        telaCompra.getDfIntervaloDataFim().setDate(null);
        telaCompra.getTfNomeProduto().setText("");
        telaCompra.getCbCategoria().setSelectedIndex(-1);
    }

    public void gerarPdf() {
        GerarPDF.gerarRelatorioCompras(listaCompras);
    }

    public void salvaKeyCode(java.awt.event.KeyEvent evt) {
        keycode = evt.getKeyCode();
    }

    public boolean filtroCampoNumerico(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoNumerico(evt)) {
            evt.consume();
            keycode = 0;
            return false;
        } else {
            return true;
        }
    }

    public void filtroCampoMonetario(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoMonetario(evt)) {
            evt.consume();
        }
    }

    public boolean validaCampoMonetario(JTextField campo) {
        if (!Validacao.validaCampoMonetario(campo.getText())) {
            campo.setBackground(new Color(255, 102, 102));
            campo.setVisible(true);
            keycode = 0;
            return false;
        } else {
            campo.setBackground(new Color(255, 255, 255));
            campo.setVisible(true);
            keycode = 0;
            return true;
        }
    }

    public void comprarEstoqueAction() {
        DecimalFormat df = new DecimalFormat("##,###.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        Produto produto = listaCompras.get(telaCompra.getTblCompras().getSelectedRow()).getProduto();
        Double valorTotal;
        DialogComprarProduto comprar = new DialogComprarProduto(telaCompra, true);
        comprar.getLblCodigo().setText(produto.getCodigoBarra());
        comprar.getLblNome().setText(produto.getNome());
        comprar.getLblPrecoUnitario().setText(produto.getPrecoCompra().toString());

        comprar.getTfQuantidade().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salvaKeyCode(evt);
            }
        });

        comprar.getTfQuantidade().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (filtroCampoNumerico(evt)) {
                    if (comprar.getTfQuantidade().getText().length() > 0) {
                        Double valortotal = produto.getPrecoCompra() * Double.parseDouble(comprar.getTfQuantidade().getText());
                        auxiliar = valortotal;
                        comprar.getLblValorTotal().setText("R$ " + df.format(produto.getPrecoCompra() * Double.parseDouble(comprar.getTfQuantidade().getText())));
                    }
                }
            }
        });
        
        comprar.getBtComprar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Compra compra = new Compra();
                CompraItem compraItem = new CompraItem();
                compraItem.setCompra(compra);
                compraItem.setPrecoCompra(produto.getPrecoCompra());
                compraItem.setProduto(produto);
                compraItem.setQuantidade(Double.parseDouble(comprar.getTfQuantidade().getText()));
                compra.setDataCompra(new Date());
                compra.setDataPagamento(new Date());
                compra.setDataVencimento(null);
                compra.setFornecedorId(produto.getFornecedorId());
                compra.setValorPago(auxiliar);
                compra.setCompraItemList(new ArrayList<>());
                compra.getCompraItemList().add(compraItem);
                daoGenericoCompra.saveOrUpdate(compra);
                produto.setEstoque(Double.parseDouble(comprar.getTfQuantidade().getText()) + produto.getEstoque());
                daoGenericoProduto.saveOrUpdate(produto);
                aplicarFiltrosAction();
                comprar.dispose();
            }
        });
        
        comprar.getBtCancelar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comprar.dispose();
            }
        });
        
        comprar.setVisible(true);
    }
}

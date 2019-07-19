/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import dao.ItemVendaDao;
import dao.ProdutoDao;
import entities.Cliente;
import entities.FormaPagamento;
import entities.Produto;
import entities.Venda;
import entities.VendaItem;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import tableModel.ItemVendaTableModel;
import util.JOptionPanes;
import util.Validacao;
import view.DialogMetodoPagamento;
import view.ModuloVendas;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class ModVendasControl {

    private final DecimalFormat df = new DecimalFormat("##,###.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    private final DecimalFormat dfQntd = new DecimalFormat("##,###.##", new DecimalFormatSymbols(new Locale("pt", "BR")));
    TelaPrincipal framePrincipal;
    ModuloVendas modVenda;
    Venda venda;
    List<VendaItem> listaVendaItem;
    List<Produto> listaProdutos;
    Cliente cliente;
    ItemVendaTableModel tabela;
    ProdutoDao produtoDao;
    ItemVendaDao itemDao;
    DaoGenerico<Venda> daoGenericoVenda;
    DaoGenerico<VendaItem> daoGenericoVendaItem;
    DaoGenerico<FormaPagamento> daoForma;
    DaoGenerico<Produto> daoProduto;
    private int keycode;
    private Double valorTotal;

    public ModVendasControl(TelaPrincipal framePrincipal, ModuloVendas modVenda) {
        this.framePrincipal = framePrincipal;
        this.modVenda = modVenda;
        this.produtoDao = new ProdutoDao();
        this.daoGenericoVenda = new DaoGenerico<>();
        this.daoGenericoVendaItem = new DaoGenerico<>();
        this.daoForma = new DaoGenerico<>();
        this.itemDao = new ItemVendaDao();
        daoProduto = new DaoGenerico<>();
        inicializacaoVenda();
        centralizaCellsTable();
    }

    private void centralizaCellsTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < modVenda.getTblListaVendaItem().getModel().getColumnCount(); i++) {
            modVenda.getTblListaVendaItem().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void inicializacaoVenda() {
        venda = new Venda();
        listaVendaItem = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        cliente = null;
        tabela = new ItemVendaTableModel(listaVendaItem);
        valorTotal = 0d;
        modVenda.getTblListaVendaItem().setModel(tabela);
        modVenda.getLblCodigoProduto().setText("");
        modVenda.getLblCpfCliente().setText("");
        modVenda.getLblNomeCliente().setText("");
        modVenda.getLblPreco().setText("");
        modVenda.getLblQntd().setText("");
        modVenda.getLblSubTotal().setText("");
        modVenda.getLblTotal().setText("");
        modVenda.getLblNomeProduto().setText("");
        modVenda.getLblCodigoProduto().requestFocus();
    }

    public void salvaKeyCode(java.awt.event.KeyEvent evt) {
        keycode = evt.getKeyCode();
    }

    public void filtroCampoNumerico(java.awt.event.KeyEvent evt) {
        evt.setKeyCode(keycode);
        if (!Validacao.filtroCampoCodigoBarraModVenda(evt, modVenda.getLblCodigoProduto().getText())) {
            evt.consume();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            inserirItem();
        } else if (evt.getKeyCode() == KeyEvent.VK_F12) {
            if (JOptionPanes.perguntaFinalizarCompra(framePrincipal) == JOptionPane.YES_OPTION) {
                finalizarCompra();
            } else {
                evt.consume();
            }
        }
    }

    public void handleF12Key(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            if (JOptionPanes.perguntaFinalizarCompra(framePrincipal) == JOptionPane.YES_OPTION) {
                finalizarCompra();
            } else {
                evt.consume();
            }
        }
    }

    public void inserirItem() {
        VendaItem item = new VendaItem();
        String[] codigoQuantidade;
        String codigo;
        Double quantidade;
        if (modVenda.getLblCodigoProduto().getText().contains("x")) {
            codigoQuantidade = modVenda.getLblCodigoProduto().getText().split("x");
            codigo = codigoQuantidade[0];
            quantidade = Double.parseDouble(codigoQuantidade[1]);
        } else {
            codigo = modVenda.getLblCodigoProduto().getText();
            quantidade = 1d;
        }
        item.setQuantidade(quantidade);
        Produto produto = produtoDao.findByCodigoBarra(codigo);
        if (produto == null) {
            JOptionPanes.avisoProdutoInexistente(framePrincipal);
            return;
        }
        item.setProduto(produto);
        item.setVenda(venda);
        item.setPrecoVenda(item.getProduto().getPrecoVenda());
        listaVendaItem.add(item);
        ((ItemVendaTableModel) modVenda.getTblListaVendaItem().getModel()).fireTableDataChanged();
        System.out.println(listaVendaItem.size());
        modVenda.getLblCodigoProduto().setText("");
        modVenda.getLblNomeProduto().setText(item.getProduto().getNome());
        modVenda.getLblPreco().setText("R$ " + df.format(item.getPrecoVenda()));
        modVenda.getLblQntd().setText(dfQntd.format(item.getQuantidade()));
        modVenda.getLblSubTotal().setText("R$ " + df.format(item.getPrecoVenda() * item.getQuantidade()));
        valorTotal += item.getPrecoVenda() * item.getQuantidade();
        modVenda.getLblTotal().setText("R$ " + df.format(valorTotal));
        modVenda.getLblCodigoProduto().requestFocus();
    }

    private void finalizarCompra() {
        if (cliente != null) {
            venda.setClienteId(cliente);
        }
        venda.setDataPagamento(new Date());
        venda.setDataVenda(new Date());
        venda.setDataVencimento(new Date());
        venda.setValorPago(valorTotal);
        DialogMetodoPagamento metodo = new DialogMetodoPagamento(framePrincipal, true);
        metodo.getBtCartao().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    venda.setFormaPagamentoid(daoForma.findById(FormaPagamento.class, 1));
                    daoGenericoVenda.saveOrUpdate(venda);
                    itemDao.saveListVendaItem(listaVendaItem);
                    metodo.dispose();
                    JOptionPanes.confirmacaoFinalizacaoCompra(framePrincipal);
                    inicializacaoVenda();
                }
            }
        });
        metodo.getBtDinheiro().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    venda.setFormaPagamentoid(daoForma.findById(FormaPagamento.class, 2));
                    Double valorPago = JOptionPanes.janelaDinheiroPago(framePrincipal);
                    String troco = "R$ " + df.format(valorPago - valorTotal);
                    JOptionPanes.informaTroco(framePrincipal, troco);
                    daoGenericoVenda.saveOrUpdate(venda);
                    itemDao.saveListVendaItem(listaVendaItem);
                    metodo.dispose();
                    JOptionPanes.confirmacaoFinalizacaoCompra(framePrincipal);
                    inicializacaoVenda();
                }
            }
        });
        metodo.setVisible(true);
    }
}

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
import entities.Fornecedor;
import entities.Produto;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tableModel.EstoqueTableModel;
import util.GerarPDF;
import util.JOptionPanes;
import util.Validacao;
import view.DialogCadastroProduto;
import view.DialogComprarProduto;
import view.ModuloEstoque;
import view.TelaCompras;
import view.TelaPrincipal;
import view.TelaVendas;

/**
 *
 * @author erick
 */
public class ModEstoqueControl {

    private TelaPrincipal framePrincipal;
    private ModuloEstoque modEstoque;
    private DialogCadastroProduto dialogCadastroProduto;
    private boolean modoFornecedor;
    private ProdutoDao produtoDao;
    private DaoGenerico<Produto> daoGenericoProduto;
    private DaoGenerico<Compra> daoGenericoCompra;
    private Fornecedor fornecedor;
    private List<Produto> listaProduto;
    private ItemCompraDao itemCompraDao;
    Double auxiliar;
    private int keycode;

    public ModEstoqueControl(TelaPrincipal framePrincipal, ModuloEstoque modEstoque, boolean modoFornecedor, Fornecedor fornecedor) {
        this.framePrincipal = framePrincipal;
        this.modEstoque = modEstoque;
        this.modoFornecedor = modoFornecedor;
        this.fornecedor = fornecedor;
        this.itemCompraDao = new ItemCompraDao();
        this.daoGenericoCompra = new DaoGenerico<>();
        produtoDao = new ProdutoDao();
        daoGenericoProduto = new DaoGenerico<>();
    }

    public void modoFornecedor() {
        if (modoFornecedor) {
            listaProduto = produtoDao.findAtivosByFornecedor(fornecedor);
            modEstoque.getTblProdutos().setModel(new EstoqueTableModel(listaProduto));
            modEstoque.getBtAlterarProduto().setEnabled(false);
            modEstoque.getBtAplicar().setEnabled(false);
            modEstoque.getBtCadastrarProduto().setEnabled(false);
            modEstoque.getBtComprasProduto().setEnabled(false);
            modEstoque.getBtExcluirProduto().setEnabled(false);
            modEstoque.getBtVendasProduto().setEnabled(false);
            modEstoque.getBtZerar().setEnabled(false);
            modEstoque.getTfIntervaloPrecoCompraFim().setEnabled(false);
            modEstoque.getTfIntervaloPrecoCompraInicio().setEnabled(false);
            modEstoque.getTfIntervaloPrecoVendaFim().setEnabled(false);
            modEstoque.getTfIntervaloPrecoVendaInicio().setEnabled(false);
            modEstoque.getTfPrecoCompra().setEnabled(false);
            modEstoque.getTfPrecoVenda().setEnabled(false);
            modEstoque.getTfProduto().setEnabled(false);
            modEstoque.getTfQuantidade().setEnabled(false);
        } else {
            listaProduto = daoGenericoProduto.getList(Produto.class);
            modEstoque.getTblProdutos().setModel(new EstoqueTableModel(listaProduto));
            modEstoque.getBtAlterarProduto().setEnabled(true);
            modEstoque.getBtAplicar().setEnabled(true);
            modEstoque.getBtCadastrarProduto().setEnabled(true);
            modEstoque.getBtComprasProduto().setEnabled(true);
            modEstoque.getBtExcluirProduto().setEnabled(true);
            modEstoque.getBtVendasProduto().setEnabled(true);
            modEstoque.getBtZerar().setEnabled(true);
            modEstoque.getTfIntervaloPrecoCompraFim().setEnabled(true);
            modEstoque.getTfIntervaloPrecoCompraInicio().setEnabled(true);
            modEstoque.getTfIntervaloPrecoVendaFim().setEnabled(true);
            modEstoque.getTfIntervaloPrecoVendaInicio().setEnabled(true);
            modEstoque.getTfPrecoCompra().setEnabled(true);
            modEstoque.getTfPrecoVenda().setEnabled(true);
            modEstoque.getTfProduto().setEnabled(true);
            modEstoque.getTfQuantidade().setEnabled(true);
        }
    }

    public void cadastrarProdutoAction() {
        dialogCadastroProduto = new DialogCadastroProduto(framePrincipal, true, null);
        dialogCadastroProduto.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!dialogCadastroProduto.isShowing()) {
                    listaProduto = daoGenericoProduto.getList(Produto.class);
                    atualizarTabela();
                }
            }
        });
        dialogCadastroProduto.setVisible(true);
    }

    public void alterarProdutoAction() {
        if (modEstoque.getTblProdutos().getSelectedRow() >= 0) {
            dialogCadastroProduto = new DialogCadastroProduto(framePrincipal, true, listaProduto.get(modEstoque.getTblProdutos().getSelectedRow()));
            dialogCadastroProduto.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!dialogCadastroProduto.isShowing()) {
                        listaProduto = daoGenericoProduto.getList(Produto.class);
                        atualizarTabela();
                    }
                }
            });
            dialogCadastroProduto.setVisible(true);
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }

    public void excluirAction() {
        if (modEstoque.getTblProdutos().getSelectedRow() >= 0) {
            if (JOptionPanes.confirmacaoExcluir(framePrincipal) == JOptionPane.YES_OPTION) {
                listaProduto.get(modEstoque.getTblProdutos().getSelectedRow()).setAtivo(false);
                daoGenericoProduto.saveOrUpdate(listaProduto.get(modEstoque.getTblProdutos().getSelectedRow()));
                listaProduto = daoGenericoProduto.getList(Produto.class);
                atualizarTabela();
            }
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }

    public void atualizarTabela() {
        ((EstoqueTableModel) modEstoque.getTblProdutos().getModel()).setListaProduto(listaProduto);
    }

    public void verCompraProdutoAction() {
        TelaCompras telaCompras = TelaCompras.getInstancia(framePrincipal, listaProduto.get(modEstoque.getTblProdutos().getSelectedRow()));
        telaCompras.setVisible(true);
    }
    
    public void verVendaProdutoAction() {
        TelaVendas telaVendas = TelaVendas.getInstancia(framePrincipal, listaProduto.get(modEstoque.getTblProdutos().getSelectedRow()));
        telaVendas.setVisible(true);
    }

    public void gerarPdf() {
        GerarPDF.gerarRelatorioEstoque(listaProduto);
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

    public void aplicarFiltrosAction() {
        // -------------------------- Verificações Iniciais antes de realizar consulta ------------------------------------------------------------
        //Verificando se todos os campos estão vazios
        if (modEstoque.getTfProduto().getText().length() == 0 & modEstoque.getTfPrecoCompra().getText().length() == 0
                & modEstoque.getTfPrecoVenda().getText().length() == 0 & modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() == 0
                & modEstoque.getTfIntervaloPrecoCompraFim().getText().length() == 0 & modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() == 0
                & modEstoque.getTfIntervaloPrecoVendaFim().getText().length() == 0 & modEstoque.getTfQuantidade().getText().length() == 0) {
            JOptionPanes.avisoTodosCamposVazios(framePrincipal);
            return;
        }
        //Verificando se algum dos intervalos está sem o inicio ou o fim
        if (modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() == 0 & modEstoque.getTfIntervaloPrecoCompraFim().getText().length() > 0) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        } else if (modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() > 0 & modEstoque.getTfIntervaloPrecoCompraFim().getText().length() == 0) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        } else if (modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() == 0 & modEstoque.getTfIntervaloPrecoVendaFim().getText().length() > 0) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        } else if (modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() > 0 & modEstoque.getTfIntervaloPrecoVendaFim().getText().length() == 0) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        }
        //Se um dos campos de preço especificos estiverem preenchidos e algum dos intervalos de preços também, mostro warning
        if (modEstoque.getTfPrecoCompra().getText().length() > 0 | modEstoque.getTfPrecoVenda().getText().length() > 0) {
            if (modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() > 0 || modEstoque.getTfIntervaloPrecoCompraFim().getText().length() > 0
                    || modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() > 0 || modEstoque.getTfIntervaloPrecoVendaFim().getText().length() > 0) {
                JOptionPanes.avisoPrecoEspecificoEIntervaloPrecoEstoque(framePrincipal);
                return;
            }
        }
        // ------------------------------------------------------------------------------------------------------------------------------------------
        //Se algum dos intervalos de preços estiverem preenchidos
        //Se deve atentar que se um intervalo estiver sem o começo ou o fim, a query vai ignorar este intervalo
        if (modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() > 0 || modEstoque.getTfIntervaloPrecoCompraInicio().getText().length() > 0
                || modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() > 0 || modEstoque.getTfIntervaloPrecoVendaInicio().getText().length() > 0) {
            listaProduto = produtoDao.findByFiltrosIntervaloPrecos(modEstoque.getTfProduto().getText(), modEstoque.getTfIntervaloPrecoCompraInicio().getText(),
                    modEstoque.getTfIntervaloPrecoCompraFim().getText(), modEstoque.getTfIntervaloPrecoVendaInicio().getText(),
                    modEstoque.getTfIntervaloPrecoVendaFim().getText(), modEstoque.getTfQuantidade().getText());
            atualizarTabela();
            return;
        }
        listaProduto = produtoDao.findByFiltrosPrecoEspecifico(modEstoque.getTfProduto().getText(), modEstoque.getTfPrecoCompra().getText(),
                modEstoque.getTfPrecoVenda().getText(), modEstoque.getTfQuantidade().getText());
        atualizarTabela();
    }

    public void zerarAction() {
        modEstoque.getTfProduto().setText("");
        modEstoque.getTfPrecoCompra().setText("");
        modEstoque.getTfPrecoVenda().setText("");
        modEstoque.getTfIntervaloPrecoCompraInicio().setText("");
        modEstoque.getTfIntervaloPrecoCompraFim().setText("");
        modEstoque.getTfIntervaloPrecoVendaInicio().setText("");
        modEstoque.getTfIntervaloPrecoVendaFim().setText("");
        modEstoque.getTfQuantidade().setText("");
        listaProduto = daoGenericoProduto.getList(Produto.class);
        atualizarTabela();
    }

    public void comprarEstoqueAction() {
        DecimalFormat df = new DecimalFormat("##,###.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        Produto produto = listaProduto.get(modEstoque.getTblProdutos().getSelectedRow());
        Double valorTotal;
        DialogComprarProduto comprar = new DialogComprarProduto(framePrincipal, true);
        comprar.getLblCodigo().setText(produto.getCodigoBarra());
        comprar.getLblNome().setText(produto.getNome());
        comprar.getLblPrecoUnitario().setText(produto.getPrecoCompra().toString());
        comprar.getLblValorTotal().setText("R$ 0,00");

        comprar.getTfQuantidade().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salvaKeyCode(evt);
            }
        });

        comprar.getTfQuantidade().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filtroCampoNumerico(evt);
            }
        });

        comprar.getTfQuantidade().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (comprar.getTfQuantidade().getText().length() > 0) {
                    Double valortotal = produto.getPrecoCompra() * Double.parseDouble(comprar.getTfQuantidade().getText());
                    auxiliar = valortotal;
                    comprar.getLblValorTotal().setText("R$ " + df.format(produto.getPrecoCompra() * Double.parseDouble(comprar.getTfQuantidade().getText())));
                } else {
                    comprar.getLblValorTotal().setText("R$ 0,00");
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
                daoGenericoCompra.saveOrUpdate(compra);
                itemCompraDao.saveCompraItem(compraItem);
                produto.setEstoque(Double.parseDouble(comprar.getTfQuantidade().getText()) + produto.getEstoque());
                daoGenericoProduto.saveOrUpdate(produto);
                atualizarTabela();
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

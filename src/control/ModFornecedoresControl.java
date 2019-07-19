/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import dao.FornecedorDao;
import dao.ProdutoDao;
import entities.Fornecedor;
import entities.Produto;
import entities.Usuario;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.FornecedorTableModel;
import util.JOptionPanes;
import view.DialogCadastroUsuario;
import view.ModuloFornecedores;
import view.TelaPrincipal;

/**
 *
 * @author erick.costa
 */
public class ModFornecedoresControl {

    TelaPrincipal framePrincipal;
    ModuloFornecedores modFornecedor;
    DialogCadastroUsuario dialogCadastro;
    DaoGenerico<Fornecedor> daoFornecedorGenerico;
    List<Fornecedor> listaFornecedor;
    FornecedorDao fornecedorDao;

    public ModFornecedoresControl(TelaPrincipal framePrincipal, ModuloFornecedores modFornecedor) {
        this.framePrincipal = framePrincipal;
        this.modFornecedor = modFornecedor;
        this.daoFornecedorGenerico = new DaoGenerico<>();
        this.fornecedorDao = new FornecedorDao();
    }

    public void novoFornecedorAction() {
        dialogCadastro = new DialogCadastroUsuario(framePrincipal, true, modFornecedor, null);
        dialogCadastro.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!dialogCadastro.isShowing()) {
                    atualizarTabela();
                }
            }
        });
        dialogCadastro.setVisible(true);
    }
    
    public void alterarFornecedorAction() {
        if (modFornecedor.getTblFornecedores().getSelectedRow() >= 0) {
            dialogCadastro = new DialogCadastroUsuario(framePrincipal, true, modFornecedor, listaFornecedor.get(modFornecedor.getTblFornecedores().getSelectedRow()));
            dialogCadastro.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!dialogCadastro.isShowing()) {
                        atualizarTabela();
                    }
                }
            });
            dialogCadastro.setVisible(true);
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }
    
    public void excluirFornecedorAction() {
        if (modFornecedor.getTblFornecedores().getSelectedRow() >= 0) {
            if (JOptionPanes.confirmacaoExcluir(framePrincipal) == JOptionPane.YES_OPTION) {
                DaoGenerico<Usuario> daoUsuario = new DaoGenerico();
                DaoGenerico<Produto> daoProdutoGenerico = new DaoGenerico();
                ProdutoDao daoProduto = new ProdutoDao();
                List<Produto> listaProdutos = daoProduto.findAtivosByFornecedor(listaFornecedor.get(modFornecedor.getTblFornecedores().getSelectedRow()));
                Usuario u = listaFornecedor.get(modFornecedor.getTblFornecedores().getSelectedRow()).getUsuarioId();
                u.setAtivo(false);
                Fornecedor f = listaFornecedor.get(modFornecedor.getTblFornecedores().getSelectedRow());
                f.setAtivo(false);
                daoUsuario.saveOrUpdate(u);
                daoFornecedorGenerico.saveOrUpdate(f);
                for (Produto p : listaProdutos) {
                    p.setAtivo(false);
                    daoProdutoGenerico.saveOrUpdate(p);
                }
                atualizarTabela();
            }
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }

    public void setTabelaInicial() {
        listaFornecedor = daoFornecedorGenerico.getList(Fornecedor.class);
        modFornecedor.getTblFornecedores().setModel(new FornecedorTableModel(listaFornecedor));
    }

    private void atualizarTabela() {
        listaFornecedor = daoFornecedorGenerico.getList(Fornecedor.class);
        ((FornecedorTableModel) modFornecedor.getTblFornecedores().getModel()).setListaFornecedor(listaFornecedor);
    }
    
    public void aplicarFiltrosAction() {
        if (modFornecedor.getTfNome().getText().length() == 0 & modFornecedor.getTfCidade().getText().length() == 0
                & modFornecedor.getDfDataCadastro().getDate() == null & modFornecedor.getDfIntervaloDataInicio().getDate() == null
                & modFornecedor.getDfIntervaloDataFim().getDate() == null & modFornecedor.getCbUf().getSelectedIndex() == -1) {
            JOptionPanes.avisoTodosCamposVazios(framePrincipal);
            return;
        }
        //Verificando se algum dos intervalos está sem o inicio ou o fim
        if (modFornecedor.getDfIntervaloDataInicio().getDate() != null & modFornecedor.getDfIntervaloDataFim().getDate() == null) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        } else if (modFornecedor.getDfIntervaloDataInicio().getDate() == null & modFornecedor.getDfIntervaloDataFim().getDate() != null) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        }
        listaFornecedor = fornecedorDao.findByFiltros(modFornecedor.getTfNome().getText(), modFornecedor.getDfIntervaloDataInicio().getDate(), 
                modFornecedor.getDfIntervaloDataFim().getDate(), modFornecedor.getDfDataCadastro().getDate(), 
                modFornecedor.getTfCidade().getText(), (String) modFornecedor.getCbUf().getSelectedItem());
        atualizarTabela();
    }
    
    public void zerarAction() {
        modFornecedor.getTfNome().setText("");
        modFornecedor.getTfCidade().setText("");
        modFornecedor.getDfDataCadastro().setDate(null);
        modFornecedor.getDfIntervaloDataInicio().setDate(null);
        modFornecedor.getDfIntervaloDataFim().setDate(null);
        modFornecedor.getCbUf().setSelectedIndex(-1);
        listaFornecedor = daoFornecedorGenerico.getList(Fornecedor.class);
        atualizarTabela();
    }
}

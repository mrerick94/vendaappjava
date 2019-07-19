/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ClienteDao;
import dao.DaoGenerico;
import entities.Cliente;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.ClienteTableModel;
import util.JOptionPanes;
import util.Validacao;
import view.DialogCadastroCliente;
import view.ModuloClientes;
import view.TelaPrincipal;

/**
 *
 * @author erick.costa
 */
public class ModClientesControl {
    TelaPrincipal framePrincipal;
    ModuloClientes modCliente;
    DialogCadastroCliente dialogCadastro;
    List<Cliente> listaCliente;
    DaoGenerico<Cliente> daoGenerico;
    ClienteDao clienteDao;
    private int keycode;
    

    public ModClientesControl(TelaPrincipal framePrincipal, ModuloClientes modCliente) {
        this.framePrincipal = framePrincipal;
        this.modCliente = modCliente;
        this.daoGenerico = new DaoGenerico<>();
        clienteDao = new ClienteDao();
        modCliente.getDfIntervaloDataInicio().setMaxSelectableDate(new Date());
        modCliente.getDfIntervaloDataInicio().setMinSelectableDate(new Date(630720000000l));
        modCliente.getDfIntervaloDataFim().setMaxSelectableDate(new Date());
        modCliente.getDfIntervaloDataFim().setMinSelectableDate(new Date(630720000000l));
        modCliente.getDfDataCadastro().setMaxSelectableDate(new Date());
        modCliente.getDfDataCadastro().setMinSelectableDate(new Date(630720000000l));
    }
    
    public void novoClienteAction() {
        dialogCadastro = new DialogCadastroCliente(framePrincipal, true, modCliente, null);
        dialogCadastro.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!dialogCadastro.isShowing()) {
                    listaCliente = daoGenerico.getList(Cliente.class);
                    atualizarTabela();
                }
            }
        });
        dialogCadastro.setVisible(true);
    }
    
    public void alterarClienteAction() {
        if (modCliente.getTblClientes().getSelectedRow() >= 0) {
            dialogCadastro = new DialogCadastroCliente(framePrincipal, true, modCliente, listaCliente.get(modCliente.getTblClientes().getSelectedRow()));
            dialogCadastro.getBtCadastrar().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!dialogCadastro.isShowing()) {
                        listaCliente = daoGenerico.getList(Cliente.class);
                        atualizarTabela();
                    }
                }
            });
            dialogCadastro.setVisible(true);
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }
    
    public void setTabelaInicial() {
        listaCliente = daoGenerico.getList(Cliente.class);
        modCliente.getTblClientes().setModel(new ClienteTableModel(listaCliente));
    }
    
    public void excluirAction() {
        if (modCliente.getTblClientes().getSelectedRow() >= 0) {
            if (JOptionPanes.confirmacaoExcluir(framePrincipal) == JOptionPane.YES_OPTION) {
                listaCliente.get(modCliente.getTblClientes().getSelectedRow()).setAtivo(false);
                daoGenerico.saveOrUpdate(listaCliente.get(modCliente.getTblClientes().getSelectedRow()));
                listaCliente = daoGenerico.getList(Cliente.class);
                atualizarTabela();
            }
        } else {
            JOptionPanes.avisoNenhumItemSelecionado(framePrincipal);
        }
    }
    
    public void zerarAction() {
        modCliente.getTfNome().setText("");
        modCliente.getTfBairro().setText("");
        modCliente.getTfCidade().setText("");
        modCliente.getTfCpf().setText("");
        modCliente.getDfDataCadastro().setDate(null);
        modCliente.getDfIntervaloDataInicio().setDate(null);
        modCliente.getDfIntervaloDataFim().setDate(null);
        modCliente.getCbUf().setSelectedIndex(-1);
        listaCliente = daoGenerico.getList(Cliente.class);
        atualizarTabela();
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
    
    public void aplicaFiltrosAction() {
        if (modCliente.getTfNome().getText().length() == 0 & modCliente.getTfCidade().getText().length() == 0
                & modCliente.getDfDataCadastro().getDate() == null & modCliente.getDfIntervaloDataInicio().getDate() == null
                & modCliente.getDfIntervaloDataFim().getDate() == null & modCliente.getTfCpf().getText().contains(" ")
                & modCliente.getTfBairro().getText().length() == 0 & modCliente.getCbUf().getSelectedIndex() == -1) {
            JOptionPanes.avisoTodosCamposVazios(framePrincipal);
            return;
        }
        //Verificando se algum dos intervalos está sem o inicio ou o fim
        if (modCliente.getDfIntervaloDataInicio().getDate() != null & modCliente.getDfIntervaloDataFim().getDate() == null) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        } else if (modCliente.getDfIntervaloDataInicio().getDate() == null & modCliente.getDfIntervaloDataFim().getDate() != null) {
            JOptionPanes.avisoNecessarioComecoFim(framePrincipal);
            return;
        }
        listaCliente = clienteDao.findByFiltros(modCliente.getTfNome().getText(), modCliente.getTfCidade().getText(), 
                modCliente.getDfIntervaloDataInicio().getDate(), modCliente.getDfIntervaloDataFim().getDate(), 
                modCliente.getTfCpf().getText(), modCliente.getDfDataCadastro().getDate(), modCliente.getTfBairro().getText(), 
                (String) modCliente.getCbUf().getSelectedItem());
        atualizarTabela();
    }
    
    public void atualizarTabela() {
        ((ClienteTableModel) modCliente.getTblClientes().getModel()).setListaCliente(listaCliente);
    }
}

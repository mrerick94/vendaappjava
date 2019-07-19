/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.FornecedorDao;
import entities.Usuario;
import view.DialogCadastroUsuario;
import view.ModuloClientes;
import view.ModuloEstoque;
import view.ModuloFornecedores;
import view.ModuloVendas;
import view.TelaCompras;
import view.TelaPrincipal;
import view.TelaVendas;

/**
 *
 * @author erick.costa
 */
public class PrincipalControl {
    private TelaPrincipal framePrincipal;
    private ModuloFornecedores modFornecedor;
    private ModuloClientes modCliente;
    private ModuloEstoque modEstoque;
    private ModuloVendas modCaixa;
    private TelaCompras telaCompras;
    private TelaVendas telaVendas;
    private Usuario usuario;

    public PrincipalControl(TelaPrincipal framePrincipal, Usuario usuario) {
        this.framePrincipal = framePrincipal;
        this.usuario = usuario;
    }
    
    public void configuracaoInicial() {
        //System.out.println(usuario.getPerfilId().getId());
        //Mostrando botões de menu para Administrador
        if (usuario.getPerfilId().getId().equals(1)) {
            for (int i = 0; i < framePrincipal.getBarraMenu().getMenuCount(); i++) {
                framePrincipal.getBarraMenu().getMenu(i).setVisible(true);
            }
            abrirEstoqueAction(false);
            return;
        }
        //Mostrando botões de menu para Vendedor
        if (usuario.getPerfilId().getId().equals(2)) {
            for (int i = 0; i < framePrincipal.getBarraMenu().getMenuCount() - 1; i++) {
                framePrincipal.getBarraMenu().getMenu(i).setVisible(false);
            }
            framePrincipal.getBarraMenu().getMenu(framePrincipal.getBarraMenu().getMenuCount() - 1).setVisible(true);
            abrirCaixaAction();
            return;
        }
        //Mostrando botões de menu para Assistente
        if (usuario.getPerfilId().getId().equals(3)) {
            for (int i = 0; i < framePrincipal.getBarraMenu().getMenuCount(); i++) {
                if (framePrincipal.getBarraMenu().getMenu(i).getText().equals("Fornecedores") 
                        | framePrincipal.getBarraMenu().getMenu(i).getText().equals("Estoque")
                        | framePrincipal.getBarraMenu().getMenu(i).getText().equals("Compras")
                        | framePrincipal.getBarraMenu().getMenu(i).getText().equals("Sair")) {
                    framePrincipal.getBarraMenu().getMenu(i).setVisible(true);
                } else {
                    framePrincipal.getBarraMenu().getMenu(i).setVisible(false);
                }
            }
            abrirEstoqueAction(false);
            return;
        }
        //Mostrando botões de menu para Fornecedor
        if (usuario.getPerfilId().getId().equals(4)) {
            for (int i = 0; i < framePrincipal.getBarraMenu().getMenuCount() - 1; i++) {
                framePrincipal.getBarraMenu().getMenu(i).setVisible(false);
            }
            framePrincipal.getBarraMenu().getMenu(framePrincipal.getBarraMenu().getMenuCount() - 1).setVisible(true);
            abrirEstoqueAction(true);
            return;
        }
    }
    
    public void abrirFornecedoresAction() {
        if (modFornecedor == null) {
            modFornecedor = ModuloFornecedores.getInstancia(framePrincipal);
            framePrincipal.setContentPane(modFornecedor);
            framePrincipal.setVisible(true);
            modFornecedor.getTfNome().requestFocus();
            framePrincipal.getBarraMenu().getMenu(1).setSelected(false);
        } else {
            framePrincipal.setContentPane(modFornecedor);
            framePrincipal.setVisible(true);
            modFornecedor.getTfNome().requestFocus();
            framePrincipal.getBarraMenu().getMenu(1).setSelected(false);
        }
    }
    
    public void abrirCaixaAction() {
        if (modCaixa == null) {
            modCaixa = ModuloVendas.getInstancia(framePrincipal);
            framePrincipal.setContentPane(modCaixa);
            framePrincipal.setVisible(true);
            modCaixa.getLblCodigoProduto().requestFocus();
            framePrincipal.getBarraMenu().getMenu(0).setSelected(false);
        } else {
            framePrincipal.setContentPane(modCaixa);
            framePrincipal.setVisible(true);
            modCaixa.getLblCodigoProduto().requestFocus();
            framePrincipal.getBarraMenu().getMenu(0).setSelected(false);
        }
    }
    
    public void abrirEstoqueAction(boolean modoFornecedor) {
        if (modEstoque == null) {
            if (usuario.getPerfilId().getId().equals(4)) {
                FornecedorDao dao = new FornecedorDao();
                modEstoque = ModuloEstoque.getInstancia(framePrincipal, modoFornecedor, dao.findByUsuarioId(usuario));
            } else {
                modEstoque = ModuloEstoque.getInstancia(framePrincipal, modoFornecedor, null);
            }
            framePrincipal.setContentPane(modEstoque);
            framePrincipal.setVisible(true);
            modEstoque.getTfProduto().requestFocus();
            framePrincipal.getBarraMenu().getMenu(2).setSelected(false);
        } else {
            framePrincipal.setContentPane(modEstoque);
            framePrincipal.setVisible(true);
            modEstoque.getTfProduto().requestFocus();
            framePrincipal.getBarraMenu().getMenu(2).setSelected(false);
        }
    }
    
    public void abrirClientesAction() {
        if (modCliente == null) {
            modCliente = ModuloClientes.getInstancia(framePrincipal);
            framePrincipal.setContentPane(modCliente);
            framePrincipal.setVisible(true);
            modCliente.getTfNome().requestFocus();
            framePrincipal.getBarraMenu().getMenu(3).setSelected(false);
        } else {
            framePrincipal.setContentPane(modCliente);
            framePrincipal.setVisible(true);
            modCliente.getTfNome().requestFocus();
            framePrincipal.getBarraMenu().getMenu(3).setSelected(false);
        }
    }
    
    public void abrirVendasAction() {
        if (telaVendas == null) {
            telaVendas = TelaVendas.getInstancia(framePrincipal, null);
            telaVendas.setVisible(true);
        } else {
            telaVendas.setVisible(true);
        }
    }
    
    public void abrirComprasAction() {
        if (telaCompras == null) {
            telaCompras = TelaCompras.getInstancia(framePrincipal, null);
            telaCompras.setVisible(true);
        } else {
            telaCompras.setVisible(true);
        }
    }
    
    public void abrirCadastroUsuarioAction() {
        DialogCadastroUsuario dialogCadastroUsuario = new DialogCadastroUsuario(framePrincipal, true, null, null);
        dialogCadastroUsuario.setVisible(true);
    }
    
    public void sairAction() {
        framePrincipal.dispose();
        ModuloClientes.reset();
        ModuloEstoque.reset();
        ModuloFornecedores.reset();
        ModuloVendas.reset();
        TelaCompras.reset();
        TelaVendas.reset();
        String[] args = null;
        Inicializar.main(args);
    }
}

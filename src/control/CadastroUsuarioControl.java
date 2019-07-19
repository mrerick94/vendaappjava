/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import entities.Endereco;
import entities.EnderecoFromCEP;
import entities.Fornecedor;
import entities.Perfil;
import entities.Usuario;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import util.Validacao;
import util.ViaCEP;
import view.DialogCadastroUsuario;
import view.ModuloFornecedores;
import view.TelaPrincipal;

/**
 *
 * @author erick.costa
 */
public class CadastroUsuarioControl {

    TelaPrincipal framePrincipal;
    ModuloFornecedores modFornecedor;
    DialogCadastroUsuario dialogCadastro;
    Fornecedor fornecedor;
    private int keycode;

    public CadastroUsuarioControl(TelaPrincipal framePrincipal, DialogCadastroUsuario dialogCadastro, ModuloFornecedores modFornecedor, Fornecedor fornecedor) {
        this.framePrincipal = framePrincipal;
        this.modFornecedor = modFornecedor;
        this.dialogCadastro = dialogCadastro;
        this.fornecedor = fornecedor;
    }

    public void verificaAlteracao() {
        if (fornecedor != null) {
            dialogCadastro.getTfBairro().setText(fornecedor.getEnderecoId().getBairro());
            dialogCadastro.getTfCep().setText(fornecedor.getEnderecoId().getCep());
            dialogCadastro.getTfCidade().setText(fornecedor.getEnderecoId().getCidade());
            if (fornecedor.getEnderecoId().getComplemento() != null) {
                dialogCadastro.getTfComplemento().setText(fornecedor.getEnderecoId().getComplemento());
            }
            dialogCadastro.getTfEmail().setText(fornecedor.getUsuarioId().getEmail());
            dialogCadastro.getTfNome().setText(fornecedor.getNome());
            dialogCadastro.getTfNum().setText(fornecedor.getEnderecoId().getNumero().toString());
            dialogCadastro.getTfPais().setText(fornecedor.getEnderecoId().getPais());
            dialogCadastro.getTfRua().setText(fornecedor.getEnderecoId().getRua());
            if (fornecedor.getUsuarioId().getSalario() != null) {
                dialogCadastro.getTfSalario().setText(fornecedor.getUsuarioId().getSalario().toString());
            }
            dialogCadastro.getTfSenha().setText(fornecedor.getUsuarioId().getSenha());
            dialogCadastro.getCbUf().setSelectedItem(fornecedor.getEnderecoId().getUf());
        }
    }

    public void marcarRadioButtonInicio() {
        dialogCadastro.getLblCepInvalido().setVisible(false);
        if (modFornecedor instanceof ModuloFornecedores) {
            dialogCadastro.getRdFornecedor().doClick();
            dialogCadastro.getRdAdmin().setEnabled(false);
            dialogCadastro.getRdAssistente().setEnabled(false);
            dialogCadastro.getRdVendedor().setEnabled(false);
        }
        radioButtonClickedAction();
    }

    public void salvarAction() {
        if (validaCamposAntesCadastro()) {
            Endereco endereco = salvarEndereco();
            Usuario usuario = salvarUsuario(endereco);
            if (usuario.getPerfilId().getId().equals(4)) {
                salvarFornecedor(endereco, usuario);
            }
            if (endereco.getId() == null | usuario.getId() == null) {
                JOptionPane.showMessageDialog(framePrincipal, "Algo deu errado no cadastro.");
                return;
            }
            dialogCadastro.dispose();
            limparDialog();
        }
    }

    private Endereco salvarEndereco() {
        DaoGenerico<Endereco> dao = new DaoGenerico<>();
        Endereco endereco = new Endereco();
        if (fornecedor != null) {
            endereco = fornecedor.getEnderecoId();
        }
        if (!dialogCadastro.getTfCep().getText().contains(" ") && !dialogCadastro.getLblCepInvalido().isVisible()) {
            endereco.setCep(dialogCadastro.getTfCep().getText());
        }
        endereco.setBairro(dialogCadastro.getTfBairro().getText());
        endereco.setCidade(dialogCadastro.getTfCidade().getText());
        endereco.setComplemento(dialogCadastro.getTfComplemento().getText());
        endereco.setNumero(Integer.parseInt(dialogCadastro.getTfNum().getText()));
        endereco.setPais(dialogCadastro.getTfPais().getText());
        endereco.setRua(dialogCadastro.getTfRua().getText());
        endereco.setUf(dialogCadastro.getCbUf().getSelectedItem().toString());
        dao.saveOrUpdate(endereco);
        return endereco;
    }

    private Usuario salvarUsuario(Endereco endereco) {
        DaoGenerico<Usuario> dao = new DaoGenerico<>();
        DaoGenerico<Perfil> daoPerfil = new DaoGenerico<>();
        Usuario usuario = new Usuario();
        if (fornecedor != null) {
            usuario = fornecedor.getUsuarioId();
            System.out.println("-----------------------------------------------");
            System.out.println(usuario.getId());
        }
        usuario.setNome(dialogCadastro.getTfNome().getText());
        usuario.setEmail(dialogCadastro.getTfEmail().getText());
        if (dialogCadastro.getTfSalario().getText().length() > 0) {
            usuario.setSalario(Double.parseDouble(dialogCadastro.getTfSalario().getText()));
        }
        usuario.setSenha(dialogCadastro.getTfSenha().getText());
        usuario.setEnderecoId(endereco);
        Integer perfilId = 0;
        if (dialogCadastro.getRdAdmin().isSelected()) {
            perfilId = 1;
        } else if (dialogCadastro.getRdAssistente().isSelected()) {
            perfilId = 3;
        } else if (dialogCadastro.getRdFornecedor().isSelected()) {
            perfilId = 4;
        } else if (dialogCadastro.getRdVendedor().isSelected()) {
            perfilId = 2;
        }
        usuario.setPerfilId(daoPerfil.findById(Perfil.class, perfilId));
        dao.saveOrUpdate(usuario);
        return usuario;
    }

    private Fornecedor salvarFornecedor(Endereco endereco, Usuario usuario) {
        DaoGenerico<Fornecedor> dao = new DaoGenerico();
        Fornecedor fornecedor = new Fornecedor();
        if (this.fornecedor != null) {
            fornecedor = this.fornecedor;
        }
        fornecedor.setNome(dialogCadastro.getTfNome().getText());
        fornecedor.setDataCadastro(new Date());
        fornecedor.setUsuarioId(usuario);
        fornecedor.setEnderecoId(endereco);
        dao.saveOrUpdate(fornecedor);
        return fornecedor;
    }

    private void limparDialog() {
        dialogCadastro.getTfBairro().setText("");
        dialogCadastro.getTfCep().setText("");
        dialogCadastro.getTfCidade().setText("");
        dialogCadastro.getTfComplemento().setText("");
        dialogCadastro.getTfEmail().setText("");
        dialogCadastro.getTfNome().setText("");
        dialogCadastro.getTfNum().setText("");
        dialogCadastro.getTfPais().setText("");
        dialogCadastro.getTfRua().setText("");
        dialogCadastro.getTfSalario().setText("");
        dialogCadastro.getTfSenha().setText("");
        dialogCadastro.getCbUf().setSelectedIndex(-1);
    }

    public void cancelarAction() {
        limparDialog();
        dialogCadastro.dispose();
    }

    public void radioButtonClickedAction() {
        if (dialogCadastro.getRdFornecedor().isSelected()) {
            dialogCadastro.getTfSalario().setEnabled(false);
        } else {
            dialogCadastro.getTfSalario().setEnabled(true);
        }
    }

    public void pesquisaCepAction() {
        if (dialogCadastro.getTfCep().getText().contains(" ")) {
            dialogCadastro.getLblCepInvalido().setVisible(false);
        }
        if (!dialogCadastro.getTfCep().getText().contains(" ")) {
            EnderecoFromCEP endereco = ViaCEP.getEnderecoFromCep(dialogCadastro.getTfCep().getText());
            if (endereco != null && endereco.getLogradouro() != null) {
                dialogCadastro.getTfBairro().setText(endereco.getBairro());
                dialogCadastro.getTfCep().setText(endereco.getCep());
                dialogCadastro.getTfCidade().setText(endereco.getLocalidade());
                if (endereco.getComplemento() != null) {
                    dialogCadastro.getTfComplemento().setText(endereco.getComplemento());
                }
                dialogCadastro.getTfRua().setText(endereco.getLogradouro());
                dialogCadastro.getTfPais().setText("Brasil");
                for (int i = 0; i < dialogCadastro.getCbUf().getItemCount(); i++) {
                    if (dialogCadastro.getCbUf().getItemAt(i).contains(endereco.getUf())) {
                        dialogCadastro.getCbUf().setSelectedIndex(i);
                        break;
                    }
                }
                dialogCadastro.getTfNum().requestFocus();
            } else {
                dialogCadastro.getLblCepInvalido().setVisible(true);
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

    //verifica se campos estão vazios ou apenas com espaço, e se for invalido, deixa campo vermelho
    public boolean validaCamposAntesCadastro() {
        boolean tudoCerto = true;
        if (dialogCadastro.getTfEmail().getText().contains(" ")) {
            dialogCadastro.getTfEmail().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfEmail().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfEmail().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfEmail().setVisible(true);
        }
        if (dialogCadastro.getTfNome().getText().length() == 0 | dialogCadastro.getTfNome().getText().trim().length() == 0) {
            dialogCadastro.getTfNome().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfNome().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfNome().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfNome().setVisible(true);
        }
        if (dialogCadastro.getTfSenha().getText().length() == 0 | dialogCadastro.getTfSenha().getText().trim().length() == 0) {
            dialogCadastro.getTfSenha().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfSenha().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfSenha().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfSenha().setVisible(true);
        }
        if (dialogCadastro.getTfBairro().getText().length() == 0 | dialogCadastro.getTfBairro().getText().trim().length() == 0) {
            dialogCadastro.getTfBairro().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfBairro().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfBairro().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfBairro().setVisible(true);
        }
        if (dialogCadastro.getTfCidade().getText().length() == 0 | dialogCadastro.getTfCidade().getText().trim().length() == 0) {
            dialogCadastro.getTfCidade().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfCidade().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfCidade().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfCidade().setVisible(true);
        }
        if (dialogCadastro.getTfNum().getText().length() == 0 | dialogCadastro.getTfNum().getText().trim().length() == 0) {
            dialogCadastro.getTfNum().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfNum().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfNum().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfNum().setVisible(true);
        }
        if (dialogCadastro.getTfPais().getText().length() == 0 | dialogCadastro.getTfPais().getText().trim().length() == 0) {
            dialogCadastro.getTfPais().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfPais().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfPais().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfPais().setVisible(true);
        }
        if (dialogCadastro.getTfRua().getText().length() == 0 | dialogCadastro.getTfRua().getText().trim().length() == 0) {
            dialogCadastro.getTfRua().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfRua().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfRua().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfRua().setVisible(true);
        }
        if (dialogCadastro.getCbUf().getSelectedIndex() == -1) {
            dialogCadastro.getCbUf().setBackground(new Color(255, 102, 102));
            dialogCadastro.getCbUf().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getCbUf().setBackground(new Color(255, 255, 255));
            dialogCadastro.getCbUf().setVisible(true);
        }
        return tudoCerto;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import entities.Cliente;
import entities.Endereco;
import entities.EnderecoFromCEP;
import java.awt.Color;
import java.util.Date;
import util.Validacao;
import util.ViaCEP;
import view.DialogCadastroCliente;
import view.ModuloClientes;
import view.TelaPrincipal;

/**
 *
 * @author erick.costa
 */
public class CadastroClienteControl {

    TelaPrincipal framePrincipal;
    ModuloClientes modCliente;
    Cliente cliente;
    DialogCadastroCliente dialogCadastro;
    DaoGenerico<Cliente> daoGenerico;
    private int keycode;

    public CadastroClienteControl(TelaPrincipal framePrincipal, ModuloClientes modCliente, Cliente cliente, DialogCadastroCliente dialogCadastro) {
        this.framePrincipal = framePrincipal;
        this.modCliente = modCliente;
        this.cliente = cliente;
        this.dialogCadastro = dialogCadastro;
        this.daoGenerico = new DaoGenerico<>();
    }

    public void popularDialog() {
        dialogCadastro.getLblCepInvalido().setVisible(false);
        if (cliente != null) {
            if (cliente.getEnderecoId() != null) {
                dialogCadastro.getSelectionCadastroEndereco().setSelected(true);
            } else {
                dialogCadastro.getSelectionCadastroEndereco().setSelected(false);
            }
            dialogCadastro.getTfCpf().setText(cliente.getCpf());
            dialogCadastro.getTfNome().setText(cliente.getNome());
        }
    }

    public void selectionChanged() {
        if (dialogCadastro.getSelectionCadastroEndereco().isSelected()) {
            dialogCadastro.getTfBairro().setEnabled(true);
            dialogCadastro.getTfCep().setEnabled(true);
            dialogCadastro.getTfCidade().setEnabled(true);
            dialogCadastro.getTfComplemento().setEnabled(true);
            dialogCadastro.getTfRua().setEnabled(true);
            dialogCadastro.getTfNum().setEnabled(true);
            dialogCadastro.getTfPais().setEnabled(true);
            dialogCadastro.getCbUf().setEnabled(true);
            if (cliente != null && cliente.getEnderecoId() != null) {
                dialogCadastro.getTfBairro().setText(cliente.getEnderecoId().getBairro());
                dialogCadastro.getTfCep().setText(cliente.getEnderecoId().getCep());
                dialogCadastro.getTfCidade().setText(cliente.getEnderecoId().getCidade());
                dialogCadastro.getTfComplemento().setText(cliente.getEnderecoId().getComplemento());
                dialogCadastro.getTfRua().setText(cliente.getEnderecoId().getRua());
                dialogCadastro.getTfNum().setText(cliente.getEnderecoId().getNumero().toString());
                dialogCadastro.getTfPais().setText(cliente.getEnderecoId().getPais());
                dialogCadastro.getCbUf().setSelectedItem(cliente.getEnderecoId().getUf());
            }
        } else if (!dialogCadastro.getSelectionCadastroEndereco().isSelected()) {
            dialogCadastro.getTfBairro().setText("");
            dialogCadastro.getTfCep().setText("");
            dialogCadastro.getTfCidade().setText("");
            dialogCadastro.getTfComplemento().setText("");
            dialogCadastro.getTfRua().setText("");
            dialogCadastro.getTfNum().setText("");
            dialogCadastro.getTfPais().setText("");
            dialogCadastro.getCbUf().setSelectedItem(-1);
            dialogCadastro.getTfBairro().setEnabled(false);
            dialogCadastro.getTfCep().setEnabled(false);
            dialogCadastro.getTfCidade().setEnabled(false);
            dialogCadastro.getTfComplemento().setEnabled(false);
            dialogCadastro.getTfRua().setEnabled(false);
            dialogCadastro.getTfNum().setEnabled(false);
            dialogCadastro.getTfPais().setEnabled(false);
            dialogCadastro.getCbUf().setEnabled(false);
        }
    }

    public void cadastrarAction() {
        if (!validaCamposAntesCadastro()) {
            return;
        }
        Endereco endereco;
        if (cliente == null) {
            cliente = new Cliente();
        }
        if (dialogCadastro.getSelectionCadastroEndereco().isSelected()) {
            endereco = new Endereco();
            if (cliente.getEnderecoId() != null) {
                endereco.setId(cliente.getEnderecoId().getId());
            }
            if (!dialogCadastro.getTfCep().getText().contains(" ") && !dialogCadastro.getLblCepInvalido().isVisible()) {
                endereco.setCep(dialogCadastro.getTfCep().getText());
            }
            endereco.setBairro(dialogCadastro.getTfBairro().getText());
            endereco.setCidade(dialogCadastro.getTfCidade().getText());
            endereco.setComplemento(dialogCadastro.getTfComplemento().getText());
            if (dialogCadastro.getTfNum().getText().length() > 0) {
                endereco.setNumero(Integer.parseInt(dialogCadastro.getTfNum().getText()));
            }
            endereco.setPais(dialogCadastro.getTfPais().getText());
            endereco.setRua(dialogCadastro.getTfRua().getText());
            endereco.setUf(dialogCadastro.getCbUf().getSelectedItem().toString());
            cliente.setEnderecoId(endereco);
        }
        cliente.setNome(dialogCadastro.getTfNome().getText());
        cliente.setCpf(dialogCadastro.getTfCpf().getText());
        cliente.setDataCadastro(new Date());
        cliente.setAtivo(true);
        daoGenerico.saveOrUpdate(cliente);
        dialogCadastro.dispose();
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
        if (dialogCadastro.getTfCpf().getText().contains(" ")) {
            dialogCadastro.getTfCpf().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfCpf().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfCpf().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfCpf().setVisible(true);
        }
        if (dialogCadastro.getTfNome().getText().length() == 0 | dialogCadastro.getTfNome().getText().trim().length() == 0) {
            dialogCadastro.getTfNome().setBackground(new Color(255, 102, 102));
            dialogCadastro.getTfNome().setVisible(true);
            tudoCerto = false;
        } else {
            dialogCadastro.getTfNome().setBackground(new Color(255, 255, 255));
            dialogCadastro.getTfNome().setVisible(true);
        }
        if (dialogCadastro.getSelectionCadastroEndereco().isSelected()) {
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
        }
        return tudoCerto;
    }
    
    public void cancelarAction() {
        dialogCadastro.dispose();
    }
}

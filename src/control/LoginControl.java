/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.UsuarioDao;
import entities.Usuario;
import java.awt.event.KeyEvent;
import view.TelaLogin;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class LoginControl {

    TelaLogin login;

    public LoginControl(TelaLogin login) {
        this.login = login;
    }

    public void validaLogin() {
        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = new Usuario();
        usuario.setEmail(login.getTfEmail().getText());
        usuario = dao.findByEmail(usuario);
        boolean comparaSenha = false;
        if (usuario != null) {
            if (usuario.getSenha().toCharArray().length == login.getPfSenha().getPassword().length) {
                for (int i = 0; i < login.getPfSenha().getPassword().length; i++) {
                    if (usuario.getSenha().toCharArray()[i] == login.getPfSenha().getPassword()[i]) {
                        comparaSenha = true;
                    } else {
                        comparaSenha = false;
                        break;
                    }
                }
            }
        }
        if (comparaSenha) {
            TelaPrincipal principal = TelaPrincipal.getInstancia(usuario);
            principal.setVisible(true);
            login.dispose();
            login.getTfEmail().setText("");
            login.getPfSenha().setText("");
        } else {
            login.getLblIncorreto().setVisible(true);
        }
    }
    
    public void keyEnterAction(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validaLogin();
        }
    }
}

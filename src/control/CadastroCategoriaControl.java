/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DaoGenerico;
import entities.Categoria;
import view.DialogCadastroCategoria;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class CadastroCategoriaControl {
    TelaPrincipal framePrincipal;
    DialogCadastroCategoria dialog;
    DaoGenerico<Categoria> dao; 

    public CadastroCategoriaControl(TelaPrincipal framePrincipal, DialogCadastroCategoria dialog) {
        this.framePrincipal = framePrincipal;
        this.dialog = dialog;
        dao = new DaoGenerico<>();
    }
    
    public void cadastrarAction() {
        Categoria categoria = new Categoria();
        categoria.setNome(dialog.getTfNome().getText());
        dao.saveOrUpdate(categoria);
        dialog.dispose();
    }
    
    public void cancelarAction() {
        dialog.dispose();
    }
}

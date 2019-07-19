/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author erick
 */
public class UsuarioDao {
    private static EntityManager manager = ConnectionFactory.getEntityManager();
    
    public Usuario findByEmail(Usuario usuario) {
        Usuario usuario2 = new Usuario();
        try {
            usuario2 = manager.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", usuario.getEmail()).getSingleResult();
            return usuario2;
        } catch (NoResultException e) {
            System.out.println("Usuario invalido");
            return null;
        }
    }
}

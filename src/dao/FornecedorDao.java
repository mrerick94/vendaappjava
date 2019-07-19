/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Fornecedor;
import entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import util.StringSQLFiltros;

/**
 *
 * @author erick
 */
public class FornecedorDao {
    private static EntityManager manager = ConnectionFactory.getEntityManager();
    
    public Fornecedor findByUsuarioId(Usuario usuario) {
        Fornecedor fornecedor = new Fornecedor();
        try {
            fornecedor = manager.createNamedQuery("Fornecedor.findByUsuarioId", Fornecedor.class).setParameter("usuarioId", usuario).getSingleResult();
            return fornecedor;
        } catch (NoResultException e) {
            System.out.println("Usuario invalido");
            return null;
        }
    }
    
    public List<Fornecedor> findByFiltros(String nome, Date dataInicio, Date dataFim, Date dataCadastro, String cidade, String estado) {
        List<Fornecedor> listaFornecedor;
        try {
            listaFornecedor = manager.createQuery(StringSQLFiltros.queryFuncionariosComTodosFiltros(nome, 
                    dataInicio, dataFim, dataCadastro, cidade, estado), Fornecedor.class).getResultList();
            return listaFornecedor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

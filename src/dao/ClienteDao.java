/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Cliente;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import util.StringSQLFiltros;

/**
 *
 * @author erick.costa
 */
public class ClienteDao {
    private static EntityManager manager = ConnectionFactory.getEntityManager();
    
    public List<Cliente> findByFiltros(String nome, String cidade, Date dataInicio, Date dataFim,
            String cpf, Date dataCadastro, String bairro, String estado) {
        List<Cliente> listaCliente;
        try {
            listaCliente = manager.createQuery(StringSQLFiltros.queryClientesComTodosFiltros(nome, cidade, dataInicio, 
                    dataFim, cpf, dataCadastro, bairro, estado), Cliente.class).getResultList();
            return listaCliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Fornecedor;
import entities.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import util.StringSQLFiltros;

/**
 *
 * @author erick
 */
public class ProdutoDao {
    private static EntityManager manager = ConnectionFactory.getEntityManager();
    
    public List<Produto> findByFornecedor(Fornecedor fornecedor) {
        List<Produto> listaProduto;
        try {
            listaProduto = manager.createNamedQuery("Produto.findByFornecedorId", Produto.class).setParameter("fornecedorId", fornecedor).getResultList();
            return listaProduto;
        } catch (NoResultException e) {
            System.out.println("Usuario invalido");
            return null;
        }
    }
    
    public List<Produto> findAtivosByFornecedor(Fornecedor fornecedor) {
        List<Produto> listaProduto;
        try {
            listaProduto = manager.createNamedQuery("Produto.findAtivoByFornecedorId", Produto.class).setParameter("fornecedorId", fornecedor).setParameter("ativo", true).getResultList();
            return listaProduto;
        } catch (NoResultException e) {
            System.out.println("Usuario invalido");
            return null;
        }
    }
    
    public Produto findByCodigoBarra(String codigoBarra) {
        Produto p;
        try {
            p = manager.createNamedQuery("Produto.findByCodigoBarra", Produto.class).setParameter("codigobarra", codigoBarra).getSingleResult();
            return p;
        } catch (NoResultException e) {
            System.out.println("Código de Barras inexistente.");
            return null;
        }
    }
    
    public List<Produto> findByFiltrosPrecoEspecifico(String nomeProduto, String precoCompra, String precoVenda, String estoque) {
        List<Produto> listaProduto;
        try {
            listaProduto = manager.createQuery(StringSQLFiltros.queryEstoqueComFiltrosPrecoEspecifico(nomeProduto, precoCompra, precoVenda, estoque), Produto.class).getResultList();
            return listaProduto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Produto> findByFiltrosIntervaloPrecos(String nomeProduto, String precoCompraInicio, String precoCompraFim, 
            String precoVendaInicio, String precoVendaFim, String estoque) {
        List<Produto> listaProduto;
        try {
            listaProduto = manager.createQuery(StringSQLFiltros.queryEstoqueComFiltrosRangePrecos(nomeProduto, precoCompraInicio, precoCompraFim, 
                    precoVendaInicio, precoVendaFim, estoque), Produto.class).getResultList();
            return listaProduto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

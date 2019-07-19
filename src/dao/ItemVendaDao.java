/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Produto;
import entities.VendaItem;
import entities.VendaItemPK;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author erick
 */
public class ItemVendaDao {
    private static EntityManager manager = ConnectionFactory.getEntityManager();
    
    Connection conexao = Conexao.getConexao();
    
    public List<VendaItem> findByProduto(Produto produto) {
        List<VendaItem> listaVendaItem;
        try {
            listaVendaItem = manager.createNamedQuery("VendaItem.findByProdutoId", VendaItem.class).setParameter("produtoId", produto.getId()).getResultList();
            return listaVendaItem;
        } catch (NoResultException e) {
            System.out.println("Nenhuma venda para este produto");
            return null;
        }
    }
    
    public void saveListVendaItem(List<VendaItem> items) {
        Connection conexao = Conexao.getConexao();
        try {
            for (VendaItem vendaItem : items) {
                PreparedStatement ps;
                ps = conexao.prepareStatement("insert into vendaitem (produto_id, venda_id, quantidade, precoVenda) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, vendaItem.getProduto().getId());
                ps.setInt(2, vendaItem.getVenda().getId());
                ps.setDouble(3, vendaItem.getQuantidade());
                ps.setDouble(4, vendaItem.getPrecoVenda());
                ResultSet rs;
                if (ps.executeUpdate() > 0) {
                    rs = ps.getGeneratedKeys();
                    rs.next();
                    vendaItem.setVendaItemPK(new VendaItemPK(rs.getInt(1), vendaItem.getProduto().getId(), vendaItem.getVenda().getId()));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

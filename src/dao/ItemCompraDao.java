/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.CompraItem;
import entities.CompraItemPK;
import entities.Produto;
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
public class ItemCompraDao {

    private static EntityManager manager = ConnectionFactory.getEntityManager();

    public List<CompraItem> findByProduto(Produto produto) {
        List<CompraItem> listaCompraItem;
        try {
            listaCompraItem = manager.createNamedQuery("CompraItem.findByProdutoId", CompraItem.class).setParameter("produtoId", produto.getId()).getResultList();
            return listaCompraItem;
        } catch (NoResultException e) {
            System.out.println("Nenhuma compra para este produto");
            return null;
        }
    }

    public void saveCompraItem(CompraItem item) {
        Connection conexao = Conexao.getConexao();
        try {
            PreparedStatement ps;
            ps = conexao.prepareStatement("insert into compraitem (produto_id, compra_id, quantidade, precoCompra) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getProduto().getId());
            ps.setInt(2, item.getCompra().getId());
            ps.setDouble(3, item.getQuantidade());
            ps.setDouble(4, item.getPrecoCompra());
            ResultSet rs;
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                rs.next();
                item.setCompraItemPK(new CompraItemPK(rs.getInt(1), item.getProduto().getId(), item.getCompra().getId()));
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

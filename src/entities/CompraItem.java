/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erick.costa
 */
@Entity
@Table(name = "compraitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraItem.findAll", query = "SELECT c FROM CompraItem c")
    , @NamedQuery(name = "CompraItem.findById", query = "SELECT c FROM CompraItem c WHERE c.compraItemPK.id = :id")
    , @NamedQuery(name = "CompraItem.findByProdutoId", query = "SELECT c FROM CompraItem c WHERE c.compraItemPK.produtoId = :produtoId")
    , @NamedQuery(name = "CompraItem.findByCompraId", query = "SELECT c FROM CompraItem c WHERE c.compraItemPK.compraId = :compraId")
    , @NamedQuery(name = "CompraItem.findByQuantidade", query = "SELECT c FROM CompraItem c WHERE c.quantidade = :quantidade")
    , @NamedQuery(name = "CompraItem.findByPrecoCompra", query = "SELECT c FROM CompraItem c WHERE c.precoCompra = :precoCompra")})
public class CompraItem implements Serializable, EntityBase {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompraItemPK compraItemPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private Double quantidade;
    @Basic(optional = false)
    @Column(name = "precoCompra")
    private Double precoCompra;
    @JoinColumn(name = "compra_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;
    @JoinColumn(name = "produto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public CompraItem() {
    }

    public CompraItem(CompraItemPK compraItemPK) {
        this.compraItemPK = compraItemPK;
    }

    public CompraItem(CompraItemPK compraItemPK, Double quantidade, Double precoCompra) {
        this.compraItemPK = compraItemPK;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
    }

    public CompraItem(int id, int produtoId, int compraId) {
        this.compraItemPK = new CompraItemPK(id, produtoId, compraId);
    }

    public CompraItem(CompraItemPK compraItemPK, Double quantidade, Double precoCompra, Compra compra, Produto produto) {
        this.compraItemPK = compraItemPK;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.compra = compra;
        this.produto = produto;
    }

    public CompraItemPK getCompraItemPK() {
        return compraItemPK;
    }

    public void setCompraItemPK(CompraItemPK compraItemPK) {
        this.compraItemPK = compraItemPK;
    }
    
    public Integer getId() {
        return this.compraItemPK.getId();
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraItemPK != null ? compraItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraItem)) {
            return false;
        }
        CompraItem other = (CompraItem) object;
        if ((this.compraItemPK == null && other.compraItemPK != null) || (this.compraItemPK != null && !this.compraItemPK.equals(other.compraItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompraItem[ compraItemPK=" + compraItemPK + " ]";
    }
    
}

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
@Table(name = "vendaitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendaItem.findAll", query = "SELECT v FROM VendaItem v")
    , @NamedQuery(name = "VendaItem.findById", query = "SELECT v FROM VendaItem v WHERE v.vendaItemPK.id = :id")
    , @NamedQuery(name = "VendaItem.findByProdutoId", query = "SELECT v FROM VendaItem v WHERE v.vendaItemPK.produtoId = :produtoId")
    , @NamedQuery(name = "VendaItem.findByVendaId", query = "SELECT v FROM VendaItem v WHERE v.vendaItemPK.vendaId = :vendaId")
    , @NamedQuery(name = "VendaItem.findByQuantidade", query = "SELECT v FROM VendaItem v WHERE v.quantidade = :quantidade")
    , @NamedQuery(name = "VendaItem.findByPrecoVenda", query = "SELECT v FROM VendaItem v WHERE v.precoVenda = :precoVenda")})
public class VendaItem implements Serializable, EntityBase {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaItemPK vendaItemPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private Double quantidade;
    @Basic(optional = false)
    @Column(name = "precoVenda")
    private Double precoVenda;
    @JoinColumn(name = "produto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "venda_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public VendaItem() {
    }

    public VendaItem(VendaItemPK vendaItemPK) {
        this.vendaItemPK = vendaItemPK;
    }

    public VendaItem(VendaItemPK vendaItemPK, Double quantidade, Double precoVenda) {
        this.vendaItemPK = vendaItemPK;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public VendaItem(int id, int produtoId, int vendaId) {
        this.vendaItemPK = new VendaItemPK(id, produtoId, vendaId);
    }

    public VendaItem(VendaItemPK vendaItemPK, Double quantidade, Double precoVenda, Produto produto, Venda venda) {
        this.vendaItemPK = vendaItemPK;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.produto = produto;
        this.venda = venda;
    }

    public VendaItemPK getVendaItemPK() {
        return vendaItemPK;
    }

    public void setVendaItemPK(VendaItemPK vendaItemPK) {
        this.vendaItemPK = vendaItemPK;
    }
    
    public Integer getId() {
        return vendaItemPK.getId();
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaItemPK != null ? vendaItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaItem)) {
            return false;
        }
        VendaItem other = (VendaItem) object;
        if ((this.vendaItemPK == null && other.vendaItemPK != null) || (this.vendaItemPK != null && !this.vendaItemPK.equals(other.vendaItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VendaItem[ vendaItemPK=" + vendaItemPK + " ]";
    }
    
}

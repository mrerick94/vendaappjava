/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author erick.costa
 */
@Embeddable
public class CompraItemPK implements Serializable, EntityBase {

    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "produto_id")
    private Integer produtoId;
    @Basic(optional = false)
    @Column(name = "compra_id")
    private Integer compraId;

    public CompraItemPK() {
    }

    public CompraItemPK(int id, int produtoId, int compraId) {
        this.id = id;
        this.produtoId = produtoId;
        this.compraId = compraId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) produtoId;
        hash += (int) compraId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraItemPK)) {
            return false;
        }
        CompraItemPK other = (CompraItemPK) object;
        if (!this.id.equals(other.id)) {
            return false;
        }
        if (!this.produtoId.equals(other.produtoId)) {
            return false;
        }
        if (!this.compraId.equals(other.compraId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompraItemPK[ id=" + id + ", produtoId=" + produtoId + ", compraId=" + compraId + " ]";
    }
    
}

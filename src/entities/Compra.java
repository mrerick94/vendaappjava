/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erick.costa
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
    , @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id")
    , @NamedQuery(name = "Compra.findByDataCompra", query = "SELECT c FROM Compra c WHERE c.dataCompra = :dataCompra")
    , @NamedQuery(name = "Compra.findByDataVencimento", query = "SELECT c FROM Compra c WHERE c.dataVencimento = :dataVencimento")
    , @NamedQuery(name = "Compra.findByDataPagamento", query = "SELECT c FROM Compra c WHERE c.dataPagamento = :dataPagamento")
    , @NamedQuery(name = "Compra.findByValorPago", query = "SELECT c FROM Compra c WHERE c.valorPago = :valorPago")})
public class Compra implements Serializable, EntityBase {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dataCompra")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorPago")
    private Double valorPago;
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fornecedor fornecedorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<CompraItem> compraItemList;

    public Compra() {
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Compra(Integer id, Date dataCompra) {
        this.id = id;
        this.dataCompra = dataCompra;
    }

    public Compra(Integer id, Date dataCompra, Date dataVencimento, Date dataPagamento, Double valorPago, Fornecedor fornecedorId, List<CompraItem> compraItemList) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.fornecedorId = fornecedorId;
        this.compraItemList = compraItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    @XmlTransient
    public List<CompraItem> getCompraItemList() {
        return compraItemList;
    }

    public void setCompraItemList(List<CompraItem> compraItemList) {
        this.compraItemList = compraItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compra[ id=" + id + " ]";
    }
    
}

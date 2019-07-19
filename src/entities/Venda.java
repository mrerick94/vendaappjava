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
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v")
    , @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id")
    , @NamedQuery(name = "Venda.findByDataVenda", query = "SELECT v FROM Venda v WHERE v.dataVenda = :dataVenda")
    , @NamedQuery(name = "Venda.findByDataVencimento", query = "SELECT v FROM Venda v WHERE v.dataVencimento = :dataVencimento")
    , @NamedQuery(name = "Venda.findByDataPagamento", query = "SELECT v FROM Venda v WHERE v.dataPagamento = :dataPagamento")
    , @NamedQuery(name = "Venda.findByValorPago", query = "SELECT v FROM Venda v WHERE v.valorPago = :valorPago")})
public class Venda implements Serializable, EntityBase {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorPago")
    private Double valorPago;
    @JoinColumn(name = "formaPagamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FormaPagamento formaPagamentoid;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Cliente clienteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<VendaItem> vendaItemList;

    public Venda() {
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Venda(Integer id, Date dataVenda) {
        this.id = id;
        this.dataVenda = dataVenda;
    }

    public Venda(Integer id, Date dataVenda, Date dataVencimento, Date dataPagamento, Double valorPago, Cliente clienteId, FormaPagamento formaPagamentoid, List<VendaItem> vendaItemList) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.clienteId = clienteId;
        this.formaPagamentoid = formaPagamentoid;
        this.vendaItemList = vendaItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
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

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public FormaPagamento getFormaPagamentoid() {
        return formaPagamentoid;
    }

    public void setFormaPagamentoid(FormaPagamento formaPagamentoid) {
        this.formaPagamentoid = formaPagamentoid;
    }

    @XmlTransient
    public List<VendaItem> getVendaItemList() {
        return vendaItemList;
    }

    public void setVendaItemList(List<VendaItem> vendaItemList) {
        this.vendaItemList = vendaItemList;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Venda[ id=" + id + " ]";
    }
    
}

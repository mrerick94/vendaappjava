/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erick.costa
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findByAtivo", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo")
    , @NamedQuery(name = "Produto.findByCodigoBarra", query = "SELECT p FROM Produto p WHERE p.codigoBarra = :codigobarra")
    , @NamedQuery(name = "Produto.findAtivoByFornecedorId", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo AND p.fornecedorId = :fornecedorId")
    , @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id")
    , @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome")
    , @NamedQuery(name = "Produto.findByPrecoCompra", query = "SELECT p FROM Produto p WHERE p.precoCompra = :precoCompra")
    , @NamedQuery(name = "Produto.findByPrecoVenda", query = "SELECT p FROM Produto p WHERE p.precoVenda = :precoVenda")
    , @NamedQuery(name = "Produto.findByEstoque", query = "SELECT p FROM Produto p WHERE p.estoque = :estoque")
    , @NamedQuery(name = "Produto.findByFornecedorId", query = "SELECT p FROM Produto p WHERE p.fornecedorId = :fornecedorId")})
public class Produto implements Serializable, EntityBase {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigobarra")
    private String codigoBarra;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "precoCompra")
    private Double precoCompra;
    @Basic(optional = false)
    @Column(name = "precoVenda")
    private Double precoVenda;
    @Basic(optional = false)
    @Column(name = "estoque")
    private Double estoque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<CompraItem> compraItemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<VendaItem> vendaItemList;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaId;
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fornecedor fornecedorId;
    @Column(name = "ativo", columnDefinition="TinyInt(1) default '1'")
    private boolean ativo;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String codigoBarra, String nome, Double precoCompra, Double precoVenda, Double estoque, boolean ativo) {
        this.id = id;
        this.codigoBarra = codigoBarra;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.estoque = estoque;
        this.ativo = ativo;
    }

    public Produto(Integer id, String codigoBarra, String nome, Double precoCompra, Double precoVenda, Double estoque, List<CompraItem> compraItemList, List<VendaItem> vendaItemList, Categoria categoriaId, Fornecedor fornecedorId, boolean ativo) {
        this.id = id;
        this.codigoBarra = codigoBarra;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.estoque = estoque;
        this.compraItemList = compraItemList;
        this.vendaItemList = vendaItemList;
        this.categoriaId = categoriaId;
        this.fornecedorId = fornecedorId;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    @XmlTransient
    public List<CompraItem> getCompraItemList() {
        return compraItemList;
    }

    public void setCompraItemList(List<CompraItem> compraItemList) {
        this.compraItemList = compraItemList;
    }

    @XmlTransient
    public List<VendaItem> getVendaItemList() {
        return vendaItemList;
    }

    public void setVendaItemList(List<VendaItem> vendaItemList) {
        this.vendaItemList = vendaItemList;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produto[ id=" + id + " ]";
    }
    
}

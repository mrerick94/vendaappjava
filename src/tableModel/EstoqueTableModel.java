/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import entities.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author erick
 */
public class EstoqueTableModel extends AbstractTableModel {

    private List<Produto> listaProduto = new ArrayList<>();

    static class Constantes {

        private static final String[] colunas = {"Nome", "Preço Compra", "Preço Venda", "Estoque", "Categoria", "Fornecedor"};
        private static final int NOME = 0;
        private static final int PRECO_COMPRA = 1;
        private static final int PRECO_VENDA = 2;
        private static final int ESTOQUE = 3;
        private static final int CATEGORIA = 4;
        private static final int FORNECEDOR = 5;
    }

    public EstoqueTableModel() {
    }

    public EstoqueTableModel(List lista) {
        listaProduto = lista;
    }

    @Override
    public int getRowCount() {
        return listaProduto.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case Constantes.NOME:
                return listaProduto.get(rowIndex).getNome();
            case Constantes.PRECO_COMPRA:
                return listaProduto.get(rowIndex).getPrecoCompra();
            case Constantes.PRECO_VENDA:
                return listaProduto.get(rowIndex).getPrecoVenda();
            case Constantes.ESTOQUE:
                return listaProduto.get(rowIndex).getEstoque();
            case Constantes.CATEGORIA:
                return listaProduto.get(rowIndex).getCategoriaId().getNome();
            case Constantes.FORNECEDOR:
                return listaProduto.get(rowIndex).getFornecedorId().getNome();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }

    public Produto getRow(int row) {
        return row >= 0 ? listaProduto.get(row) : null;
    }

    public void addRow(Produto obj) {
        listaProduto.add(obj);
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeRow(int row) {
        listaProduto.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, Produto obj) {
        listaProduto.set(row, obj);
        this.fireTableRowsUpdated(row, row);
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
        fireTableDataChanged();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import entities.CompraItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author erick
 */
public class TelaComprasTableModel extends AbstractTableModel {

    private List<CompraItem> listaCompraItem = new ArrayList<>();

    static class Constantes {

        private static final String[] colunas = {"Código Barra", "Nome", "Preço de Compra", "Quantidade", "Preço Total", "Data da Compra"};
        private static final int CODIGO_BARRA = 0;
        private static final int NOME = 1;
        private static final int PRECO_COMPRA = 2;
        private static final int QUANTIDADE = 3;
        private static final int PRECO_TOTAL = 4;
        private static final int DATA_COMPRA = 5;
    }

    public TelaComprasTableModel() {
    }

    public TelaComprasTableModel(List lista) {
        listaCompraItem = lista;
    }

    @Override
    public int getRowCount() {
        return listaCompraItem.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case Constantes.CODIGO_BARRA:
                return listaCompraItem.get(rowIndex).getProduto().getCodigoBarra();
            case Constantes.NOME:
                return listaCompraItem.get(rowIndex).getProduto().getNome();
            case Constantes.PRECO_COMPRA:
                return listaCompraItem.get(rowIndex).getPrecoCompra();
            case Constantes.QUANTIDADE:
                return listaCompraItem.get(rowIndex).getQuantidade();
            case Constantes.PRECO_TOTAL:
                return listaCompraItem.get(rowIndex).getPrecoCompra() * listaCompraItem.get(rowIndex).getQuantidade();
            case Constantes.DATA_COMPRA:
                return listaCompraItem.get(rowIndex).getCompra().getDataCompra();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }

    public CompraItem getRow(int row) {
        return row >= 0 ? listaCompraItem.get(row) : null;
    }

    public void addRow(CompraItem obj) {
        listaCompraItem.add(obj);
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeRow(int row) {
        listaCompraItem.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, CompraItem obj) {
        listaCompraItem.set(row, obj);
        this.fireTableRowsUpdated(row, row);
    }

    public List<CompraItem> getListaCompraItem() {
        return listaCompraItem;
    }

    public void setListaCompraItem(List<CompraItem> listaCompraItem) {
        this.listaCompraItem = listaCompraItem;
        fireTableDataChanged();
    }
}

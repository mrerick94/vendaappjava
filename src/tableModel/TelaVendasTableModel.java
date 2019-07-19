/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import entities.VendaItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author erick
 */
public class TelaVendasTableModel extends AbstractTableModel {
    private List<VendaItem> listaVendaItem = new ArrayList<>();

    static class Constantes {

        private static final String[] colunas = {"Código Barra", "Nome", "Preço de Venda", "Quantidade", "Preço Total", "Data da Venda"};
        private static final int CODIGO_BARRA = 0;
        private static final int NOME = 1;
        private static final int PRECO_VENDA = 2;
        private static final int QUANTIDADE = 3;
        private static final int PRECO_TOTAL = 4;
        private static final int DATA_VENDA = 5;
    }

    public TelaVendasTableModel() {
    }

    public TelaVendasTableModel(List lista) {
        listaVendaItem = lista;
    }

    @Override
    public int getRowCount() {
        return listaVendaItem.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case Constantes.CODIGO_BARRA:
                return listaVendaItem.get(rowIndex).getProduto().getCodigoBarra();
            case Constantes.NOME:
                return listaVendaItem.get(rowIndex).getProduto().getNome();
            case Constantes.PRECO_VENDA:
                return listaVendaItem.get(rowIndex).getPrecoVenda();
            case Constantes.QUANTIDADE:
                return listaVendaItem.get(rowIndex).getQuantidade();
            case Constantes.PRECO_TOTAL:
                return listaVendaItem.get(rowIndex).getPrecoVenda()* listaVendaItem.get(rowIndex).getQuantidade();
            case Constantes.DATA_VENDA:
                return listaVendaItem.get(rowIndex).getVenda().getDataVenda();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }

    public VendaItem getRow(int row) {
        return row >= 0 ? listaVendaItem.get(row) : null;
    }

    public void addRow(VendaItem obj) {
        listaVendaItem.add(obj);
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeRow(int row) {
        listaVendaItem.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, VendaItem obj) {
        listaVendaItem.set(row, obj);
        this.fireTableRowsUpdated(row, row);
    }

    public List<VendaItem> getListaVendaItem() {
        return listaVendaItem;
    }

    public void setListaVendaItem(List<VendaItem> listaVendaItem) {
        this.listaVendaItem = listaVendaItem;
    }
}

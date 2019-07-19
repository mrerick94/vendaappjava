/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import entities.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author erick
 */
public class FornecedorTableModel extends AbstractTableModel {
    
    private List<Fornecedor> listaFornecedor = new ArrayList<>();

    static class Constantes {

        private static final String[] colunas = {"Nome", "Data Cadastro", "Estado", "Cidade"};
        private static final int NOME = 0;
        private static final int DATA_CADASTRO = 1;
        private static final int ESTADO = 2;
        private static final int CIDADE = 3;
    }

    public FornecedorTableModel() {
    }

    public FornecedorTableModel(List lista) {
        listaFornecedor = lista;
    }

    @Override
    public int getRowCount() {
        return listaFornecedor.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case Constantes.NOME:
                return listaFornecedor.get(rowIndex).getNome();
            case Constantes.DATA_CADASTRO:
                return listaFornecedor.get(rowIndex).getDataCadastro();
            case Constantes.ESTADO:
                return listaFornecedor.get(rowIndex).getEnderecoId().getUf();
            case Constantes.CIDADE:
                return listaFornecedor.get(rowIndex).getEnderecoId().getCidade();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }

    public Fornecedor getRow(int row) {
        return row >= 0 ? listaFornecedor.get(row) : null;
    }

    public void addRow(Fornecedor obj) {
        listaFornecedor.add(obj);
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeRow(int row) {
        listaFornecedor.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, Fornecedor obj) {
        listaFornecedor.set(row, obj);
        this.fireTableRowsUpdated(row, row);
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
        fireTableDataChanged();
    }
}

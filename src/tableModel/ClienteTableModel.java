/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author erick.costa
 */
public class ClienteTableModel extends AbstractTableModel {
    
    private List<Cliente> listaCliente = new ArrayList<>();

    static class Constantes {

        private static final String[] colunas = {"Nome", "CPF", "Estado", "Cidade", "Bairro", "Data Cadastro"};
        private static final int NOME = 0;
        private static final int CPF = 1;
        private static final int ESTADO = 2;
        private static final int CIDADE = 3;
        private static final int BAIRRO = 4;
        private static final int DATA_CADASTRO = 5;
    }

    public ClienteTableModel() {
    }

    public ClienteTableModel(List lista) {
        listaCliente = lista;
    }

    @Override
    public int getRowCount() {
        return listaCliente.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case Constantes.NOME:
                return listaCliente.get(rowIndex).getNome();
            case Constantes.CPF:
                return listaCliente.get(rowIndex).getCpf();
            case Constantes.ESTADO:
                if (listaCliente.get(rowIndex).getEnderecoId() != null) {
                    return listaCliente.get(rowIndex).getEnderecoId().getUf();
                }
                return "";
            case Constantes.CIDADE:
                if (listaCliente.get(rowIndex).getEnderecoId() != null) {
                    return listaCliente.get(rowIndex).getEnderecoId().getCidade();
                }
                return "";
            case Constantes.BAIRRO:
                if (listaCliente.get(rowIndex).getEnderecoId() != null) {
                    return listaCliente.get(rowIndex).getEnderecoId().getBairro();
                }
                return "";
            case Constantes.DATA_CADASTRO:
                return listaCliente.get(rowIndex).getDataCadastro();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }

    public Cliente getRow(int row) {
        return row >= 0 ? listaCliente.get(row) : null;
    }

    public void addRow(Cliente obj) {
        listaCliente.add(obj);
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeRow(int row) {
        listaCliente.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, Cliente obj) {
        listaCliente.set(row, obj);
        this.fireTableRowsUpdated(row, row);
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
        fireTableDataChanged();
    }
}

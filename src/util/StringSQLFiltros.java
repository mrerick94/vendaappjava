/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;

/**
 *
 * @author erick
 */
public class StringSQLFiltros {

    public static String queryEstoqueComFiltrosPrecoEspecifico(String nomeProduto, String precoCompra, String precoVenda, String estoque) {
        String query = "SELECT p FROM Produto p WHERE p.ativo=1";
        if (nomeProduto.length() > 0) {
            query += " AND p.nome like '%" + nomeProduto + "%'";
        }
        if (precoCompra.length() > 0) {
            query += " AND p.precoCompra=" + precoCompra;
        }
        if (precoVenda.length() > 0) {
            query += " AND p.precoVenda=" + precoVenda;
        }
        if (estoque.length() > 0) {
            query += " AND p.estoque=" + estoque;
        }
        return query;
    }

    public static String queryEstoqueComFiltrosRangePrecos(String nomeProduto, String precoCompraInicio, String precoCompraFim,
            String precoVendaInicio, String precoVendaFim, String estoque) {
        String query = "SELECT p FROM Produto p WHERE p.ativo=1";
        if (nomeProduto.length() > 0) {
            query += " AND p.nome like '%" + nomeProduto + "%'";
        }
        if (precoCompraInicio.length() > 0 & precoCompraFim.length() > 0) {
            query += " AND p.precoCompra BETWEEN " + precoCompraInicio + " AND " + precoCompraFim;
        }
        if (precoVendaInicio.length() > 0 & precoVendaFim.length() > 0) {
            query += " AND p.precoVenda BETWEEN " + precoVendaInicio + " AND " + precoVendaFim;
        }
        if (estoque.length() > 0) {
            query += " AND p.estoque=" + estoque;
        }
        return query;
    }

    public static String queryClientesComTodosFiltros(String nome, String cidade, Date dataInicio, Date dataFim,
            String cpf, Date dataCadastro, String bairro, String estado) {
        String query = "SELECT c FROM Cliente c WHERE c.ativo=1";
        if (nome.length() > 0) {
            query += " AND c.nome like '%" + nome + "%'";
        }
        if (cidade.length() > 0) {
            query += " AND c.enderecoId.cidade like '%" + cidade + "%'";
        }
        if (bairro.length() > 0) {
            query += " AND c.enderecoId.bairro like '%" + bairro + "%'";
        }
        if (estado != null) {
            query += " AND c.enderecoId.uf='" + estado + "'";
        }
        if (!cpf.contains(" ")) {
            query += " AND c.cpf='" + cpf + "'";
        }
        if (dataCadastro != null) {
            query += " AND c.dataCadastro='" + ConversorData.dataToSql(dataCadastro) + "'";
        }
        if (dataFim != null && dataInicio != null) {
            query += " AND c.dataCadastro BETWEEN '" + ConversorData.dataToSql(dataInicio) + "' AND '" + ConversorData.dataToSql(dataFim) + "'";
        }
        return query;
    }

    public static String queryFuncionariosComTodosFiltros(String nome, Date dataInicio, Date dataFim, Date dataCadastro, String cidade, String estado) {
        String query = "SELECT f FROM Fornecedor f WHERE f.ativo=1";
        if (nome.length() > 0) {
            query += " AND f.nome like '%" + nome + "%'";
        }
        if (cidade.length() > 0) {
            query += " AND f.enderecoId.cidade like '%" + cidade + "%'";
        }
        if (estado != null) {
            query += " AND f.enderecoId.uf='" + estado + "'";
        }
        if (dataCadastro != null) {
            query += " AND f.dataCadastro='" + ConversorData.dataToSql(dataCadastro) + "'";
        }
        if (dataFim != null && dataInicio != null) {
            query += " AND f.dataCadastro BETWEEN '" + ConversorData.dataToSql(dataInicio) + "' AND '" + ConversorData.dataToSql(dataFim) + "'";
        }
        return query;
    }
}

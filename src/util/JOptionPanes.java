/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;
import view.TelaPrincipal;

/**
 *
 * @author erick
 */
public class JOptionPanes {
    
    public static void avisoPrecoEspecificoEIntervaloPrecoEstoque(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "Você não pode pesquisar Preços específicos e Intervalo de Preços Ao mesmo tempo.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void avisoTodosCamposVazios(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "Todos os campos estão vazios.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void avisoNecessarioComecoFim(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "O Inicio e o fim de um Intervalo devem ser preenchidos para ser pesquisado.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void avisoProdutoInexistente(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "Este Produto não existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    public static int perguntaFinalizarCompra(TelaPrincipal frame) {
        return JOptionPane.showConfirmDialog(frame, "Finalizar Compra?", "Finalizar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    public static void confirmacaoFinalizacaoCompra(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "Venda Finalizada. Grande Dia!", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static Double janelaDinheiroPago(TelaPrincipal frame) {
        return Double.parseDouble(JOptionPane.showInputDialog(frame, "Digite a quantidade paga:", "0.00"));
    }
    
    public static void informaTroco(TelaPrincipal frame, String troco) {
        JOptionPane.showMessageDialog(frame, "Troco: " + troco, "Troco", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int confirmacaoExcluir(TelaPrincipal frame) {
        return JOptionPane.showConfirmDialog(frame, "Excluir este item?", "Excluir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    public static void avisoNenhumItemSelecionado(TelaPrincipal frame) {
        JOptionPane.showMessageDialog(frame, "Nenhum item selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}

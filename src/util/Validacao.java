/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.KeyEvent;

/**
 *
 * @author erick
 */
public class Validacao {

    public static boolean filtroCampoMonetario(java.awt.event.KeyEvent evt) {
        if (evt.isActionKey() | evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            return true;
        } else if (evt.getKeyChar() != KeyEvent.VK_0 &
                evt.getKeyCode() != KeyEvent.VK_1 &
                evt.getKeyCode() != KeyEvent.VK_2 &
                evt.getKeyCode() != KeyEvent.VK_3 &
                evt.getKeyCode() != KeyEvent.VK_4 &
                evt.getKeyCode() != KeyEvent.VK_5 &
                evt.getKeyCode() != KeyEvent.VK_6 &
                evt.getKeyCode() != KeyEvent.VK_7 &
                evt.getKeyCode() != KeyEvent.VK_8 &
                evt.getKeyCode() != KeyEvent.VK_9 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD0 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD1 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD2 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD3 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD4 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD5 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD6 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD7 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD8 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD9 &
                evt.getKeyChar() != KeyEvent.VK_PERIOD
                ) {
            System.out.println("Digito invalido: " + evt.getKeyChar());
            if (evt.getKeyCode() == KeyEvent.VK_COMMA) {
                evt.setKeyCode(46);
                evt.setKeyChar('.');
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public static boolean validaCampoMonetario(String string) {
        if (string == null | string.equals("")) {
            return true;
        }
        try {
            Double d = Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean filtroCampoNumerico(java.awt.event.KeyEvent evt) {
        if (evt.isActionKey() | evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            return true;
        } else if (evt.getKeyChar() != KeyEvent.VK_0 &
                evt.getKeyCode() != KeyEvent.VK_1 &
                evt.getKeyCode() != KeyEvent.VK_2 &
                evt.getKeyCode() != KeyEvent.VK_3 &
                evt.getKeyCode() != KeyEvent.VK_4 &
                evt.getKeyCode() != KeyEvent.VK_5 &
                evt.getKeyCode() != KeyEvent.VK_6 &
                evt.getKeyCode() != KeyEvent.VK_7 &
                evt.getKeyCode() != KeyEvent.VK_8 &
                evt.getKeyCode() != KeyEvent.VK_9 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD0 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD1 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD2 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD3 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD4 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD5 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD6 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD7 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD8 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD9
                ) {
            System.out.println("Digito invalido: " + evt.getKeyChar());
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean filtroCampoCodigoBarraModVenda(java.awt.event.KeyEvent evt, String campoText) {
        System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_X & campoText.contains("x")) {
            return false;
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER | evt.getKeyCode() == KeyEvent.VK_BACK_SPACE | evt.getKeyCode() == KeyEvent.VK_F12) {
            return true;
        } else if (evt.getKeyChar() != KeyEvent.VK_0 &
                evt.getKeyCode() != KeyEvent.VK_1 &
                evt.getKeyCode() != KeyEvent.VK_2 &
                evt.getKeyCode() != KeyEvent.VK_3 &
                evt.getKeyCode() != KeyEvent.VK_4 &
                evt.getKeyCode() != KeyEvent.VK_5 &
                evt.getKeyCode() != KeyEvent.VK_6 &
                evt.getKeyCode() != KeyEvent.VK_7 &
                evt.getKeyCode() != KeyEvent.VK_8 &
                evt.getKeyCode() != KeyEvent.VK_9 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD0 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD1 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD2 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD3 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD4 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD5 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD6 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD7 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD8 &
                evt.getKeyCode() != KeyEvent.VK_NUMPAD9 &
                evt.getKeyCode() != KeyEvent.VK_X
                ) {
            System.out.println("Digito invalido: " + evt.getKeyChar());
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean validaCampoCpf() {
        //TO DO
        return false;
    }
    
    public static boolean validaCampoData() {
        //TO DO
        return false;
    }
}

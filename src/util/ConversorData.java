/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author erick
 */
public class ConversorData {
    
    public static String dataToSql(Date data) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(data);
    }
}

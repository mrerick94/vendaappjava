/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.EnderecoFromCEP;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author erick
 */
public class ViaCEP {

    public static EnderecoFromCEP getEnderecoFromCep(String cep) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://viacep.com.br/ws/" + cep.replaceFirst("-", "") + "/json/");
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            return new Gson().fromJson(content, EnderecoFromCEP.class);
        } catch (IOException ex) {
            Logger.getLogger(ViaCEP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

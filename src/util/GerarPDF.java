/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.CompraItem;
import entities.Produto;
import entities.VendaItem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author erick
 */
public class GerarPDF {
    //Jasper estava ignorando primeiro valor da lista, e dava muito trabalho fazer um workaround na fonte do problema
    //entao fiz uma lista com o primeiro valor sendo um novo item irrelevante, em todos os relatorios

    public static void gerarRelatorioEstoque(List<Produto> lista) {
        List<Produto> novaLista = new ArrayList<>();
        novaLista.add(new Produto());
        novaLista.addAll(lista);
        String outputPath = System.getProperty("user.home") + File.separatorChar + "RelatorioEstoque.pdf";
        try {
            System.out.println("Gerando Relatório PDF de Estoque...");
            InputStream stream = GerarPDF.class.getResourceAsStream("RelatorioEstoqueNovo.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(novaLista);
            Map parameters = new HashMap();
            parameters.put("ItemDataSource", ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
            OutputStream out = new FileOutputStream(new File(outputPath));
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            System.out.println("Geração Completa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioCompras(List<CompraItem> lista) {
        List<CompraItem> novaLista = new ArrayList<>();
        novaLista.add(new CompraItem());
        novaLista.addAll(lista);
        String outputPath = System.getProperty("user.home") + File.separatorChar + "RelatorioCompra.pdf";
        try {
            System.out.println("Gerando Relatório PDF de Compras...");
            InputStream stream = GerarPDF.class.getResourceAsStream("RelatorioCompra.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(novaLista);
            Map parameters = new HashMap();
            parameters.put("ItemDataSource", ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
            OutputStream out = new FileOutputStream(new File(outputPath));
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            System.out.println("Geração Completa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void gerarRelatorioVendas(List<VendaItem> lista) {
        List<VendaItem> novaLista = new ArrayList<>();
        novaLista.add(new VendaItem());
        novaLista.addAll(lista);
        String outputPath = System.getProperty("user.home") + File.separatorChar + "RelatorioVenda.pdf";
        try {
            System.out.println("Gerando Relatório PDF de Vendas...");
            InputStream stream = GerarPDF.class.getResourceAsStream("RelatorioVenda.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(novaLista);
            Map parameters = new HashMap();
            parameters.put("ItemDataSource", ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
            OutputStream out = new FileOutputStream(new File(outputPath));
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            System.out.println("Geração Completa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

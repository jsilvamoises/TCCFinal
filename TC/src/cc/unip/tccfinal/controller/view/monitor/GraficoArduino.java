///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.controller.view.monitor;
//
//import cc.unip.tccfinal.fxml.model.Sensor;
//import cc.unip.tccfinal.serialport.Arduino;
//import cc.unip.tccfinal.util.CacheLeitura;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//import javafx.application.Platform;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//
///**
// *
// * @author Moisés
// */
//public class GraficoArduino {
//    
//    private List<Sensor> dados = new ArrayList<>();
//    private final Arduino arduino;
//    SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");
//    // -------------------------------------------------------------------------
//    private LineChart<String, Number> lineChart;
//    private CategoryAxis xAxis;
//    private NumberAxis yAxis;
//    // -------------------------------------------------------------------------
//    XYChart.Series temperatura;
//    XYChart.Series umidade;// = new XYChart.Series();
//    XYChart.Series luminosidade;// = new XYChart.Series();
//
//    // -------------------------------------------------------------------------
//    public GraficoArduino(Arduino arduino) {
//        this.arduino = arduino;
//    }
//
//    // -------------------------------------------------------------------------
//    public GraficoArduino build() {
//        //#######################################
//        xAxis = new CategoryAxis();
//        yAxis = new NumberAxis(null, 0, 100, 5);
//        lineChart = new LineChart<>(xAxis, yAxis);
//        lineChart.setAnimated(false);
//        lineChart.setPrefHeight(300);
//        lineChart.setMaxHeight(300);
//        //#######################################
//        xAxis.setLabel("Sensores");
//        lineChart.setTitle("Sensores");
//        lineChart.setTitle("Monitoramento de ambiente");
//        lineChart.setCreateSymbols(false);
//        
//        
//        dados = CacheLeitura.getInstance().getDados();
//        startUpdateGraficoThread(); //Inicia atualização do grafico
//        return this;
//    }
//
//    public LineChart getLineChart() {
//        return lineChart;
//    }
//
//    private void startUpdateGraficoThread() {
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    gerarGrafico();
//                });
//            }
//        };
//        new Timer().scheduleAtFixedRate(update, 0, 500);
//    }
//
//    
//
//  
//    private void gerarGrafico() {
//        if (dados.size() > 0) {
//            lineChart.getData().clear();
//            
//            temperatura = new XYChart.Series();
//            temperatura.setName("Temperatura");
//
//            umidade = new XYChart.Series();
//            umidade.setName("Umidade");
//
//            luminosidade = new XYChart.Series();
//            luminosidade.setName("Luminosidade");
//
//            try {
//
//                CacheLeitura.getInstance().getDados().stream().forEach((dd) -> {
//                    try {                        
//                        temperatura.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getTemperatura()));
//                        umidade.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getUmidade()));
//                        luminosidade.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getLuminosidade()));
//                       
//                    } catch (Exception e) {
//                        System.err.println(e);
//                    }
//                });
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            lineChart.getData().addAll(temperatura, umidade, luminosidade);
//
//        }
//    }
//}

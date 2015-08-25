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
//import componentes.lcd.LCD;
//import eu.hansolo.enzo.lcd.Lcd;
//import java.util.Timer;
//import java.util.TimerTask;
//import javafx.application.Platform;
//import javafx.geometry.Insets;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//
///**
// *
// * @author Moisés
// */
//public class BoxLateralDireito {
//
//    private Lcd lcdTemperatura;
//    private Lcd lcdUmidade;
//    private Lcd lcdLuminosidade;
//    private VBox boxDireito;
//    GridPane gridPane;
//    private final Arduino arduino;
//    private Sensor sensor;
//
//    public BoxLateralDireito(Arduino arduino) {
//        this.arduino = arduino;
//    }
//    // -------------------------------------------------------------------------
//    public BoxLateralDireito build() {     
//
//        gridPane = new GridPane();
//        lcdTemperatura = LCD.getInstance().getLctType(LCD.LCDType.LCD_TEMPERATURA);
//        lcdUmidade = LCD.getInstance().getLctType(LCD.LCDType.LCD_UNIDADE);
//        lcdLuminosidade = LCD.getInstance().getLctType(LCD.LCDType.LCD_LUMINOSIDADE);
//
//        gridPane.add(lcdTemperatura, 0, 0, 1, 1);
//        gridPane.add(lcdUmidade, 0, 1, 1, 1);
//        gridPane.add(lcdLuminosidade, 0, 2, 1, 1);
//        gridPane.setPrefHeight(300);
//
//        boxDireito = new VBox(gridPane);
//        boxDireito.getStyleClass().add("background-app");
//        boxDireito.setPadding(new Insets(0, 10, 0, 10));
//        boxDireito.setPrefWidth(250);
//        startUpdateLCDThread();
//        return this;
//    }
//    // -------------------------------------------------------------------------
//    private void startUpdateLCDThread() {
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    atualizarLCD();
//                });
//            }
//        };
//        new Timer().scheduleAtFixedRate(update, 0, 1000);
//    }
//    // -------------------------------------------------------------------------
//    private void atualizarLCD() {
//        sensor = CacheLeitura.getInstance().getUltimoDadoRecebidoSensor();
//        if (sensor != null) {
//            lcdLuminosidade.setValue(sensor.getLuminosidade());
//            lcdTemperatura.setValue(sensor.getTemperatura());
//            lcdUmidade.setValue(sensor.getUmidade());
//        }
//
//    }
//    // -------------------------------------------------------------------------
//    public Lcd getLcdTemperatura() {
//        return lcdTemperatura;
//    }
//
//    public BoxLateralDireito setLcdTemperatura(Lcd lcdTemperatura) {
//        this.lcdTemperatura = lcdTemperatura;
//        return this;
//    }
//
//    public Lcd getLcdUmidade() {
//        return lcdUmidade;
//    }
//
//    public BoxLateralDireito setLcdUmidade(Lcd lcdUmidade) {
//        this.lcdUmidade = lcdUmidade;
//        return this;
//    }
//
//    public Lcd getLcdLuminosidade() {
//        return lcdLuminosidade;
//    }
//
//    public BoxLateralDireito setLcdLuminosidade(Lcd lcdLuminosidade) {
//        this.lcdLuminosidade = lcdLuminosidade;
//        return this;
//    }
//
//    public VBox getBoxDireito() {
//        return boxDireito;
//    }
//
//    public BoxLateralDireito setBoxDireito(VBox boxDireito) {
//        this.boxDireito = boxDireito;
//        return this;
//    }
//
//}

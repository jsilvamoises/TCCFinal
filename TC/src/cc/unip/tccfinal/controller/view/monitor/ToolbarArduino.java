///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package cc.unip.tccfinal.controller.view.monitor;
//
//import cc.unip.tccfinal.serialport.Arduino;
//import cc.unip.tccfinal.serialport.JavaSerialPort;
//import cc.unip.tccfinal.util.Icon32;
//import java.util.List;
//import java.util.Optional;
//import java.util.Timer;
//import java.util.TimerTask;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.geometry.Insets;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ChoiceDialog;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.ToolBar;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
///**
// *
// * @author Moisés
// */
//public class ToolbarArduino {
//    private Arduino arduino;    
//    private ToolBar toolBar;
//    //BOTOES
//    private Button btnIniciarMonitoramento,btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico;
//    private boolean estaSalvandoNoBanco, estaModoAutomatico,estaMonitorandoPorta;
//    
//
//    public ToolbarArduino(Arduino arduino) {
//        this.arduino = arduino;
//    }
//    
//    public ToolbarArduino build(){
//        
//        //######################################################################
//        btnIniciarMonitoramento = new Button("Iniciar Leiturara",new ImageView(new Image(Icon32.getInstance().ICON_MONITORING())));
//        btnIniciarMonitoramento.setContentDisplay(ContentDisplay.TOP);
//        btnIniciarMonitoramento.getStyleClass().add("btn-top");        
//        btnIniciarMonitoramento.setOnAction((ActionEvent event) -> {
//            desabilitarBotoes(btnIniciarMonitoramento);
//            habilitarBotoes(btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico);
//            estaMonitorandoPorta=true;
//            lerPortasArduino();
//        });
//        
//        btnPararMonitoramento = new Button("Parar Leitura",new ImageView(new Image(Icon32.getInstance().ICON_STOP())));
//        btnPararMonitoramento.setContentDisplay(ContentDisplay.TOP);
//        btnPararMonitoramento.getStyleClass().add("btn-top");
//        btnPararMonitoramento.setOnAction((ActionEvent event)->{
//            habilitarBotoes(btnIniciarMonitoramento);
//            desabilitarBotoes(btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico);
//            estaMonitorandoPorta=false;
//            arduino.pararDeReceberDados();
//        });
//        
//        btnSalvarAmostra = new Button("OFF", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//        btnSalvarAmostra.setContentDisplay(ContentDisplay.TOP);
//        btnSalvarAmostra.getStyleClass().add("btn-top");
//        btnSalvarAmostra.setOnAction((ActionEvent event)->{
//            if(estaSalvandoNoBanco){
//                estaMonitorandoPorta = false;
//                arduino.naoSalvarDadosNoBanco();
//            }else{
//               arduino.salvarDadosNoBanco();
//               estaSalvandoNoBanco = true;
//            }
//            
//            System.out.println("Você clicou no botão Salvar Amostra");
//        });
//        
//        btnManualAutomatico = new Button("OFF", new ImageView(new Image(Icon32.getInstance().ICON_ROBOT())));
//        btnManualAutomatico.setContentDisplay(ContentDisplay.TOP);
//        btnManualAutomatico.getStyleClass().add("btn-top");
//        btnManualAutomatico.setOnAction((ActionEvent event)->{
//            System.out.println("Você clicou no botão Manual Automatico");
//        });
//        dimensionarBotoesTopo(btnIniciarMonitoramento,btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico);//Redimensiona todos os botões
//        toolBar = new ToolBar(btnIniciarMonitoramento,btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico);// Adiciona os botões ao toolbar
//        toolBar.setPrefHeight(100);
//        toolBar.setPadding(new Insets(0, 0, 0, 240));
//        toolBar.getStyleClass().add("toolbar");
//        
//        desabilitarBotoes(btnPararMonitoramento,btnSalvarAmostra,btnManualAutomatico);
//        return this;
//    }
//    private void dimensionarBotoesTopo(Button... b)  {
//        for(Button but:b){
//            but.setPrefSize(170, 68);
//        }
//    }
//    public Button getBtnIniciarMonitoramento() {
//        return btnIniciarMonitoramento;
//    }
//
//    public void setBtnIniciarMonitoramento(Button btnIniciarMonitoramento) {
//        this.btnIniciarMonitoramento = btnIniciarMonitoramento;
//    }
//
//    public ToolBar getToolBar() {
//        return toolBar;
//    }
//
//    public void setToolBar(ToolBar toolBar) {
//        this.toolBar = toolBar;
//    }
//    
//    
//    private void desabilitarBotoes(Button... b){
//        for(Button bt:b){
//            bt.setDisable(true);
//        }
//    }
//    
//    private void habilitarBotoes(Button... b){
//        for(Button bt:b){
//            bt.setDisable(false);
//        }
//    }
//    
//    private boolean lerPortasArduino() {
//
//        List<String> choises = JavaSerialPort.getInstance().getPortas();
//        if (!choises.isEmpty()) {
//            ChoiceDialog<String> dialog = new ChoiceDialog<>(choises.get(0), choises);
//
//            dialog.setTitle("Seleção de portas disponíveis");
//            dialog.setHeaderText("Portas encontrada!!!");
//            dialog.setContentText("Selecione a porta desejada!!!");
//
//            Optional<String> result = dialog.showAndWait();
//            if (result.isPresent()) {
//                arduino = new Arduino(result.get());
//                arduino.lerPorta();
//            }
//            return true;
//        } else {
//            desabilitarBotoes();
//            new Alert(Alert.AlertType.INFORMATION, "Não foi possível detectar uma dispositivo com comunição serial puglado a esse PC!!", ButtonType.OK).show();
//            return false;
//        }
//
//    }
    
    
    
//        private void startUpdateGraficoThread() {
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    arduino.salvarDadosNoBanco();
//                });
//            }
//        };
//        new Timer().scheduleAtFixedRate(update, 0, 1000);
//        }
    
//}

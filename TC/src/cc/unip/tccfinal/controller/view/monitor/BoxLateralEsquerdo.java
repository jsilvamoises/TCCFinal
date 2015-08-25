///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.controller.view.monitor;
//
//import cc.unip.tccfinal.serialport.Arduino;
//import cc.unip.tccfinal.util.CommandCode;
//import cc.unip.tccfinal.util.Icon32;
//import componentes.lcd.ITButton;
//import eu.hansolo.enzo.experimental.tbutton.TButton;
//import eu.hansolo.enzo.lcd.LcdClock;
//import eu.hansolo.enzo.lcd.LcdClockBuilder;
//import java.util.Locale;
//import javafx.geometry.Insets;
//import javafx.scene.effect.Reflection;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.Text;
//
///**
// *
// * @author Moisés
// */
//public class BoxLateralEsquerdo {
//    private Arduino arduino;
//    private VBox boxEsquerdo;
//    private GridPane gridPane;
//    //TOGGLEBUTTON ENZO
//    private TButton tbArcondicionado;
//    private TButton tbAquecedor;
//    private TButton tbIluminacao;
//    private TButton tbUmidificador;
//    private LcdClock relogio;
//
//    public BoxLateralEsquerdo(Arduino arduino) {
//        this.arduino = arduino;
//    }
//    
//       public BoxLateralEsquerdo build() {
//        gridPane = new GridPane();
//        boxEsquerdo  = new VBox();
//        
//        relogio = LcdClockBuilder.create().prefSize(300,50).locale(new Locale("pt_BR")).backgroundVisible(true).crystalOverlayVisible(true).build();
//           
//        tbArcondicionado = ITButton.getInstance().getButton("OFF", Color.AQUAMARINE);
//        tbAquecedor = ITButton.getInstance().getButton("OFF", Color.AQUAMARINE);
//        tbIluminacao = ITButton.getInstance().getButton("OFF", Color.AQUAMARINE);
//        tbUmidificador = ITButton.getInstance().getButton("OFF", Color.AQUAMARINE);
////        //======================ADICIONA O LISTENERS DOS EVENTOS ===============
//        //AR CONDICIONADO
//       tbArcondicionado.setOnDeselect((TButton.SelectEvent event) -> {
//           arduino.write(CommandCode.DESLIGAR_AR_CONDICIONADO);           
//        });
//        tbArcondicionado.setOnSelect((TButton.SelectEvent event) -> {
//            arduino.write(CommandCode.LIGAR_AR_CONDICIONADO);            
//        });
////        //AQUECEDOR
//        tbAquecedor.setOnDeselect((TButton.SelectEvent event) -> {
//           arduino.write(CommandCode.DESLIGAR_AQUECEDOR); 
//        });
//        tbAquecedor.setOnSelect((TButton.SelectEvent event) -> {
//           arduino.write(CommandCode.LIGAR_AQUECEDOR); 
//        });
//        //ILUMINACAO
//        tbIluminacao.setOnSelect((TButton.SelectEvent event) -> {
//          arduino.write(CommandCode.DESLIGAR_ILUMINACAO); 
//        });
//        tbIluminacao.setOnDeselect((TButton.SelectEvent event) -> {
//           arduino.write(CommandCode.LIGAR_ILUMINACAO); 
//        });
//        //UMIDIFICADOR
//        tbUmidificador.setOnDeselect((TButton.SelectEvent event) -> {
//            arduino.write(CommandCode.DESLIGAR_UMIDIFICADOR); 
//        });
//        tbUmidificador.setOnSelect((TButton.SelectEvent event) -> {
//           arduino.write(CommandCode.LIGAR_UMIDIFICADOR); 
//       });
//        //TEXTO DE INDICAÇÃO
////        //======================================================================
//        Reflection r = new Reflection();
//        r.setFraction(0.7f);
//        //======================================================================
//        Text arCondicionado = new Text("Arcondicionado");
//        arCondicionado.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
//        arCondicionado.setFill(Color.RED);
//        arCondicionado.setEffect(r);
//        //======================================================================
//        Text Aquecedor = new Text("Aquecedor");
//        Aquecedor.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
//        Aquecedor.setFill(Color.RED);
//        Aquecedor.setEffect(r);
//        //======================================================================
//        Text txtIluminacao = new Text("Iluminação");
//        txtIluminacao.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
//        txtIluminacao.setFill(Color.RED);
//        txtIluminacao.setEffect(r);
//        //======================================================================
//        Text txtUmidificador = new Text("Umidificador");
//        txtUmidificador.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
//        txtUmidificador.setFill(Color.RED);
//        txtUmidificador.setEffect(r);
////        //======================================================================
//        gridPane.add(new ImageView(new Image(Icon32.getInstance().ICON_AR_COND_WHITE())),0,0);
//        gridPane.add(arCondicionado, 1, 0);
//        gridPane.add(tbArcondicionado, 2, 0);
//        
//        gridPane.add(new ImageView(new Image(Icon32.getInstance().ICON_AQUECEDOR_WHITE())),0,1);
//        gridPane.add(Aquecedor, 1, 1);
//        gridPane.add(tbAquecedor, 2, 1);
//        
//        gridPane.add(new ImageView(new Image(Icon32.getInstance().ICON_ILUMINACAO_WHITE())),0,2);
//        gridPane.add(txtIluminacao, 1, 2);
//        gridPane.add(tbIluminacao, 2, 2);
//        
//        gridPane.add(new ImageView(new Image(Icon32.getInstance().ICON_UMIDIFICADOR_WHITE())),0,3);
//        gridPane.add(txtUmidificador, 1, 3);
//        gridPane.add(tbUmidificador, 2, 3);
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        //REDIMENCIONA O TAMANHO DAS COLUNAS DO GRIDPANE
//        gridPane.getColumnConstraints().add(0, new ColumnConstraints(55));
//        gridPane.getColumnConstraints().add(1, new ColumnConstraints(140));
//        gridPane.getColumnConstraints().add(2, new ColumnConstraints(60));
//        boxEsquerdo.getStyleClass().add("background-app");
//        boxEsquerdo.getStyleClass().add("box-esquerdo");
//        boxEsquerdo.getChildren().addAll(relogio,gridPane);
//        return this;
////      
//    }
//
//    public VBox getBoxEsquerdo() {
//        return boxEsquerdo;
//    }
//    
//    
//}

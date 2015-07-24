/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

import cc.unip.tccfinal.rede.InterfaceTreinoRede;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Moisés
 */
public class Treino extends Application {

    private IGrafico graficos;    
    private InterfaceTreinoRede redeNeural;
    BorderPane root;
    private IToolbar toolbar;
    private IBoxEsquerdo boxEsquerdo;
    private IPrompt prompt;
    Dimension d;
    
    public Treino treino(){
        return this;
    }
    
    private void config(){
        d = Toolkit.getDefaultToolkit().getScreenSize();
        redeNeural = InterfaceTreinoRede.getInstance().setNrNeuroniosEntrada(3).setNrNeuroniosPrimeiraCamada(3).setPorcentagemTreinamento(50).prepararDados().build();
        toolbar = new IToolbar(redeNeural, this).builder();
        boxEsquerdo = new IBoxEsquerdo(redeNeural, this).build();
        prompt = new IPrompt(redeNeural, this).setWidth(d.width).build();
        graficos = new IGrafico(redeNeural, this).build();
    }
    @Override
    public void start(Stage primaryStage) {
        config();
                
       
        root = new BorderPane();

        graficos = new IGrafico(redeNeural, this).build();
        

        // prompt.setPrefHeight(100);
        root.setTop(toolbar.getToolbar());
        root.setLeft(boxEsquerdo.getBoxEsquerdo());
        root.setBottom(prompt.getBox());
        root.setCenter(graficos.getBox());

        Scene scene = new Scene(root, d.width / 2, d.height - 100);

        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());

        root.getStyleClass().add("background-app");

        primaryStage.setTitle("Treino Rede Neural");
        primaryStage.setScene(scene);

        //primaryStage.setMaximized(true);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

//    private void startUpdateGraficoThread() {
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    gerarGrafico();
//                });
//            }
//
//        };
//        new Timer().scheduleAtFixedRate(update, 0, 1000);
//    }

    public IGrafico getGraficos() {
        return graficos;
    }

    public IToolbar getToolbar() {
        return toolbar;
    }

    public IBoxEsquerdo getBoxEsquerdo() {
        return boxEsquerdo;
    }

    public InterfaceTreinoRede getRedeNeural() {
        return redeNeural;
    }

    
}

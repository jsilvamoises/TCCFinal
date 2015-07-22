/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

import cc.unip.tccfinal.rede.InterfaceTreinoRede;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Moisés
 */
public class Treino extends Application {
    
    private InterfaceTreinoRede redeNeural;
    BorderPane root;
    @Override
    public void start(Stage primaryStage) {
        redeNeural = InterfaceTreinoRede.getInstance().setNrNeuroniosEntrada(3).setNrNeuroniosPrimeiraCamada(3).setPorcentagemTreinamento(50).prepararDados().build();
        root = new BorderPane();
        
        
        
        
        
        root.setTop(new IToolbar().builder());
        root.setLeft(new IBoxEsquerdo().build());
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        
        root.getStyleClass().add("background-app");
       
        primaryStage.setTitle("Treino Rede Neural");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Moisés
 */
public class Monitor extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent   root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/cc/unip/tccfinal/fxml/Monitoramento.fxml"));            
        } catch (IOException ex) {
            Logger.getLogger(Treino.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            System.exit(0);
        });
        primaryStage.setTitle("Rede Neural");
        primaryStage.setMaximized(true);
       // primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

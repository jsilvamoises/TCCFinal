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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Moisés
 */
public class Treino extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent   root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/cc/unip/tccfinal/fxml/Treino.fxml"));            
        } catch (IOException ex) {
            Logger.getLogger(Treino.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Treino Rede Neural!");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

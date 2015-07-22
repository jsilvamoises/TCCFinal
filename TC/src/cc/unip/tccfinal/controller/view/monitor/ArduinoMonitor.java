/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.monitor;

import cc.unip.tccfinal.serialport.Arduino;
import eu.hansolo.enzo.experimental.tbutton.TButton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Moisés
 */
public class ArduinoMonitor extends Application {
    private Arduino arduino;
    private BorderPane borderPane;
    private ToolBar barraDeFerramenta;
    VBox boxLeft;
    VBox boxRight;
    VBox boxCentro;
    LineChart chart;
    ToolBar toolbar;
    TableView tableView;
    @Override
    public void start(Stage primaryStage) {
        init();
        
        
        //BorderPane root = new BorderPane();
        //root.getChildren().add(borderPane);
        
        Scene scene = new Scene(borderPane);
        
        primaryStage.setTitle("Redes Neurais Artificiais");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest((WindowEvent event)->{System.exit(0);});
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        
        boxCentro = new VBox();        
        arduino = new Arduino("COM3");
        chart = new GraficoArduino(arduino).build().getLineChart();
        tableView = new ArduinoTableView(arduino).build().getTableView();
        toolbar = new ToolbarArduino(arduino).build().getToolBar();
        boxLeft = new BoxLateralEsquerdo(arduino).build().getBoxEsquerdo();
        boxRight = new BoxLateralDireito(arduino).build().getBoxDireito();
        barraDeFerramenta  = new ToolBar();
        borderPane = new BorderPane();
        borderPane.setTop(toolbar);
        boxCentro.getChildren().add(0, chart);
        boxCentro.getChildren().add(1, tableView);
        borderPane.setCenter(boxCentro);
        borderPane.setLeft(boxLeft);
        borderPane.setRight(boxRight);
    }
    
    
    
    private void desabilitarComponentes(Object... o){
        for(Object obj:o){
            if(obj instanceof Button){
              ((Button) obj). setDisable(true);
            }else if(obj instanceof TButton){
                ((TButton) obj).setDisable(true);
            }
        }
    }
    
}

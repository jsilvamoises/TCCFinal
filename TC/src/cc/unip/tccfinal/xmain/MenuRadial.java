/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.xmain;

import eu.hansolo.enzo.radialmenu.RadialMenu;
import eu.hansolo.enzo.radialmenu.RadialMenuBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuItemBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuOptionsBuilder;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import cc.unip.tccfinal.util.IBackGround;

/**
 *
 * @author Moisés
 */
public class MenuRadial extends Application {
    private static final String ITEM_MONITORAR = "Monitorar";
    private static final String ITEM_TEMPERATURA = "Temperatura";
    private static final String ITEM_TREINAR_REDE = "Treinar rede";
    private Stage stage;
    private static double formWidth;
    private static double formHeight;
    
    private void setSize(){
        formWidth = stage.getWidth();
        formHeight = stage.getHeight();
    }
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        setSize();
        primaryStage.setMaximized(true);
       
        final RadialMenu radialMenu = RadialMenuBuilder.create()
                .options(
                        RadialMenuOptionsBuilder.create()
                        .degrees(360)
                        //.offset(-90)
                        .radius(200)
                        .buttonSize(72)
                        .buttonHideOnSelect(false)
                        .buttonHideOnClose(false)
                        .buttonAlpha(1.0
                        ).build())
                .items(
                        RadialMenuItemBuilder.create().thumbnailImageName(getClass().getResource("/resources/images/add-icon.png").toExternalForm()).size(70).backgroundColor(Color.AQUA).tooltip(ITEM_MONITORAR).build(),
                        RadialMenuItemBuilder.create().thumbnailImageName(getClass().getResource("/resources/images/add-icon.png").toExternalForm()).size(70).backgroundColor(Color.AQUA).tooltip(ITEM_TEMPERATURA).build(),
                        RadialMenuItemBuilder.create().thumbnailImageName(getClass().getResource("/resources/images/add-icon.png").toExternalForm()).size(70).backgroundColor(Color.AQUA).tooltip(ITEM_TREINAR_REDE).build()
                )
                
                .build();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       
        radialMenu.setOnItemClicked((RadialMenu.ItemEvent event) -> {
            String itemSelecionado = event.item.getTooltip();
            switch(itemSelecionado){
                case ITEM_MONITORAR:
                    break;
                case ITEM_TEMPERATURA:
                    break;
                case ITEM_TREINAR_REDE:
                    break;
            }
        });
        BorderPane root = new BorderPane(radialMenu, null, null, null, null);
        root.setBackground(IBackGround.BACKGROUND_MENU);
       // root.getChildren().add(radialMenu);

        Scene scene = new Scene(root);
        radialMenu.setPrefHeight(screenSize.getHeight());
        radialMenu.setPrefWidth(screenSize.getWidth());
       
        primaryStage.setTitle("Universidade Paulista UNIP - Ciência da computação 2015:: Redes Neurais Aplicada à Sensores.");
        primaryStage.setScene(scene);
        scene.setOnKeyPressed((KeyEvent event) -> {
            
            switch(event.getCode()){
                case F1:
                    System.out.println("Apertou F1");
                    break;
            }
        });
        primaryStage.show();
       
        
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

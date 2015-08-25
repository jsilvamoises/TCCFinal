///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.controller.view.treino;
//
//import cc.unip.tccfinal.rede.InterfaceTreinoRede;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.HBox;
//
///**
// *
// * @author Moisés
// */
//public class IPrompt {
//    private TextArea prompt;
//    HBox footerBox;
//    private InterfaceTreinoRede iTreino;
//    private Treino treino;
//
//    public IPrompt(InterfaceTreinoRede iTreino,Treino treino) {
//         this.treino = treino;
//         this.iTreino = iTreino;
//         footerBox = new HBox();
//         prompt = InterfaceTreinoRede.getInstance().build().getPrompt();    
//    }
//    
//    
//    public IPrompt build(){ 
//            
//        footerBox.getChildren().add(prompt);
//        return this;
//    }
//
//    public HBox getBox() {
//        return footerBox;
//    }
//    
//}

package lixo;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.model;
//
//import cc.unip.tccfinal.controller.EquipamentoController;
//import cc.unip.tccfinal.controller.IdEquipamento;
//import cc.unip.tccfinal.serialport.Arduino;
//import cc.unip.tccfinal.util.CommandCode;
//import java.util.Date;
//
///**
// *
// * @author Moisés
// */
//public class Teste  {
//
//    public static void main(String[] args) {
//        Equipamento e = new Equipamento();
//
//        e.setDataAmostra(new Date());
//        e.setId(
//                new EquipamentoId()
//                .setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO)
//                .setStatusEquipamento(new Byte("0"))
//                .setValorSensorReferencia(46.0)
//        );
//        
//        new EquipamentoController().save(e);
//        Arduino a = new Arduino("COM3");
//        a.lerPorta();
//        a.salvarDadosNoBanco();
//        a.write(CommandCode.LIGAR_AR_CONDICIONADO);
//    }
//
//}

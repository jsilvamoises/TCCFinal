/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.serialport;

import cc.unip.tccfinal.util.CacheLeitura;
import javax.swing.JButton;


/**
 *
 * @author MOISES
 */
public class Arduino {

    private JavaSerialPort javaSerialPort;
    /*
     ############################################################################
     #             METODO CONSTRUTOR QUE RECEBER UMA PORTA POR PARAM            #
     ############################################################################
     */

    public Arduino(String porta) {
        javaSerialPort = JavaSerialPort.getInstance();
        javaSerialPort.setPort(porta, 9600);
    }
    /*
     ############################################################################
     #             METODO CONSTRUTOR QUE RECEBER UMA PORTA / FREQUENCIA         #
     ############################################################################
     */

    public Arduino(String porta, int frequencia) {
        javaSerialPort = JavaSerialPort.getInstance();
        javaSerialPort.setPort(porta, frequencia);
    }
    /*
     ############################################################################
     #             INDICA QUE OS VALORES COLETADOS É PARA SER PERSISTIDO        #
     ############################################################################
     */

    public void salvarDadosNoBanco() {
        CacheLeitura.getInstance().setIsSaving(true);
    }
    /*
     ############################################################################
     #         INDICA QUE OS VALORES COLETADOS NÃO É PARA SER PERSISTIDO        #
     ############################################################################
     */

    public void naoSalvarDadosNoBanco() {
        CacheLeitura.getInstance().setIsSaving(false);
    }

    public void pararDeEnviarDados() {
        javaSerialPort.closeOut();
    }

    public void pararDeReceberDados() {
        try {
            javaSerialPort.pararLeitura();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    

    public void lerPorta() {        
        javaSerialPort.iniciarLeitura();
    }

    public void escreverNaPorta(byte valor) {
        javaSerialPort.enviaDados(valor);
    }

    public void escreverNaPorta(int valor) {
        javaSerialPort.enviaDados(valor);
    }

    public void comunicacaoArduino(JButton button) {

        if ("Ligar".equals(button.getActionCommand())) {
            javaSerialPort.enviaDados(131);
            System.out.println(button.getText());//Imprime o nome do botão pressionado
        } else if ("Desligar".equals(button.getActionCommand())) {
            javaSerialPort.enviaDados(130);
            System.out.println(button.getText());//Imprime o nome do botão pressionado
        } else if ("Ler".equals(button.getActionCommand())) {
            javaSerialPort.iniciarLeitura();
            System.out.println("*********");
            System.out.println(button.getText() + "\n ");//Imprime o nome do botão pressionado
        } else {
            javaSerialPort.closeOut();
            System.out.println(button.getText());//Imprime o nome do botão pressionado
        }
    }

    
    public void write(int porta) {
        try {
            javaSerialPort.enviaDados(porta);
        } catch (Exception e) {
             throw new IllegalArgumentException("O valor passada não é válido!!!!", e);
        }
    }

    
}

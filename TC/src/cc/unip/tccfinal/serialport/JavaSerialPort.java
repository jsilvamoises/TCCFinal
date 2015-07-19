/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.serialport;

import cc.unip.tccfinal.util.GSONConverter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

/**
 *
 * @author MOISES
 */
public class JavaSerialPort implements SerialPortEventListener {

    String linha = "";
    private String serialIn;
    private OutputStream serialOut;
    private int taxa;
    private String portaCom;
    boolean isIniciado;
    private InputStream input;
    private Map<String, String> mapaPortas = new HashMap<>();
    SerialPort port;
    private static JavaSerialPort instance;

    public static JavaSerialPort getInstance() {
        return instance == null ? instance = new JavaSerialPort() : instance;
    }
    /*CONSTRUTOR PRIVADO*/

    private JavaSerialPort() {
    }

    public void setPort(String portaCom, int taxa) {
        try {
            if (!mapaPortas.containsKey(portaCom)) {
                this.taxa = taxa;
                this.portaCom = portaCom;
                mapaPortas.put(portaCom, portaCom);
                this.initialize();
            }
        } catch (Exception e) {
            System.out.println("Porta de comunicação em uso!!!!!");
        }
    }

    /**
     * INICIA UM CONEXÃO COM A PORTA
     */
    private void initialize() {

        try {
            port = new SerialPort(portaCom);
            port.openPort();
            port.setParams(
                    this.taxa,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR + SerialPort.MASK_BREAK;//Prepare mask
            port.setEventsMask(mask);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Porta com não encontrada!!!!");
            alert.setHeaderText("A porta " + portaCom + " não foi encontrada");
            alert.setContentText("Verifique se o dispositivo está conectado." + e);
            alert.showAndWait();
        }

    }

    /**
     * Método que fecha a comunicação com a porta serial
     */
    public void closeOut() {
        try {
            serialOut.close();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível fechar porta COM. >> erro::" + e, ButtonType.OK).showAndWait();
        }
    }

    public void closeIn() {
        try {
            // serialIn.close();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível fechar porta COM. >> erro::" + e, ButtonType.OK).showAndWait();
        }
    }

    /**
     * @param opcao - Valor a ser enviado pela porta Serial
     */
    public void enviaDados(int opcao) {
        try {
            port.writeInt(opcao);
            // serialOut.write(opcao);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível enviar o dado. >> erro::" + e, ButtonType.OK).showAndWait();
        }
    }

    public void enviaDados(byte opcao) {
        try {
            port.writeByte(opcao);
            // serialOut.write(opcao);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível enviar o dado. >> erro::" + e, ButtonType.OK).showAndWait();
        }
    }

    public void pararLeitura() {
        System.out.println(">>Interrompendo leitura da porta " + portaCom);
        try {
            port.removeEventListener();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível parar a leitura da porta. >> erro::" + e, ButtonType.OK).showAndWait();
        }
    }

    public void iniciarLeitura() {
        try {
            if (portaCom.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Não foi possível detectar uma porta para se conectar >> erro::", ButtonType.OK).showAndWait();
            } else {
                System.out.println("Iniciado leitura da porta " + portaCom);

                port.addEventListener(this);

                //Verificar se funciona assim
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    @Override
    public void serialEvent(SerialPortEvent spe) {

        switch (spe.getEventType()) {
            case SerialPortEvent.BREAK:
                break;
            case SerialPortEvent.CTS:
                break;
            case SerialPortEvent.DSR:
                break;
            case SerialPortEvent.RXCHAR: {
                try {
                    linha += new String(port.readBytes());
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (linha.contains("*")) {                    
                    processarString(linha.replace("*", ""));
                    linha = "";
                }

            }
            break;

            case SerialPortEvent.ERR:
                break;
            case SerialPortEvent.RING:
                break;
            case SerialPortEvent.RLSD:
                break;
            case SerialPortEvent.RXFLAG:
                break;
            case SerialPortEvent.TXEMPTY:
                break;

        }
    }

    private List<String> portas = new ArrayList<>();

    public List<String> getPortas() {
        try {
            String[] serialPortNames = SerialPortList.getPortNames();
            portas.clear();
            for (String s : serialPortNames) {
                portas.add(s);
            }

            return portas;
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, "Não foi possível ler a porta >>" + e, ButtonType.CLOSE).show();
            return null;
        }

    }

    public void processarString(String s) {
        if (s.contains("SENSOR#")) {
            s = s.replace("SENSOR#", "");
            GSONConverter.getInstance().transformToObject(s, GSONConverter.ObjectType.SENSOR);
        } else if (s.contains("STATUS#")) {
            s = s.replace("STATUS#", "");
            GSONConverter.getInstance().transformToObject(s, GSONConverter.ObjectType.STATUS_EQUIPAMENTO);

        }

    }

}

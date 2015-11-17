/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.util;

import cc.unip.tccfinal.fxml.controller.EquipamentoController;
import cc.unip.tccfinal.fxml.model.Equipamento;
import cc.unip.tccfinal.fxml.model.IdEquipamento;
import cc.unip.tccfinal.fxml.model.Sensor;
import java.util.Calendar;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author Moises
 */
public class GeradorDeAmostras {

    private static final int SENSOR_TEMPERATURA = 0;
    private static final int SENSOR_UMIDADE = 1;
    private static final int SENSOR_LUMINOSIDADE = 2;

    public int totalAmostra = 0;   

    //AR CONDICIONADO
    private static final int MIN_AR_ON = 36;
    private static final int MAX_AR_ON = 45;
    private static final int MIN_AR_OFF = 10;
    private static final int MAX_AR_OFF = 22;
    //AQUECEDOR
    private static final int MIN_AQ_ON = 10;
    private static final int MAX_AQ_ON = 21;
    private static final int MIN_AQ_OFF = 25;
    private static final int MAX_AQ_OFF = 40;
    //AR UMIDIFICADOR
    private static final int MIN_UMI_ON = 30;
    private static final int MAX_UMI_ON = 50;
    private static final int MIN_UMI_OFF = 60;
    private static final int MAX_UMI_OFF = 80;
    //ILUMINACAO
    private static final int MIN_ILU_ON = 10;
    private static final int MAX_ILU_ON = 55;
    private static final int MIN_ILU_OFF = 60;
    private static final int MAX_ILU_OFF = 80;

    protected static Random random = new Random();
    
    private  ProgressBar progress;
    
    public static  double porcentagemConclusao;
    public static boolean concluido= false;
    
    public  GeradorDeAmostras(int totalAmostra) {
        this.totalAmostra = totalAmostra;
    }
    
    public GeradorDeAmostras(int totalAmostra, ProgressBar progress) {
        this.totalAmostra = totalAmostra;
        this.progress = progress;
        this.progress.setVisible(true);
       
        
    }

    public static double randomInRange(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }
    
    
    

    public static void main(String[] args) {
            System.gc();
            new GeradorDeAmostras(1000).gerarAmostras();
            
            System.gc();
 
        System.exit(0);
    }
    
    private void atualizarProgress(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(!concluido){
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }
                    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(porcentagemConclusao);
                         System.out.println(porcentagemConclusao);
                    }
                });
                }
                
               
               
            }
        }).start();
    }

    public void gerarAmostras() {
        concluido = false;
        porcentagemConclusao = 0;
        ///atualizarProgress();
        for (int i = 0; i < totalAmostra; i++) {
            
            porcentagemConclusao = (Double.parseDouble(String.valueOf(i))/Double.parseDouble(String.valueOf(totalAmostra)));
            
            Sensor aquecedorOn = getSensor(IdEquipamento.ID_AQUECEDOR, 1);
            Sensor aquecedorOff = getSensor(IdEquipamento.ID_AQUECEDOR, 0);

            Equipamento e[] = new Equipamento[8];

            e[1] = sensorToEquipamento(aquecedorOn);
            e[2] = sensorToEquipamento(aquecedorOff);
            double a = randomInRange(MIN_AQ_OFF, MAX_AQ_OFF);
            //Aquecedor desligado
            new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_AQUECEDOR).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_AQUECEDOR))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_AQ_OFF, MAX_AQ_OFF))).setValorSensorReferencia(randomInRange(MIN_AQ_OFF, MAX_AQ_OFF)));
            //Aquecedor Ligado
             new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_AQUECEDOR).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_AQUECEDOR))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_AQ_ON, MAX_AQ_ON))).setValorSensorReferencia(randomInRange(MIN_AQ_ON, MAX_AQ_ON)));
             
             //Ar condicionado desligado
            new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_AR_CONDICIONADO))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_AR_OFF, MAX_AR_OFF))).setValorSensorReferencia(randomInRange(MIN_AR_OFF, MAX_AR_OFF)));
            //Ar condicionado  Ligado
             new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_AR_CONDICIONADO))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_AR_ON, MAX_AR_ON))).setValorSensorReferencia(randomInRange(MIN_AR_ON, MAX_AR_ON)));
             
              //Umidificador desligado
            new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_UMIDIFICADOR).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_UMIDIFICADOR))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_UMI_OFF, MAX_UMI_OFF))).setValorSensorReferencia(randomInRange(MIN_UMI_OFF, MAX_UMI_OFF)));
            //Umidificador  Ligado
             new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_UMIDIFICADOR).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_UMIDIFICADOR))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_UMI_ON, MAX_UMI_ON))).setValorSensorReferencia(randomInRange(MIN_UMI_ON, MAX_UMI_ON)));
             
             
               //Iluminacao desligado
            new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_ILUMINACAO).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_ILUMINACAO))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_ILU_OFF, MAX_ILU_OFF))).setValorSensorReferencia(randomInRange(MIN_ILU_OFF, MAX_ILU_OFF)));
            //Iluminacao  Ligado
             new EquipamentoController().save(new Equipamento().setAno(
                    DataAtual.getAno()).setDataAmostra(Calendar.getInstance().getTime()).setDia(DataAtual.getDia())
                    .setHora(DataAtual.getHora()).setIdEquipamento(IdEquipamento.ID_ILUMINACAO).setMes(DataAtual.getMes())
                    .setMinuto(DataAtual.getMinuto()).setNomeEquipamento(getNomeEquipamento(IdEquipamento.ID_ILUMINACAO))
                    .setStatusEquipamento(getStatusAquecedor(randomInRange(MIN_ILU_ON, MAX_ILU_ON))).setValorSensorReferencia(randomInRange(MIN_ILU_ON, MAX_ILU_ON)));

           
        }
        
        concluido = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new Alert(Alert.AlertType.INFORMATION, "Concluído!!!!").showAndWait();
            }
        });
        
                
    }

    public Equipamento sensorToEquipamento(Sensor sensor) {
        return new Equipamento().setAno(sensor.getAno()).setDataAmostra(sensor.getHoraAmostra()).setDia(sensor.getDia())
                .setHora(sensor.getHora()).setIdEquipamento(sensor.getIdEquipamento()).setMes(sensor.getMes())
                .setMinuto(sensor.getMin()).setNomeEquipamento(getNomeEquipamento(sensor.getIdEquipamento()))
                .setStatusEquipamento(getStatusEquipamento(sensor.getIdEquipamento(), getValorSensor(sensor, sensor.getIdEquipamento())))
                .setValorSensorReferencia(getValorSensor(sensor, sensor.getIdEquipamento()));
    }

    private Sensor getSensor(double idEquipamento, int onOff) {
        double valorTemperatura = onOff == 0 ? randomInRange(MIN_AR_OFF, MAX_AR_OFF) : randomInRange(MIN_AR_ON, MAX_AR_ON);
        double valorUmidade = onOff == 0 ? randomInRange(MIN_UMI_OFF, MAX_UMI_OFF) : randomInRange(MIN_UMI_ON, MAX_UMI_ON);
        double valorLuminosidade = onOff == 0 ? randomInRange(MIN_ILU_OFF, MAX_ILU_OFF) : randomInRange(MIN_ILU_ON, MAX_ILU_ON);
        Sensor sensor = new Sensor();
        sensor
                .setAno(DataAtual.getAno())
                .setDia(DataAtual.getDia())
                .setHora(DataAtual.getHora())
                .setMes(DataAtual.getMes())
                .setMin(DataAtual.getMinuto())
                .setHoraAmostra(Calendar.getInstance().getTime())
                .setIdEquipamento(idEquipamento)
                .setLuminosidade(valorLuminosidade)
                .setUmidade(valorUmidade)
                .setTemperatura(valorTemperatura)
                .setStatusAquecedor(getStatusAquecedor(valorTemperatura))
                .setStatusArcondicionado(getStatusArcondicionado(valorTemperatura))
                .setStatusIluminacao(getStatusIluminacao(valorLuminosidade))
                .setStatusUmidificador(getStatusUmidificador(valorUmidade));

        return sensor;
    }

    private double getValorSensor(Sensor sensor, double idEquipamento) {
        if (IdEquipamento.ID_AQUECEDOR == idEquipamento) {
            return sensor.getTemperatura();
        } else if (IdEquipamento.ID_AR_CONDICIONADO == idEquipamento) {
            return sensor.getTemperatura();
        }
        if (IdEquipamento.ID_ILUMINACAO == idEquipamento) {
            return sensor.getLuminosidade();
        }
        if (IdEquipamento.ID_UMIDIFICADOR == idEquipamento) {
            return sensor.getUmidade();
        }
        return 0;
    }

    private byte getStatusEquipamento(double idEquipamento, double valorSensorRef) {
        if (IdEquipamento.ID_AQUECEDOR == idEquipamento) {
            getStatusAquecedor(valorSensorRef);
        } else if (IdEquipamento.ID_AR_CONDICIONADO == idEquipamento) {
            getStatusArcondicionado(valorSensorRef);
        } else if (IdEquipamento.ID_ILUMINACAO == idEquipamento) {
            getStatusIluminacao(valorSensorRef);
        } else if (IdEquipamento.ID_UMIDIFICADOR == idEquipamento) {
            getStatusUmidificador(valorSensorRef);
        }
        return 0;
    }

    private byte getStatusAquecedor(double temperatura) {
        return (byte) (temperatura < 21 ? 1 : 0);
    }

    private byte getStatusArcondicionado(double temperatura) {
        return (byte) (temperatura < 22 ? 0 : 1);
    }

    private byte getStatusIluminacao(double luminosidade) {
        return (byte) (luminosidade < 56 ? 1 : 0);
    }

    private byte getStatusUmidificador(double umidade) {
        return (byte) (umidade <= 55 ? 1 : 0);
    }

    private String getNomeEquipamento(double idEquipamento) {
        if (idEquipamento == IdEquipamento.ID_AQUECEDOR) {
            return "AQUECEDOR";
        } else if (idEquipamento == IdEquipamento.ID_AR_CONDICIONADO) {
            return "AR CODICIONADO";
        }
        if (idEquipamento == IdEquipamento.ID_ILUMINACAO) {
            return "ILUMINACAO";
        }
        if (idEquipamento == IdEquipamento.ID_UMIDIFICADOR) {
            return "UMIDIFICADOR";
        }
        return "";
    }

}

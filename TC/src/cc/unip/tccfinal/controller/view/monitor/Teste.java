///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.controller.view.monitor;
//
//import cc.unip.tccfinal.controller.IdEquipamento;
//import cc.unip.tccfinal.model.Equipamento;
//import cc.unip.tccfinal.model.EquipamentoId;
//import cc.unip.tccfinal.rede.RedeMLPExe;
//import cc.unip.tccfinal.util.HibernateUtil;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//
//import org.hibernate.Session;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Property;
//
//
///**
// *
// * @author Moisés
// */
//public class Teste {
//
//    double CONJUNTO_CLASSIFICAR[] = new double[8];
//
//    private static final int DIVIDIR_POR_10 = 100;
//    private static final int DIVIDIR_POR_100 = 100;
//
//    private DecimalFormat formato = new DecimalFormat("#0.000");
//    private double MATRIZ_DADOS[][];
//    private double RESULTADO_ESPERADO[];
//    private static final double BIAS = 1.0;
//    private int totalColunas = 0;
//    private RedeMLPExe rede;
//
//    private List<Equipamento> listagem = new ArrayList<>();
//
//    public static void main(String[] args) {
//        // new Teste().consultarDados();
//        // new Teste().buscarDados();
//        new Teste().consultaJPQL();
//
//        System.exit(0);
//    }
//
//    private void treinarRede() {
//        rede = RedeMLPExe.getInstance()
//                .setCONJUNTO_TREINAMENTO(MATRIZ_DADOS)
//                .setQuantidadeNeuronioPrimeiraCamada(3)
//                .setQuantidadequantidadeNeuroniosEntrada(3)
//                .setRESULTADOS_ESPERADOS(RESULTADO_ESPERADO)
//                .treinarRede();
//    }
//
//    private void classificar(double[] amostras) {
//        CONJUNTO_CLASSIFICAR[0] = IdEquipamento.ID_AR_CONDICIONADO;
//        CONJUNTO_CLASSIFICAR[1] = 0.070;
//        CONJUNTO_CLASSIFICAR[2] = 0.180;
//        CONJUNTO_CLASSIFICAR[3] = 0.280;
//        CONJUNTO_CLASSIFICAR[4] = 0.230;
//        CONJUNTO_CLASSIFICAR[5] = 0.660;
//        CONJUNTO_CLASSIFICAR[0] = 1.00;
//        CONJUNTO_CLASSIFICAR[0] = 1.00;
//     
//        rede  = RedeMLPExe.getInstance();
//
//    System.out.println (
//            
//
//"Valor da resposta do treinamento:: "+rede.classificar(CONJUNTO_CLASSIFICAR));
//    }
//
//    private void consultarDados() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.getTransaction().begin();
//        
//
//
//
//try {
//            Criteria cri = session.createCriteria(Dado.class
//
//);
//            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            listagem = cri.list();
//            System.out.println("Tamanho da lista:: " + listagem.size());
//
//        } catch (Exception e) {
//        } finally {
//            session.getTransaction().commit();
//        }
//
//        transformDadosToMatriz(listagem);
//        imprimirMatriz();
//        treinarRede();
//        // imprimirResultadosEsperados();
//    }
//
//    public void transformDadosToMatriz(List<Dado> dados) {
//        int colunaAtual = 0;
//        // Calendar mes = Calendar.getInstance();
//        //MATRIZ_DADOS = new double[dados.size()][8];
//        MATRIZ_DADOS = new double[8][dados.size() * 3];//MULTIPLICA POR TRES PORQUE ESTAREMOS GERANTO TREZ MODELOS DE DADOS
//        RESULTADO_ESPERADO = new double[dados.size() * 3]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS
//        // linha coluna
//        for (int i = 0; i < dados.size() - 1; i += 2) {
//            /*
//            
//             */
//            //MONTANDO MATRIZ TRANSPOSTA AQUECEDOR :: ADICIONA NA PRIMEIRA COLUNA DA MATRIZ
//            MATRIZ_DADOS[0][colunaAtual] = EquipamentoId.AQUECEDOR_ID; // ID DO EQUIPAMENTO :: AQUECEDOR
//            //MATRIZ_DADOS[1][colunaAtual] = (dados.get(i).getMes()) / 10;//mes.get(Calendar.MONTH) / 10;
//            //MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//            MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getLuminosidade() / 100;
//            MATRIZ_DADOS[3][colunaAtual] = dados.get(i).getTemperatura() / 100;
//            MATRIZ_DADOS[4][colunaAtual] = dados.get(i).getUmidade() / 100;
//            MATRIZ_DADOS[5][colunaAtual] = dados.get(i).getStatusGeral();
//            MATRIZ_DADOS[6][colunaAtual] = BIAS;
//            RESULTADO_ESPERADO[colunaAtual] = dados.get(i).getStatusAquecedor();
//
//            //MONTANDO MATRIZ TRANSPOSTA AR CONDICIONADO :: ADICIONA NA SEGUNDA COLUNA DA MATRIZ
//            MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.AR_CONDICIONADO_ID; // ID DO EQUIPAMENTO :: AR CONDICIONADO
//            MATRIZ_DADOS[1][colunaAtual] = dados.get(i).getMes() / 10;//mes.get(Calendar.MONTH) / 10;
//            MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//            MATRIZ_DADOS[3][colunaAtual] = dados.get(i).getLuminosidade() / 100;
//            MATRIZ_DADOS[4][colunaAtual] = dados.get(i).getTemperatura() / 100;
//            MATRIZ_DADOS[5][colunaAtual] = dados.get(i).getUmidade() / 100;
//            MATRIZ_DADOS[6][colunaAtual] = dados.get(i).getStatusGeral();
//            MATRIZ_DADOS[7][colunaAtual] = BIAS;
//            RESULTADO_ESPERADO[colunaAtual] = dados.get(i).getStatusArcondicionado();
//
//            //MONTANDO MATRIZ TRANSPOSTA AR CONDICIONADO :: ADICIONA NA SEGUNDA COLUNA DA MATRIZ
//            MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.ILUMINACAO_ID; // ID DO EQUIPAMENTO :: ILUMINAÇAO
//            MATRIZ_DADOS[1][colunaAtual] = dados.get(i).getMes() / 10;//mes.get(Calendar.MONTH) / 10;
//            MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//            MATRIZ_DADOS[3][colunaAtual] = dados.get(i).getLuminosidade() / 100;
//            MATRIZ_DADOS[4][colunaAtual] = dados.get(i).getTemperatura() / 100;
//            MATRIZ_DADOS[5][colunaAtual] = dados.get(i).getUmidade() / 100;
//            MATRIZ_DADOS[6][colunaAtual] = dados.get(i).getStatusGeral();
//            MATRIZ_DADOS[7][colunaAtual] = BIAS;
//            RESULTADO_ESPERADO[colunaAtual] = dados.get(i).getStatusIluminacao();
//            // }
//            ++colunaAtual;
//            totalColunas += 3;
//            
//            
//        }
//    }
//
//    public void imprimirMatriz() {
////        for(int i = 0 ; i <= MATRIZ_DADOS.length-1;i++){
////            for(int j= 0; j<8;j++){
////                System.out.print(" | "+MATRIZ_DADOS[i][j]+" | ");
////            }
////            //System.out.print(" | "+RESULTADO_ESPERADO[i]+" | ");
////            System.out.println("\n");
////        }
//        for (double[] MATRIZ_DADOS1 : MATRIZ_DADOS) {
//            for (int j = 0; j < totalColunas; j++) {
//                System.out.print(" | " + formato.format(MATRIZ_DADOS1[j]) + " | ");
//            }
//            //System.out.print(" | "+RESULTADO_ESPERADO[i]+" | ");
//            System.out.println("\n");
//        }
//    }
//
//    public void imprimirResultadosEsperados() {
//        System.out.println("IMPRIMINDO RESULTADOS ESPERADOS");
//        for (double RESULTADO_ESPERADO1 : RESULTADO_ESPERADO) {
//            System.out.println(RESULTADO_ESPERADO1);
//        }
//    }
//
//    public List buscarDados() {
//        /*
//            MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.AR_CONDICIONADO_ID; // ID DO EQUIPAMENTO :: AR CONDICIONADO
//            MATRIZ_DADOS[1][colunaAtual] = dados.get(i).getMes()/10;//mes.get(Calendar.MONTH) / 10;
//            MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getDia()/100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//            MATRIZ_DADOS[3][colunaAtual] = dados.get(i).getLuminosidade() / 100;
//            MATRIZ_DADOS[4][colunaAtual] = dados.get(i).getTemperatura() / 100;
//            MATRIZ_DADOS[5][colunaAtual] = dados.get(i).getUmidade() / 100;
//            MATRIZ_DADOS[6][colunaAtual] = dados.get(i).getStatusGeral();
//            MATRIZ_DADOS[7][colunaAtual] = BIAS;
//            RESULTADO_ESPERADO[colunaAtual] = dados.get(i).getStatusArcondicionado
//        
//        private Date dataColeta;
//    private float temperatura;
//    private float umidade;
//    private float luminosidade;    
//    private String identificador; *      
//    private byte statusIluminacao;*
//    private byte statusAlarmeIncencio;
//    private byte statusAquecedor;*
//    private byte statusArcondicionado;*
//    private byte statusGeral;*
//    private float dia*
//    private float mes*
//         */
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.getTransaction().begin();
//        Criteria criteria;
//        List<Object[]> objetos = new ArrayList<>();
//        
//
//
//
//try {
//            criteria = session.createCriteria(Dado.class
//
//)
//                    .setProjection(Projections.projectionList()
//                            .add(Property.forName("mes"))
//                            .add(Property.forName("dia"))
//                            .add(Property.forName("luminosidade"))
//                            .add(Property.forName("temperatura"))
//                            .add(Property.forName("umidade"))
//                            .add(Property.forName("statusGeral"))
//                            .add(Property.forName("statusArcondicionado"))
//                            .add(Property.forName("statusAquecedor"))
//                            .add(Property.forName("statusIluminacao"))
//                    )
//                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) //.setResultTransformer(new AliasToBeanResultTransformer(DadoFilter.class))
//                    ;
//            objetos = criteria.list();
//
////            for(Iterator i = objetos.iterator(); i.hasNext();){
////                DadoFilter d = (DadoFilter) i.next();
////                System.out.println(d.getDia());
////            }
//            for (Object[] o : objetos) {
//                System.out.println(o[0]);
//            }
//
//            System.out.println(objetos.size());
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            session.getTransaction().commit();
//        }
//        return objetos;
//    }
//
//    private void consultaJPQL() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.getTransaction().begin();
//        /*
//         MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.AR_CONDICIONADO_ID; // ID DO EQUIPAMENTO :: AR CONDICIONADO
//            MATRIZ_DADOS[1][colunaAtual] = dados.get(i).getMes()/10;//mes.get(Calendar.MONTH) / 10;
//            MATRIZ_DADOS[2][colunaAtual] = dados.get(i).getDia()/100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//            MATRIZ_DADOS[3][colunaAtual] = dados.get(i).getLuminosidade() / 100;
//            MATRIZ_DADOS[4][colunaAtual] = dados.get(i).getTemperatura() / 100;
//            MATRIZ_DADOS[5][colunaAtual] = dados.get(i).getUmidade() / 100;
//            MATRIZ_DADOS[6][colunaAtual] = dados.get(i).getStatusGeral();
//            MATRIZ_DADOS[7][colunaAtual] = BIAS;
//            RESULTADO_ESPERADO[colunaAtual] = dados.get(i).getStatusArcondicionado
//         */
//        try {
//            /*
//            ARRAY[0] = CHAVE DISTINCT
//            ARRAY[1] = MES
//            ARRAY[2] = DIA
//            ARRAY[3] = LUMINOSIDADE
//            ARRAY[4] = TEMPERATURA
//            ARRAY[5] = UMIDADE
//            ARRAY[6] = STATUS GERAL
//            ARRAY[7] = STATUS AQUECEDOR
//            ARRAY[8] = STATUS AR CONDICIONADO
//            ARRAY[9] = STATUS ILUMINACAO
//             */
//            Query q = session.createQuery("SELECT DISTINCT(CONCAT(D.mes,D.dia,D.luminosidade,D.temperatura,D.umidade,D.statusGeral,D.statusAquecedor,D.statusArcondicionado,D.statusIluminacao)), D.mes,D.dia,D.luminosidade,D.temperatura,D.umidade,D.statusGeral,D.statusAquecedor,D.statusArcondicionado,D.statusIluminacao FROM Dado D");
//            List<Object[]> objects = q.list();
//            System.out.println(objects.size());
//            MATRIZ_DADOS = new double[8][objects.size() * 3];//MULTIPLICA POR TRES PORQUE ESTAREMOS GERANTO TREZ MODELOS DE DADOS
//            RESULTADO_ESPERADO = new double[objects.size() * 3]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS
//            for (Object[] o : objects) {
//                System.out.println(o[0]);
//            }
//            int colunaAtual = 0;
//
//            for (int i = 0; i < objects.size(); i++) {
//                Object[] o = objects.get(i);
//                //MONTANDO MATRIZ TRANSPOSTA AQUECEDOR :: ADICIONA NA PRIMEIRA COLUNA DA MATRIZ
//                MATRIZ_DADOS[0][colunaAtual] = EquipamentoId.AQUECEDOR_ID; // ID DO EQUIPAMENTO :: AQUECEDOR
//                MATRIZ_DADOS[1][colunaAtual] = (float) o[1]/100 ;//(dados.get(i).getMes()) / 10;//mes.get(Calendar.MONTH) / 10;
//                MATRIZ_DADOS[2][colunaAtual] = (float) o[2]/100 ;//dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//                MATRIZ_DADOS[3][colunaAtual] = (float) o[3]/100 ;//dados.get(i).getLuminosidade() / 100;
//                MATRIZ_DADOS[4][colunaAtual] = (float) o[4]/100 ;//dados.get(i).getTemperatura() / 100;
//                MATRIZ_DADOS[5][colunaAtual] = (float) o[5]/100 ;//dados.get(i).getUmidade() / 100;
//                MATRIZ_DADOS[6][colunaAtual] = (byte) o[6];//dados.get(i).getStatusGeral();
//                MATRIZ_DADOS[7][colunaAtual] = BIAS;
//                RESULTADO_ESPERADO[colunaAtual] = (byte) o[7];//dados.get(i).getStatusAquecedor();
//
//                //MONTANDO MATRIZ TRANSPOSTA AR CONDICIONADO :: ADICIONA NA SEGUNDA COLUNA DA MATRIZ
//                MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.AR_CONDICIONADO_ID; // ID DO EQUIPAMENTO :: AR CONDICIONADO
//                MATRIZ_DADOS[1][colunaAtual] = (float) o[1]/100 ;//dados.get(i).getMes() / 10;//mes.get(Calendar.MONTH) / 10;
//                MATRIZ_DADOS[2][colunaAtual] = (float) o[2]/100 ;//dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//                MATRIZ_DADOS[3][colunaAtual] = (float) o[3]/100;//dados.get(i).getLuminosidade() / 100;
//                MATRIZ_DADOS[4][colunaAtual] = (float) o[4]/100;//dados.get(i).getTemperatura() / 100;
//                MATRIZ_DADOS[5][colunaAtual] = (float) o[5]/100;//dados.get(i).getUmidade() / 100;
//                MATRIZ_DADOS[6][colunaAtual] = (byte) o[6];//dados.get(i).getStatusGeral();
//                MATRIZ_DADOS[7][colunaAtual] = BIAS;
//                RESULTADO_ESPERADO[colunaAtual] = (byte) o[8];//dados.get(i).getStatusArcondicionado();
//
//                //MONTANDO MATRIZ TRANSPOSTA AR CONDICIONADO :: ADICIONA NA SEGUNDA COLUNA DA MATRIZ
//                MATRIZ_DADOS[0][++colunaAtual] = EquipamentoId.ILUMINACAO_ID; // ID DO EQUIPAMENTO :: ILUMINAÇAO
//                MATRIZ_DADOS[1][colunaAtual] = (float) o[1]/100;//dados.get(i).getMes() / 10;//mes.get(Calendar.MONTH) / 10;
//                MATRIZ_DADOS[2][colunaAtual] = (float) o[2]/100;//dados.get(i).getDia() / 100;//mes.get(Calendar.DAY_OF_MONTH) / 10;
//                MATRIZ_DADOS[3][colunaAtual] = (float) o[3]/100;//dados.get(i).getLuminosidade() / 100;
//                MATRIZ_DADOS[4][colunaAtual] = (float) o[4]/100;//dados.get(i).getTemperatura() / 100;
//                MATRIZ_DADOS[5][colunaAtual] = (float) o[5]/100;//dados.get(i).getUmidade() / 100;
//                MATRIZ_DADOS[6][colunaAtual] = (byte) o[6];//dados.get(i).getStatusGeral();
//                MATRIZ_DADOS[7][colunaAtual] = BIAS;
//                RESULTADO_ESPERADO[colunaAtual] = (byte) o[9];//dados.get(i).getStatusIluminacao();
//
//                ++colunaAtual;
//                totalColunas += 3;
//                
//                
//            }
//
//           imprimirMatriz();
//            imprimirResultadosEsperados();
//            System.out.println("TREINANDO REDE");
//            treinarRede();
//            System.out.println("IMPRIMINDO RESULTADOS ANTES DO TREINO");
//            classificar(CONJUNTO_CLASSIFICAR);
//            
//            
//            System.out.println("IMPRIMINDO RESULTADOS APOS O TREINO");
//            classificar(CONJUNTO_CLASSIFICAR);
//            
//            System.out.println("IMPRIMINDO DE COPIA");
//            classificar(CONJUNTO_CLASSIFICAR);
//
//        } catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//    }
//
//    public float padronizarValor(Float f, int op) {
//        switch (op) {
//            case 10:
//                return f / 10;
//            case 100:
//                return f / 100;
//        }
//        return f;
//
//    }
//}

package cc.unip.tccfinal.fxml.rede.estrutura;

import cc.unip.tccfinal.fxml.rede.objeto.TreinoVO;



public class Camada {

    protected Neuronio neuronios[];

    public Camada(Integer nrNeuronios, Integer nrConexoesEntrada) {
        this.neuronios = new Neuronio[nrNeuronios];
        this.inicializarNeuronios(nrConexoesEntrada);
    }

    protected void inicializarNeuronios(Integer nrConexoesEntrada) {
        for (int i = 0; i < neuronios.length; i++) {
            neuronios[i] = new Neuronio(nrConexoesEntrada);
        }
    }

    public Double[] propagarSinais(Double entradas[]) {
        Double saidas[] = new Double[neuronios.length];
        for (int i = 0; i < neuronios.length; i++) {
            saidas[i] = neuronios[i].propagarSinais(entradas);
        }
        return saidas;
    }

    public void retropropagarErro(TreinoVO treinoVO) {
        for (int i = 0; i < neuronios.length; i++) {
            for (int j = 0; j < neuronios[i].conexoes.length; j++) {
                neuronios[i].conexoes[j] += treinoVO.getFatorAdaptacao()
                        * treinoVO.getEntradas()[j] * treinoVO.getGradientes()[i];
            }
        }
    }

}

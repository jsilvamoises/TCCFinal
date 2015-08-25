package cc.unip.tccfinal.fxml.rede.treinamento;

import cc.unip.tccfinal.fxml.rede.estrutura.PerceptronMultiplasCamadas;



public class BackPropagation {

	private  Double FATOR_ADAPTACAO = 0.03;
	private  Double ERRO_MAXIMO = 0.05;
	private  Integer NR_MAXIMO_EPOCAS = 100000;

	protected PerceptronMultiplasCamadas mlp;
	private Integer nrEpocas;
	private Double erros[];

	public BackPropagation(Integer nrEntradas, Integer nrNeuroniosCamadaIntermediaria, Integer nrNeuroniosCamadaSaida) {
		this.mlp = new PerceptronMultiplasCamadas(nrEntradas, nrNeuroniosCamadaIntermediaria, nrNeuroniosCamadaSaida);
		this.iniciarVetorDeErros(nrNeuroniosCamadaSaida);
	}

	public void iniciarVetorDeErros(int nrNeuroniosCamadaSaida) {
		erros = new Double[nrNeuroniosCamadaSaida];
		for (int i = 0; i < erros.length; i++) {
			erros[i] = 1.0;
		}
	}

	public Double[] classificar(Double entradas[]) {
		Double saidas[] = mlp.propagarSinais(entradas);
		for (int i = 0; i < saidas.length; i++) {
			saidas[i] = new Double(Math.round(saidas[i].doubleValue()));
		}
		return saidas;
	}

	public void treinar(Double entradas[][], Double esperados[][]) {
		this.nrEpocas = 0;
		while (erroMaiorQueMinimo() && naoAtingiuMaximoDeEpocas()) {
			for (int i = 0; i < entradas[0].length; i++) {
				Double saidas[] = mlp.propagarSinais(entradas[i]);
				this.processarErros(esperados, saidas[i], i);
				Double gradientes[] = this.calcularGradientesDeRetropopagacao(saidas);
				mlp.retropropagarErro(entradas[i], gradientes, FATOR_ADAPTACAO);
			}
			this.nrEpocas++;
		}
	}

	private Double[] calcularGradientesDeRetropopagacao(Double saidas[]) {
		Double gradientes[] = new Double[saidas.length];
		for (int i = 0; i < gradientes.length; i++) {
			gradientes[i] = saidas[i] * (1 - saidas[i]) * erros[i];
		}
		return gradientes;
	}

	private boolean naoAtingiuMaximoDeEpocas() {
		return nrEpocas < NR_MAXIMO_EPOCAS;
	}

	private boolean erroMaiorQueMinimo() {
		Double eqm = this.calcularErroQuadraticoMedio(erros);
		return Math.abs(eqm) > ERRO_MAXIMO;
	}

	private Double calcularErroQuadraticoMedio(Double erros[]) {
		Double eqm = 0d;
		for (int i = 0; i < erros.length; i++) {
			eqm += (erros[i] * erros[i]);
		}
		return eqm / erros.length;
	}

	private void processarErros(Double[][] esperados, Double saida, int i) {
		for (int j = 0; j < esperados[i].length; j++) {
			erros[j] = esperados[i][j] - saida;
		}
	}

    public Double getFATOR_ADAPTACAO() {
        return FATOR_ADAPTACAO;
    }

    public BackPropagation setFATOR_ADAPTACAO(Double FATOR_ADAPTACAO) {
        this.FATOR_ADAPTACAO = FATOR_ADAPTACAO;
        return this;
    }

    public Double getERRO_MAXIMO() {
        return ERRO_MAXIMO;
    }

    public BackPropagation setERRO_MAXIMO(Double ERRO_MAXIMO) {
        this.ERRO_MAXIMO = ERRO_MAXIMO;
        return this;
    }

    public Integer getNR_MAXIMO_EPOCAS() {
        return NR_MAXIMO_EPOCAS;
    }

    public BackPropagation setNR_MAXIMO_EPOCAS(Integer NR_MAXIMO_EPOCAS) {
        this.NR_MAXIMO_EPOCAS = NR_MAXIMO_EPOCAS;
        return this;
    }

    public Integer getNrEpocas() {
        return nrEpocas;
    }

    public BackPropagation setNrEpocas(Integer nrEpocas) {
        this.nrEpocas = nrEpocas;
        return this;
    }

    public Double[] getErros() {
        return erros;
    }

    public BackPropagation setErros(Double[] erros) {
        this.erros = erros;
        return this;
    }
        
        
        
        

}
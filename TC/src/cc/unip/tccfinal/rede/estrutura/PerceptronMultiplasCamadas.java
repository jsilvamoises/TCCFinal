package cc.unip.tccfinal.rede.estrutura;

import cc.unip.tccfinal.rede.objeto.PojoTreino;



public class PerceptronMultiplasCamadas {

	protected CamadaIntermediaria camadaIntermediaria;
	protected Camada camadaSaida;

	public PerceptronMultiplasCamadas(Integer nrEntradas, Integer nrNeuroniosCamadaIntermediaria, Integer nrNeuroniosCamadaSaida) {
		this.camadaIntermediaria = new CamadaIntermediaria(nrNeuroniosCamadaIntermediaria, nrEntradas);
		this.camadaSaida = new Camada(nrNeuroniosCamadaSaida, nrNeuroniosCamadaIntermediaria);
	}

	public Double[] propagarSinais(Double entradas[]) {
		Double entradasUltimaCamada[] = this.camadaIntermediaria.propagarSinais(entradas);
		return this.camadaSaida.propagarSinais(entradasUltimaCamada);
	}

	public void retropropagarErro(Double[] entradas, Double[] gradientes, Double fatorAdaptacao) {
		Double entradasUltimaCamada[] = this.camadaIntermediaria.propagarSinais(entradas);
		PojoTreino pojoTreino = new PojoTreino(entradas, entradasUltimaCamada, gradientes, fatorAdaptacao);
		camadaSaida.retropropagarErro(pojoTreino);
		camadaIntermediaria.retropropagarErro(pojoTreino);
	}

}
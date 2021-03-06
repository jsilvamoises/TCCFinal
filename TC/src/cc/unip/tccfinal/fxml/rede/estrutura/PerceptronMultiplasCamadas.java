//package cc.unip.tccfinal.fxml.rede.estrutura;
//
//import cc.unip.tccfinal.fxml.rede.objeto.TreinoVO;
//
//
//
//public class PerceptronMultiplasCamadas {
//
//	protected CamadaIntermediaria camadaIntermediaria;
//	protected Camada camadaSaida;
//
//	public PerceptronMultiplasCamadas(Integer nrEntradas, Integer nrNeuroniosCamadaIntermediaria, Integer nrNeuroniosCamadaSaida) {
//		this.camadaIntermediaria = new CamadaIntermediaria(nrNeuroniosCamadaIntermediaria, nrEntradas);
//		this.camadaSaida = new Camada(nrNeuroniosCamadaSaida, nrNeuroniosCamadaIntermediaria);
//	}
//
//	public Double[] propagarSinais(Double entradas[]) {
//		Double entradasUltimaCamada[] = this.camadaIntermediaria.propagarSinais(entradas);
//		return this.camadaSaida.propagarSinais(entradasUltimaCamada);
//	}
//
//	public void retropropagarErro(Double[] entradas, Double[] gradientes, Double fatorAdaptacao) {
//		Double entradasUltimaCamada[] = this.camadaIntermediaria.propagarSinais(entradas);
//		TreinoVO pojoTreino = new TreinoVO(entradas, entradasUltimaCamada, gradientes, fatorAdaptacao);
//		camadaSaida.retropropagarErro(pojoTreino);
//		camadaIntermediaria.retropropagarErro(pojoTreino);
//	}
//
//}
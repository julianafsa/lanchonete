/**
 * 
 */
package lanches;

/**
 * @author juliana
 *
 */
public abstract class Lanche {
	
	protected double preco;
	
	/**
	 * Tempo de entrega do lanche por quilômetro (em minutos).
	 */
	protected double tempoEntregaPorKilometro = 10;
	
	protected static double tempoPreparo;

	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	protected void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the tempoEntregaPorKilometro
	 */
	public double getTempoEntregaPorKilometro() {
		return tempoEntregaPorKilometro;
	}

	/**
	 * @param tempoEntregaPorKilometro the tempoEntregaPorKilometro to set
	 */
	protected void setTempoEntregaPorKilometro(double tempoEntregaPorKilometro) {
		this.tempoEntregaPorKilometro = tempoEntregaPorKilometro;
	}

	/**
	 * @return the tempoPreparo
	 */
	public static double getTempoPreparo() {
		return tempoPreparo;
	}

	/**
	 * @param tempoPreparo the tempoPreparo to set
	 */
	protected static void setTempoPreparo(double tempoPreparo) {
		Lanche.tempoPreparo = tempoPreparo;
	}
	
	/**
	 * Imprime o resumo do pedido.
	 */
	protected abstract void imprimirResumo();

	/**
	 * Calcula o preço do lanche.
	 */
	protected abstract void calcularPreco();

	/**
	 * Compreende o tempo de preparo do lanche adicionado ao tempo de entrega de
	 * acordo com a distância do endereço de entrega para o restaurante.
	 * @param distancia Distância para o restaurante.
	 * @return Tempo de chegada.
	 */
	protected abstract double calcularTempoChegada(double distancia);
	
}

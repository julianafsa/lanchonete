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
	
	protected abstract void imprimirResumo();

	protected abstract void calcularPreco();

	protected abstract double calcularTempoChegada(double distancia);
	
}

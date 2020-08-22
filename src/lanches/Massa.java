/**
 * 
 */
package lanches;

/**
 * @author juliana
 *
 */
public abstract class Massa extends Lanche {
	
	protected String molho;
	
	/**
	 * Compreende o tempo de preparo mais o tempo de entrega.
	 */
	protected double tempoTotal;
	
	/**
	 * @return the tempoTotal
	 */
	public double getTempoTotal() {
		return tempoTotal;
	}

	/**
	 * @param tempoTotal the tempoTotal to set
	 */
	public void setTempoTotal(double tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

	/**
	 * Massas têm tempo de preparo de 30 minutos.
	 * Esse bloco é executado uma única vez quando a classe é carregada.
	 */
	static {
		tempoPreparo = 30;
	}
	
	/**
	 * @return the molho
	 */
	public String getMolho() {
		return molho;
	}

	/**
	 * @param molho the molho to set
	 */
	public void setMolho(String molho) {
		this.molho = molho;
	}

	@Override
	protected double calcularTempoChegada(double distancia) {
		tempoTotal = (super.getTempoEntregaPorKilometro() * distancia) + tempoPreparo;
		return tempoTotal;
	}	

}

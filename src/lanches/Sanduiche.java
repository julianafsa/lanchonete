/**
 * 
 */
package lanches;

import util.Message;

/**
 * @author juliana
 *
 */
public class Sanduiche extends Lanche {
	
	private static final int quantidadeMaximaIngredientes = 10;
	
	/**
	 * Compreende o tempo de preparo mais o tempo de entrega.
	 */
	private double tempoTotal;
	
	/**
	 * @return the quantidademaximaingredientes
	 */
	public static int getQuantidadeMaximaIngredientes() {
		return quantidadeMaximaIngredientes;
	}

	/**
	 * Sanduíches tem tempo de preparo de 15 minutos.
	 * Esse bloco é executado uma única vez quando a classe é carregada.
	 */
	static {
		tempoPreparo = 15;
	}	
	
	/**
	 * Vetor de no máximo 10 ingredientes do sanduíche.
	 */
	String[] ingredientes;

	/**
	 * @return the ingredientes
	 */
	public String[] getIngredientes() {
		return ingredientes;
	}

	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(String[] ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * @param ingredientes
	 */
	public Sanduiche() {
	}

	/**
	 * @param ingredientes
	 */
	public Sanduiche(String[] ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	/*
	 * Calcula o preço do sanduíche e define o preço.
	 */
	public void calcularPreco() {
		int quantidadeIngredientes = 0;
		if (ingredientes != null) {
			for (int i = 0; i < ingredientes.length; i++) {
				if (ingredientes[i] == null) {
					quantidadeIngredientes = i + 1;
					break;
				}	
			}
		}	
		super.setPreco(10 + quantidadeIngredientes * 0.10);
	}

	public double calcularTempoChegada(double distancia) {
		tempoTotal = (super.getTempoEntregaPorKilometro() * distancia) + tempoPreparo;
		return tempoTotal;
	}

	@Override
	protected void imprimirResumo() {
		Message message = Message.getInstancia();
		try {
			System.out.println("===================================================");
			String nome = message.getMessage("mensagem.resumo.pedido");
			String metade = "", borda = "";
			for (int i = 0; i < ((50 - nome.length())/2) ; i++)
				metade += " ";
			borda = metade + nome + metade;
			System.out.println(borda);
			System.out.println("___________________________________________________");
			System.out.println(message.getMessage("mensagem.sanduiche.resumo"));
			for (String ingrediente : ingredientes) {
				if (ingrediente != null)
					System.out.println("- " + ingrediente);
			}
			System.out.println("___________________________________________________");			
			System.out.println(message.getMessage("mensagem.preco") + this.getPreco());
			System.out.println(message.getMessage("mensagem.tempo.total") + this.tempoTotal);
			System.out.println("===================================================");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Erro ao carregar o seu pedido.");
		}
	}

}

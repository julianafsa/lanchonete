package lanches;

import java.util.List;
import java.util.ArrayList;
import util.Message;

/**
 * @author juliana
 *
 */
public class Bolo extends Lanche {
	
	/**
	 * Tipo de massa do bolo.
	 */
	private String massa;
	
	/**
	 * Tipo de recheio do bolo.
	 */
	private String recheio;
	
	/**
	 * Tipo de cobertura do bolo.
	 */
	private String cobertura;
	
	private static final double PRECO_BOLO_BASICO = 40;
	
	public static List<String> tiposDeMassa = null;
	public static List<String> tiposDeRecheio = null;
	public static List<String> tiposDeCobertura = null;
	
	/**
	 * @return the tiposDeMassa
	 */
	public static List<String> getTiposDeMassa() {
		if (tiposDeMassa == null)
			tiposDeMassa = Message.lerTipos("mensagem.bolo.massa.tipo");
		return tiposDeMassa;
	}
	
	/**
	 * @return the tiposDeMassa
	 */
	public static List<String> getTiposDeRecheio() {
		if (tiposDeRecheio == null)
			tiposDeRecheio = Message.lerTipos("mensagem.bolo.recheio.tipo");
		return tiposDeRecheio;
	}	

	/**
	 * @return the tiposDeMassa
	 */
	public static List<String> getTiposDeCobertura() {
		if (tiposDeCobertura == null)
			tiposDeCobertura = Message.lerTipos("mensagem.bolo.cobertura.tipo");
		return tiposDeCobertura;
	}		
	
	/**
	 * Cria uma instância de um bolo com um preço básico.
	 */
	public Bolo() {
		this.setPreco(PRECO_BOLO_BASICO);
	}	
	
	/**
	 * Cria a instância de um bolo com a massa, o recheio, a cobertura definidos.
	 * @param massa Tipo de massa do bolo.
	 * @param recheio Tipo de recheio do bolo.
	 * @param cobertura Tipo de cobertura do bolo.
	 */
	public Bolo(String massa, String recheio, String cobertura) {
		this.massa = massa;
		this.recheio = recheio;
		this.cobertura = cobertura;
		this.setPreco(PRECO_BOLO_BASICO);
	}

	/**
	 * Bolo têm tempo de preparo de 10 minutos.
	 * Esse bloco é executado uma única vez quando a classe é carregada.
	 */
	static {
		tempoPreparo = 10;
	}
	
	/**
	 * Compreende o tempo de preparo mais o tempo de entrega.
	 */
	protected double tempoTotal;
	
	/**
	 * Obtém o tempo total de entrega.
	 * @return the tempoTotal Tempo total de entrega.
	 */
	public double getTempoTotal() {
		return tempoTotal;
	}

	/**
	 * Define o tempo total de entrega.
	 * @param tempoTotal O atributo tempoTotal a ser definido.
	 */
	protected void setTempoTotal(double tempoTotal) {
		this.tempoTotal = tempoTotal;
	}	

	@Override
	protected void calcularPreco() {
		super.setPreco(PRECO_BOLO_BASICO);
		
	}

	@Override
	protected double calcularTempoChegada(double distancia) {
		tempoTotal = (super.getTempoEntregaPorKilometro() * distancia) + tempoPreparo;
		return tempoTotal;
	}

	/**
	 * @return the massa
	 */
	public String getMassa() {
		return massa;
	}

	/**
	 * @param massa the massa to set
	 */
	public void setMassa(String massa) {
		this.massa = massa;
	}

	/**
	 * @return the recheio
	 */
	public String getRecheio() {
		return recheio;
	}

	/**
	 * @param recheio the recheio to set
	 */
	public void setRecheio(String recheio) {
		this.recheio = recheio;
	}

	/**
	 * @return the cobertura
	 */
	public String getCobertura() {
		return cobertura;
	}

	/**
	 * @param cobertura the cobertura to set
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
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
			System.out.println(message.getMessage("mensagem.bolo.resumo"));
			System.out.println("- " + message.getMessage("mensagem.bolo.massa") +
					": " + this.massa);
			System.out.println("- " + message.getMessage("mensagem.bolo.recheio") +
					": " + this.recheio);
			System.out.println("- " + message.getMessage("mensagem.bolo.cobertura") + 
					": " + this.cobertura);
			System.out.println("___________________________________________________");			
			System.out.println(message.getMessage("mensagem.preco") + this.getPreco());
			System.out.println(message.getMessage("mensagem.tempo.total") + this.getTempoTotal());
			System.out.println("===================================================");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Erro ao carregar o seu pedido.");
		}
	}
	
}
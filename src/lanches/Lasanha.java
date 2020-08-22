/**
 * 
 */
package lanches;

import util.Message;

/**
 * @author juliana
 *
 */
public class Lasanha extends Massa {
	
	public Lasanha() {
		preco = 30;
	}	

	@Override
	protected void calcularPreco() {
		super.setPreco(30);
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
			System.out.println(message.getMessage("mensagem.massa.lasanha.resumo"));
			System.out.println("- " + super.getMolho());
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

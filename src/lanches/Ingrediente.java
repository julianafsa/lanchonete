/**
 * 
 */
package lanches;

/**
 * @author juliana
 *
 */
public class Ingrediente {
	
	/**
	 * Nome do ingrediente.
	 */
	private String nome;

	/**
	 * @return the nome Nome do ingrediente.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome a ser definido.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param nome Nome do ingrediente.
	 */
	public Ingrediente(String nome) {
		this.nome = nome;
	}
	
}

/**
 * 
 */
package lanches;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.Message;

/**
 * @author juliana
 *
 */
public class Programa {
	
	/**
	 * Internacionalização.
	 */
	private static Message mensagem = Message.getInstancia();
	
	/**
	 * Código de cancelamento de pedido.
	 */
	private static final int SAIR = 0;
	private static final int FINALIZAR_PEDIDO = 11;
	
	private static Scanner entrada;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Define o idioma e o país (Internacionalização). 
		// Default é Português Brasil
		//message.setLocale("en", "us");
		
		Lanche lanche = null;
		entrada = new Scanner(System.in);
		
		// Exibe o menu principal e retorna a opção escolhida pelo cliente. 
		int opcao = menuPrincipal();
		
		// Baseado na opcao escolhida pelo cliente, exibe um submenu ou finaliza o programa.
		switch (opcao) {
			case SAIR: 
				System.out.println(mensagem.getMessage("mensagem.desistir.pedido"));
				break;
			case 1: // sanduíche
				lanche = new Sanduiche();
				submenuSanduiche((Sanduiche)lanche);
				break;
			case 2: // massa
				Massa massa = null;
				submenuMassa(massa);
				break;
			case 3: // bolo	
				lanche = new Bolo();
				submenuBolo((Bolo)lanche);
				break;
			default: 
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				break;
		} // FIM DO SWITCH
	}
	
	/**
	 * Exibe o menu principal e retorna a opção escolhida pelo cliente.
	 * Enquanto o cliente não escolher uma opção válida, o menu é reexibido.
	 * O cliente pode escolher a opção sair e desistir de realizar o pedido.
	 * @return A opção escolhida pelo cliente.
	 */
	private static int menuPrincipal() {
		// Contrói o menu principal.
		int i = 0;
		try {
			System.out.println("===================================================");
			String nome = mensagem.getMessage("restaurante.nome");
			String metade = "", borda = "";
			for (int indice = 0; indice < ((50 - nome.length())/2) ; indice++)
				metade += " ";
			borda = metade + nome + metade;
			System.out.println(borda);
			System.out.println("___________________________________________________");
			System.out.println(mensagem.getMessage("mensagem.opcao"));
			boolean temOpcao = true;
			while (temOpcao) {
				try {
					String menuOpcao = mensagem.getMessage("mensagem.opcao" + i);
					System.out.println("[" + i + "]" + menuOpcao);
					i++;
				} catch (Exception e) {
					temOpcao = false;
				}
			} 
			System.out.println("===================================================");
		} catch (Exception e) {
			System.out.println("Erro ao carregar o menu");
		}
		
		int opcao = -1;
		entrada = new Scanner(System.in);
		boolean valida = false;
		// Lê a opção do menu principal escolhida.
		do {
			try {
				opcao = entrada.nextInt();
				if (opcao >= i || opcao < 0) {
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				} else {
					valida = true;
				}
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}
		} while (!valida);	
		return opcao;
	}
	
	/**
	 * Exibe as opções de ingredientes para o cliente montar o pedido de sanduíche.
	 */
	public static void submenuSanduiche(Sanduiche sanduiche) {
		// Monta o submenu ingredientes do sanduíche
		int quantidadeMaximaIngredientes = Sanduiche.getQuantidadeMaximaIngredientes();
		constroiMenu("mensagem.sanduiche.principal", 
				"mensagem.sanduiche.opcao", 
				"mensagem.sanduiche.ingrediente",
				quantidadeMaximaIngredientes,
				"mensagem.sanduiche.finalizar");
		
		// Lê os ingredientes do sanduíche
		int opcao = -1;
		entrada = new Scanner(System.in);
		String[] ingredientes = new String[quantidadeMaximaIngredientes];
		boolean valida = false;
		int contador = 0;
		do {
			try {
				opcao = entrada.nextInt();
				if (opcao == 0) {
					System.out.println(mensagem.getMessage("mensagem.desistir.pedido"));
					ingredientes = null;
					valida = true;
				}	
				else if (opcao > 0 && 
						 opcao <= quantidadeMaximaIngredientes && 
						 contador < quantidadeMaximaIngredientes) {
					ingredientes[contador] = mensagem.getMessage(
						"mensagem.sanduiche.ingrediente" + opcao);
					System.out.println(mensagem.getMessage("mensagem.adicionar") + 
							ingredientes[contador]);
					contador++;
				} else if (opcao == FINALIZAR_PEDIDO) {
					if (contador == 0)
						System.out.println(mensagem.getMessage("mensagem.sanduiche.finalizar.semingrediente"));
					else
						valida = true;
				} else {
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				}
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}	
		//} while (contador < quantidadeMaximaIngredientes && contador != 0 && opcao != SAIR && opcao != FINALIZAR_PEDIDO);	
		} while (!valida && contador < quantidadeMaximaIngredientes);	

		if (contador == quantidadeMaximaIngredientes) {
			System.out.println("___________________________________________________");
			try {
				System.out.println(mensagem.getMessage("mensagem.sanduiche.ingredientes.maximo"));
			} catch (Exception e) {
				System.out.println("Erro ao processar o seu pedido.");
			}
		}
		if (ingredientes != null) {
			sanduiche.setIngredientes(ingredientes);
			double distancia = submenuDistancia();
			sanduiche.calcularPreco();
			sanduiche.calcularTempoChegada(distancia);
			sanduiche.imprimirResumo();
		}
	}
	
	/**
	 * Obtém a distância da casa do cliente para o restaurante.
	 * @return a distância da casa do cliente para o restaurante.
	 */
	public static double submenuDistancia() {
		double distancia = 0;
		entrada = new Scanner(System.in);
		boolean valida = false;		
		do {
			try {
				System.out.println("===================================================");
				System.out.print(mensagem.getMessage("mensagem.distancia") + " ");
				distancia = entrada.nextDouble();
				if (distancia < 0)
					System.out.println(mensagem.getMessage("mensagem.distancia.negativa"));
				else
					valida = true;
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}			
		} while (!valida);
		return distancia;
	}
	
	/**
	 * Exibe as opções de massa e molho.
	 */
	public static void submenuMassa(Massa massa) {
		int opcao = -1;
		entrada = new Scanner(System.in);
		boolean valida = false;	
		int i = 0;
		
		// Monta o submenu Tipo de Massa
		List<String> tiposDeMassa = Message.lerTipos("mensagem.massa.tipo");
		constroiMenu("mensagem.massa.principal", "mensagem.opcao", tiposDeMassa);
		
		// Lê o tipo de massa escolhido e instancia a massa corretamente.
		do {
			try {
				opcao = entrada.nextInt();
				switch (opcao) {
					case 0: System.out.println(mensagem.getMessage("mensagem.desistir.pedido")); break;
					case 1: massa = new Macarrao(); valida = true; break;
					case 2: massa = new Pizza(); valida = true; break;
					case 3: massa = new Lasanha(); valida = true; break;
					default: 
						System.out.println(mensagem.getMessage("mensagem.opcao.invalida")); 
						valida = false;
						break;
				}		
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				valida = false;
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}	
		} while (!valida);	
		
		if (massa != null) {
			// Monta o submenu Tipo de Molho
			List<String> molhos = new ArrayList<>();
			do {
				try {
					System.out.println("===================================================");
					String nome = mensagem.getMessage("mensagem.massa.molho.principal");
					String metade = "", borda = "";
					for (int i1 = 0; i1 < ((50 - nome.length())/2) ; i1++)
						metade += " ";
					borda = metade + nome + metade;
					System.out.println(borda);
					System.out.println("___________________________________________________");
					System.out.println(mensagem.getMessage("mensagem.opcao"));
					i = 0;
					boolean temOpcao = true;
					while (temOpcao) {
						try {
							String menuOpcao;
							if (i == 0) {
								menuOpcao = mensagem.getMessage("mensagem.opcao0");
								System.out.println("[" + i + "]" + menuOpcao);
							} else {
								menuOpcao = mensagem.getMessage("mensagem.massa.molho" + i);
								molhos.add(menuOpcao);
								System.out.println("[" + i + "]" + menuOpcao);
							}
							i++;							
						} catch (Exception e) {
							temOpcao = false;
						}
					} 
					System.out.println("===================================================");
					valida = true;
				} catch (Exception e) {
					entrada = null;
					entrada = new Scanner(System.in);
					valida = false;
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				}
			} while (!valida);
			
			// Lê o tipo de molho escolhido e define o molho na massa.
			valida = false;
			do {
				try {
					opcao = entrada.nextInt();
					if (opcao == 0) {
						System.out.println(mensagem.getMessage("mensagem.desistir.pedido")); 
					} else if (opcao < 0 || opcao > molhos.size()) {
						System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
					} else {
						valida = true;
					}
				} catch (Exception e) {
					entrada = null;
					entrada = new Scanner(System.in);
					valida = false;
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				}
			} while (!valida);	
			
			if (opcao != 0) {
				massa.setMolho(molhos.get(opcao-1));
				double distancia = submenuDistancia();
				massa.calcularPreco();
				massa.calcularTempoChegada(distancia);
				massa.imprimirResumo();
			}	
		}
	}
	
	public static void submenuBolo(Bolo bolo) {
		// Monta o submenu Tipo de Massa
		List<String> tiposDeMassa = Bolo.getTiposDeMassa();
		constroiMenu("mensagem.bolo.massa.principal", "mensagem.opcao", tiposDeMassa);
	
		int opcao = -1;
		entrada = new Scanner(System.in);
		boolean valida = false;	
		// Lê o tipo de massa escolhido e define a massa no bolo.
		do {
			try {
				opcao = entrada.nextInt();
				if (opcao == 0) {
					System.out.println(mensagem.getMessage("mensagem.desistir.pedido")); 
				} else if (opcao < 0 || opcao > tiposDeMassa.size()) {
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				} else {
					valida = true;
					bolo.setMassa(tiposDeMassa.get(opcao-1));
				}
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				valida = false;
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}
		} while (!valida);	

		// Monta o submenu Tipo de Recheio
		List<String> tiposDeRecheio = Bolo.getTiposDeRecheio();
		constroiMenu("mensagem.bolo.recheio.principal", "mensagem.opcao", tiposDeRecheio);
	
		// Lê o tipo de recheio escolhido e define o recheio no bolo.
		valida = false;
		do {
			try {
				opcao = entrada.nextInt();
				if (opcao == 0) {
					System.out.println(mensagem.getMessage("mensagem.desistir.pedido")); 
				} else if (opcao < 0 || opcao > tiposDeRecheio.size()) {
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				} else {
					valida = true;
					bolo.setRecheio(tiposDeRecheio.get(opcao-1));
				}
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				valida = false;
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}
		} while (!valida);	

		// Monta o submenu Tipo de Cobertura
		List<String> tiposDeCobertura = Bolo.getTiposDeCobertura();
		constroiMenu("mensagem.bolo.cobertura.principal", "mensagem.opcao", tiposDeCobertura);
		
		// Lê a cobertura escolhida e define a cobertura do bolo.
		valida = false;
		do {
			try {
				opcao = entrada.nextInt();
				if (opcao == 0) {
					System.out.println(mensagem.getMessage("mensagem.desistir.pedido")); 
				} else if (opcao < 0 || opcao > tiposDeCobertura.size()) {
					System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
				} else {
					valida = true;
					bolo.setCobertura(tiposDeCobertura.get(opcao-1));
				}
			} catch (Exception e) {
				entrada = null;
				entrada = new Scanner(System.in);
				valida = false;
				System.out.println(mensagem.getMessage("mensagem.opcao.invalida"));
			}
		} while (!valida);	

		if (opcao != 0) {
			double distancia = submenuDistancia();
			bolo.calcularPreco();
			bolo.calcularTempoChegada(distancia);
			bolo.imprimirResumo();
		}	
	}
	
	private static void constroiMenu(String chaveTitulo, String chaveOpcoes, List<String> opcoes) {
		try {
			System.out.println("===================================================");
			String titulo = mensagem.getMessage(chaveTitulo);
			String metade = "", linha = "";
			for (int indice = 0; indice < ((50 - titulo.length())/2) ; indice++)
				metade += " ";
			linha = metade + titulo + metade;
			System.out.println(linha);
			System.out.println("___________________________________________________");
			System.out.println(mensagem.getMessage(chaveOpcoes));
			int i = SAIR;
			System.out.println("[" + i++ + "]" + mensagem.getMessage("mensagem.opcao0"));
			for (String opcao : opcoes)
				System.out.println("[" + i++ + "]" + opcao);
			System.out.println("===================================================");
		} catch (Exception e) {
			System.out.println("Não foi possível construir o menu.");
		}
	}
	
	private static void constroiMenu(String chaveTitulo, String chaveSubtitulo, String chaveOpcoes, int n, String finalizar) {
		try {
			System.out.println("===================================================");
			String titulo = mensagem.getMessage(chaveTitulo);
			String metade = "", linha = "";
			for (int indice = 0; indice < ((50 - titulo.length())/2) ; indice++)
				metade += " ";
			linha = metade + titulo + metade;
			System.out.println(linha);
			System.out.println("___________________________________________________");
			System.out.println(mensagem.getMessage(chaveSubtitulo));
			int i = SAIR;
			System.out.println("[" + i++ + "]" + mensagem.getMessage("mensagem.opcao0"));
			String[] opcoes = Message.lerTiposComoString(chaveOpcoes, n);
			for (String opcao : opcoes)
				System.out.println("[" + i++ + "]" + opcao);
			
			System.out.println("[" + i++ + "]" + mensagem.getMessage("mensagem.finalizar"));
			System.out.println("===================================================");
		} catch (Exception e) {
			System.out.println("Não foi possível construir o menu.");
		}
	}

}
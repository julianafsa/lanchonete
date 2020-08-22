package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Message {
	
	private String language;
	private String country;
	private ResourceBundle bundle;
	private static String fileNamePrefix = "resources.messages";
	private static Message instancia;
	
	/**
	 * @param language
	 * @param country
	 */
	private Message(String language, String country) {
		this.language = language;
		this.country = country;
		this.setLocale(language, country);
	}
	
	/**
	 * @param language
	 * @param country
	 */
	private Message() {
		this.language = "pt";
		this.country = "br";
		//this.language = "en";
		//this.country = "us";		
		this.setLocale(language, country);
	}	
	
	/**
	 * 
	 * @return
	 */
	public static Message getInstancia() {
		if (instancia == null) {
			instancia = new Message();
		}	
		return instancia;
	}

	/**
	 * 
	 * @param language
	 * @param country
	 */
	public void setLocale(String language, String country) {
		this.language = language;
		this.country = country;
		Locale locale = new Locale(language.toLowerCase(), 
				country.toUpperCase());
		//System.out.println(locale.getDisplayName());
		bundle = ResourceBundle.getBundle(fileNamePrefix, locale);
		
	}
	
	public String getMessage(String key) {
		return bundle.getString(key);
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	public static List<String> lerTipos(String tipo) {
		List<String> list = new ArrayList<>();
		Message message = Message.getInstancia();
		boolean temOpcao = true;
		int i = 1;
		while (temOpcao) {
			try {
				list.add(message.getMessage(tipo + i));
				i++;
			} catch (Exception e) {
				temOpcao = false;
			}		
		}
		return list;
	}
	
	public static String[] lerTiposComoString(String tipo, int n) {
		String[] list = new String[n];
		Message message = Message.getInstancia();
		boolean temOpcao = true;
		int i = 1;
		while (temOpcao) {
			try {
				list[i-1] = message.getMessage(tipo + i);
				i++;
			} catch (Exception e) {
				temOpcao = false;
			}		
		}
		return list;
	}

}

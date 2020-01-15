package mx.solser.bpi.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Format {

	private final static String FORMAT_DECIMAL_NUMBER = "###,###,###.00";
	public final static String FORMAT_NUMBER_TRES_DIGITOS = "000";
	
	
	public static String formato(Number number,String formato){
		String valor = "0000";
		DecimalFormat numberFormat = new DecimalFormat(formato);
		if(number != null){
			valor = numberFormat.format(number);
		}
		return valor;
	}
	
	public static String formateaNumerico(Number number,Locale locale){
		String valor = "0.0";
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);		
		if(number != null){
			valor = numberFormat.format(number);
		}
		return valor;
	}
	
	/**
	 * Formatea un String a Double  
	 * @param cantidad
	 * @return Double
	 */
	public static Double formatoPesosADouble(String cantidad){
		cantidad = cantidad.substring(cantidad.indexOf("$") + 1).trim();
		while (cantidad.indexOf(",") >= 0){
			cantidad = cantidad.substring(0, cantidad.indexOf(",")) + cantidad.substring(cantidad.indexOf(",") + 1);
		}
		return Double.valueOf(cantidad).doubleValue(); 
	}
	
	
	public static String acentosHTML(String cadena){
		String cadRet = "";
		
		while(cadena.indexOf("\\") >= 0){
			char caracter = cadena.charAt(cadena.indexOf("\\")+1);
			switch (caracter){
				case 'a':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&aacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'e':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&eacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'i':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&iacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'o':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&oacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'u':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&uacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'n':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&ntilde;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'A':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Aacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'E':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Eacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'I':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Iacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'O':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Oacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'U':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Uacute;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;
				case 'N':
					cadRet += cadena.substring(0, cadena.indexOf("\\")) + "&Ntilde;";
					cadena = cadena.substring(cadena.indexOf("\\")+2);
					break;	
				
			}
		}
		
		return cadRet + cadena;
	}
	
	public static String depurarBasuraEspacios(String cadena){
		String cadenaLimpia = "";
		if (cadena.contains("%2520"))
			cadenaLimpia = cadena.replaceAll("%2520", " ");
		else if (cadena.contains("%20"))
			cadenaLimpia = cadena.replaceAll("%20", " ");
		else if (cadena.contains("%2C"))
			cadenaLimpia = cadena.replaceAll("%2C", ",");
		else 
			cadenaLimpia = cadena;
		return cadenaLimpia;
	}
}

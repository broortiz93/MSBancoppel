package mx.solser.bpi.util;

import java.text.DecimalFormat;


/**
 * 
 */

/**
 * @author cchong
 *
 */
public class Utilerias {

	public static String formateaNumeroToMoneda(Number monto) {
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		String montoFormateado = "0";
		montoFormateado = df.format(monto);

		return montoFormateado;

	}
	
	
}

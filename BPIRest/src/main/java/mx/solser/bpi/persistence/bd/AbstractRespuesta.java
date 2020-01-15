package mx.solser.bpi.persistence.bd;

import java.io.Serializable;

/**
 * @author jarellano
 */
public abstract class AbstractRespuesta implements Serializable {
	public static final String EXITO = "EXITO";
	public static final String ERROR = "ERROR";
	public static final String NO_EXITO_NEGOCIO = "OPCION NO EXITOSA NEGOCIO";
	private static final long serialVersionUID = -2814227248628040258L;
	private String codigoRet  = "";
	private String codigoError  = "";
	private String errorData  = "";

	/**
	 * @return El codigoRet
	 */
	public final String getCodigoRet() {
		return codigoRet;
	}
	
	/**
	 * @param codigoRet lo setea en la propiedad codigoRet
	 */
	public final void setCodigoRet(final String codigoRetParam) {
		this.codigoRet = codigoRetParam;
	}
	
	/**
	 * @return El codigoError
	 */
	public final String getCodigoError() {
		return codigoError;
	}
	
	/**
	 * @param codigoError lo setea en la propiedad codigoError
	 */
	public final void setCodigoError(final String codigoErrorParam) {
		this.codigoError = codigoErrorParam;
	}
	
	/**
	 * @return the error
	 */
	public final String getError() {
		return errorData;
	}
	
	/**
	 * @param error the error to set
	 */
	public final void setError(final String errorParam) {
		this.errorData = errorParam;
	}

}

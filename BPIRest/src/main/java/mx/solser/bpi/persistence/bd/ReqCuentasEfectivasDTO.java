/**
* Todos los derechos reservados
* ReqCuentasEfectivasDTO.java
*
* Control de versiones:
*
* Version Date/Hour 	By 		Company 	Description
* ------- --------------- ----------- -------- -----------------------------------------------------------------
* 1.0 	22/06/2011 12:31:22 	Carlos Chong	 Solser 	 Creacion
*
*/
package mx.solser.bpi.persistence.bd;

import java.io.Serializable;


public class ReqCuentasEfectivasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5941569126402811200L;
	
	private String numCliente;
	
	ReqCuentasEfectivasDTO(final String numCliente){
		super();
		this.numCliente = numCliente;
	}

	/**
	 * @return la propiedad numCliente
	 */
	public String getNumCliente() {
		return numCliente;
	}

	/**
	 * @param numCliente se setea en la propiedad numCliente
	 */
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}



	
	
	

}

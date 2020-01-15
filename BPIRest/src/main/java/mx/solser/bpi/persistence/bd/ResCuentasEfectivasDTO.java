/**
* Todos los derechos reservados
* ResCuentasEfectivasDTO.java
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
import java.util.Collections;
import java.util.List;


public class ResCuentasEfectivasDTO   extends AbstractRespuesta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6758070408084688972L;
	
	private List<EfectivaDTO> efectivaDTOList = Collections.emptyList();

	ResCuentasEfectivasDTO(){
		super();
	}
	
	public List<EfectivaDTO> getEfectivaDTOList() {
		return efectivaDTOList;
	}

	public void setEfectivaDTOList(List<EfectivaDTO> efectivaDTOList) {
		this.efectivaDTOList = efectivaDTOList;
	}

	
	
	
	

}

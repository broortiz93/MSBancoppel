/**
 * 
 */
package mx.solser.bpi.persistence;

import syndein.informixInterface.Header;
import syndein.tools.cmn.cmn_InterAPI;

/**
 * @author cchong
 *
 */
public interface IConexionInterAct {
	Header executeInterAct(String tramaEjecutar, String database,cmn_InterAPI con)throws Exception;
	Header executeInterAct(String tramaEjecutar, String database,int numRegistros, cmn_InterAPI con)throws Exception;
	void closeConexion();
	void setId(long id);
	long getId();
	void liberaConexion();
	long getTiempoUltimaEjecucion();
	void setTiempoUltimaEjecucion(long tiempo);
	boolean isEjecutando();

}

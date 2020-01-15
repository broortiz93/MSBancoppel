package mx.solser.bpi.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import syndein.tools.cmn.cmn_InterAPI;

public class InterActDataSource4 {
	private static final Logger LOG = LoggerFactory.getLogger(InterActDataSource4.class.getName());
	private static InterActDataSource4 interActDataSource = new InterActDataSource4();
	private static int numConexiones = 0;
//	private IConfiguracion configuracionService = null;
	private String ip = "";
	private int puerto = 0;
	private List<cmn_InterAPI> poolConexionesLibres = Collections.synchronizedList(new ArrayList<cmn_InterAPI>());
	private List<cmn_InterAPI> poolConexionesOcupadas = Collections.synchronizedList(new ArrayList<cmn_InterAPI>());
	
	private String propinstancia="BPI028";
	private String propnumConexiones="5";
	private String 		propnumIntentos="3";
	private String	propserverIPInformix="10.26.215.210";
	private String	serverPuertoInformix="7000";
	
	public InterActDataSource4() {
		super();
	}
	
	public static InterActDataSource4 getInstance() {
		return interActDataSource;
	}

	public void init() {
		LOG.info("Init InterActDataSource");	
		LOG.info("Antes de request");	
		LOG.info("Despues de servletcontext");
		try {	
			LOG.info("antes de numconexiones");
			numConexiones = Integer.parseInt(propnumConexiones);
			puerto = Integer.parseInt(serverPuertoInformix);
			LOG.info("setConexion Exito########################");					
			ConexionInterAct con = null;
		
			for (int i=0; i<=numConexiones-1; i++){
				con =  ConexionInterAct.creaInstancia(propserverIPInformix, puerto, propinstancia, 3);
				poolConexionesLibres.add(con.getIapi());
				LOG.info("Conexion pool : " +i+" " + poolConexionesLibres.get(i));
			}
	  } catch (NumberFormatException e) {
		  LOG.error("init() - ERROR: " + e.getMessage(), e);
	  }
		LOG.info("setConexion Exito Final########################");
	}
	
	public synchronized cmn_InterAPI getConexion(){
		cmn_InterAPI con = null;
		try {
			LOG.info("Conexiones libres " + poolConexionesLibres.size());
			LOG.info("Conexiones ocupadas " + poolConexionesOcupadas.size());
			if (poolConexionesLibres.isEmpty() && poolConexionesLibres.size() <= numConexiones) {
				con = poolConexionesLibres.get(0);
			
				if(poolConexionesLibres.size() <= numConexiones){
					poolConexionesOcupadas.add(con);			
					poolConexionesLibres.remove(0);
					LOG.info("Tama�o Conexiones Libres Despues Conectar: " +poolConexionesLibres.size());
					LOG.info("Tama�o Conexiones ocupadas Despues Conectar: " +poolConexionesOcupadas.size());
				}
			} else {
				LOG.error(new Date() + " - No hay conexion disponible ");
				throw new Exception("No hay conexion disponible");
			}
		} catch (Exception e) {
			LOG.error("getConexion() - ERROR: " + e.getMessage(), e);
		}
		return con;
	}
	
	
	public synchronized void liberaConexion() {
		cmn_InterAPI con = null;
		LOG.info("liberando conexion diferente de null  >>>>>>>>>>>>>>>>>> ");
		
		if(poolConexionesOcupadas.isEmpty() && poolConexionesOcupadas.size() <= numConexiones){
			con = poolConexionesOcupadas.get(0);
			if (poolConexionesLibres.size() < numConexiones) {
				poolConexionesLibres.add(con);
				poolConexionesOcupadas.remove(0);
			}
		}
		LOG.info("Tama�o de libres despues de liberar : " + poolConexionesLibres.size());
		LOG.info("Tama�o de ocupadas despues de liberar : " + poolConexionesOcupadas.size());
	}
		
	public synchronized boolean reconect (cmn_InterAPI iapi) {
		LOG.info("reconectando la conexion:");
		boolean flag = false;
		InterActDataSource4 inter = new InterActDataSource4();
		inter.liberaConexion();
		if(iapi != null){
			iapi.ICAclose();
		}
		final ConexionInterAct conexionInteract = new ConexionInterAct(propserverIPInformix,
				Integer.parseInt(serverPuertoInformix), propinstancia, 3);
		if (poolConexionesLibres.size() > 1){
			poolConexionesLibres.remove(0);
		}
		poolConexionesLibres.add(conexionInteract.getIapi());
		flag = true;
		return flag;
	}

	public void close(IConexionInterAct obj){
		obj.liberaConexion();
	}
	
	public void fin(){
		LOG.info("Desconectando pol...#####");		
		poolConexionesLibres.clear();
		poolConexionesOcupadas.clear();
	}

	/**
	 * @return El configuracionService
	 */
//	public IConfiguracion getConfiguracionService() {
//		return configuracionService;
//	}
//
//	/**
//	 * @param configuracionService lo setea en la propiedad configuracionService
//	 */
//	public void setConfiguracionService(IConfiguracion configuracionService) {
//		this.configuracionService = configuracionService;
//	}

}

package mx.solser.bpi.persistence;

import com.syndein.jdbc.datasource.SynDataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import syndein.informixInterface.Header;
import syndein.tools.cmn.cmn_InterAPI;

public class ConexionInterAct {
	private static final Logger LOG = LoggerFactory.getLogger(ConexionInterAct.class.getName());
	private boolean isEjecutando = false;
	private long tiempoUltimaEjecucion = 0;
	private long id; 
	private cmn_InterAPI iapi =null;
	private String ip;
	private int puerto;
	private String instancia;
	private static short timeOut = 60;
	private int reintentos;
	
	
	public cmn_InterAPI getIapi() {
		return iapi;
	}

	public void setIapi(cmn_InterAPI iapi) {
		this.iapi = iapi;
	}

	public ConexionInterAct(final String ip, final int puerto, final String instancia, final int reintentos){
		super();
		this.ip = ip;
		this.puerto = puerto;
		this.instancia = instancia;
		this.reintentos = reintentos;
	}
	
	private void connect() throws Exception{
		cmn_InterAPI cmn_InterAPI =null;
		short        rc=0;
		cmn_InterAPI = new cmn_InterAPI();
		rc = cmn_InterAPI.ICAopen(this.ip, this.puerto,this.instancia, this.reintentos);
		LOG.info("Estatus de la conexion: {}", rc);
		if (rc == 0){
			this.iapi = cmn_InterAPI;
		}else{
			LOG.info("Estatus de la conexion: {} descripcion {}", Short.valueOf(rc), estatusInteract(rc));
			throw new Exception("Estatus de la conexion: "+Short.valueOf(rc)+" descripcion: "+estatusInteract(rc));
		}
		
	}
	
	public static ConexionInterAct creaInstancia(final String ip, final int puerto, final String instancia, final int reintentos){
		ConexionInterAct conexionInteract = null;
		ConexionInterAct conexionInteractTemp  = new ConexionInterAct(ip,puerto,instancia,reintentos);
		try{
			conexionInteractTemp.connect();
			conexionInteract = conexionInteractTemp;
		}catch (Exception pe) {
			LOG.error("Error", pe);
		}
		return conexionInteract;
	}

	public static Header executeInterAct(final String tramaEjecutar, final String dataBase,SynDataSource iapi)throws Exception{
		Header header = null;
		java.util.Date fechaHoy = new java.util.Date();
		SimpleDateFormat fechaS =  new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss S");
		String fecha = fechaS.format(fechaHoy);
		StringBuffer solicitud, respuesta;
		int        rc = -1;
		short        _nNumTran = 1;
		Header oSol = new Header();
		oSol.set_cs2_ret_code("0");
		oSol.set_infmxsrv_ret_code("0");
		oSol.set_informix_ret_code("0");
		oSol.set_err_row("0");
		oSol.set_err_col("0");
		oSol.set_db_name(dataBase);
		oSol.set_num_rows("10");
		oSol.set_more_rows("1");
		oSol.set_io_buf(tramaEjecutar);						
		solicitud  = new StringBuffer(oSol.toString());
		respuesta = new StringBuffer();
		String idSession="00000001";
		//setEjecutando(true);
		try {
			LOG.info("trama " + tramaEjecutar);
			final long tiempoIni = System.currentTimeMillis();
			rc = iapi.Request(solicitud, respuesta, _nNumTran, timeOut);
			final long tiempoFin = System.currentTimeMillis();
			final double tiempo = (tiempoFin - tiempoIni) / 1000.0;
			LOG.info("@#@#@# fecha=" + fecha + "|idSesion=" + idSession + "|tiempo="
					+ tiempo + "|trama=" + tramaEjecutar.substring(0, tramaEjecutar.indexOf("("))
					+ "|respuesta=" + respuesta + "|codigoRetorno=" + rc + "|canal=BPI|ip=127.0.0.1");
			LOG.debug("\n TIEMPO:[{}] TRAMA: [{}] \n RESPUESTA: [{}] ",new Object[]{tiempo,tramaEjecutar,respuesta.toString()});
			header = new Header(respuesta.toString());
			if (respuesta.indexOf(tramaEjecutar) > 0) {
				throw new Exception(" Error, verificar el SP posiblemente no existe, o quizas se modifico, favor de verificarlo:"+tramaEjecutar);	
			}
		} catch (NamingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SQLException e) {
			throw new Exception(e.getMessage(), e);
		}
		return header;
	}
	
	public static Header executeInterAct(final String tramaEjecutar, final String dataBase,
			ArchitectSessionBean sessionBean, SynDataSource iapi)throws Exception {
		Header header = null;
		java.util.Date fechaHoy = new java.util.Date();
		SimpleDateFormat fechaS =  new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss S");
		String fecha = fechaS.format(fechaHoy);
		StringBuffer solicitud, respuesta;
		int        rc = -1;
		int        _nNumTran = 1;
		Header oSol = new Header();
		oSol.set_cs2_ret_code("0");
		oSol.set_infmxsrv_ret_code("0");
		oSol.set_informix_ret_code("0");
		oSol.set_err_row("0");
		oSol.set_err_col("0");
		oSol.set_db_name(dataBase);
		oSol.set_num_rows("10");
		oSol.set_more_rows("1");
		oSol.set_io_buf(tramaEjecutar);						
		solicitud  = new StringBuffer(oSol.toString());
		respuesta = new StringBuffer();
		try {
			long tiempoIni = System.currentTimeMillis();
			rc = iapi.Request(solicitud,respuesta,_nNumTran,timeOut);
			long tiempoFin = System.currentTimeMillis();
			double tiempo = (tiempoFin-tiempoIni)/1000.0;
			LOG.info("@#@#@# fecha=" + fecha + "|idSesion=" + sessionBean.getIdSession()
				+ "|tiempo=" + tiempo + "|trama=" + tramaEjecutar.substring(0, tramaEjecutar.indexOf("("))
				+ "|respuesta=" + respuesta + "|codigoRetorno=" + rc + "|canal=BPI|ip=" + sessionBean.getIp());
			LOG.debug("\n TIEMPO:[{}] idSession:[{}] TRAMA: [{}] \n RESPUESTA: [{}] ",
					new Object[]{tiempo, sessionBean.getIdSession(), tramaEjecutar, respuesta.toString()});
			header = new Header(respuesta.toString());
			if (respuesta.indexOf(tramaEjecutar) > 0) {
				throw new Exception("Error, verificar el SP posiblemente no existe, o quizas se modifico, favor de verificarlo:"+tramaEjecutar);	
			}
		} catch (NamingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SQLException e) {
			throw new Exception(e.getMessage(), e);
		}
		return header;
	}
	
	public void closeConexion(){
		InterActDataSource4 dataSource = InterActDataSource4.getInstance();
		dataSource.liberaConexion();
	}
	
	public void liberaConexion(){
		iapi.ICAclose();
	}

	/**
	 * @return El id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id lo setea en la propiedad id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El tiempoUltimaEjecucion
	 */
	public long getTiempoUltimaEjecucion() {
		return tiempoUltimaEjecucion;
	}

	/**
	 * @param tiempoUltimaEjecucion lo setea en la propiedad tiempoUltimaEjecucion
	 */
	public void setTiempoUltimaEjecucion(long tiempoUltimaEjecucion) {
		this.tiempoUltimaEjecucion = tiempoUltimaEjecucion;
	}

	/**
	 * @return El isEjecutando
	 */
	public boolean isEjecutando() {
		return isEjecutando;
	}

	/**
	 * @param isEjecutando lo setea en la propiedad isEjecutando
	 */
	public void setEjecutando(boolean isEjecutando) {
		this.isEjecutando = isEjecutando;
	}
	
	private String estatusInteract(short codigo){
		String respuesta = "";
		switch(codigo){
			case 0:
					respuesta = "Operaci�n exitosa";
					break;
			case 1:
				respuesta = "Tipo de cliente inv�lido.";
				break;
			case 2:
				respuesta = "Error al cargar la librer�a de Windows Sockets.";
				break;
			case 3:
				respuesta = "Error en la versi�n de Windows Sockets.";
				break;
			case 4:
				respuesta = "Error al crear el socket.";
				break;
			case 5:
				respuesta = "Error al conectarse a InterAct (connect).";
				break;
			case 6:
				respuesta = "Error al obtener datos del socket (getsockname).";
				break;
			case 7:
				respuesta = "Error al recibir aceptaci�n de InterAct.";
				break;
			case 8:
				respuesta = "Conexi�n no aceptada por InterAct.";
				break;
			case 9:
				respuesta = "Error al registrarse en InterAct.";
				break;
			case 10:
				respuesta = "No es posible establecer mas conexiones hacia InterAct.";
				break;
			case 11:
				respuesta = "Reintentos de apertura de conexi�n excedidos.";
				break;
			case 12:
				respuesta = "No fue posible encontrar el Host.";
				break;
			case 13:
				respuesta = "Tipo de servidor inv�lido.";
				break;
			case 21:
				respuesta = "No se ha realizado la conexi�n con InterAct.";
				break;
			case 22:
				respuesta = "Error al enviar el paquete a InterAct.";
				break;
			case 23:
				respuesta = "Paquete demasiado grande (> 10,000 bytes).";
				break;
			case 24:
				respuesta = "Tiempo de espera agotado.";
				break;
			case 25:
				respuesta = "Error al abrir el archivo.";
				break;
			case 26:
				respuesta = "Error al leer el archivo.";
				break;
			case 27:
				respuesta = "Error al procesar el envio/recepci�n de un archivo.";
				break;
			case 39:
				respuesta = "Comando inv�lido.";
				break;
			case 40:
				respuesta = "Operaci�n cancelada.";
				break;
			case 41:
				respuesta = "Error al cerrar la conexi�n con InterAct.";
				break;
			case 50:
				respuesta = "Error al crear una sesi�n de SSL.";
				break;
			case 51:
				respuesta = "No es posible inicializar la sesi�n de SSL.";
				break;
			case 52:
				respuesta = "Error al transmitir datos utilizando el esquema SSL.";
				break;
			case 53:
				respuesta = "Error al recibir datos mediante el esquema de SSL.";
				break;
			case 61:
				respuesta = "Error en esquema de seguridad.";
				break;
			case 62:
				respuesta = "No es posible obtener memoria.";
				break;
			case 63:
				respuesta = "No es posible obtener el IP local.";
				break;
			case 64:
				respuesta = "El buffer de recepci�n esta truncado.";
				break;
			case 65:
				respuesta = "El dato no cuenta con el formato correcto.";
				break;
			case 66:
				respuesta = "El dato no es valido.";
				break;
			case 88:
				respuesta = "No se encontr� el valor de la variable de ambiente para el directorio de descarga.";
				break;
			case 89:
				respuesta = "Reconexi�n durante la recepci�n de informaci�n.";
				break;
			case 90:
				respuesta = "Error en el tama�o del buffer de recepci�n.";
				break;
			case 91:
				respuesta = "Paquete truncado (paquete recibido es mayor al �rea de recepci�n).";
				break;
			case 92:
				respuesta = "Error al escribir en el archivo.";
				break;
			case 98:
				respuesta = "Error al recibir el paquete de InterAct.";
				break;
			case 99:
				respuesta = "Se recibi� un archivo como respuesta.";
				break;

				
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConexionInterAct other = (ConexionInterAct) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static Header executeInterAct(String tramaEjecutar, final String dataBase,
			int numRegistros,SynDataSource iapi) throws Exception {
		String numReg = "10";
		boolean flag = false;
		Header header = null;
		//ConexionInterAct con = 
		//	ConexionInterAct.setTiempoUltimaEjecucion(System.currentTimeMillis());
		java.util.Date fechaHoy = new java.util.Date();
		SimpleDateFormat fechaS =  new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss S");
		String fecha = fechaS.format(fechaHoy);
		//InterActDataSource inter = new InterActDataSource();
		StringBuffer solicitud, respuesta;
		int        rc = -1;
		short        _nNumTran = 1;
		Header oSol = new Header();
		oSol.set_cs2_ret_code("0");
		oSol.set_infmxsrv_ret_code("0");
		oSol.set_informix_ret_code("0");
		oSol.set_err_row("0");
		oSol.set_err_col("0");
		oSol.set_db_name(dataBase);
		if(numRegistros != 0){
			numReg = String.valueOf(numRegistros);
		}
		oSol.set_num_rows(numReg);
		oSol.set_more_rows("1");
		oSol.set_io_buf(tramaEjecutar);						
		solicitud  = new StringBuffer(oSol.toString());
		respuesta = new StringBuffer();
		
		//setEjecutando(true);
		try {
			String idSession = "0000001";
			long tiempoIni = System.currentTimeMillis();
			rc = iapi.Request(solicitud,respuesta,_nNumTran,timeOut);
			long tiempoFin = System.currentTimeMillis();
			double tiempo = (tiempoFin-tiempoIni)/1000.0;
			
			LOG.info("@#@#@# fecha=" + fecha + "|idSesion=" + idSession.trim() + "|tiempo="
					+ tiempo + "|trama=" + tramaEjecutar.substring(0, tramaEjecutar.indexOf("("))
					+ "|respuesta=" + respuesta + "|codigoRetorno=" + rc + "|canal=BPI|ip=127.0.0.1");
			LOG.debug("\n TRAMA: [{}] \n RESPUESTA: [{}] ",tramaEjecutar,respuesta.toString());
			header = new Header(respuesta.toString());
		} catch (NamingException e) {
			LOG.error(" executeInterAct() - ERROR: " + e.getMessage(), e);
		} catch (SQLException e) {
			LOG.error("executeInterAct() - ERROR: " + e.getMessage(), e);
		}
		/*if(flag){
			iapi = inter.getConexion();
			rc = iapi.ICArequest(solicitud,respuesta,_nNumTran,timeOut);
			inter.liberaConexion();
			logger.info("@#@#@# fecha="+fecha+"|idSesion="+idSesion.trim()+"|tiempo="+tiempo+"|trama="+tramaEjecutar.substring(0, tramaEjecutar.indexOf("("))+"|respuesta="+Respuesta+"|codigoRetorno="+rc+"|canal=BPI|ip="+request.getRemoteAddr());
			LOG.debug("\n TRAMA: [{}] \n RESPUESTA2: [{}] ",tramaEjecutar,respuesta.toString());
			header = new Header(respuesta.toString());
		}
		*/
		if(respuesta.indexOf(tramaEjecutar)>0){
			throw new Exception("Error, verificar el SP posiblemente no existe, o quizas se modifico, favor de verificarlo:"+tramaEjecutar);	
		}
		//setTiempoUltimaEjecucion(System.currentTimeMillis());
		return header;
	}

	/* (non-Javadoc)
	 * @see mx.com.solser.architecbase.bancoppel.persistencia.conexion.IConexionInterAct#executeInterAct(java.lang.String, java.lang.String, short, mx.com.solser.bancoppel.architecbase.beans.core.ArchitectSessionBean)
	 */
	public static String executeInterAct(String tramaEjecutar, String dataBase,
			int transaccion, ArchitectSessionBean sessionBean,SynDataSource iapi)
			throws Exception {
		boolean flag = false;
		Header header = null;
		InterActDataSource4 inter = new InterActDataSource4();
		//setTiempoUltimaEjecucion(System.currentTimeMillis());
		java.util.Date fechaHoy = new java.util.Date();
		SimpleDateFormat fechaS =  new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss S");
		String fecha = fechaS.format(fechaHoy);
		StringBuffer solicitud, respuesta;
		int rc = -1;
		int _nNumTran = transaccion;

		solicitud  = new StringBuffer(tramaEjecutar);
		respuesta = new StringBuffer();
		
		try {
			final long tiempoIni = System.currentTimeMillis();
			rc = iapi.Request(solicitud, respuesta, _nNumTran, timeOut);
			final long tiempoFin = System.currentTimeMillis();
			final double tiempo = (tiempoFin - tiempoIni) / 1000.0;
			LOG.debug("\n TIEMPO:[{}] idSession:[{}] TRAMA: [{}] \n RESPUESTA: [{}] ",
					new Object[]{tiempo, sessionBean.getIdSession(), tramaEjecutar, respuesta.toString()});
		} catch (NamingException e) {
			LOG.error(" executeInterAct() - ERROR: " + e.getMessage(), e);
		} catch (SQLException e) {
			LOG.error("executeInterAct() - ERROR: " + e.getMessage(), e);
		}

		if(respuesta.indexOf(tramaEjecutar)>0){
			throw new Exception("Error, verificar el SP posiblemente no existe, o quizas se modifico, favor de verificarlo:"+tramaEjecutar);	
		}
		//setTiempoUltimaEjecucion(System.currentTimeMillis());
		return respuesta.toString();
	}

}

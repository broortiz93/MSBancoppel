package mx.solser.bpi.persistence;

import java.io.Serializable;



public  class ArchitectSessionBean implements Serializable {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 8093194781879793759L;
	private String idSession;
	private String ip; 
	private String llaveSeguridad;
	private String checksum;
	private String mosaico;
	
	public String getMosaico() {
		return mosaico;
	}

	public void setMosaico(String mosaico) {
		this.mosaico = mosaico;
	}

	/**
	 * Constructor privado
	 */
	ArchitectSessionBean(){
		super();
	} 
	

	/**
	 * @return El ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip lo setea en la propiedad ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return el idSession
	 */
	public String getIdSession() {
		return idSession;
	}

	/**
	 * @param Setea el parametro idSession a la propiedad idSession.
	 */
	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public String getLlaveSeguridad() {
		return llaveSeguridad;
	}

	public void setLlaveSeguridad(String llaveSeguridad) {
		this.llaveSeguridad = llaveSeguridad;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	
	
	
}

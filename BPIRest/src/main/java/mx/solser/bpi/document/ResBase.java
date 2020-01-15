package mx.solser.bpi.document;



import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;


public class ResBase{

	@Transient
	private String pCodRetorno;
	@Transient
	private String pMensaje;
	
	protected Date fechaCreacion;
	protected Date fechaActualiza;
	
	@Transient
	protected String creacion;
	@Transient
	protected String actualizacion;
	
	
	private long pTotalItems;
	
	private List<Usuario> listUsuarios;


	public long getpTotalItems() {
		return pTotalItems;
	}

	public void setpTotalItems(long pTotalItems) {
		this.pTotalItems = pTotalItems;
	}

	public String getpCodRetorno() {
		return pCodRetorno;
	}

	public void setpCodRetorno(String pCodRetorno) {
		this.pCodRetorno = pCodRetorno;
	}

	public String getpMensaje() {
		return pMensaje;
	}

	public void setpMensaje(String pMensaje) {
		this.pMensaje = pMensaje;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualiza() {
		return fechaActualiza;
	}

	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}

	public String getCreacion() {
		return creacion;
	}

	public void setCreacion(String creacion) {
		this.creacion = creacion;
	}
	
	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

}

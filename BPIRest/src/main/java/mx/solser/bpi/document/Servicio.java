package mx.solser.bpi.document;



import mx.solser.pv.documents.Base;


public class Servicio extends Base {

	private static final long serialVersionUID = 1L;
	
	private String convenio = "";	
	private String idPagoServicio = "";
	private String nombreComercial = "";
	private String razonSocial = "";
	private String pathImagen = "";	
	private String pathImagenOver = "";
	private String url = "";
	private String parametros = "";
	
	
	public Servicio(String convenio, String idPagoServicio, String nombreComercial, String razonSocial,
			String pathImagen, String pathImagenOver, String url, String parametros) {
		super();
		this.convenio = convenio;
		this.idPagoServicio = idPagoServicio;
		this.nombreComercial = nombreComercial;
		this.razonSocial = razonSocial;
		this.pathImagen = pathImagen;
		this.pathImagenOver = pathImagenOver;
		this.url = url;
		this.parametros = parametros;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getIdPagoServicio() {
		return idPagoServicio;
	}
	public void setIdPagoServicio(String idPagoServicio) {
		this.idPagoServicio = idPagoServicio;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getPathImagen() {
		return pathImagen;
	}
	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	public String getPathImagenOver() {
		return pathImagenOver;
	}
	public void setPathImagenOver(String pathImagenOver) {
		this.pathImagenOver = pathImagenOver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

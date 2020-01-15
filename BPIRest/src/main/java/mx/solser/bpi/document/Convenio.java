package mx.solser.bpi.document;



import mx.solser.pv.documents.Base;


public class Convenio extends Base {

	private static final long serialVersionUID = 1L;
	
	private String idCategoria = "";
	private String idConvenio = "";
	
	
	public Convenio() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Convenio(String idCategoria, String idConvenio) {
		super();
		this.idCategoria = idCategoria;
		this.idConvenio = idConvenio;
	}


	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getIdConvenio() {
		return idConvenio;
	}


	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

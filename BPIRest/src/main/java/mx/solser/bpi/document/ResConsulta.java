package mx.solser.bpi.document;


import java.util.List;


import mx.solser.pv.documents.Base;


public class ResConsulta extends Base {

	private static final long serialVersionUID = 1L;
	private List<Servicio> listServicio;
	private List<Convenio> listCategoria;
	public List<Convenio> getListConvenio() {
		return listCategoria;
	}
	public void setListConvenio(List<Convenio> list) {
		this.listCategoria = list;
	}
	public List<Servicio> getListServicio() {
		return listServicio;
	}
	public void setListServicio(List<Servicio> listServicio) {
		this.listServicio = listServicio;
	}
	
	
}

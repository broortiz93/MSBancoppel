package mx.solser.bpi.document;



import mx.solser.pv.documents.Base;


public class ReqBase extends Base {

	private static final long serialVersionUID = 1L;
	
	private String param = "";

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

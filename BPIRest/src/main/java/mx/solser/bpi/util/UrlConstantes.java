package mx.solser.bpi.util;

public abstract class UrlConstantes {

	private UrlConstantes() {
		throw new IllegalStateException("Constantes class");
	}


	public static final String TEST = "/admin/consulta"; // LOGIN PARA WEB
	public static final String CONSULTA_CAT = "controller/consultaCategorias.do"; // LOGIN PARA WEB
	public static final String LOGIN = "controller/login.do"; // LOGIN PARA WEB
	public static final String GET_SESSION = "controller/getSession.do";
	public static final String CLOSE_SESSION = "controller/cerrar.do";
	public static final String PRIVILEGIOS_POR_MENU = "controller/obtenerPrivilegiosPorMenu.do";
	
	public static final String PAGAR_SERVICIOS = "/pagarServicios.do"; // LOGIN PARA WEB
	
	public static final String CONSULTA_MONGO = "controller/consultaMongo.do"; // LOGIN PARA WEB

	
}

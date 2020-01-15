package mx.solser.bpi.config;

import java.io.File;


public abstract class Constantes {
	
	//ADMC
	public static final String CONFIRM_ACCEPT = "accept";
	public static final String CONFIRM_CANCEL = "cancel";
	
	// Servicios Controller DASHBOARD
	public static final String URLPREFIXDASHBOARD = "/controller";
	
	// Tipos de Header
	public static final String HEADER_STATUS = "COD_STATUS";
	public static final String HEADER_MESSAGE = "MESSAGE";
	public static final String HEADER_SESSION = "SESSION_PARAM";
	public static final String HEADER_CODE = "HEADER_CODE";
	public static final String HEADER_NAVEGADOR = "user-agent";
	public static final String HEADER_CONTET = "Content-Type";
	public static final String HEADER_ACCEPT = "Accept";
	
	// Tipos de  COD_STATUS en Header
		public static final String COD_STATUS_OK = "OK";
		public static final String COD_ERROR_00000 = "00000";
		public static final String COD_ERROR_00001 = "00001";
		public static final String COD_ERROR_00002 = "00002";
		public static final String COD_ERROR_00003 = "00003";
		public static final String COD_ERROR_00004 = "00004";
		public static final String COD_ERROR_00005 = "00005";
		public static final String COD_ERROR_00006 = "00006";
		public static final String COD_ERROR_00007 = "00007";
		public static final String COD_ERROR_00008 = "00008";
		public static final String COD_ERROR_00009 = "00009";
		public static final String COD_STATUS_ERROR = "ERROR";
		public static final String COD_MSGERROR_03 = "NO URL";
		public static final String COD_MSGERROR_02 = "MSJ NULL";
		public static final String COD_ERROR_EXITO = "EXITO";
		public static final String COD_ERROR_ERROR = "EXITO";

	
	public static final String CONTENT_TYPE = "application/json";
	public static final Integer RESET_NUM_INTENTOS_FALLIDOS = 0;
	public static final Integer INCREMENTAR_INTENTOS_FALLIDOS = 1;
	public static final String  SEPARATOR = "¬";
	
	public static final String  GRID_1 = "1";
	public static final String  GRID_2 = "2";
	public static final String  BLANK = "";
	public static final String  EMPTY = " ";
	public static final String  PASSWORD = "******";
	public static final String  TAG = "*#*";
	public static final String  PERCENT = "%";
	public static final String COMMA=",";
	
	final public static String 	XML = ".xml";
	final public static String 	COMPROBANTES = "Facturas_Empresa";
	final public static String 	COMPROBANTES_PROVEEDOR = "Facturas_Proveedor";
	final public static String 	FACTURA = "Factura";
	
	public static final String DOBLE_SESSION="Existe otra persona utilizando esta cuenta";
	
	
	
	public static final String HEADER_RESQUESTWITH_KEY = "x-requested-with";
	public static final String HEADER_TOKEN_AUT = "X-Auth-Token";
	

	    // Signing key for HS512 algorithm
	    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

	    // JWT token defaults
	    public static final String TOKEN_HEADER = "Authorization";
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String TOKEN_TYPE = "JWT";
	    public static final String TOKEN_ISSUER = "secure-api";
	    public static final String TOKEN_AUDIENCE = "secure-app";

	
	
	public final static String 	TXT=".txt";
	public final static String 	ZIP=".zip";
	public final static String  PDF=".pdf";
	public final static String 	EXCEL=".xls";
	
	
	public static final  String CONFIG_INTENTOS="INTENTOS";
	public static final String ELEMENT = "elemento";
	public static final String FLUJO = "flujo";
	public static final String FACTORY = "factory";
	public static final String ELEMENT_NULL = "null";

	
	public static final  String ORDER_CODIGO = "codigo";
	public static final  String ORDER_NOMBRE = "nombre";
	
	public static final  String ORDER_ID = "id";
	public static final int CERO = 0;
	public static final int UNO = 1;
	public static final int DOS = 2;	
	public static final int TRES = 3;
	public static final int CUATRO = 4;
	public static final String SEPARATOR_VALUE = "|";
	public static final int MENOS_UNO= -1;
	
	public static final String CADENA_VACIA = "";
	public static final boolean ALTA = true;
	public static final boolean BAJA = false;
	
	public static final String TIPO_CATALOGO = "PRODUCTO";
	public static final String RUTA_PRODUCTOS = "productos";
	
	public static final String RUTA_BASE="/puntoVenta";
	public static final String RUTA_FOTO_PERFIL = File.separator+"profile_photo"+File.separator;

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String LOCALE_ES = "es";
	public static final String LOCALE_MX = "MX";
	
	public static final String REGEX_LIKE= "i";
	
	public static final String RUTA_REPORTE = "reporte";
	public static final String RUTA_REPORTE_PLANTILLA = "plantilla";
	// Venta
	public static final String SECUENCIA_FOLIO_AUTORIZACION = "FOLIO_AUTORIZACION";
	public static final String SECUENCIA_ID = "_id";
	public static final String SECUENCIA_DES = "seq";
	public static final String AUTORIZACION_SOCKET = "/topic/acutualizaAutorizacion";
}

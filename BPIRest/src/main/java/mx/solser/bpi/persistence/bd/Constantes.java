package mx.solser.bpi.persistence.bd;

import java.math.BigDecimal;
import java.util.Locale;

public final class Constantes {
	/**
	 * Strings
	 */
	public static final String CERO_N 		=  "0";
	public static final String UNO_N 		=  "1";
	public static final String DOS_N 		=  "2";
	public static final String FIVE_N 		=  "5";
	public static final String SIX_N 		=  "6";
	public static final String UNO_NE_N  	=  "-1.0";
	public static final String TREINTA_N	=  "30";
	public static final String EQUAL 	 	=  "=";
	public static final String COLONS		=  ":";
	public static final String MONEY		=  "$ ";
	public static final String COMMA		=  ",";
	public static final String SPACE		=  " ";
	public static final String HYPHEN 		=  "-";
	public static final String PERCENT		=  "%";
	public static final String POINT		=  ".";
	
	/**
	 * Numeros int
	 */
	public static final int INT_1 	=  1;
	public static final int INT_2	=  2;
	public static final int INT_3 	=  3;
	public static final int INT_4 	=  4;
	public static final int INT_5 	=  5;
	public static final int INT_6 	=  6;
	public static final int INT_7 	=  7;
	public static final int INT_8 	=  8;
	public static final int INT_9 	=  9;
	public static final int INT_10 	=  10;
	public static final int INT_15 	=  15;
	public static final int INT_99	=  99;
	public static final int INT_100	=  100;
	public static final int INT_140	=  140;
	public static final int INT_151	=  151;
	
	/**
	 * Constantes para formato de fechas
	 */
	public static final String FORMATO_FECHA_MM_DIAG_DD_DIAG_YYYY = "MM/dd/yyyy";
	public static final String FORMATO_FECHA_DD_DIAG_MM_DIAG_YYYY = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_DD_MMM_YY = "dd/MMM/yy";
	public static final String FORMATO_FECHA_DD_MM_YY = "ddMMyy";
	public static final String FORMATO_FECHA_DD_MM_YYYY = "ddMMyyyy";
	public static final String FORMATO_FECHA_DDMMYYHHMMSSSS = "ddMMyyHHmmssSS";
	public static final String FORMATO_HORA_HHMM = "HH:mm";
	public static final String FORMATO_HORA_HHMMSS = "HH:mm:ss";
	public static final String FORMATO_HORA_HHMM_SIN_PUNTOS = "HHmm";
	public static final String FORMATO_FECHA_COMPLETA_BD = "yyyy-MM-dd hh:mm:ss";
	public static final String FORMATO_FECHA_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMATO_FECHA_MM_DD_YYYY = "MM-dd-yyyy";
	
	public static final String MD5 = "MD5";

	public static final BigDecimal BIG_DECIMAL_CERO = new BigDecimal("0.0");
	public static final Integer CERO = new Integer(CERO_N);
	public static final Integer UNO = new Integer(UNO_N);
	public static final Integer FIVE = new Integer(FIVE_N);
	public static final Integer SIX = new Integer(SIX_N);
	public static final Integer TREINTA = new Integer(TREINTA_N);
	
	public static final Integer TOKEN_BLOQ_INT_CVE_DINAMICA = Integer.valueOf(151);
	public static final Integer INTENTOS_LOGIN = 3;
	public static final String CANAL = "03"; 
	
	public static final Locale LOCALE_USA = new Locale("en", "US");
	
	/**
	 * Pago de servicios
	 */
	public static final String SERVICIO_TEL_RESID	= "02";
	public static final String SERVICIO_TV		 	= "06";
	public static final String SERVICIO_COSMETICOS  = "09002";
	public static final String SERVICIO_EDITORIALES = "09001";
	public static final String SERVICIO_PAGOSGDF 	= "08";
	public static final String SERVICIO_ELECTRICIDAD  = "04";
	public static final String BANCO_SPEI_TRANSFER  = "684";
	public static final String SERVICIO_MOVIL = "07";
	
	public static final String CHECKSUM1 = "1";
	public static final String CHECKSUM2 = "2";
	public static final String CHECKSUM3 = "3";
	public static final String CHECKSUM4 = "4";
	public static final String CHECKSUM5 = FIVE_N;
	public static final String SLASH = "/";
	public static final String BLANK = "";
	public static final String BLANK_SPACE = " ";
	
	/**
	 * Constantes Encryptado
	 */
	public static final String ID_CTE = "000000000";
	
	public static final String SI = "SI";
	public static final String NO = "NO";
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";
	public static final String ERROR_MIN = "error";
	public static final String TRUE = "true";
	
	public static final int TIPO_OP_9301 = 9301;   	//	
	public static final int TIPO_OP_9302 = 9302;   	//	

	public static final String SERVERPUERTOINFORMIX = "serverPuertoInformix";
	public static final String SERVERIPINFORMIX = "serverIPInformix";
	public static final String CLIENTEWEBPATH = "ClientWebPath";
	public static final String CLEINTEWEB = "ClientWeb";
	
	public static final String DRIVER_DATASOURCE 		 = "java:jboss/driver/SynDriver";
	public static final String DRIVER_DATASOURCE_SESSION = "java:jboss/driver/SynDriverSesion";
	public static final String DRIVER_DATASOURCE_HSM 	 = "java:jboss/driver/SynDriverHSM";
	

	public static final String ENCODING 	= "ISO-8859-1";
	public static final String COD_ERROR 	= "001";
	public static final String COD_ERROR00000 	= "00000";
	public static final String COD_ERROR00001 	= "00001";
	public static final String COD_ERROR00002 	= "00002";
	public static final String COD_ERROR00003 	= "00003";
	public static final String COD_ERROR00004 	= "00004";
	public static final String COD_ERROR00005 	= "00005";
	public static final String COD_ERROR00006 	= "00006";
	public static final String COD_ERROR00007 	= "00007";
	public static final String COD_ERROR00008 	= "00008";
	public static final String COD_ERROR00009 	= "00009";
	public static final String COD_ERROR00099 	= "00099";
	public static final String COD_ERROR99999 	= "99999";
	public static final String COD_ERROR00010 	= "00010";
	public static final String COD_ERROR00011 	= "00011";
	
	/*codigo de error para SolicitudChequera*/
	public static final String COD_ERROR00012 	= "00012";
	
	public static final String PORTALBPI 	= "PORTALBPI";
	
	/**
	 * Transacciones SPEI
	 */
	public static final String EN_CUENTA = "enCuenta";
	public static final String PAGO_PROG = "pagoProg";
	public static final String MSJ_HORARIO = "mensajeHorario";
	public static final String INDICE = "indice";
	public static final String CUENTA = "cuenta";
	public static final String SDO_CTA = "sdoCta";
	public static final String CTAS_AHORRO = "ctasAho";
	public static final String SALDO_DISP = "saldoDisp";
	public static final String TIPO_CTA = "tipoCta";
	public static final String PROD_COMIS_CERO = "prodComisCero";
	public static final String COMISION_SPEI = "comisionSPEI";
	public static final String VALOR_IVA = "valorIVA";
	public static final String SE_PROGRAMA = "sePrograma";
	public static final String HR_INICIO = "hrInicio";
	public static final String HR_FIN = "hrFin";
	public static final String FOCO = "foco";
	public static final String FEC_APLICACION = "fecAplicacion";
	public static final String FECHA_HOY = "fechaHoy";
	public static final String FECHA_OPERACION = "fechaOperacion";
	public static final String REF_PAGO_PROG = "referenciaPagoProg";
	public static final String SIVA = "sIva";
	public static final String MSG_ESTATUS_6 = "msgCtaOrigenEstatus6";
	public static final String MSG_ESTATUS_7 = "msgCtaOrigenEstatus7";
	public static final String CTA_ORIGEN = "ctaOrigen";
	public static final String CTA_DESTINO = "ctaDestino";
	public static final String NOM_BENEFICIARIO = "nomBeneficiario";
	public static final String NOM_BANCO = "nomBanco";
	public static final String CVE_BANCO = "cveBanco";
	public static final String DESC_TIPO_CUENTA = "descTipoCuenta";
	public static final String TIPO_CUENTA = "tipoCuenta";
	public static final String TIPO_CUENTA_ORIG = "tipoCuentaOrigen";
	public static final String TITULO = "Titulo";
	public static final String RES_HORARIOS_DTO = "resHorariosDTO";
	public static final String REQ_COMISIONIVA_DTO = "reqComisionIvaDTO";
	public static final String PAGE = "page";
	public static final String IVA = "iva";
	public static final String IMPORTE_TOTAL = "importeTotal";
	public static final String REFERENCIA_PAGO = "referenciaPago";
	public static final String REFERENCIA_COBRANZA = "referenciaCobranza";
	public static final String FEC_OPERACION = "fecOperacion";
	public static final String MONTO_MAX = "montoMaximo";
	public static final String CORREO_ELEC = "correoElec";
	public static final String BAN_REF_COBRANZA = "banderaRefCobranza";
	public static final String ETI_NUM_CTA_ORIGEN = "etiNumCtaOrigen";
	public static final String NUM_CTA_ORIGEN = "numCtaOrigen";
	public static final String ETI_NUM_CTA_DESTINO = "etiNumCtaDestino";
	public static final String NUM_CTA_DESTINO = "numCtaDestino";
	public static final String MASCARA = "mascara";
	public static final String TRANS_MONTO = "transMonto";
	public static final String COMISION = "comision";
	public static final String TRANS_MONTO_MONEDA = "transMontoMoneda";
	public static final String COMISION_MONEDA = "comisionMoneda";
	public static final String HR_INI = "hrIni";
	public static final String HR_SPEI = "hrSPEI";
	public static final String NUM_TOKEN = "numToken";
	public static final String ERROR_TRANS_SPEI = "errorTranSPEI";
	public static final String ONE_COLONS = "1:";
	public static final String HORA_ENVIO = "horaEnvio";
	public static final String NUM_RASTREO = "numRastreo";
	public static final String BAN_RFC = "banderaRfc";
	public static final String NOM_IMG_RFC = "nombreImagenRfc";
	public static final String DURA_SEGUNDOS = "duracionSegundos";
	public static final String TITULO_PDF = "tituloPDF";
	public static final String LEYENDA_PDF = "leyendaPDF";
	public static final String USUARIO_DTO = "usuarioDTO";
	public static final String RES_TRANSSPEIDTO = "resTransaccionSpeiDTO";
	public static final String REQ_TRANSSPEIDTO = "reqTransaccioneSpeiDTO";
	public static final String COMISION_M = "Comisi�n:";
	public static final String XXXX_XXX = "XXXX XXX";
	public static final String SMENSAJE_PROG = "sMensajeProg";
	public static final String REF_COBRO = "refCobro";
	public static final String CDESTINO = "cDestino";
	/** 
	 * Mensajes SPEI
	 */
	public static final String INFO_ESTATUS_7 = "info.articulo61.ctaOrigenEstatus7";
	public static final String INFO_ESTATUS_6 = "info.articulo61.ctaOrigenEstatus6";
	public static final String MSJ_HR_SPEI = "privado.frmTransferenciaSPEIConfirmar.hrSPEI";
	/**
	 * Errores SPEI
	 */
	public static final String TRANS_SPEI = "error.trans.spei";
	public static final String TOKEN_BLOQUEADO = "error.transfSPEI.tokenBloqueado";
	public static final String TOKEN_BLOQ_INACTIVO = "error.actvaToken.tokenBloqInactivo";
	public static final String TOKEN_ERRONEO = "error.transfSPEI.tokenErroneo";
	public static final String EXCEDIO_LIMITE = "error.excedioLimite";
	public static final String SALDO_INSUFICIENTE = "error.web.saldoInsuficiente";
	public static final String IMPORTE_EN_CEROS = "error.web.montoEnCeros";
	public static final String MONTO_INVALIDO = "error.web.montoInvalido";
	public static final String EXCEDIO_MTO_CTA_FREC = "error.excedioMontoCuentaFrecuente";
	public static final String TRANS_REPETIDA = "error.transaccionRepetida";
	public static final String TRANS_REPETIDA_VAL = "error.transaccionRepetidaVal";
	public static final String ERROR_TRANSFER_SPEI = "error.trans.speiTelTransfer";
	
	/**
	 * Actualizar Datos de Usuario
	 */
	public static final String TEL_INVALIDO = "telInvalido";
	public static final String CEL_INVALIDO = "celInvalido";
	public static final String INACTIVOS = "inactivos";
	public static final String ERROR_SP = "errorsp";
	public static final String EXTENSION = "extension";
	
	/**
	 * Edo de Cuenta
	 */
	public static final String NUM_CUENTA = "numeroCuenta";
		
	/**
	 * Compra de Tiempo Aire
	 * 
	 */
	public static final String NUM_CLAVE_TAE_301="301";
	public static final String NUM_CLAVE_TAE_304="304";
	public static final String NUM_CLAVE_TAE_313="313";
	public static final String NUM_CLAVE_TAE_314="314";
	public static final String NUM_CLAVE_TAE_312="312";
	private Constantes() {
		//Prevenir instanciacion
	}
}

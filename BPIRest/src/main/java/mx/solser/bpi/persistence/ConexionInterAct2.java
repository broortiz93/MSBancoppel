package mx.solser.bpi.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import syndein.informixInterface.Header;
import syndein.tools.cmn.cmn_InterAPI;




public class ConexionInterAct2 implements IConexionInterAct{
	private static final Logger LOG = LoggerFactory.getLogger(ConexionInterAct2.class.getName());
			
	private boolean isEjecutando = false;
	private long tiempoUltimaEjecucion = 0;
	private long id; 
	private cmn_InterAPI iapi =null;
	private String ip;
	private int puerto;
	private String instancia;
	private short timeOut;
	private int reintentos;
	private static final int DEFAULT_TIME_OUT_SEGUNDOS = 60;
	
	private ConexionInterAct2(final String ip, final int puerto, final String instancia, final int reintentos){
		super();
		this.ip = ip;
		this.puerto = puerto;
		this.instancia = instancia;
		this.reintentos = reintentos;
		this.timeOut = DEFAULT_TIME_OUT_SEGUNDOS;
	}
	
	private void connect() throws Exception{
		
	}
	
	
	public static ConexionInterAct2 creaInstancia(final String ip, final int puerto, final String instancia, final int reintentos){
		ConexionInterAct2 conexionInteract = null;
		ConexionInterAct2 conexionInteractTemp  = new ConexionInterAct2(ip,puerto,instancia,reintentos);
		try{
			conexionInteractTemp.connect();
			conexionInteract = conexionInteractTemp;
		}catch (Exception pe) {
			LOG.error("Error", pe);
		}
		return conexionInteract;
	}
	
	private boolean reconect(){
		LOG.info("reconectando la conexion: {} ", id);
		boolean flag = false;
		liberaConexion();
		try{
			connect();
			flag = true;
		}catch(Exception pe){
			pe.printStackTrace();
		}
		return flag;
	}
	
	public Header executeInterAct(final String tramaEjecutar, final String dataBase)throws Exception{
		Header header = null;
		setTiempoUltimaEjecucion(System.currentTimeMillis());
		StringBuffer respuesta;
		respuesta = new StringBuffer();
		setEjecutando(true);
		
		if("EXECUTE PROCEDURE sp_inicia_session_bpi('001', '5287F4D0D2186924', '5C31AF7FA8C9F692', '0:0:0:0:0:0:0:1' )".equals(tramaEjecutar)){ 
			header = new Header("000000000000000000bdibpi                                                                          001000445000  000001074           MONICA                                              MARTINEZ ULLOA            TORRES                    +00030+00000001402012-08-22 17:47:142012-08-22 17:48:43      52040                                                                                                                                                                                                                                                          ");
		}else if("EXECUTE PROCEDURE sp_inicia_session_bpi( '001', '5287F4D0D2186924', '5C31AF7FA8C9F692','127.0.0.1')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdibpi                                                                          001000445000  000001074           MONICA                                              MARTINEZ ULLOA            TORRES                    +00030+00000001402012-10-23 12:58:152012-10-23 12:59:17      52040                                                                                                                                                                                                                                                          ");
		}else if("EXECUTE PROCEDURE sp_inicia_session_bpi( '001', 'F3A0A35511ECF976', '6C6C7D9F3855A92F','127.0.0.1')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdibpi                                                                          001000445000  000001074           MONICA                                              MARTINEZ ULLOA            TORRES                    +00030+00000001402012-10-23 12:58:152012-10-23 12:59:17      52040                                                                                                                                                                                                                                                          ");
		}else if("EXECUTE PROCEDURE sp_actualizaraccesousuario('000001074')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdibpi                                                                          00100000500000"); 
		}else if("EXECUTE PROCEDURE sp_obtenerdatosusuario('000001074')".equals(tramaEjecutar)){	
			header = new Header("000000000000000000bdibpi                                                                          00100037700000solser13                                          52040      carlos.pantoja@dolsersistem.net                                                                     avatar1   El conocimiento alimenta el alma                                                                                                                                                                        1");
		}else if("EXECUTE PROCEDURE cons_cre_bpi('001', '000001074','01');".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000108000  000001074           600000064573        AA4268070201720531    A6001 TARJETA CREDITO BANCOPPEL VISA     "); 
		}else if("EXECUTE PROCEDURE cons_inv_pagare('001', '000001074',0);".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinvers                                                                        001000039000  300030000000578         11/25/2012");
		}else if("EXECUTE PROCEDURE cons_inv_sdo_pagare('001','30000000578         ',0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinvers                                                                        001000073159  10000.0         0.0             14.17           10000005016         ");
		}else if("EXECUTE PROCEDURE sp_consulta_saldos_general('001','600000064573        ')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001001056000000Se realiz� la consulta correctamente.                                           600000064573        0307/19/200707/16/2011320.0               06/26/2012+0000000000+00000000003900.0              65.0       36.0       0.0             1028.0              0.0                 0.0                 0.0                 1028.0              0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 0.0                 1028.0              1.1                 0.0                 2872.0              0.0                 VIGENTE NORMAL                                              +0000000000                                                                                                                  +0000000000                                                                            +0000000000                                                                           ");
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE encabezado2_edocta('001', '600000064573', ") >= 0){
			header = new Header("000000000000000000bdicred                                                                         001001354185  ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"); 
		}else if("EXECUTE PROCEDURE sc_cons_ctasdos_bpi('001', '000001074',0);".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         005000735000  10000505167         649890.0        0.0             649890.0        2000 CUENTA EFECTIVA                    0.0             137746100005051673000  11000000037         650000.0        0.0             650000.0        1100 INVERSION CRECIENTE                0.0             137180110000000372000  13000000021         646097.0        0.0             646097.0        1300 PRODUCTO BASICO DE NOMINA          0.0             137180130000000213000  18000402202         649693.0        0.0             649693.0        1800 CUENTA EFECTIVA PLUS               0.0             137180180004022027000  19000000011         653568.0        0.0             653550.08       1900 CUENTA EFECTIVA CHEQUES            -17.92          137730190000000111"); 
		//}else if ("EXECUTE PROCEDURE sp_consultMovsChq_bpi('001', '19000000011         ', '', '', '0')".equals(tramaEjecutar)) { //ERROR
		}else if ("EXECUTE PROCEDURE sp_consultMovsChq_bpi('001', '19000000011', '08/28/2012', '08/28/2012', 0)".equals(tramaEjecutar)) { //OK
			//ERROR
			//header = new Header("000000000000000000bdicheq                                                                         001000104100  01/01/1900                                                         0.0             0.0             ");
			//NO HAY
			//header = new Header("000000000000000000bdicheq                                                                         000000000");
			//OK
			header = new Header ("000000000000000000bdicheq                                                                         001000104000  08/28/20120239            TRASPASO EN CUENTA                      C1.0             653550.08       ");       
		}else if ("EXECUTE PROCEDURE consultMovsCre_bpi('001', '600000064573', '08/30/2012', '08/30/2012', 0)".equals(tramaEjecutar)) { //OK
			//OK 1 registro
			header = new Header ("000000000000000000bdicred                                                                         001000111000  08/30/20127139                   SU PAGO POR INTERNET                    A5.0             244.0           	");
			//OK varios registros
			//header = new Header ("000000000000000000bdicred                                                                         005000555000  08/30/20127139                   SU PAGO POR INTERNET                    A5.0             244.0           000  08/29/20127139                   SU PAGO POR INTERNET                    A150.0           244.0           000  08/29/20127139                   SU PAGO POR INTERNET                    A1.0             244.0           000  08/28/20127139                   SU PAGO POR INTERNET                    A128.0           244.0           000  08/28/20127139                   SU PAGO POR INTERNET                    A500.0           244.0           ");
			//NO HAY
			//header = new Header("000000000000000000bdicheq                                                                         000000000");
		}else if("EXECUTE PROCEDURE sp_obt_fec_edo_cta_deb('10000505167         ')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         003000093000  20100508/24/201009/23/2010000  20100406/24/201007/23/2010000  20100305/24/201006/23/2010"); 
		}else if("EXECUTE PROCEDURE sp_obt_fec_edo_cta_deb('13000000021         ')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         003000093000  20100202/09/201003/08/2010000  20100101/09/201002/08/2010000  20091212/09/200901/08/2010"); 
		}else if("EXECUTE PROCEDURE sp_obt_fec_edo_cta_deb('18000402202         ')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         000000000"); 
		}else if("EXECUTE PROCEDURE sp_obt_fec_edo_cta_deb('19000000011         ')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         003000093000  20120211/28/201112/27/2011000  20120111/28/201112/27/2011000  20120311/28/201112/27/2011");  
		}else if("EXECUTE PROCEDURE sp_obt_fec_edo_cta_cred('600000064573')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000015000  06/20/2012");
		}else if("EXECUTE PROCEDURE sp_obtener_fecha()".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         0010000252012-08-31 13:53:20      ");
		}else if("EXECUTE PROCEDURE sp_obtieneoperacionesdeldia('52040', '08-31-2012', 0, 10)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         002000312000   08/31/2012TRASPASO CTAS PROPIAS                             10000505167 18000402202 3400.00         08/31/20123108120823308521                        000   08/31/2012TRASPASO CTAS PROPIAS                             13000000021 10000505167 1.00            08/31/20123108120142535657                        ");
		}else if("EXECUTE PROCEDURE sp_obt_num_tarj_cred('600000064573','001')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000021000  4268070201720531");
		}else if("EXECUTE PROCEDURE encabezado_edocta('001','4268070201720531','06-20-2012')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001001330000  06/20/2012600000064573        000001074           4268070201720531    MONICA MARTINEZ ULLOA TORRES                                                                                                                          PRESA DE LAS TRUCHA           980 8                                                                                                                                                                     LAS QUINTAS                                                                                                             CULIACAN                                                                                                                SINALOA                                                                                                                 SUC. ANGEL, CULIACAN                                                                                                    RIOS INZUNZA KRISHNA                                                                                                                                  6677158466          04/20/2011800601/L/004/S/F/M/02/08/88/02681/00000/0411/00000/NDND/-/0/00320EACD8808191860004/100305/91072158/94134375/0220/003671/00980PRESA DEL AZUCAR Y BLVD XICOTENCATL     A UNA CUADRA DE OXXO                                                            ");
		}else if("EXECUTE PROCEDURE encabezado2_edocta('001', '600000064573', '06-20-2012')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001001354000  06/20/2012600000064573        320.0           0.0             1219.12         06/16/201206/20/20122905.14         400.0           0.0             0.0             0.0             0.0             153.15          22.59           0.0             0.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             320.0           156.18          24.27           0.0             0.0             0.0             0.0             0.0             2680.88         3900.0          05/21/201206/20/201231                                                                                                                                                                                                                                                             0.0             0.0             175.74          153.15          2905.14         0.0             ");
		}else if("EXECUTE PROCEDURE sp_obt_cant_det_cred('600000064573',06,2012)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000016000  +0000000021");
		}else if("EXECUTE PROCEDURE detalle_edocta('001', '600000064573','06-20-2012',0);".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         010103430000  06/20/2012600000064573        +00001+00001         USTED DEBIA                                                                                                                                                                                                                                                    978.56                          000  06/20/2012600000064573        +00002+0000122-MAY-12BERSHKA CULIACAN 508818 BME                                                                                                                                                                                                                                    1026.00                         000  06/20/2012600000064573        +00002+00002         0004112J6                                                                                                                                                                                                                                                                                      000  06/20/2012600000064573        +00002+00003         74540612022625567272903                                                                                                                                                                                                                                                                        000  06/20/2012600000064573        +00003+0000130-MAY-12SU PAGO POR INTERNET                                                                                                                                                                                                                                                           980.00          000  06/20/2012600000064573        +00004+000011-JUN-12 BERSHKA CULIACAN 920757 BME                                                                                                                                                                                                                                    398.00                          000  06/20/2012600000064573        +00004+00002         0004112J6                                                                                                                                                                                                                                                                                      000  06/20/2012600000064573        +00004+00003         74540612032625137278214                                                                                                                                                                                                                                                                        000  06/20/2012600000064573        +00005+000017-JUN-12 MISUSHI HUMAYA 287554                                                                                                                                                                                                                                          150.00                          000  06/20/2012600000064573        +00005+00002         PAGA750829S99                                                                                                                                                                                                                                                                                  ");
		}else if("EXECUTE PROCEDURE detalle_edocta('001', '600000064573','06-20-2012',10);".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         010103430000  06/20/2012600000064573        +00005+00003         74047482037034100090774                                                                                                                                                                                                                                                                        000  06/20/2012600000064573        +00006+0000113-JUN-12MISUSHI HUMAYA 243551                                                                                                                                                                                                                                          108.00                          000  06/20/2012600000064573        +00006+00002         PAGA750829S99                                                                                                                                                                                                                                                                                  000  06/20/2012600000064573        +00006+00003         74047482043042100017178                                                                                                                                                                                                                                                                        000  06/20/2012600000064573        +00007+0000114-JUN-12MZ GUADALUPE 491593 AZA                                                                                                                                                                                                                                        67.00                           000  06/20/2012600000064573        +00007+00002         431201HD8                                                                                                                                                                                                                                                                                      000  06/20/2012600000064573        +00007+00003         74524222044482260003942                                                                                                                                                                                                                                                                        000  06/20/2012600000064573        +00008+0000115-JUN-12SU PAGO POR INTERNET                                                                                                                                                                                                                                                           554.06          000  06/20/2012600000064573        +00009+0000115-JUN-12FARM LUX 532192 PQF 850725SW7                                                                                                                                                                                                                                  6.50                            000  06/20/2012600000064573        +00009+00002         74043802045001170580540                                                                                                                                                                                                                                                                        "); 
		}else if("EXECUTE PROCEDURE detalle_edocta('001', '600000064573','06-20-2012',20);".equals(tramaEjecutar)){ 
			header = new Header("000000000000000000bdicred                                                                         001000343000  06/20/2012600000064573        +00010+00001         USTED DEBE                                                                                                                                                                                                                                                     1200.00                         ");
		}else if("EXECUTE PROCEDURE sp_obt_cant_aclara_cred('600000064573',06,2012)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000016000  +0000000000");
		}else if("EXECUTE PROCEDURE sp_obt_cant_mensajes_cred('600000064573',06,2012)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000016000  +0000000012");
		}else if("EXECUTE PROCEDURE mensajes_edocta('001','600000064573','06-20-2012',0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         010105570000  06/20/2012600000064573        +00000+00000                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              000  06/20/2012600000064573        +00001+00001                                                                                                                                                                                                                                                               Si usted decide realizar solo los pagos m�nimos de su cuenta y no realiza m�s compras o                                                                                                                                                                        000  06/20/2012600000064573        +00001+00002                                                                                                                                                                                                                                                               disposiciones de efectivo, usted tardar� 15 meses en liquidar su adeudo. (Esto depender� de los                                                                                                                                                                000  06/20/2012600000064573        +00001+00003                                                                                                                                                                                                                                                               cambios en tasa seg�n sea el caso)                                                                                                                                                                                                                             000  06/20/2012600000064573        +00002+000013577.59                                                                                                                                                                                                                                                        Si usted paga $407.64 al mes y no realiza mas compras y/o disposiciones de efectivo, terminar� de                                                                                                                                                              000  06/20/2012600000064573        +00002+00002                                                                                                                                                                                                                                                               pagar su adeudo en 12 meses. (Esto depender� de los cambios en tasa seg�n sea el caso)                                                                                                                                                                         000  06/20/2012600000064573        +00003+00001                                                                                                                                                                                                                                                               La Tarjeta de Cr�dito BanCoppel VISA es un producto que cobra una tasa de inter�s fija revisable,                                                                                                                                                              000  06/20/2012600000064573        +00003+00002                                                                                                                                                                                                                                                               esto significa que la tasa puede cambiar mensualmente                                                                                                                                                                                                          000  06/20/2012600000064573        +00004+00001                                                                                                                                                                                                                                                               Le recordamos que el pago oportuno de su tarjeta de Cr�dito BanCoppel VISA, le permitir� mantener                                                                                                                                                              000  06/20/2012600000064573        +00004+00002                                                                                                                                                                                                                                                               un buen historial crediticio.                                                                                                                                                                                                                                  ");
		}else if("EXECUTE PROCEDURE pie_edocta('001','600000064573','06-20-2012');".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicred                                                                         001000114000  06/20/2012600000064573        5.42    65.00   89.90   1171.29             0  36.0            3.0             "); 
		}else if("EXECUTE PROCEDURE bdinteg:sp_agregarbitacora_bpi('2012-09-06 16:36:32','1007','5003',52040,'0:0:0:0:0:0:0:1','09-06-2012','600000064573','','0','1007','','','','')".equals(tramaEjecutar)){ 
			header = new Header("000000000000000000bdinteg                                                                         001000005000  ");	
		}else if("EXECUTE PROCEDURE bdinteg:sp_agregarbitacora_bpi('2012-09-06 16:36:32','1007','5003',52040,'127.0.0.1','09-06-2012','600000064573','','0','1007','','','','')".equals(tramaEjecutar)){ 
			header = new Header("000000000000000000bdinteg                                                                         001000005000  ");	
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE bdinteg:sp_agregarbitacora_bpi")>=0){
			header = new Header("000000000000000000bdinteg                                                                         001000005000  ");				
		}else if("EXECUTE PROCEDURE sp_consultacuentasfctesinhabiles('000001074','02',0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         000000000"); 	
		}else if("EXECUTE PROCEDURE sp_ConsultaCuentasDestino_bpi('000001074','02',0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         03510983500000 10020094287         ADRIAN  SANCHEZ ALVAREZ                                                                             137  BANCOPPEL, S. A.                             00                                                  01ADRIAN              SAAA891127GFA1.0               00000 10026172484         ADRIAN  HERNANDEZ ALCANTARA                                                                         137  BANCOPPEL, S. A.                             00                                                  01ADRIAN HDZ          HEAA860126SR01.0               00000 13001069220         ARTURO ALEJANDRO VAZQUEZ� FERNANDEZ                                                                 137  BANCOPPEL, S. A.                             055589632144mcervantes@mailbancoppel.com            01ALEX                             1000.0            00000 10017710228         ANASTACIO  CABRERA ROMUALDO                                                                         137  BANCOPPEL, S. A.                             00                                                  01ANASTACIO           CARA630429MX21.0               00000 10020093981         ANGEL  GONZALEZ GUERRA                                                                              137  BANCOPPEL, S. A.                             00                                                  01ANGEL               GOGA8804055861.0               00000 10029116950         ANGEL  ARGUELLO JUAREZ                                                                              137  BANCOPPEL, S. A.                             00                                                  01ANGEL ARGUELLO      AUJA881101BTA1.0               00000 18000620595         BIBIANA  GAXIOLA VERDUGO                                                                            137  BANCOPPEL, S. A.                             015559874158mcervantes@mailbancoppel.com            01BIBIGAGA                         1.0               00000 10000500203         BRENDA  VALDEZ DIAZ                                                                                 137  BANCOPPEL, S. A.                             00                                                  01BRENDA              VADB790101LI11.0               00000 10029146646         CINTHIA AZUCENA ESCORCIA RODRIGUEZ                                                                  137  BANCOPPEL, S. A.                             00                                                  01CINTHIA AZUCENA ESC EORC840419H701.0               00000 10025841960         CORNELIO  SEBA MALAGA                                                                               137  BANCOPPEL, S. A.                             00                                                  01CORNELIO            SEMC750506HS41.0               00000 10020338321         CRISTIAN RUBEN JAIMEZ BAEZ                                                                          137  BANCOPPEL, S. A.                             00                                                  01CRISTIAN            JABC841112E871.0               00000 10000005148         RICARDO  CARMONA RODRIGUEZ                                                                          137  BANCOPPEL, S. A.                             00                                                  01CTA TIPO 4          CARR6502223M41.0               00000 10022910707         DANIEL  MOZO BUSTAMANTE                                                                             137  BANCOPPEL, S. A.                             00                                                  01DANIEL MOZO         MOBD800601BI51.0               00000 10027984857         DANTE ABRAHAM MARTINEZ SERRATO                                                                      137  BANCOPPEL, S. A.                             00                                                  01DANTE ABRAHAM MTZ   MASD841013QU71.0               00000 10023113614         GERMAN  VAZQUEZ JIMENEZ                                                                             137  BANCOPPEL, S. A.                             00                                                  01GERMAN              VAJG620528JPA1.0               00000 10000005113         GLORIA  ORTIGOSA PEREZ                                                                              137  BANCOPPEL, S. A.                             00          ihernandezm@bancoppel.com               01GLORIA                           1.0               00000 10025843237         GONZALO  DE DIOS CHIPOL                                                                             137  BANCOPPEL, S. A.                             00                                                  01GONZALO             DICG800101UB61.0               00000 10008005368         GUADALUPE  MENDOZA RESENDIZ                                                                         137  BANCOPPEL, S. A.                             00                                                  01GUADALUPE MENDOZA   MERG700803N831.0               00000 10023166165         JAVIER DIEGO JUAREZ                                                                                 137  BANCOPPEL, S. A.                             00                                                  01JAVIER              JUXJ6801224W51.0               00000 18000000031         GERARDO  VILLAR ARRUBARRENA                                                                         137  BANCOPPEL, S. A.                             025555555555jcruz@bancoppel.com                     01JEFE DE JEFES                    1.0               00000 10023259627         JOSE DOLORES TINOCO FARIAS                                                                          137  BANCOPPEL, S. A.                             00                                                  01JOSE DOLORES TINOCO TIFD4804095N01.0               00000 10023169288         JOSE  GARCIA BARILLAS                                                                               137  BANCOPPEL, S. A.                             00                                                  01JOSE GARCIA BARILLASGABJ530415IYA1.0               00000 10025615234         JOSE LUIS GARCIA MENDOZA                                                                            137  BANCOPPEL, S. A.                             00                                                  01JOSE LUIS GARCIA    GAML830813NQ31.0               00000 10024855258         JUAN CARLOS HERNANDEZ ALCANTARA                                                                     137  BANCOPPEL, S. A.                             00                                                  01JUAN CARLOS HDZ     HEAJ780810EQ31.0               00000 10027743540         LORENZO  PIAGA CAPI                                                                                 137  BANCOPPEL, S. A.                             00                                                  01LORENZO PIAGA       PICL6104297661.0               00000 18000400021         CLAUDIA GUADALUP� CALETE RIVAS                                                                      137  BANCOPPEL, S. A.                             055599525366mcervantes@mailbancoppel.com            01LUPITA                           1.0               00000 10022798109         MARCELO  JIMENEZ CARREON                                                                            137  BANCOPPEL, S. A.                             00                                                  01MARCELO             JICM8404061M91.0               00000 10017406235         MARIA GABRIELA TOSCANO DUARTE                                                                       137  BANCOPPEL, S. A.                             00                                                  01MARIA GABRIELA TOSCATODG6504117I01.0               00000 10023109650         MARTIN  TRUJILLO CRUZ                                                                               137  BANCOPPEL, S. A.                             00                                                  01MARTIN              TUCM870701AM21.0               00000 10020339247         MARTIN  TRUJILLO CRUZ                                                                               137  BANCOPPEL, S. A.                             00                                                  01MARTIN R            TUCM870701AM21.0               00000 10023276920         MIGUEL ANGEL VAZQUEZ CAMACHO                                                                        137  BANCOPPEL, S. A.                             00                                                  01MIGUEL ANGEL        VACM760729KQ91.0               00000 10009686148         NICOLAS  COLIN GARCIA                                                                               137  BANCOPPEL, S. A.                             00                                                  01NICOLAS COLIN G     COGN641114T731.0               00000 10024859350         PABLO MAURO CRISANTO ROBLES                                                                         137  BANCOPPEL, S. A.                             00                                                  01PABLO MAURO         CIRP670206D911.0               00000 10027028972         PASCUAL  JIMENEZ CARREON                                                                            137  BANCOPPEL, S. A.                             00                                                  01PASCUAL JIMENEZ     JICP890320NR31.0               00000 18000161400         GERARDO  FRE FRE                                                                                    137  BANCOPPEL, S. A.                             015596332545mcervantes@mailbancoppel.com            01PRUEBAS4                         200000.0          "); 
		}else if("EXECUTE PROCEDURE sp_ConsultaCuentasDestino_bpi('000001074','02',35)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         01000281000000 10018036016         RENE  CABRERA ORDAZ                                                                                 137  BANCOPPEL, S. A.                             00                                                  01RENE                CAOR850713UP81.0               00000 10023327576         ROCIO  ESCOBAR VIZCARRA                                                                             137  BANCOPPEL, S. A.                             00                                                  01ROCIO               EOVR8109148W51.0               00000 10026188275         ROSALIO  CASTILLO TUFI�O                                                                            137  BANCOPPEL, S. A.                             00                                                  01ROSALIO CASTILLO    CATR890830K451.0               00000 10006563495         SALVADOR  CASTILLO ROSALES                                                                          137  BANCOPPEL, S. A.                             00                                                  01SALVADOR CASTILLO   CARS870806LK01.0               00000 10029329317         SANDRA ISABEL RUIZ RANGEL                                                                           137  BANCOPPEL, S. A.                             00                                                  01SANDRA ISABEL RUIZ  RURS8308286H11.0               00000 10066450258         SEBASTIAN  HERNANDEZ TOLEDO                                                                         137  BANCOPPEL, S. A.                             010445555555ABC@ABC.COM.MX                          01SEBAS                            1.0               00000 17000000531         SEBASTIAN  HERNANDEZ TOLEDO                                                                         137  BANCOPPEL, S. A.                             010445555555abc@abc.com.mx                          01SEBAS                            1.0               00000 19000000534         SEBASTIAN  HERNANDEZ TOLEDO                                                                         137  BANCOPPEL, S. A.                             010445555555abc@abc.com.mx                          01SEBAS                            1.0               00000 10006806550         SUSANA  GARCIA MENDOZA                                                                              137  BANCOPPEL, S. A.                             00                                                  01SUSANA GARCIA       GAMS8501283211.0               00000 22000000012         UNION DE HABITANTES DEL PR                                                                          137  BANCOPPEL, S. A.                             010445566555abc@abc.com.mx                          01UH MORAL                         1.0               "); 
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE encabezado2_edocta('001', '600000064573'")>=0){
				header = new Header("000000000000000000bdicred                                                                         001001354185  ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"); 
		}else if("EXECUTE PROCEDURE sp_obtenerDatos_mismoBanco('001','22000000012')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         001000151000  2200 CUENTA EJE EMPRESARIAL CHEQUES       UNION DE HABITANTES DEL PREDIO XALPA                                                                    ");			
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE sp_obtenerDatos_mismoBanco('001'")>=0){
			
		}else if("EXECUTE PROCEDURE sp_obt_rfccte('000001074')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000018000  MAT7103167C3 ");			
		}else if("EXECUTE PROCEDURE sp_incrementafolio()".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdibpi                                                                          00100001600000+0000000059");			
		}else if("EXECUTE PROCEDURE sp_obt_valoriva(47)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000021000  0.16            ");			
		}else if("EXECUTE PROCEDURE sp_obt_cve_banco('012')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000016000  +0000040012");						
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE sp_regordenctecte_pp('001','5003','transBPI',40012,10.0,'0274',") >= 0 ){
			header = new Header("000000000000000000bdispei                                                                         001000135000                                                                                                      COPL5003TRANSBPI0009510       ");			
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE sp_valfecha_banca('001', ") >= 0){
			header = new Header("000000000000000000bdinteg                                                                         001000265000                                                                                                                                                                                                                                                            10/23/2012");
		}else if("EXECUTE PROCEDURE sp_obtenerbanderaoperacion(3)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdibpi                                                                          00100000600000f");
		}else if("EXECUTE PROCEDURE sp_obt_comision('TARIFA 0')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdispei                                                                         001000021000  5.0             ");
		}else if("EXECUTE PROCEDURE sp_obt_valoriva('47')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000021000  0.16            ");
		}else if("EXECUTE PROCEDURE sp_obt_rfccte('000001074')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000018000  MAT7103167C3 ");
		}else if("EXECUTE PROCEDURE sp_consultacuentasfctesinhabiles('000001074', '03', 0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         000000000");
		}else if("EXECUTE PROCEDURE sp_ConsultaCuentasDestino_bpi('000001074', '03', 0)".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdiprog                                                                         00100028100000 5204163713614492    JOSE ARTURO CRUZ ESPINO                                                                             002  BANCO NACIONAL DE MEXICO, S.A.               015555555555chuchita@bancoppel.com                  03A2R2                             20000.0           00000 4152312021708374    JOSE ANTONIO                                                                                        012  BANCOMER,S.A.                                031234567889joanlo5@gmail.com                       03AJL                              100000.0          00000 4152312021707929    ANTONIO                                                                                             012  BANCOMER,S.A.                                00                                                  03AL                               1.0               00000 4152312098762217    ANTONIO2                                                                                            012  BANCOMER,S.A.                                031234534534antonio.lozada@solsersistem.net         03AL2                              1.0               00000 4152312021707923    ANTONIO3                                                                                            012  BANCOMER,S.A.                                00                                                  03AL3                              1000.0            00000 4152312021988726    ANTONIO4                                                                                            012  BANCOMER,S.A.                                00                                                  03AL4                              1.0               00000 4152312021897366    ANTONIO5                                                                                            012  BANCOMER,S.A.                                00                                                  03AL5                              1111.0            00000 5579056211636503    BIBIANA                                                                                             014  BANCO SANTANDER, S. A.                       015589963222mcervantes@mailbancoppel.com            03BIBIGAGA                         1.0               00000 044180001095128377  CUENTA CLABE                                                                                        044  SCOTIABANK INVERLAT, S. A.                   015598963333mcervantes@mailbancoppel.com            02CLABE                            1.0               00000 002180019469296694  ALEJANDRO                                                                                           002  BANCO NACIONAL DE MEXICO, S.A.               035553060606ihernandezm@bancoppel.com               02CUENTA ALEJANDRO    AAAA111111AA11.0               00000 002560407800077552  ARTURO LOPEZ                                                                                        002  BANCO NACIONAL DE MEXICO, S.A.               00                                                  02CUENTA ARTURO       AAAA111111AA11.0               00000 002180006344520870  ARTURO RODRIGUEZ                                                                                    002  BANCO NACIONAL DE MEXICO, S.A.               00                                                  02CUENTA ARTURO R     SDFS222222AA21.0               00000 032180000103505277  CONCEPCION                                                                                          032  IXE BANCO,S.A.                               00                                                  02CUENTA CONCHA       AAAA111111AA11.0               00000 012180004452602054  ELOISA YOLANDA                                                                                      012  BANCOMER,S.A.                                00                                                  02CUENTA ELOISA       ABCD123456EF11.0               00000 4027660200003376    FERNANDO ROJAS                                                                                      007  CITIBANK MEXICO, S. A.                       00                                                  03CUENTA FERNANDO     AAAA111111AA11.0               00000 4008123456781234    HUGO PEREZ                                                                                          002  BANCO NACIONAL DE MEXICO, S.A.               00                                                  03CUENTA HUGO         AAAA111111AA11.0               00000 012180001598533831  ISMAEL HERNANDEZ                                                                                    012  BANCOMER,S.A.                                00                                                  02CUENTA ISMAEL       AAAA111111AA11.0               00000 002180028733486189  JAIME                                                                                               002  BANCO NACIONAL DE MEXICO, S.A.               00                                                  02CUENTA JAIME        AAAA111111AA11.0               00000 4334540200123309    JUAN PEREZ                                                                                          032  IXE BANCO,S.A.                               00                                                  03CUENTA JUAN         AAAA111111AA11.0               00000 8152310704669234    LUIS ACOSTA                                                                                         002  BANCO NACIONAL DE MEXICO, S.A.               00                                                  03CUENTA LUIS         AAAA111111AA11.0               00000 044180001001358018  MARIA DE LOS                                                                                        044  SCOTIABANK INVERLAT, S. A.                   00                                                  02CUENTA MARIA        AAAA111111AA11.0               00000 012180004457603485  RAUL                                                                                                012  BANCOMER,S.A.                                00                                                  02CUENTA RAUL         AAAA111111AA11.0               00000 1234123457689012    JUAN PEREZ PERZ                                                                                     003  BANCA SERFIN S.A.                            00                                                  03CUENTA TD JUAN      AAAA111111AA11.0               00000 4152310860572256    JOAQUIN LOPEZ D                                                                                     012  BANCOMER,S.A.                                00                                                  03JQ                               1.0               00000 032180000100130917  GUADALUPE TORRES DE MARTINEZ ULLOA                                                                  032  IXE BANCO,S.A.                               00          lupitatorres400@hotmail.com             02MAMA                MATM7103167C31.0               00000 5579100000517183    MAURICIO COPPEL                                                                                     014  BANCO SANTANDER, S. A.                       00          ihernandezm@bancoppel.com               03MAURICIO                         1.0               00000 014180605120396141  JOSE ANTONIO MERIGO LAMBARRI                                                                        014  BANCO SANTANDER, S. A.                       015518504386joseantonio.merigo@barcap.com           02PEPE                MELA700711L221.0               00000 4152310654369264    JAIME GONZALEZ                                                                                      012  BANCOMER,S.A.                                045554542323ihernandezm@bancoppel.com               03PRUEBA 2                         1.0               00000 002743417201376643  SANDRA MARIA RAMIREZ SAMANO                                                                         002  BANCO NACIONAL DE MEXICO, S.A.               015596333333mcervantes@mailbancoppel.com            02PRUEBAS1                         1000.0            00000 4059303575845391    SANDRA MARIA MARTINEZ SAMANO                                                                        002  BANCO NACIONAL DE MEXICO, S.A.               015589621488mcervantes@mailbancoppel.com            03PRUEBAS3                         1000.0            00000 4334540200123310    MARIA CONCEPCION                                                                                    032  IXE BANCO,S.A.                               00                                                  03TARJETA CONCHA      AAAA111111AA11.0               00000 4152310333642362    FABIO TORRES                                                                                        012  BANCOMER,S.A.                                00                                                  03TARJETA FABIO       AAAA111111AA11.0               ");
		}else if("EXECUTE PROCEDURE sp_obt_cve_banco('002')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         001000016000  +0000040002");						
		}else if(tramaEjecutar.indexOf("EXECUTE PROCEDURE sp_regordenctecte_pp('001','5003','transBPI',") >= 0){
			header = new Header("000000000000000000bdispei                                                                         001000135000                                                                                                      COPL5003TRANSBPI000796        ");
		}else if("EXECUTE PROCEDURE cons_sdoschq_bpi('001', '13000000021', '0000000000000000')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         001000127000  618313.2        0.0             618295.28       1900 CUENTA EFECTIVA CHEQUES            -17.92          137730190000000111");
		}else if("EXECUTE PROCEDURE cons_sdoschq_bpi('001','19000000011','0000000000000000')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicheq                                                                         001000127000  618313.2        0.0             618295.28       1900 CUENTA EFECTIVA CHEQUES            -17.92          137730190000000111");
		}else if("EXECUTE PROCEDURE sp_obt_direcciones_cliente('000001074')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdinteg                                                                         002000744000  +00000000011CASA                -BUQUE SONORA                           3620                2 DE FEBRERO                                                ACAPULCO DE JUAREZ                                                                                  GUERRERO                      39750                                                                                000  +00000000033ENVIOS              1 CDA DE MIRAVALLE                      25411     52        EL CHICO                                                    ATOYAC DE ALVAREZ                                                                                   GUERRERO                      40930                                                                                ");
		}else if("EXECUTE PROCEDURE sp_validar_promedio_chequera('19000000011', '21')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000065000  100                                                         ");
		}else if("EXECUTE PROCEDURE sp_validar_num_cheques('001', '19000000011', '3')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000005000  ");
		}else if("EXECUTE PROCEDURE sp_conchequera_xactivar('001','19000000011','0')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000128000  Mancomunado         ??????????+0000000051+0000000075+00000000253         Entregada                                         ");
		}else if("EXECUTE PROCEDURE sp_conchequera_xactivar('001','19000000011','10')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000128001  Mancomunado         ??????????+0000000000+0000000000+00000000000                                                           ");
		}else if("EXECUTE PROCEDURE sp_concheques_bpi('001','19000000011','3','0','0')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       010001040000  +0000000075+0000000051E11/12/20120.0             Entregado                                         000  +0000000075+0000000052E11/12/20120.0             Entregado                                         000  +0000000075+0000000053E11/12/20120.0             Entregado                                         000  +0000000075+0000000054E11/12/20120.0             Entregado                                         000  +0000000075+0000000055E11/12/20120.0             Entregado                                         000  +0000000075+0000000056E11/12/20120.0             Entregado                                         000  +0000000075+0000000057E11/12/20120.0             Entregado                                         000  +0000000075+0000000058E11/12/20120.0             Entregado                                         000  +0000000075+0000000059E11/12/20120.0             Entregado                                         000  +0000000075+0000000060E11/12/20120.0             Entregado                                         ");
		}else if("EXECUTE PROCEDURE sp_concheques_bpi('001','19000000011','3','0','10')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       010001040000  +0000000075+0000000061E11/12/20120.0             Entregado                                         000  +0000000075+0000000062E11/12/20120.0             Entregado                                         000  +0000000075+0000000063E11/12/20120.0             Entregado                                         000  +0000000075+0000000064E11/12/20120.0             Entregado                                         000  +0000000075+0000000065E11/12/20120.0             Entregado                                         000  +0000000075+0000000066E11/12/20120.0             Entregado                                         000  +0000000075+0000000067E11/12/20120.0             Entregado                                         000  +0000000075+0000000068E11/12/20120.0             Entregado                                         000  +0000000075+0000000069E11/12/20120.0             Entregado                                         000  +0000000075+0000000070E11/12/20120.0             Entregado                                         ");
		}else if("EXECUTE PROCEDURE sp_concheques_bpi('001','19000000011','3','0','20')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       005000520000  +0000000075+0000000071E11/12/20120.0             Entregado                                         000  +0000000075+0000000072E11/12/20120.0             Entregado                                         000  +0000000075+0000000073E11/12/20120.0             Entregado                                         000  +0000000075+0000000074E11/12/20120.0             Entregado                                         000  +0000000075+0000000075E11/12/20120.0             Entregado                                         ");
		}else if("EXECUTE PROCEDURE sp_concheques_bpi('001','19000000011','3','0','30')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000104005  +0000000000+0000000000???????????0.0             ??????????????????????????????????????????????????");
		}else if("EXECUTE PROCEDURE sp_cambincompleta_solicitarchqra('001', '19000000011', 3, 'transBPI')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000104000  ????????????????????+0000000004");
		}else if("EXECUTE PROCEDURE sp_actcanchequera('001', '19000013784', 1, 1, 0, 'transBPI')".equals(tramaEjecutar)){
			header = new Header("000000000000000000bdicntchq                                                                       001000104000  ");
		}else{
			LOG.debug("trama faltante: [{}]",tramaEjecutar);
		}
		
		
		
		
		
				 		
		
		
		
		setEjecutando(false);
	
		setTiempoUltimaEjecucion(System.currentTimeMillis());
		return header;
	}
	
	
	public void closeConexion(){
		InterActDataSource4 dataSource = InterActDataSource4.getInstance();
		dataSource.liberaConexion();
	}
	
	public void liberaConexion(){
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
		ConexionInterAct2 other = (ConexionInterAct2) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public Header executeInterAct(String tramaEjecutar, String database,
			int numRegistros) throws Exception {
		return executeInterAct(tramaEjecutar, database);
	}

	/* (non-Javadoc)
	 * @see mx.com.solser.architecbase.bancoppel.persistencia.conexion.IConexionInterAct#executeInterAct(java.lang.String, java.lang.String, mx.com.solser.bancoppel.architecbase.beans.core.ArchitectSessionBean)
	 */



	/* (non-Javadoc)
	 * @see mx.com.solser.architecbase.bancoppel.persistencia.conexion.IConexionInterAct#executeInterAct(java.lang.String, java.lang.String, int, mx.com.solser.bancoppel.architecbase.beans.core.ArchitectSessionBean)
	 */


	@Override
	public Header executeInterAct(String tramaEjecutar, String database,
			cmn_InterAPI con) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Header executeInterAct(String tramaEjecutar, String database,
			int numRegistros, cmn_InterAPI con) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

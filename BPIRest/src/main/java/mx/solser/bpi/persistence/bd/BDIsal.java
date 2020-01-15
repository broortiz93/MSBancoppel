/**
* Todos los derechos reservados
* BDIsal.java
*
* Control de versiones:
*
* Version Date/Hour 	         By 		Company 	Description
* ------- --------------- ----------- -------- -----------------------------------------------------------------
* 1.0 	14/09/2012 10:05:51 	Carlos Chong	 Solser 	 Creacion
*
*/
package mx.solser.bpi.persistence.bd;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.solser.bpi.persistence.Executor;
import mx.solser.bpi.persistence.PersistenciaConstantes;
import syndein.informixInterface.Header;

@Repository
public class BDIsal {
	
	private final Executor executor = Executor.getInstance();
	
	private final static String[] indices = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private final static String[] indices2 = {"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	private final static String SP_CONS_CTADOS_BPI = "EXECUTE PROCEDURE sc_cons_ctasdos_bpi(''{0}'', ''{1}'',{2});";
	
	
	
	public ResCuentasEfectivasDTO getCuentasEfectivas(final String numCliente){
		final int NUM_REG_X_CONSULTA = 10;
		ResCuentasEfectivasDTO resCuentasEfectivasDTO = new ResCuentasEfectivasDTO();
		EfectivaDTO efectivaDTO = null;
		List<EfectivaDTO> efectivasDTOList = new ArrayList<EfectivaDTO>();
		String trama = "";
		Header oRec = null;
		int longTrama = 0;
		int numRenglones = 0;
		int longRegistros = 0;
		int iContReg = 0;
		int ind1 = 0;
		int ind2 = 0;
		String stTarjeta = "000000000000";
		String stCuenta = "X";
		boolean flagContinua = true;

		try{
			do{
				oRec = null;
				longRegistros = 0;
				longTrama = 0;
				
				trama = MessageFormat.format(SP_CONS_CTADOS_BPI, 
						new Object[]{PersistenciaConstantes.NUM_EMPRESA,numCliente,iContReg});
				oRec = executor.executeInterAct(trama,"bdicheq"  );
				if(oRec != null){ 
					longTrama = oRec.get_io_buf().length();
					numRenglones = new Integer(oRec.get_num_rows()).intValue();
				} else {
					longTrama = 0;
					numRenglones = 0;
				}
				
				
				if(numRenglones>0){
					longRegistros = longTrama / numRenglones;
					String tramaRegistros = oRec.get_io_buf();
					String codRet = tramaRegistros.substring(0, 5);
					Integer codRetNum = Integer.valueOf(codRet.trim());
					String statusArt61="";
					if (codRetNum.intValue() == 0){
						for(int index = 0;index<numRenglones; index++){
							if (ind1 > 25){
								ind1 = 0;
								ind2++;
							}
							statusArt61="NO";
							if(tramaRegistros.length()>=148){
								if("2000,1300,1400,1700,1900,1800,2500".contains(tramaRegistros.substring(73, 77)) && (
										tramaRegistros.substring(147, 148).equals("7") || tramaRegistros.substring(147, 148).equals("6"))){
									statusArt61=tramaRegistros.substring(147, 148);	
								}
							}
							efectivaDTO = getEfectivaDTO(tramaRegistros,stCuenta,stTarjeta, indices2[ind2] + indices[ind1], statusArt61);
							efectivasDTOList.add(efectivaDTO);
							ind1++;
							tramaRegistros = tramaRegistros.substring(longRegistros);
						}

					}else{
						flagContinua = false;
						break;
					}
				}else if(iContReg>0){
					break;
				}else{
					flagContinua = false;
					break;
				}
				iContReg = iContReg + NUM_REG_X_CONSULTA;
			}while (numRenglones == NUM_REG_X_CONSULTA);
			if(flagContinua){
				resCuentasEfectivasDTO.setEfectivaDTOList(efectivasDTOList);
				resCuentasEfectivasDTO.setCodigoError("EXITO");
			}else{
				resCuentasEfectivasDTO.setCodigoError("ERROR");
			}
			
		}catch(Exception e){
			resCuentasEfectivasDTO.setCodigoError("ERROR");
			e.printStackTrace();
		}
		return resCuentasEfectivasDTO;
	}



	public static EfectivaDTO getEfectivaDTO(final String trama,final String cta, final String tarjeta, final String indice, final String statusArt61){
		
	 final int EFECTIVA_TOTAL_LEN = 127;
		final int EFECTIVA_CFDI_TOTAL_LEN = 86;
		 final int EFECTIVA_TIPO_CUENTA_LEN = 40;
		final int EFECTIVA_CFDI_TIPO_CUENTA_LEN = 55;
		final int EFECTIVA_CUENTA_LEN = 		20;
		final int EFECTIVA_TARJETA_LEN =		20;
		 int EFECTIVA_CLABE_LEN = 		18;
		 int EFECTIVA_SALDO_DISP_LEN =	16;
		 int EFECTIVA_RETENIDO_LEN = 	16;
		 int EFECTIVA_SALDO_TOTAL_LEN =	16;
		 int EFECTIVA_SALDO_CONG_LEN = 16;
		 int EFECTIVA_SERV_ELEC_LEN = 1;
		 int EFECTIVA_STATUS_PORTABILIDAD_LEN = 1;
		EfectivaDTO efectivaDTO = new EfectivaDTO();
		String saldoDisponible = "";
		String retenido = "";
		String saldoTotal = "";
		String saldoCongelado = "";
		String statusServElec = "";
		String statusPortabilidad = "";
		int aux = 0;
		if (cta.equals("X")){
				aux = EFECTIVA_CUENTA_LEN;
		}
		efectivaDTO.setCuenta(cta.trim());
		efectivaDTO.setStatusArt61(statusArt61);
		if( trama.length() >= (EFECTIVA_TOTAL_LEN + aux)){
			int iPos = 5;
			
			if (cta.equals("X")){
				efectivaDTO.setCuenta(trama.substring(iPos, iPos + EFECTIVA_CUENTA_LEN).trim());
			}
			
			iPos = 5 + aux;
			
			//iPos = 210;		
			saldoDisponible = trama.substring(iPos, iPos + EFECTIVA_SALDO_DISP_LEN);
			
			efectivaDTO.setSaldoDisponible(Constantes.BIG_DECIMAL_CERO);
			if(!"????????????????".equals(saldoDisponible)){
				efectivaDTO.setSaldoDisponible(new BigDecimal(saldoDisponible.trim()));
			}

			//iPos = 226;
			iPos = 21 + aux;
			retenido = trama.substring(iPos, iPos + EFECTIVA_RETENIDO_LEN);
			efectivaDTO.setRetenido(Constantes.BIG_DECIMAL_CERO);
			if(!"????????????????".equals(retenido)){
				efectivaDTO.setRetenido(new BigDecimal(retenido.trim()));
			}
			
			//iPos = 274;
			iPos= 37 + aux;
			saldoTotal = trama.substring(iPos, iPos + EFECTIVA_SALDO_TOTAL_LEN);
			efectivaDTO.setSaldoTotal(Constantes.BIG_DECIMAL_CERO);
			if(!"????????????????".equals(saldoTotal)){
				efectivaDTO.setSaldoTotal(new BigDecimal(saldoTotal.trim()));
			}
			
			//iPos = 291;
			iPos = 53 + aux;

			efectivaDTO.setTipoCuenta(trama.substring(iPos, iPos + EFECTIVA_TIPO_CUENTA_LEN));
			
			//iPos = 387;
			iPos = 93 + aux;
			saldoCongelado = trama.substring(iPos, iPos + EFECTIVA_SALDO_CONG_LEN);
			efectivaDTO.setSaldoCongelado(Constantes.BIG_DECIMAL_CERO);
			if(!"????????????????".equals(saldoCongelado)){
				efectivaDTO.setSaldoCongelado(new BigDecimal(saldoCongelado.trim()));
			}
			
			//iPos= 453;
			iPos = 109 + aux;
			if (efectivaDTO.getTipoCuenta().substring(0,4).compareTo("1100") != 0){
				efectivaDTO.setClabe(trama.substring(iPos, iPos + EFECTIVA_CLABE_LEN));
			}else{
				efectivaDTO.setClabe("------------------");
			}
			
			efectivaDTO.setCuentaTipoDebito(efectivaDTO.getCuenta().toString() + " - " + efectivaDTO.getTipoCuenta());
			
			//DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
			
			efectivaDTO.setEtiCuenta("*******" + efectivaDTO.getCuenta().trim().substring(7) + " - " + efectivaDTO.getTipoCuenta());
			
			efectivaDTO.setEtiCuentaTerceros(efectivaDTO.getCuenta().trim() + " - " + efectivaDTO.getTipoCuenta());
			efectivaDTO.setNumTarjeta(tarjeta);
			efectivaDTO.setIndice(indice);
			
			if(trama.length() > 148){
				iPos = 128 + aux;
				statusServElec = trama.substring(iPos, iPos + EFECTIVA_SERV_ELEC_LEN);
				efectivaDTO.setStatusServElec(statusServElec);
			}
			
			if(trama.length()>148){
				iPos = 129 + aux;
				statusPortabilidad = trama.substring(iPos, iPos + EFECTIVA_STATUS_PORTABILIDAD_LEN);
				efectivaDTO.setStatusPortabilidad(statusPortabilidad);
				//EFECTIVA_STATUS_PORTABILIDAD_LEN
			}
		}else{
			throw new ArrayIndexOutOfBoundsException("Longitud de la trama incorrecta");
		}
		
		return efectivaDTO;
	}
	

}


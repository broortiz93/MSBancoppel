/**
* Todos los derechos reservados
* EfectivaDTO.java
*
* Control de versiones:
*
* Version Date/Hour 	By 		Company 	Description
* ------- --------------- ----------- -------- -----------------------------------------------------------------
* 1.0 	16/08/2012 12:31:22 	Carlos Chong	 Solser 	 Creacion
*
*/
package mx.solser.bpi.persistence.bd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import mx.solser.bpi.util.Format;
import mx.solser.bpi.util.Utilerias;




public class EfectivaDTO implements Serializable,Comparable<EfectivaDTO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2845868606573185869L;
	private String tipoCuenta;
	private String cuenta;
	private String etiCuenta;
	private String etiCuentaTerceros;
	private String clabe;
	private BigDecimal saldoDisponible = new BigDecimal("0.0");
	private BigDecimal retenido = new BigDecimal("0.0");
	private BigDecimal saldoTotal = new BigDecimal("0.0");
	private BigDecimal saldoCongelado = new BigDecimal("0.0");
	private String cuentaTipoDebito;
	private String numTarjeta;
	private String indice;
	private String statusArt61="";
	private String statusServElec;
	private String statusPortabilidad="";
	private String detCuentas;
	
	public  String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	
	public EfectivaDTO(){
		setTipoCuenta("");
		setCuenta("");
		setClabe("");
	}

	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe.trim();
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}


	public BigDecimal getRetenido() {
		return retenido;
	}
	public String getStrRetenido() {
		return Format.formateaNumerico(retenido,new Locale("en", "US"));
	}
	public void setRetenido(BigDecimal retenido) {
		this.retenido = retenido;
	}
	
	
	
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public String getStrSaldoDisponible() {
		return Format.formateaNumerico(saldoDisponible,new Locale("en", "US"));
	}
	
	public String getStrSaldoDisponibleTDCB() {
		return Utilerias.formateaNumeroToMoneda(saldoDisponible);
	}
	
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}
	public String getStrSaldoTotal() {
		return Format.formateaNumerico(saldoTotal, new Locale("en", "US"));
	}
	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta.trim();
	}

	public BigDecimal getSaldoCongelado() {
		return saldoCongelado;
	}
	public String getStrSaldoCongelado() {
		return Format.formateaNumerico(saldoCongelado,new Locale("en", "US"));
	}
	public void setSaldoCongelado(BigDecimal saldoCongelado) {
		this.saldoCongelado = saldoCongelado;
	}
	public String getCuentaTipoDebito() {
		return cuentaTipoDebito;
	}
	public void setCuentaTipoDebito(String cuentaTipoDebito) {
		this.cuentaTipoDebito = cuentaTipoDebito;
	}
	public String getEtiCuenta() {
		return etiCuenta;
	}
	public void setEtiCuenta(String etiCuenta) {
		this.etiCuenta = etiCuenta;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getEtiCuentaTerceros() {
		return etiCuentaTerceros;
	}
	public void setEtiCuentaTerceros(String etiCuentaTerceros) {
		this.etiCuentaTerceros = etiCuentaTerceros;
	}
	public String getStatusArt61() {
		return statusArt61;
	}
	public void setStatusArt61(String statusArt61) {
		this.statusArt61 = statusArt61;
	}
	public String getStatusServElec() {
		return statusServElec;
	}
	public void setStatusServElec(String statusServElec) {
		this.statusServElec = statusServElec;
	}
	
	public String getStatusPortabilidad() {
		return statusPortabilidad;
	}
	public void setStatusPortabilidad(String statusPortabilidad) {
		this.statusPortabilidad = statusPortabilidad;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EfectivaDTO o) {
		int resp = 0;
		if(o!=null){
			resp = this.tipoCuenta.compareTo(o.tipoCuenta);
			if(resp==0){
				resp = this.cuenta.compareTo(o.cuenta);
			}
		}
		return resp;
	}
	@Override
	public String toString() {
		return "EfectivaDTO [tipoCuenta=" + tipoCuenta + ", cuenta=" + cuenta
				+ ", etiCuenta=" + etiCuenta + ", etiCuentaTerceros="
				+ etiCuentaTerceros + ", clabe=" + clabe + ", saldoDisponible="
				+ saldoDisponible + ", retenido=" + retenido + ", saldoTotal="
				+ saldoTotal + ", saldoCongelado=" + saldoCongelado
				+ ", cuentaTipoDebito=" + cuentaTipoDebito + ", numTarjeta="
				+ numTarjeta + ", indice=" + indice + ", statusArt61="
				+ statusArt61 + ", statusServElec=" + statusServElec + statusPortabilidad + "]";
	}
	
	public String getDetCuentas() {
		return detCuentas;
	}
	public void setDetCuentas(String detCuentas) {
		this.detCuentas = detCuentas;
	}
//	public List<ChequerasDTO> getListdetCuentas() {
//		return listdetCuentas;
//	}
//	public void setListdetCuentas(List<ChequerasDTO> listdetCuentas) {
//		this.listdetCuentas = listdetCuentas;
//	}
	
}

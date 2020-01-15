package mx.solser.bpi.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import mx.solser.bpi.document.Convenio;
import mx.solser.bpi.document.Servicio;

public class LeerProperties {

	public static List<Servicio> getServicios() {
		Properties propiedades = new Properties();
		List <Servicio>listPagoServicios = new ArrayList<>();
		try {
			String directorioRaiz = System.getProperty("user.dir");
			propiedades.load(new FileReader(directorioRaiz+"/resource/catPagoServicios.properties"));
			for (Enumeration e = propiedades.keys(); e.hasMoreElements() ; ) {
			    // Obtenemos el objeto
			    Object obj = e.nextElement();
			    String val=propiedades.getProperty(obj.toString());
			    System.out.println(obj + ": " + propiedades.getProperty(obj.toString()));
			    
			    String[] arrDatos = val.split("\\|");
			    listPagoServicios.add(new Servicio (arrDatos[0],
						arrDatos[1], arrDatos[2], arrDatos[3],
						arrDatos[5], arrDatos[4],  arrDatos[5],  arrDatos[6]));
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listPagoServicios;
	}
	
	
	public static List<Convenio>  getConvenios() {
		List<Convenio> listConvenio= new ArrayList<>();
		
		Properties propiedades = new Properties();
	    String directorioRaiz = System.getProperty("user.dir");
		try {
			propiedades.load(new FileReader(directorioRaiz+"/resource/catConvenios.properties"));
			
			for (Enumeration e = propiedades.keys(); e.hasMoreElements() ; ) {
			    Object obj = e.nextElement();
			    String val=propiedades.getProperty(obj.toString());
			    System.out.println(obj + ": " + propiedades.getProperty(obj.toString()));
			    listConvenio.add(new Convenio(obj.toString(),val));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listConvenio;
	}
	

	
}

package mx.solser.bpi.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syndein.jdbc.datasource.SynDataSource;

import syndein.informixInterface.Header;

public class Executor {
	
	private static final Logger LOG = LoggerFactory.getLogger(Executor.class.getName());
	
	public static final String DRIVER_DATASOURCE 		 = "java:jboss/driver/SynDriver";
	public static final String DRIVER_DATASOURCE_SESSION = "java:jboss/driver/SynDriverSesion";
	public static final Executor executor = new Executor();
	
	public static final Executor getInstance(){
		return executor;
	}
	
	private Executor(){super();}
	
	public Header executeInterAct2(final String trama,final String database) 
			throws Exception{
		Header oRec = null;
		SynDataSource conn= null;
		
		try {	
				conn = new SynDataSource(DRIVER_DATASOURCE_SESSION);
				LOG.debug("EXECUTE INTERACT2 ");
				oRec = ConexionInterAct.executeInterAct(trama, database,conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn = null;
		}
		
		
		return oRec;
	}
	
	
	public Header executeInterAct(final String trama,final String database) 
			throws Exception{
		Header oRec = null;
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		t = null;
		SynDataSource conn=null;
		
		try {
			conn = new SynDataSource(DRIVER_DATASOURCE);
				LOG.debug("idConexion:{} class: {} metodo:{} trama:{}",new Object[]{conn, elements[1].getClassName(),elements[1].getMethodName(),trama});
				oRec = ConexionInterAct.executeInterAct(trama, database,conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn = null;
		}
		
		
		return oRec;
	}
	
	public Header executeInterAct(final String trama,final String database,int numRegistros) 
			throws Exception{
		Header oRec = null;
		Throwable t = new Throwable();
		SynDataSource conn = null;
	
		try {
			StackTraceElement[] elements = t.getStackTrace();
			t = null;
			conn = new SynDataSource(DRIVER_DATASOURCE);
			LOG.debug("idConexion:{} class: {} metodo:{} trama:{}",new Object[]{conn, elements[1].getClassName(),elements[1].getMethodName(),trama});
			oRec = ConexionInterAct.executeInterAct(trama, database,numRegistros,conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn = null;
		}
		return oRec;
	}
	
	
	public Header executeInterAct(final String trama,final String database,ArchitectSessionBean sessionBean) 
			throws Exception{
		Header oRec = null;
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		t = null;
		SynDataSource conn = null;
		
		try {
			conn = new SynDataSource(DRIVER_DATASOURCE);
				LOG.debug("idSession:{} idConexion:{} class: {} metodo:{} trama:{}",new Object[]{sessionBean.getIdSession(),conn, elements[1].getClassName(),elements[1].getMethodName(),trama});
				oRec = ConexionInterAct.executeInterAct(trama, database,sessionBean,conn);
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn = null;
		}
		return oRec;
	}

	public String executeInterAct(final String trama,final String database,int transaccion,ArchitectSessionBean sessionBean) 
			throws Exception{
		String oRec = null;
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();
		t = null;
		SynDataSource conn = null;
		
		try {
			conn = new SynDataSource(DRIVER_DATASOURCE);
				LOG.debug("idSession:{} idConexion:{} class: {} metodo:{} trama:{}",new Object[]{sessionBean.getIdSession(),conn, elements[1].getClassName(),elements[1].getMethodName(),trama});
				oRec = ConexionInterAct.executeInterAct(trama, database,transaccion, sessionBean,conn);
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn = null;
		}
		
		return oRec;
	}
	
}

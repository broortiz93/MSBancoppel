package mx.solser.bpi.util;


public  enum UrlPermisos {
	
	LOGIN("BPI",UrlConstantes.CONSULTA_CAT,PermisosEnum.CONSULTA),	
//	SUCURSALES_CONSULTA(FlujoEnum.ALMACEN, UrlConstantes.SUCURSAL_CONSULTA_LISTA, PermisosEnum.ADMIN),
//	SUCURSALES_CONSULTA_TODO_PER(FlujoEnum.ALMACEN, UrlConstantes.CONSULTA_SUCURSAL_PERMISOS, PermisosEnum.ADMIN),
//	
//	PRODUCTO_OBTENER(FlujoEnum.PRODUCTO,UrlConstantes.PRODUCTO_OBTENER_TODOS, PermisosEnum.CONSULTA),
	

	;
	
	private String flujo;
	private String url;
	private PermisosEnum permiso;
	
	
	private UrlPermisos(String flujo, String url, PermisosEnum permiso) {
		this.flujo = flujo;
		this.url = url;
		this.permiso = permiso;
	}

	public String getFlujo() {
		return flujo;
	}

	public String getUrl() {
		return url;
	}

	public PermisosEnum getPermiso() {
		return permiso;
	}
	
	public String getAutorizacion() {
		return getFlujo()+"_"+getPermiso();
		
	}

}

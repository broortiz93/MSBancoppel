/**
 * 
 */
package mx.solser.bpi.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.solser.bpi.document.Convenio;
import mx.solser.bpi.document.ReqBase;
import mx.solser.bpi.document.ResBase;
import mx.solser.bpi.document.ResConsulta;
import mx.solser.bpi.persistence.mongo.UsuarioRepository;
import mx.solser.bpi.util.LeerProperties;
import mx.solser.bpi.util.UrlConstantes;


/**
 * @author Solser 
 *
 */
@RestController
public class UtilService {


	@Autowired
	private UsuarioRepository userRepo;



	@GetMapping(value = UrlConstantes.CONSULTA_CAT)
	@ResponseBody
	public ResConsulta msConvenios() {
			System.out.println("Consulta convenios");
			ResConsulta resConsulta= new ResConsulta();
			resConsulta.setListConvenio(LeerProperties.getConvenios());
			resConsulta.setListServicio(LeerProperties.getServicios());
		
		return  resConsulta;
	}
	
	@PostMapping(value = UrlConstantes.PAGAR_SERVICIOS)
	@ResponseBody
	public ResConsulta msPagarService(@RequestBody Convenio reqConvenio ) {
			System.out.println("Pagar convenios");
			ResConsulta resConsulta= new ResConsulta();
			

//			usuarioDTO.getCuentasAhorro();
			List<T> reqCtasAgorroUsuario= new ArrayList<>();
//			ProductosProperties productosProperties = productosService.getProductosProperties();
//			Map<String, List<String>> mapProductos = productosProperties.getProductosMap();
			List<T> listProd= new ArrayList<>();
	
			

		
		return  resConsulta;
	}
	
	@PostMapping(value = UrlConstantes.CONSULTA_MONGO)
	@ResponseBody
	public ResBase msMongo(ReqBase req) {
			System.out.println("Consulta Mongo"+ req.getParam());
			ResBase resConsulta= new ResBase();
			resConsulta.setListUsuarios(userRepo.findAll());
		
		return  resConsulta;
	}
	

}

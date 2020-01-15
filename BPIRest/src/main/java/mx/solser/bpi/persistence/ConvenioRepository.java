package mx.solser.bpi.persistence;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.solser.bpi.document.Convenio;


public interface ConvenioRepository extends MongoRepository<Convenio, Serializable>{

//	Convenio findById(ObjectId id);
//	
//	List<Convenio> findByIdSucursal(String idSucursal);
	
}

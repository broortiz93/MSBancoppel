package mx.solser.bpi.persistence.mongo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.solser.bpi.document.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Serializable> {
	Usuario findByUsername(String user);
}

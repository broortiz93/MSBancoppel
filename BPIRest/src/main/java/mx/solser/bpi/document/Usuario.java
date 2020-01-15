package mx.solser.bpi.document;

import org.springframework.data.mongodb.core.index.Indexed;

public class Usuario {
	
	private static final long serialVersionUID = 276166503609845632L;

	private String id;

	@Indexed
	private String username;
	private String password;

	private String photoProfile;
	private String idPerfil;

	private String idCorporativo;
	private String idEmpresa;
	

	private String idioma;

	public Usuario() {
		super();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}
	
	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}


	public String getIdCorporativo() {
		return idCorporativo;
	}

	public void setIdCorporativo(String idCorporativo) {
		this.idCorporativo = idCorporativo;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}





}

package practica1;

public class Usuario {
	private long id;
	private String nombre;
	private Fecha fecha_nac;
	private String ciudad_nac;
	private Direccion dir;
	private long tel;
	private String email;

	public Usuario(long id, String nombre, Fecha fecha_nac, String ciudad_nac, Direccion dir, long tel, String email) {
		this.id = id;
		this.nombre = nombre;
		this.fecha_nac = fecha_nac;
		this.ciudad_nac = ciudad_nac;
		this.dir = dir;
		this.tel = tel;
		this.email = email;
	}

	// ToString

	@Override
	public String toString() {
		return "ID: " + id + "\nNombre: " + nombre + "\nFecha de nacimiento: " + fecha_nac + "\nCiudad de nacimiento: "
				+ ciudad_nac + "\nDirección: " + dir + "\nTeléfono: " + tel + "\nEmail: " + email;
	}

	// Gets y sets
	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Fecha getFecha_nac() {
		return fecha_nac;
	}

	public String getCiudad_nac() {
		return ciudad_nac;
	}

	public Direccion getDir() {
		return dir;
	}

	public long getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha_nac(Fecha fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public void setCiudad_nac(String ciudad_nac) {
		this.ciudad_nac = ciudad_nac;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

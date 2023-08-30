

public class Direccion {
	private String calle;
	private int noCalle;
	private String nomenclatura;
	private String barrio;
	private String ciudad;

	public Direccion(String calle, int noCalle, String nomenclatura, String barrio, String ciudad) {
		this.calle = calle;
		this.noCalle = noCalle;
		this.nomenclatura = nomenclatura;
		this.barrio = barrio;
		this.ciudad = ciudad;
	}

	// ToString

	@Override
	public String toString() {
		return "Calle: " + calle + ", No. Calle: " + noCalle + ", Nomenclatura: " + nomenclatura + ", Barrio: " + barrio
				+ ", Ciudad: " + ciudad;
	}

	// Gets y sets

	public String getCalle() {
		return calle;
	}

	public int getNoCalle() {
		return noCalle;
	}

	public String getNomenclatura() {
		return nomenclatura;
	}

	public String getBarrio() {
		return barrio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNoCalle(int noCalle) {
		this.noCalle = noCalle;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}


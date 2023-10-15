

public class Direccion {
    private String calle;
    private int noCalle;
    private String nomenclatura;
    private String barrio;
    private String ciudad;
    private String urbanizacion;
    private String apartamento;

    public Direccion(String calle, int noCalle, String nomenclatura, String barrio, String ciudad, String urbanizacion, String apartamento) {
        this.calle = calle;
        this.noCalle = noCalle;
        this.nomenclatura = nomenclatura;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.urbanizacion = urbanizacion;
        this.apartamento = apartamento;
    }

    public Direccion(String calle, int noCalle, String nomenclatura, String barrio, String ciudad) {
        this.calle = calle;
        this.noCalle = noCalle;
        this.nomenclatura = nomenclatura;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.urbanizacion = null;
        this.apartamento = null;
    }

    // ToString
    @Override
    public String toString() {
        return "Calle " + calle + ", No. " + noCalle + ", " + nomenclatura + ", " + barrio
                + ", " + ciudad + ", " + urbanizacion + ", Apto: " + apartamento;
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

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public String getApartamento() {
        return apartamento;
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

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
}

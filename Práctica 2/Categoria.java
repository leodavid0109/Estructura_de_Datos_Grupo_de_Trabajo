import java.util.ArrayList;

public enum Categoria {
    Empleado,
    Administrador;

    public static ArrayList<Categoria> getCategoria(){
        ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();
        listaCategoria.add(Empleado);
        listaCategoria.add(Administrador);
        return listaCategoria;
    }
}

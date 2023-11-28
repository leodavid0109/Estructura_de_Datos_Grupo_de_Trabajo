# Clase principal
import lista
import matriz


class Proyecto:
    opcion = ""

    while opcion != "0":
        print("\nMenu:")
        print("1. Cargar datos en matriz de adyacencia")
        print("2. Cargar datos en lista de adyacencia")
        print("0. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            print("Cargando datos en matriz de adyacencia...")
            matriz.Matriz()

        elif opcion == "2":
            print("Cargando datos en lista de adyacencia...")
            lista.Lista()


if __name__ == "__main__":
    Proyecto()

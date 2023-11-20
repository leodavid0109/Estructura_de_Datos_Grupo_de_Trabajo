import networkx as nx
from matplotlib import pyplot as plt


def lectura_archivo(nombre_archivo):
    G = nx.Graph()
    with open(nombre_archivo, "r") as file:
        # Se lee la primera linea del archivo
        file.readline()
        for line in file:
            line = line.strip()
            line = line.split(",")
            G.add_edge(line[0], line[1], distancia=int(line[2]), tiempo=int(line[3]))
    return G


def impresion_camino(camino):
    print("El camino más corto es: ")
    for i in range(len(camino)):
        if i == len(camino) - 1:
            print(camino[i])
        else:
            print(camino[i] + " -> ", end="")


def draw_graph(G):
    options = {
        "node_color": "skyblue",
        "node_size": 1000,
        "with_labels": True,
        "font_size": 10,
        "font_color": "black",
        "font_weight": "bold",
        "width": 2,
    }

    nx.draw(G, **options)
    plt.show()


# Clase Principal
class Main:
    opcion = ""
    G = lectura_archivo("Dataset/Datos vias Colombia 2.csv")

    while opcion != "0":
        print("\nMenu:")
        print("1. Verificar si dos ciudades están conectadas")
        print("2. Encontrar el camino más corto en kilómetros")
        print("3. Encontrar el camino más corto en tiempo")
        print("4. Ver grafo")
        print("5. Ver lista de adyacencia")
        print("0. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            if G.has_edge(ciudad_1, ciudad_2):
                print("Las ciudades están conectadas.")
            else:
                print("Las ciudades no están conectadas.")

        elif opcion == "2":
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            longitud_camino, camino_mas_corto = nx.single_source_dijkstra(G, ciudad_1, ciudad_2, weight="distancia")
            print(f"Camino más corto entre {ciudad_1} y {ciudad_2}: {longitud_camino} km")
            impresion_camino(camino_mas_corto)

        elif opcion == "3":
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            longitud_camino, camino_mas_corto = nx.single_source_dijkstra(G, ciudad_1, ciudad_2, weight="tiempo")
            print(f"Camino más corto entre {ciudad_1} y {ciudad_2}: {longitud_camino} minutos")
            impresion_camino(camino_mas_corto)

        elif opcion == "4":
            draw_graph(G)

        elif opcion == "5":
            ad = G.adj
            # Impresion lista de adyacencia
            for i in ad:
                print(i + ": " + str(ad[i]))


if __name__ == "__main__":
    Main()

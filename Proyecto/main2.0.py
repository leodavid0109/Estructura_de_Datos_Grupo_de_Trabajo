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

def custom_layout(G):
    # Definir el espacio entre líneas y nodos por línea
    line_spacing = 2.0
    nodes_per_line = 10

    pos = {}
    current_line = 0
    current_node_in_line = 0

    for node in G.nodes():
        # Calcular las coordenadas x e y para el nodo
        x = current_node_in_line
        y = -current_line * line_spacing

        pos[node] = (x, y)

        # Mover a la siguiente línea después de 10 nodos
        current_node_in_line += 1
        if current_node_in_line == nodes_per_line:
            current_line += 1
            current_node_in_line = 0

    return pos

def draw_graph(G):
    # Utilizar spring_layout para disponer los nodos en una cuadrícula
    pos = custom_layout(G)

    options = {
        "node_color": "skyblue",
        "node_size": 500,
        "with_labels": True,
        "font_size": 5,
        "font_color": "black",
        "font_weight": "bold",
        "width": 2,
    }

    nx.draw(G, pos, **options)
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
            nx.
            if G.has_edge(ciudad_1, ciudad_2):
                print("Las ciudades están conectadas por una única carretera.")
            else:
                print("Las ciudades no están conectadas por una única carretera.")

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

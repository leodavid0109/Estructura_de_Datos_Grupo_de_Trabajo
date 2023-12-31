import pandas as pd
import numpy as np
import os
import heapq
import matplotlib.pyplot as plt


# Función para imprimir una matriz en formato de tabla
def print_matrix(matrix, title, cities):
    fig, ax = plt.subplots(figsize=(20, 20))  # Ajusta el tamaño de la figura según tus necesidades
    cax = ax.matshow(matrix, cmap='viridis')

    for i in range(matrix.shape[0]):
        for j in range(matrix.shape[1]):
            if matrix[i, j] == float('inf'):
                ax.text(j, i, '0', ha='center', va='center', fontsize=10)
            else:
                ax.text(j, i, f'{int(matrix[i, j])}', ha='center', va='center', fontsize=10)

    plt.xticks(range(matrix.shape[1]), cities, rotation=90, fontsize=10)
    plt.yticks(range(matrix.shape[0]), cities, fontsize=10)
    plt.title(title, fontsize=12)
    plt.colorbar(cax)
    plt.show()


def verificar_conexion(ciudad_a, ciudad_b, distance_matrix, cities, city_indices):
    if ciudad_a not in cities or ciudad_b not in cities:
        print("Al menos una de las ciudades ingresadas no existe en los datos proporcionados.")
        return False

    idx_ciudad_a = city_indices[ciudad_a]
    idx_ciudad_b = city_indices[ciudad_b]

    if distance_matrix[idx_ciudad_a][idx_ciudad_b] != float('inf'):
        print(f"Las ciudades {ciudad_a} y {ciudad_b} están conectadas por una carretera directa.")
    else:
        print(f"Las ciudades {ciudad_a} y {ciudad_b} no están conectadas por una carretera directa.")


def dijkstra(graph, start, end, weights):
    # Inicialización de variables
    distances = {node: float('inf') for node in graph}
    distances[start] = 0
    priority_queue = [(0, start)]

    while priority_queue:
        current_distance, current_node = heapq.heappop(priority_queue)

        if current_distance > distances[current_node]:
            continue

        for neighbor in graph[current_node]:
            distance = current_distance + graph[current_node][neighbor]
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances[end]


def camino_mas_corto(ciudad_a, ciudad_b, matrix, cities, city_indices, unidades):
    if ciudad_a not in cities or ciudad_b not in cities:
        print("Al menos una de las ciudades ingresadas no existe en los datos proporcionados.")
        return

    idx_ciudad_a = city_indices[ciudad_a]
    idx_ciudad_b = city_indices[ciudad_b]

    # Construir el grafo a partir de la matriz proporcionada
    graph = {city: {cities[i]: distance for i, distance in enumerate(row) if distance != float('inf')}
             for city, row in zip(cities, matrix)}

    shortest_distance = dijkstra(graph, ciudad_a, ciudad_b, matrix)

    print(f"El camino más corto entre {ciudad_a} y {ciudad_b} es de {int(shortest_distance)} {unidades}.")


def mostrar_menu():
    print("\nMenu:")
    print("1. Verificar si dos ciudades están conectadas")
    print("2. Encontrar el camino más corto en kilómetros")
    print("3. Encontrar el camino más corto en tiempo")
    print("4. Ver matriz de adyacencia de distancias")
    print("5. Ver matriz de adyacencia de tiempos")
    print("0. Salir")

def Matriz():
    # Lee el archivo CSV
    data = pd.read_csv(os.path.join('Dataset', 'Datos vias Colombia.csv'))

    # Obtiene la lista de ciudades únicas
    cities = sorted(set(data['Origen'].unique()) | set(data['Destino'].unique()))

    # Crea una matriz de adyacencia inicializada con distancias infinitas
    inf = float('inf')
    num_cities = len(cities)
    adj_matrix = np.full((num_cities, num_cities), inf)

    # Asigna las distancias al grafo (matriz de adyacencia)
    city_indices = {city: idx for idx, city in enumerate(cities)}

    distance_matrix = np.full((num_cities, num_cities), inf)
    time_matrix = np.full((num_cities, num_cities), inf)

    for _, row in data.iterrows():
        origin_idx = city_indices[row['Origen']]
        dest_idx = city_indices[row['Destino']]
        distance = row['KM']
        time = row['Minutos']

        distance_matrix[origin_idx][dest_idx] = distance
        distance_matrix[dest_idx][origin_idx] = distance  # La matriz es simétrica

        time_matrix[origin_idx][dest_idx] = time
        time_matrix[dest_idx][origin_idx] = time  # La matriz es simétrica

    opcion = ""

    while opcion != "0":
        mostrar_menu()
        opcion = input("Ingrese el número de la opción que desea ejecutar: ")

        if opcion == '0':
            print("¡Hasta luego!")
            break
        elif opcion == '1':
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            verificar_conexion(ciudad_1, ciudad_2, distance_matrix, cities, city_indices)
        elif opcion == '2':
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            camino_mas_corto(ciudad_1, ciudad_2, distance_matrix, cities, city_indices, "kilómetros")
        elif opcion == '3':
            ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
            ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
            camino_mas_corto(ciudad_1, ciudad_2, time_matrix, cities, city_indices, "minutos")
        elif opcion == '4':
            print_matrix(distance_matrix, 'Distance Matrix', cities)
        elif opcion == '5':
            print_matrix(time_matrix, 'Time Matrix', cities)
        else:
            print("Opción inválida. Por favor, ingrese un número válido.")

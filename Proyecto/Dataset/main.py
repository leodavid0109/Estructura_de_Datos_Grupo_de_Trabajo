import pandas as pd
import numpy as np
import os
import heapq

# Lee el archivo CSV
data = pd.read_csv(os.path.join('proyecto', 'Dataset', 'Datos vias Colombia.csv'))

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
    distance_matrix[dest_idx][origin_idx] = distance # La matriz es simétrica
    
    time_matrix[origin_idx][dest_idx] = time
    time_matrix[dest_idx][origin_idx] = time # La matriz es simétrica

def verificar_conexion(ciudad_a, ciudad_b, distance_matrix, cities):
    if ciudad_a not in cities or ciudad_b not in cities:
        print("Al menos una de las ciudades ingresadas no existe en los datos proporcionados.")
        return False
    
    idx_ciudad_a = city_indices[ciudad_a]
    idx_ciudad_b = city_indices[ciudad_b]
    
    if distance_matrix[idx_ciudad_a][idx_ciudad_b] != float('inf'):
        # Si la distancia entre las ciudades es finita, están conectadas por al menos una carretera.
        # Verificar si hay una única conexión directa entre ellas.
        unique_connection = True
        for idx, distance in enumerate(distance_matrix[idx_ciudad_a]):
            if distance != float('inf') and idx != idx_ciudad_b:
                # Si hay otra conexión diferente a la ciudad B, no es una conexión única.
                unique_connection = False
                break
        
        if unique_connection:
            print(f"Las ciudades {ciudad_a} y {ciudad_b} están conectadas por una única carretera.")
            return True
        else:
            print(f"Las ciudades {ciudad_a} y {ciudad_b} están conectadas por más de una carretera.")
            return False
    else:
        print(f"No hay conexión directa entre las ciudades {ciudad_a} y {ciudad_b}.")
        return False

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
    print("Menu:")
    print("1. Verificar si dos ciudades están conectadas")
    print("2. Encontrar el camino más corto en kilómetros")
    print("3. Encontrar el camino más corto en tiempo")
    print("0. Salir")

while True:
    mostrar_menu()
    opcion = input("Ingrese el número de la opción que desea ejecutar: ")

    if opcion == '0':
        print("¡Hasta luego!")
        break
    elif opcion == '1':
        ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
        ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
        verificar_conexion(ciudad_1, ciudad_2, distance_matrix, cities)
    elif opcion == '2':
        ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
        ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
        camino_mas_corto(ciudad_1, ciudad_2, distance_matrix, cities, city_indices, "kilómetros")
    elif opcion == '3':
        ciudad_1 = input("Ingrese el nombre de la primera ciudad: ")
        ciudad_2 = input("Ingrese el nombre de la segunda ciudad: ")
        camino_mas_corto(ciudad_1, ciudad_2, time_matrix, cities, city_indices, "minutos")
    else:
        print("Opción inválida. Por favor, ingrese un número válido.")

# Mostrar las matrices de distancia y tiempo
# np.set_printoptions(threshold=np.inf)
# print("Matriz de distancias:")
# print(distance_matrix)

# print("\nMatriz de tiempos:")
# print(time_matrix)
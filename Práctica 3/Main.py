import networkx as nx
from matplotlib import pyplot as plt

from BT.BinaryTree import BinaryTree
from BT.BinarySearchTree import BinarySearchTree
from Usuario import Usuario


def Preorder(T, v):
    preorder_list = []
    preorder_list.append(v.getData())
    print(v.getData())  # visit(v)
    if T.hasLeft(v):
        preorder_list_left = Preorder(T, T.left(v))  # recursive call on left child
        preorder_list += preorder_list_left
    if T.hasRight(v):
        preorder_list_right = Preorder(T, T.right(v))
        preorder_list += preorder_list_right
    return preorder_list


def Inorder(T, v):
    inorder_list = []
    if T.hasLeft(v):
        inorder_list_left = Inorder(T, T.left(v))  # recursive call on left child
        inorder_list += inorder_list_left
    inorder_list.append(v.getData())  # visit(v)
    if T.hasRight(v):
        inorder_list_right = Inorder(T, T.right(v))  # recursive call on right child
        inorder_list += inorder_list_right
    return inorder_list


def Posorder(T, v):
    preorder_list = []
    if T.hasLeft(v):
        preorder_list_left = Posorder(T, T.left(v))  # recursive call on left child
        preorder_list += preorder_list_left
    if T.hasRight(v):
        preorder_list_right = Posorder(T, T.right(v))  # recursive call on right child
        preorder_list += preorder_list_right
    preorder_list.append(v.getData())
    print(v.getData())  # visit(v)
    return preorder_list


def max(T, v):
    if T.hasRight(v):
        return max(T, T.right(v))
    else:
        return v.getData()


# Métodos para dibujar el árbol
def add_edges(root, graph, pos, x=0, y=0, layer=1, dx=1.0, dy=1.0):
    if root is not None:
        pos[root.getData()] = (x, -layer)
        if root.getLeft() is not None:
            graph.add_edge(root.getData(), root.getLeft().getData())
            add_edges(root.getLeft(), graph, pos, x - dx / 2, y - dy, layer + 1, dx / 2, dy)
        if root.getRight() is not None:
            graph.add_edge(root.getData(), root.getRight().getData())
            add_edges(root.getRight(), graph, pos, x + dx / 2, y - dy, layer + 1, dx / 2, dy)


def draw_binary_tree(tree):
    if tree.isEmpty():
        print("El árbol está vacío.")
        return
    graph = nx.Graph()
    pos = {}
    graph.add_node(tree.root().getData())
    add_edges(tree.root(), graph, pos)

    options = {
        "node_color": "skyblue",
        "node_size": 6000,
        "with_labels": True,
        "font_size": 10,
        "font_color": "black",
        "font_weight": "bold",
        "width": 2,
    }

    nx.draw(graph, pos, **options)
    plt.show()


# Clase de pruebas de las clases BinaryTree y BinarySearchTree
class Test:
    @classmethod
    def test_binary_tree(cls):
        # Create a binary tree
        bt = BinaryTree()

        # Test addRoot
        bt.addRoot(1)
        assert bt.root().getData() == 1
        assert bt.size() == 1

        # Test insertLeft and insertRight
        bt.insertLeft(bt.root(), 2)
        bt.insertRight(bt.root(), 3)
        assert bt.left(bt.root()).getData() == 2
        assert bt.right(bt.root()).getData() == 3
        assert bt.size() == 3

        # Test parent
        assert bt.parent(bt.left(bt.root())) == bt.root()
        assert bt.parent(bt.right(bt.root())) == bt.root()

        # Test depth
        assert bt.depth(bt.root()) == 0
        assert bt.depth(bt.left(bt.root())) == 1

        # Test height
        assert bt.height(bt.root()) == 1

        # Test remove
        bt.remove(bt.right(bt.root()))
        assert bt.right(bt.root()) is None
        assert bt.size() == 2

        # Test Preorder
        print("Preorder traversal:")
        Preorder(bt, bt.root())

        # Test Inorder
        print("Inorder traversal:")
        Inorder(bt, bt.root())

        # Test Posorder
        print("Postorder traversal:")
        Posorder(bt, bt.root())

        print("All tests passed.")

    @classmethod
    def test_binary_search_tree(cls):
        # Create a binary search tree
        bst = BinarySearchTree()

        # Test insert
        bst.insert("apple", 1)
        bst.insert("banana", 2)
        bst.insert("cherry", 3)
        assert bst.root().getData().getKey() == 1
        assert bst.root().getRight().getData().getKey() == 2
        assert bst.root().getRight().getRight().getData().getKey() == 3

        # Test find
        assert bst.find(1).getData().getData() == "apple"
        assert bst.find(2).getData().getData() == "banana"
        assert bst.find(3).getData().getData() == "cherry"

        # Test remove
        bst.remove(2)
        assert bst.find(2) is None
        assert bst.root().getRight().getData().getKey() == 3

        print("All tests passed.")


# Clase principal
class Main:
    opcion = ""
    bst = BinarySearchTree()

    # Creación Árbol auxiliar de la práctica
    # bst.insert(Usuario("Juan", "10101013"), Usuario.sumaCedula("10101013"))
    # bst.insert(Usuario("Pablo", "10001011"), Usuario.sumaCedula("10001011"))
    # bst.insert(Usuario("Maria", "10101015"), Usuario.sumaCedula("10101015"))
    # bst.insert(Usuario("Ana", "1010000"), Usuario.sumaCedula("1010000"))
    # bst.insert(Usuario("Diana", "10111105"), Usuario.sumaCedula("10111105"))
    # bst.insert(Usuario("Mateo", "10110005"), Usuario.sumaCedula("10110005"))

    while opcion != "8":
        print("\nMenú:")
        print("1. Insertar nuevo usuario")
        print("2. Eliminar usuario")
        print("3. Buscar usuario por clave")
        print("4. Usuario con mayor cédula")
        print("5. Usuario con menor cédula")
        print("6. Ver árbol")
        print("7. Recorrido Inorder")
        print("8. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            nombre = input("Ingrese el nombre del usuario: ")
            cedula = input("Ingrese la cédula del usuario: ")
            usuario = Usuario(nombre, cedula)
            k = Usuario.sumaCedula(usuario.getCedula())
            bst.insert(usuario, k)

        elif opcion == "2":
            clave = input("Ingrese la clave del usuario a eliminar: ")
            if bst.find(int(clave)) is not None:
                aux = bst.remove(int(clave))
                print(f"Usuario con clave {clave} eliminado con éxito.")

        elif opcion == "3":
            if bst.isEmpty():
                print("El árbol está vacío.")
                continue
            clave = input("Ingrese la clave del usuario a buscar: ")
            if bst.find(int(clave)) is not None:
                print(f"Usuario con cédula {clave} encontrado.")
                print(bst.find(int(clave)).getData())
            else:
                print(f"Usuario con cédula {clave} no encontrado.")

        elif opcion == "4":
            if bst.isEmpty():
                print("El árbol está vacío.")
                continue
            bst.maxNode(bst.root())
            print(f"Usuario con mayor cédula: \n{bst.maxNode(bst.root()).getData()}")

        elif opcion == "5":
            if bst.isEmpty():
                print("El árbol está vacío.")
                continue
            bst.minNode(bst.root())
            print(f"Usuario con menor cédula: \n{bst.minNode(bst.root()).getData()}")

        elif opcion == "6":
            draw_binary_tree(bst)

        elif opcion == "7":
            if bst.isEmpty():
                print("El árbol está vacío.")
                continue
            print("Recorrido Inorder:")
            inorder = Inorder(bst, bst.root())
            j = 1
            for i in inorder:
                print(f"Paso {j}: {i.getKey()}")
                j += 1

        elif opcion == "8":
            print("Saliendo del programa. ¡Hasta luego!")
            break

        else:
            print("Opción no válida. Por favor, seleccione un número dentro del rango de posibiliades.")


# Ejecutar el programa
if __name__ == "__main__":
    # Ejemplo de uso de impresión de un árbol binario:
    # Crear un árbol binario
    # tree = BinaryTree()
    # tree.addRoot(1)
    # root = tree.root()
    # tree.insertLeft(root, 2)
    # tree.insertRight(root, 3)
    # tree.insertLeft(tree.left(root), 4)
    # tree.insertRight(tree.left(root), 5)
    # tree.insertLeft(tree.right(root), 6)
    # tree.insertRight(tree.right(root), 7)
    # tree.insertLeft(tree.left(tree.left(root)), 8)
    # tree.insertRight(tree.left(tree.left(root)), 9)

    # draw_binary_tree(tree)

    # print("\nPruebas de la clase BinaryTree:")
    # Test.test_binary_tree()
    # print("\nPruebas de la clase BinarySearchTree:")
    # Test.test_binary_search_tree()
    # print("\nEjecutando programa principal:")
    Main()

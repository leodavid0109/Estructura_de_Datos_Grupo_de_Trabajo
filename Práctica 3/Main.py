from BT.BinaryTree import BinaryTree
from BT.BinarySearchTree import BinarySearchTree
from Usuario import Usuario


def Preorder(T, v):
    preorder_list = []
    preorder_list.append(v.getData())
    print(v.getData())  # visit(v)
    if T.hasLeft(v):
        preorder_list_left = (T, T.left(v))  # recursive call on left child
        preorder_list += preorder_list_left
    if T.hasRight(v):
        preorder_list_right = (T, T.right(v))
        preorder_list += preorder_list_right
    return preorder_list


def Inorder(T, v):
    preorder_list = []
    if T.hasLeft(v):
        preorder_list_left = (T, T.left(v))  # recursive call on left child
        preorder_list += preorder_list_left
    preorder_list.append(v.getData())
    print(v.getData())  # visit(v)
    if T.hasRight(v):
        preorder_list_right = (T, T.right(v))  # recursive call on right child
        preorder_list += preorder_list_right
    return preorder_list


def Posorder(T, v):
    preorder_list = []
    if T.hasLeft(v):
        preorder_list_left = (T, T.left(v))  # recursive call on left child
        preorder_list += preorder_list_left
    if T.hasRight(v):
        preorder_list_right = (T, T.right(v))  # recursive call on right child
        preorder_list += preorder_list_right
    preorder_list.append(v.getData())
    print(v.getData())  # visit(v)
    return preorder_list


def max(T, v):
    if T.hasRight(v):
        return max(T, T.right(v))
    else:
        return v.getData()

def impresionAB


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
        print(bst.find(2).getData().getData())
        assert bst.find(2) is None
        assert bst.root().getRight().getData().getKey() == 3

        print("All tests passed.")


# Clase principal
class Main:
    opcion = ""
    arbol = BinaryTree()
    bst = BinarySearchTree()

    while opcion != "3":
        print("\nMenú:")
        print("1. Agregar usuario")
        print("2. Ver árbol")
        print("3. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            nombre = input("Ingrese el nombre del usuario: ")
            cedula = input("Ingrese la cédula del usuario: ")
            usuario = Usuario(nombre, cedula)
            k = usuario.sumaCedula()
            bst.insert(usuario, k)

        elif opcion == "2":
            print("\nÁrbol de usuarios en Preorder:")
            lista = Preorder(bst, bst.root())
            print(lista)

        elif opcion == "3":
            print("Saliendo del programa. ¡Hasta luego!")

        else:
            print("Opción no válida. Por favor, seleccione un número dentro del rango de posibiliades.")


# TODO 1: Implementar el método insert de la clase BinarySearchTree
# TODO 2: Implementar el método remove de la clase BinarySearchTree
# TODO 3: Implementar el método searchObject de la clase BinarySearchTree
# TODO 4: Implementar el método máximo de la clase BinarySearchTree
# TODO 5: Implementar el método minimo de la clase BinarySearchTree
# TODO 6: Implementar el método mostrarArbol de la clase BinarySearchTree
# TODO 7: Implementar el método inorder de la clase BinarySearchTree

# Ejecutar el programa
if __name__ == "__main__":
    print("Pruebas de la clase BinaryTree:")
    Test.test_binary_tree()
    print("\nPruebas de la clase BinarySearchTree:")
    Test.test_binary_search_tree()
    print("\nEjecutando programa principal:")
    Main()

from Estructuras.List import List
from BT.BinaryTree import BinaryTree
from BT.BinarySearchTree import BinarySearchTree
from Usuario import Usuario


def Preorder(T, v):
    print(v.getData())  # visit(v)
    if T.hasLeft(v):
        Preorder(T, T.left(v))  # recursive call on left child
    if T.hasRight(v):
        Preorder(T, T.right(v))

def Inorder(T, v):
    if T.hasLeft(v):
        Inorder(T, T.left(v))  # recursive call on left child
    print(v.getData())  # visit(v)
    if T.hasRight(v):
        Inorder(T, T.right(v))  # recursive call on right child

def Posorder(T, v):
    if T.hasLeft(v):
        Posorder(T, T.left(v))  # recursive call on left child
    if T.hasRight(v):
        Posorder(T, T.right(v))  # recursive call on right child
    print(v.getData())  # visit(v)




class Test:
    def test_binary_tree():
        # Create a binary tree
        bt = BinaryTree()

        # Test addRoot
        bt.addRoot(1)
        assert bt.root.getData() == 1
        assert bt.size == 1

        # Test insertLeft and insertRight
        bt.insertLeft(bt.root, 2)
        bt.insertRight(bt.root, 3)
        assert bt.left(bt.root).getData() == 2
        assert bt.right(bt.root).getData() == 3
        assert bt.size == 3

        # Test parent
        assert bt.parent(bt.left(bt.root)) == bt.root
        assert bt.parent(bt.right(bt.root)) == bt.root

        # Test depth
        assert bt.depth(bt.root) == 0
        assert bt.depth(bt.left(bt.root)) == 1

        # Test height
        assert bt.height(bt.root) == 1

        # Test remove
        bt.remove(bt.right(bt.root))
        assert bt.right(bt.root) == None
        assert bt.size == 2

        # Test Preorder
        print("Preorder traversal:")
        Preorder(bt, bt.root)

        # Test Inorder
        print("Inorder traversal:")
        Inorder(bt, bt.root)

        # Test Posorder
        print("Postorder traversal:")
        Posorder(bt, bt.root)

        print("All tests passed.")

    def test_binary_search_tree():
        # Create a binary search tree
        bst = BinarySearchTree()

        # Test insert
        bst.insert("apple", 1)
        bst.insert("banana", 2)
        bst.insert("cherry", 3)
        assert bst.root.getData().getKey() == 1
        assert bst.root.getRight().getData().getKey() == 2
        assert bst.root.getRight().getRight().getData().getKey() == 3

        # Test find
        assert bst.find(1).getData().getData() == "apple"
        assert bst.find(2).getData().getData() == "banana"
        assert bst.find(3).getData().getData() == "cherry"

        # Test remove
        bst.remove(2) # El método remove no esta funcionando 😰
        print(bst.find(2).getData().getData())
        assert bst.find(2) is None
        assert bst.root.getRight().getData().getKey() == 3

        print("All tests passed.")

class Main:
    opcion=""
    arbol = BinaryTree()
    bst = BinarySearchTree()

    while opcion!="3":
        print("\nMenú:")
        print("1. Agregar usuario")
        print("2. Ver árbol")
        print("3. Salir")

        opcion = input("Seleccione una opción (1/2/3): ")

        if opcion == "1":
            nombre = input("Ingrese el nombre del usuario: ")
            cedula = input("Ingrese la celula del usuario: ")
            usuario = Usuario(nombre,cedula)
            k = 0
            for n in cedula:
                k+=int(n)
            bst.insert(usuario,k)
            
        elif opcion == "2":
            print("\nÁrbol de usuarios en Preorder:")
            lista = Preorder(bst,bst.root)

        elif opcion == "3":
            print("Saliendo del programa. ¡Hasta luego!")
        else:
            print("Opción no válida. Por favor, seleccione 1, 2 o 3.")


if __name__ == "__main__":
    Main
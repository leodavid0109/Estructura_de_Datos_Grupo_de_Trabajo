from Estructuras.List import List
from BT.BinaryTree import BinaryTree
from BT.BinarySearchTree import BinarySearchTree

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

class Main:
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

if __name__ == "__main__":
    Main.test_binary_tree()
    Main.test_binary_search_tree()
from Estructuras.Node import Node
from BT.BinaryTree import BinaryTree
from BT.BSTEntry import BSTEntry

class BinarySearchTree(BinaryTree):
    def __init__(self):
        super().__init__()  # Call the initializer of the superclass

    def find(self, k):
        return self.searchTree(k, self.root)

    def searchTree(self, key, v):
        u = v.getData()
        if key == u.getKey():  # Base case
            return v
        elif key < u.getKey():
            if v.getLeft() is not None:
                return self.searchTree(key, v.getLeft())
        else:
            if v.getRight() is not None:
                return self.searchTree(key, v.getRight())
            
    def addEntry(self, v, o):
        temp = v.getData()
        nD = Node(o)
        if o.getKey() < temp.getKey():
            if self.hasLeft(v):
                self.addEntry(self.left(v), o)
            else:
                v.setLeft(nD)
        else:
            if self.hasRight(v):
                self.addEntry(self.right(v), o)
            else:
                v.setRight(nD)

    def insert(self, e, k):
        O = BSTEntry(e, k)
        if self.isEmpty():
            self.addRoot(O)
        else:
            self.addEntry(self.root, O)

    def maxNode(self, temp):
        if self.hasRight(temp):
            return self.maxNode(self.right(temp))
        else:
            return temp

    def predecesor(self, v):
        temp = v.getLeft()
        return self.maxNode(temp)

    def remove(self, k):
        v = self.find(k)
        temp = v.getData()
        if self.hasLeft(v) and self.hasRight(v):  # case 2
            w = self.predecesor(v)
            v.setData(w.getData())
            super().remove(w)
        else:  # case 1
            super().remove(v)
        return temp
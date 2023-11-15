from Estructuras.Node import Node
from Estructuras.Queue import Queue


class BinaryTree:
    def __init__(self):
        self._root = None
        self._size = 0

    def size(self):
        return self._size

    def isEmpty(self):
        return self._size == 0

    def isRoot(self, node):
        return node == self._root

    def isInternal(self, node):
        return self.hasLeft(node) or self.hasRight(node)

    def hasLeft(self, node):
        return node.getLeft() is not None

    def hasRight(self, node):
        return node.getRight() is not None

    def root(self):
        return self._root

    def left(self, node):
        return node.getLeft()

    def right(self, node):
        return node.getRight()

    def parent(self, v):
        if self.isRoot(v):
            return None
        else:
            Q = Queue()
            Q.enqueue(self._root)
            temp = self._root
            while (not Q.isEmpty()) and (self.left(temp) != v) and (self.right(temp) != v):
                temp = Q.dequeue()
                if self.hasLeft(temp):
                    Q.enqueue(self.left(temp))
                if self.hasRight(temp):
                    Q.enqueue(self.right(temp))
            return temp

    def depth(self, v):
        if self.isRoot(v):
            return 0
        else:
            return 1 + self.depth(self.parent(v))

    def height(self, v):
        if not self.isInternal(v):
            return 0
        else:
            return 1 + max(self.height(self.left(v)), self.height(self.right(v)))

    def addRoot(self, data):
        self._root = Node(data)
        self._size = 1

    def insertLeft(self, node, data):
        left = Node(data)
        node.setLeft(left)
        self._size += 1

    def insertRight(self, node, data):
        right = Node(data)
        node.setRight(right)
        self._size += 1

    def remove(self, v):
        p = self.parent(v)
        if self.hasLeft(v) or self.hasRight(v):  # Caso en que v tiene al menos un hijo
            if self.hasLeft(v):
                child = self.left(v)
            else:
                child = self.right(v)
            if self.left(p) == v:  # Se conecta el hijo de v con el padre
                p.setLeft(child)
            else:
                p.setRight(child)
            v.setLeft(None)  # Se desconecta v
            v.setRight(None)
        else:  # Caso en que v es una hoja
            if self.left(p) == v:
                p.setLeft(None)
            else:
                p.setRight(None)
        self._size -= 1

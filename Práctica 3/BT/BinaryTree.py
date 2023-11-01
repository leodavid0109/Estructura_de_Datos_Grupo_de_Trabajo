from Estructuras.Node import Node
from Estructuras.Queue import Queue

class BinaryTree:
    def __init__(self):
        self.root = None
        self.size = 0

    def size(self):
        return self.size

    def isEmpty(self):
        return self.size == 0

    def isRoot(self, node):
        return node == self.root

    def isInternal(self, node):
        return node and (node.left or node.right)

    def hasLeft(self, node):
        return node.left is not None

    def hasRight(self, node):
        return node.right is not None

    def root(self):
        return self.root

    def left(self, node):
        return node.left

    def right(self, node):
        return node.right

    def parent(self, v):
        if self.isRoot(v):
            return None
        else:
            Q = Queue()
            Q.enqueue(self.root)
            temp = self.root
            while not Q.isEmpty() and self.left(Q.first()) != v and self.right(Q.first()) != v:
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

    def height(self, node):
        if node is None:
            return -1
        else:
            return 1 + max(self.height(node.left), self.height(node.right))

    def addRoot(self, data):
        self.root = Node(data)
        self.size += 1

    def insertLeft(self, node, data):
        node.left = Node(data)
        self.size += 1

    def insertRight(self, node, data):
        node.right = Node(data)
        self.size += 1

    def remove(self, v):
        if v is None:
            return
        if self.hasLeft(v) or self.hasRight(v):
            # If the node has children, remove the entire subtree
            v.left = None
            v.right = None
        else:
            # If the node has no children, find the parent and remove the node
            parent = self.parent(v)
            if parent is None:
                # The node is the root
                self.root = None
            elif parent.left == v:
                parent.left = None
            else:
                parent.right = None
        self.size -= 1
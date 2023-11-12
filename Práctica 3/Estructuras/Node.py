# Clase que representa un nodo de un arbol binario
class Node:
    def __init__(self, data=None):
        self._data = data
        self._left = None
        self._right = None

    def getLeft(self):
        return self._left

    def getRight(self):
        return self._right

    def getData(self):
        return self._data

    def setLeft(self, node):
        self._left = node

    def setRight(self, node):
        self._right = node

    def setData(self, data):
        self._data = data

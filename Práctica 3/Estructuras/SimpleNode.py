# Clase nodo simple para uso en Listas Simples
class SimpleNode:
    def __init__(self, data=None):
        self._data = data
        self._next = None

    def getNext(self):
        return self._next

    def getData(self):
        return self._data

    def setNext(self, node):
        self._next = node

    def setData(self, data):
        self._data = data
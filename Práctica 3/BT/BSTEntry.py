from Usuario import Usuario
class BSTEntry:
    def __init__(self, e, k):
        self._data = e
        self._k = k

    def getData(self):
        return self._data

    def getKey(self):
        return self._k

    def setData(self, d):
        self._data = d

    def setKey(self, k):
        self._k = k
    
    def __str__(self) -> str:
        return f"Nombre: {self.getData().getNombre()} Cedula: {self.getData().getCedula()} Indice: {self._k}"
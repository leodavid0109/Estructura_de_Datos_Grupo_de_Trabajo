from Usuario import Usuario
class BSTEntry:
    def __init__(self, d, k):
        self.data = d
        self.k = k

    def getData(self):
        return self.data

    def getKey(self):
        return self.k

    def setData(self, d):
        self.data = d

    def setKey(self, k):
        self.k = k
    
    def __str__(self) -> str:
        return f"Nombre: {self.getData().getNombre()} Cedula: {self.getData().getCedula()} Indice: {self.k}"
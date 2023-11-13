class Usuario:
    def __init__(self, nombre, cedula):
        self._nombre = nombre
        self._cedula = cedula

    def getNombre(self):
        return self._nombre

    def getCedula(self):
        return self._cedula

    def setNombre(self, nombre):
        self._nombre = nombre

    def setCedula(self, cedula):
        self._cedula = cedula

    def __str__(self):
        return "Nombre: " + self._nombre + ", Cedula: " + self._cedula

    @staticmethod
    def sumaCedula(cedula):
        suma = 0
        for i in cedula:
            suma += int(i)
        return suma

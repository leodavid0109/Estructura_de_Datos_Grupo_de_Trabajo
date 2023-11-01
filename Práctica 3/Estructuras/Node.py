class Node:
    def __init__(self, data=None):
        self.data = data
        self.left = None
        self.right = None

    def getLeft(self):
        return self.left

    def getRight(self):
        return self.right

    def getData(self):
        return self.data

    def setLeft(self, node):
        self.left = node

    def setRight(self, node):
        self.right = node

    def setData(self, data):
        self.data = data
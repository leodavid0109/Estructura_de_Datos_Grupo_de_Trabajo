from Estructuras.List import List


class Queue:
    def __init__(self):
        self.data = List()

    def size(self):
        return self.data.getSize()

    def isEmpty(self):
        return self.data.isEmpty()

    def enqueue(self, e):
        self.data.addLast(e)

    def dequeue(self):
        if self.isEmpty():
            return None
        return self.data.removeFirst()

    def first(self):
        if self.isEmpty():
            return None
        return self.data.First().getData()

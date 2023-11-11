class ArrayQueue:
    def __init__(self, capacity):
        self.data = [None] * capacity
        self.first = -1
        self.rear = -1

    def size(self):
        return (len(self.data) - self.first + self.rear) % len(self.data) + 1

    def isEmpty(self):
        return self.size() == 0

    def enqueue(self, e):
        if self.size() < len(self.data):
            self.rear = (self.rear + 1) % len(self.data)
            self.data[self.rear] = e

    def dequeue(self):
        if self.isEmpty():
            return None
        else:
            temp = self.data[self.first]
            self.data[self.first] = None
            self.first = (self.first + 1) % len(self.data)
            return temp

    def First(self):
        if self.isEmpty():
            return None
        return self.data[self.first]

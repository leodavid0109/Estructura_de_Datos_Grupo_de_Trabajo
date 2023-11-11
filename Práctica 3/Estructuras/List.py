from Estructuras.Node import Node


class List:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    def getSize(self):
        return self.size

    def isEmpty(self):
        return self.size == 0

    def setSize(self, s):
        self.size = s

    def First(self):
        return self.head

    def Last(self):
        return self.tail

    def addFirst(self, e):
        n = Node(e)
        if self.isEmpty():
            self.head = n
            self.tail = n
        else:
            n.next = self.head
            self.head = n
        self.size += 1

    def addLast(self, e):
        n = Node(e)
        if self.isEmpty():
            self.head = n
            self.tail = n
        else:
            self.tail.next = n
            self.tail = n
        self.size += 1

    def removeFirst(self):
        if not self.isEmpty():
            temp = self.head
            self.head = temp.next
            temp.next = None
            self.size -= 1
            return temp.data
        else:
            return None

    def removeLast(self):
        if self.size == 1:
            return self.removeFirst()
        else:
            temp = self.tail
            previous = self.head
            while previous.next != self.tail:
                previous = previous.next
            previous.next = None
            self.tail = previous
            self.size -= 1
            return temp.data

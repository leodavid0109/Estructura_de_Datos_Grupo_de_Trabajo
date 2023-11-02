from Estructuras.Node import Node


class List:
    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def getSize(self):
        return self._size

    def isEmpty(self):
        return self._size == 0

    def setSize(self, s):
        self._size = s

    def First(self):
        return self._head

    def Last(self):
        return self._tail

    def addFirst(self, e):
        n = Node(e)
        if self.isEmpty():
            self._head = n
            self._tail = n
        else:
            n.next = self._head
            self._head = n
        self._size += 1

    def addLast(self, e):
        n = Node(e)
        if self.isEmpty():
            self._head = n
            self._tail = n
        else:
            self._tail.next = n
            self._tail = n
        self._size += 1

    def removeFirst(self):
        if not self.isEmpty():
            temp = self._head
            self._head = temp.next
            temp.next = None
            self._size -= 1
            return temp.data
        else:
            return None

    def removeLast(self):
        if self._size == 1:
            return self.removeFirst()
        else:
            temp = self._tail
            previous = self._head
            while previous.next != self._tail:
                previous = previous.next
            previous.next = None
            self._tail = previous
            self._size -= 1
            return temp.data

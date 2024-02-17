# 원형 큐 클래스 구현
class ArrayQueue:
    def __init__(self, capacity = 10):
        self.capacity = capacity
        self.array = [None]*capacity
        self.front = 0 # 전단 인덱스
        self.rear = 0 # 후단 인덱스

    def isEmpty(self):
        return self.front == self.rear
    def isFull(self):
        return self.front == (self.rear + 1) % self.capacity

    def enqueue(self, item):
        if not self.isFull():
            self.rear = (self.rear + 1) % self.capacity
            self.array[self.rear] = item
        else: pass

    def dequeue(self):
        if not self.isEmpty():
            self.front = (self.front + 1) % self.capacity
            return self.array[self.front]
        else: pass

    def peek(self):
        if not self.isEmpty():
            return self.array[(self.front + 1) % self.capacity]
        else: pass

    # size() : 전체 요소의 수 == (rear - front + capacity) % capacity
    def size(self):
        return (self.rear - self.front + self.capacity) % self.capacity

    def display(self, msg):
        print(msg, end = ' = [')
        for i in range(self.front + 1, self.front + 1 + self.size()):
            print(self.array[i % self.capacity], end = ' ')
        print("]")
        
# 난수 발생 원형 큐 테스트 프로그램
import random
if __name__ == "__main__":
    q = ArrayQueue(8)

    q.display("Initial State")
    while not q.isFull():
        q.enqueue(random.randint(0, 100)) # queue 가 isFull() 일때 까지
    q.display("Full State")

    print("삭제 순서: ", end = ' ')
    while not q.isEmpty():
        print(q.dequeue(), end = ' ')
    print()
    
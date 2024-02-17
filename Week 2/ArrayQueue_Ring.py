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

    def enqueue2(self, item): # Ring Buffer Queue
        self.rear = (self.rear + 1) % self.capacity
        self.array[self.rear] = item

        if self.isEmpty():
            self.front = (self.front + 1) % self.capacity
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

    for i in range(6):
        q.enqueue2(i)
    q.display("Input 0~5")

    q.enqueue2(6)
    q.enqueue2(7)
    q.display("Input 6, 7")

    q.enqueue2(8)
    q.enqueue2(9)
    q.display("Input 8, 9")
    
    q.dequeue()
    q.dequeue()
    q.display("twice of delete")
    print()
    
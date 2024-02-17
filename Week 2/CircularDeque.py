# ArrayQueue 를 상속하는 CircularDeque 구현
# Deque == double-ended queue; 전단과 후단에서 모두 삽입과 삭제가 가능한 큐
from ArrayQueue import *

class CircularDeque(ArrayQueue): 
    def __init__(self, capacity = 10): # 생성자는 상속되지 않아서 다시 구현
        super().__init__(capacity) # super() 생성자로 초기화

    def addRear(self, item):
        self.enqueue(item)
    
    def deleteFront(self):
        return self.dequeue()
    
    def getFront(self):
        return self.peek()

    # ArrayQueue 에 없는 연산 추가 구현
    def addFront(self, item):
        if not self.isFull():
            self.array[self.front] = item
            self.front = (self.front - 1 + self.capacity) % self.capacity
        else: pass
    
    def deleteRear(self):
        if not self.isEmpty():
            item = self.array[self.rear];
            self.rear = (self.rear - 1 + self.capacity) % self.capacity
            return item
        else: pass

    def getRear(self):
        if not self.isEmpty():
            item = self.array[self.rear]
            return item
        else: pass

if __name__ == "__main__":
    dq = CircularDeque()

    for i in range(9):
        if i % 2 == 0: # 짝수면 후단 삽입
            dq.addRear(i)
        else: # 홀수면 전단 삽입
            dq.addFront(i) 
    dq.display("# 짝수면 후단 삽입/홀수면 전단 삽입")

    for i in range(2):            
        dq.deleteFront()
    for i in range(3):
        dq.deleteRear()        
    dq.display("전단 삭제 2번, 후단 삭제 3번")

    for i in range(9, 14): # 9, 10, 11, 12, 13 을 front 에 삽입
        dq.addFront(i)
    dq.display("전단 9~13 삽입")
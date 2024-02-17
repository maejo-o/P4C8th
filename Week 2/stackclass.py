class ArrayStack :
    def __init__(self, capacity): # 생성자 (constructor)
        self.capacity = capacity 
        self.array = [None]*self.capacity
        self.top = -1  # empty

    def isEmpty(self) : return self.top == -1
    def isFull(self) : return self.top == self.capacity-1

    def push(self, item):
        if not self.isFull():
            self.top += 1
            self.array[self.top] = item
        else: pass # overflow 예외는 처리하지 않았음

    def pop(self):
        if not self.isEmpty():
            self.top -= 1
            return self.array[self.top + 1]
        else: pass  # underflow 예외는 처리하지 않았음

    def peek(self):
        if not self.isEmpty():
            return self.array[self.top]
        else: pass # underflow 예외는 처리하지 않았음

    def size() : return top + 1
    

if __name__ == "__main__":
    s = ArrayStack(100) # stack object 생성
    msg= input("Input the String: ")
    for c in msg:
        s.push(c)

    print("Print the String: ", end='')
    while not s.isEmpty():
        print(s.pop(), end='') # pop() 반환값이 top+1 이므로
    print()
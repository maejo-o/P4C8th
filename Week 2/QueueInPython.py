# python module queue 포함시키기

import queue
import random

q = queue.Queue(8) # queuue module class name: Queue

print("삽입 순서: ", end = ' ')
while not q.full():
    v = random.randint(0, 100)
    q.put(v)
    print(v, end = ' ')
print()

print("삭제 순서: ", end = ' ')
while not q.empty():
    print(q.get(), end = ' ')
print()

import collections
dq = collections.deque() # 용량이 무한대인 덱 객체 생성

print("덱이 공백 상태가 아님" if dq else "덱은 공백 상태")
for i in range(9):
    if i % 2 == 0:
        dq.append(i)
    else:
        dq.appendleft(i)
print("홀수는 전단, 짝수는 후단 삽입", dq)

for i in range(2):
    dq.popleft() # queue 의 front 대신 left 를 사용!
for i in range(3):
    dq.pop()
print("전단 삭제 2회, 후단 삭제 3회", dq)

for i in range(9, 14):
    dq.appendleft(i)
print("전단에 9~13 삽입")

print("덱이 공백 상태가 아님" if dq else "덱은 공백 상태")



from queue import Queue
from random import randint

q = Queue(8) # queue 모듈의 Queue 클래스를 직접 사용

print("삽입 순서: ", end = ' ')
while not q.full():
    v = randint(0, 100) # random 모듈의 randint 함수를 직접 사용
    q.put(v)
    print(v, end = ' ')
print()

print("삭제 순서: ", end = ' ')
while not q.empty():
    print(q.get(), end = ' ')
print()
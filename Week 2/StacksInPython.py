# 문자열 역순 출력(파이썬 리스트 이용)
s = list()

str1 = input("Input the String 1: ")

for c in str1:
    s.append(c)
print("Print the String 1: ", end='')
while len(s) > 0:
    print(s.pop(), end=' ')
print()

# 문자열 역순 출력(LifoQueue 이용)
import queue 			        # 파이썬의 큐 모듈 포함
a = queue.LifoQueue(maxsize=100) # stack object 생성 (capacity: 100)

str2 = input("Input the str2: ")
for c in str2:
    a.put(c)
print("Print the str2: ", end=' ')
while not a.empty():
    print(a.get(), end=' ')
print()


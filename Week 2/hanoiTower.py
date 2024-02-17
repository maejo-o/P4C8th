# n=원판의 수, fr = 시작막대, tmp = 임시막대, to = 목표막대
def hanoi_tower(n, fr, tmp, to): 
    if (n==1):
        return("원판 1: %s --> %s" % (fr, to))
    else:
        hanoi_tower(n-1, fr, to, tmp) # 순환 호출
        print("원판 %d: %s --> %s" % (n, fr, to))
        hanoi_tower(n-1, tmp, fr, to)
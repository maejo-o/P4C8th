import json

while True:
    start=input("회원가입/로그인/탈퇴\n")
    print(start, "기능입니다.")
    print()

    if start=='회원가입':
        # 사용자로부터 ID, PW 를 입력 받는다.
        id = input('ID: ')
        pw = input('PW: ')
        with open('/Users/ijisu/Desktop/P4C/과제/Week 1/assign0102/user.json', "r", encoding="utf8") as f:
            datas=json.load(f)

            # 'user.data' 에 'users' 가 존재하지 않을 시 초기화
            if 'users' not in datas:
                datas['users'] = {}
            
            if 'users' in datas and id in datas['users']:
                print('이미 사용중인 ID 입니다.')
            else:
                # 입력받은 id, pw 저장
                datas['users'][id] = pw

                # 파일에 다시 쓰기
                with open('/Users/ijisu/Desktop/P4C/과제/Week 1/assign0102/user.json', "w", encoding="utf8") as f:
                    json.dump(datas, f)
                print('회원가입이 성공적으로 완료 되었습니다.')
            

    elif start=='로그인':
        # 사용자로부터 ID, PW 를 입력 받는다.
        id = input('ID: ')
        pw = input('PW: ')
        with open('/Users/ijisu/Desktop/P4C/과제/Week 1/assign0102/user.json', "r", encoding="utf8") as f:
            datas=json.load(f)

            # 기능 확인용
            # print(json_data)
            # print(json.dumps(json_data))

            if 'users' in datas and id in datas['users']:
                if datas['users'][id] == pw:
                    print('로그인에 성공했습니다.')
                else:
                    print('비밀번호가 틀렸습니다.')
            else:
                print('존재하지 않는 사용자 입니다.')
                
        
    # 회원탈퇴를 선택했을 경우
    else:
        # 사용자로부터 ID, PW 를 입력 받는다.
        id = input('ID:')
        pw = input('PW:')
        with open('/Users/ijisu/Desktop/P4C/과제/Week 1/assign0102/user.json', "r", encoding="utf8") as f:
            datas=json.load(f)

            if 'users' in datas and id in datas['users']:
                if datas['users'][id] == pw:
                    a = input('정말 탈퇴 하시겠습니까? (y/n): ')
                    if a == 'y' :
                        del datas['users'][id]

                        # 사용자 정보 삭제 후 파일 갱신
                        with open('/Users/ijisu/Desktop/P4C/과제/Week 1/assign0102/user.json', "w", encoding="utf8") as f:
                            json.dump(datas, f)

                        print('사용자 정보가 성공적으로 삭제 되었습니다.')

                else:
                    print('존재하지 않는 사용자 입니다.')



 

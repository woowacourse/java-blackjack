# java-blackjack

블랙잭 미션 저장소

# 블랙잭 게임

- 게임 룰
    - [x] 카드 덱은 총 52장 (4종류 문양, 13장의 숫자 카드)으로 구성되어 있다.
    - 카드 숫자 계산은 카드 숫자를 기본으로 하고, A(에이스)는 1또는 11로 계산할 수 있으며, J, Q, K는 각각 10으로 계산한다.
    - 가진 카드 숫자의 합이 21을 초과(버스트)하면 패배한다.
        - 딜러와 참여자가 둘 다 버스트한 경우 딜러가 승리한다.
    - 참여 인원은 최대 5인으로 제한한다.

## 프로그램 흐름

- 참가자 이름 입력
    - 게임에 참여할 참여자의 이름을 입력 받는다.
        - `,` 기준으로 분리
        - 이름은 2자 이상 10자 이하만 가능
        - 최소 1인, 최대 5인

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason
```

- 초기 카드 분배
    - 딜러와 참여자들에게 카드를 2장씩 나누어준다.
    - 딜러의 카드 목록(1장)을 공개 한다.
    - 참여자들의 카드 목록(2장)을 공개한다.
```
딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
```

- 참여자들의 추가 카드 분배
    - 참여자들이 원하는 만큼 카드를 더 받는다.
        - 카드를 더 받을지 물어본다
            - y(예): 카드를 한장 더 받고, 가진 카드 목록을 출력한다.
            - n(아니오): 카드를 그만 받고 다음 단계로 넘어간다.
                - 처음 답변이 n일 경우, 가진 카드 목록을 출력한다.
        - 카드를 더 받을 수 없는 경우(덱에서 뽑을 카드가 부족한 경우) 프로세스5로 넘어간다.
```
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드
```
- 딜러의 추가 카드 분배
    - 딜러의 카드의 합이 16이하면 강제로 카드를 한 장 더 받는다.
        - A(에이스)는 11로 취급하여 합을 계산한다.
        - A(에이스)가 2장인 경우, 합을 12(1 + 11)로 취급하고 카드를 한 장 받아야 한다.
```
딜러는 16이하라 한장의 카드를 더 받았습니다.
```
- 카드 점수 합산
    - 딜러의 카드 목록을 출력하고, 결과(합)를 출력한다.
    - 참여자들의 카드 목록을 출력하고, 결과(합)를 출력한다.
```
딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```
- 승패 결과 출력
    - 딜러의 최종 승패 결과 출력 (`x승 y패 z무`)
    - 참여자들의 최종 승패 결과 출력 (`승`, `패`, `무`)
```
## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```

### 예외 처리
- 예외가 발생하면 발생 시점부터 재입력을 받는다.

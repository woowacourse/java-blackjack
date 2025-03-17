# 블랙잭

블랙잭 게임은 다음과 같은 순서로 이루어진다.

1. 게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
2. 각 참가자들의 배팅 금액을 입력받는다.
3. 게임이 시작되면 딜러와 참가자들에게 2장의 카드를 나눠준다.
4. 딜러와 플레이어의 시작 손패를 보여준다.
5. 각 참가자들은 카드를 더 받을 수 있다면 받는다.
    1. 각 참가자들은 카드를 더 받을 수 있다면 받을지 물어본다.
    2. 카드를 받으면 현재 손패를 보여준다.
    3. 추가 카드를 한 번도 받지 않았다면 손패를 보여준다.
6. 딜러는 카드를 받아야 하는 조건이라면 카드를 받는다.
    1. 딜러의 손패가 16이하면 카드를 받는다.
7. 모든 사람의 카드 받기가 끝나면 모든 사람의 손패를 보여준다.
8. 승패를 계산한다.
9. 최종 수익을 계산하고 모든 사람의 최종 수익을 보여준다.

실행 결과 예제

```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
pobi카드: 2하트, 8스페이드, A클로버
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

# 구현할 기능

## 게임에 참여할 사람 이름을 입력받는다.

- [x] 공백이 입력되었을 경우, 예외를 발생시킨다.
- [x] 쉼표 기준으로 분리 후 참여자를 생성한다.
- [x] 참여자의 최대 인원수는 9명으로 제한한다.
    - [x] 만약 9명을 초과할 경우, 예외를 발생시킨다.
- [x] 참여자의 이름은 중복될 수 없다.
    - [x] 중복된 이름이 입력되었을 경우, 예외를 발생시킨다.

## 배팅 금액

- [x] 각 참여자의 배팅 금액을 입력받는다.
- [x] 배팅 금액은 0보다 큰 정수이다.

## 덱 생성

- [x] 카드 뭉치로부터 덱을 생성한다
    - [x] 덱이 생성될 때 카드들이 섞여야 한다
- [x] 덱에 들어가는 카드는 하트, 스페이스, 다이아, 클로버다.
- [x] 각 카드 별 A, 1, 2, 3, ... 10, Q, K, J 을 한 장씩 가지고 있어야 한다.

## 카드 분배

- [x] 참여자 모두에게 2장의 카드를 나눠준다
    - [x] 딜러에게 2장의 카드를 나눠준다
    - [x] 참여자 모두에게 2장의 카드를 나눠준다
- [x] 나눠준 카드들을 덱에서 제거한다.

## 추가 카드 요청

- [x] 모든 참여자에게 카드를 받을지 선택 여부를 입력받는다.
    - [x] y 일 경우 카드를 뽑고, n일 경우 카드를 뽑지 않는다.
        - [x] y or n이 아닌 값이 입력되었을 때, 예외를 발생시킨다.
    - [x] 참가자가 가진 카드의 합을 계산하는 기능
        - [x] A의 값은 11로 계산하되 21을 넘는 경우 1로 계산된다
    - [x] 가진 카드의 합이 특정 값을 초과하는지 판별할 수 있어야 한다.
    - [x] 참가자의 현재 카드의 합이 21을 초과할 경우, 입력받지 않는다.

## 딜러 카드 뽑기

- [x] 딜러가 카드를 뽑을 수 있는지 판별한다
- [x] 딜러의 현재 카드 상태가 16 이하라면, 카드를 한 장 더 뽑는다.
- [x] 딜러의 현재 카드 상태가 17 이상이라면, 추가 카드를 뽑지 않는다.

## 블랙잭 승패 계산

- [x] 참여자의 최종 카드 정보를 출력한다.
- [x] 최종 승패를 출력한다.
- [x] 딜러와 플레이어간의 승패를 계산하는 기능
- [x] 딜러와 플레이어가 bust 라면 딜러가 승리한다.
- [x] 카드 2장으로 21점을 만든 블랙잭일 경우 항상 블랙잭이 승리한다.
- [x] 딜러와 플레이어가 블랙잭일 경우 무승부이다.
- [x] 딜러와 플레이어가 bust가 아니라면 카드의 합이 높은 쪽이 승리한다.
- [x] 딜러와 플레이어가 bust가 아니고 카드의 합이 동일하다면 무승부이다.

## 모든 사람의 수익 계산

- [ ] 모든 참가자의 최종 수익을 출력한다.
- [ ] 플레이어가 우승하면 배팅 금액을 받는다.
- [ ] 플레이어가 블랙잭으로 우승하면 배팅 금액의 1.5배를 받는다.

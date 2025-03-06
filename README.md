# java-blackjack

## 기능 요구사항

### 플레이어

- [X] 플레이어는 게임 시작 시 두 장의 카드를 받는다.
- [x] 현재 보유한 카드 합계를 계산한다.
    - [x] 현재 보유한 카드 합계가 21 이하일 경우 플레이어는 카드를 한 장 더 뽑을지, 중단할지 결정한다.
    - [x] 현재 보유한 카드 합계가 21 초과일 경우 패배
    - [x] 딜러보다 21에 가까울 경우 승리

### 딜러

- [x] 딜러는 게임 시작 시 두 장의 카드를 받는다.
    - [x] 둘 중 하나의 카드만 공개한다.
- [x] 딜러는 처음에 받은 2장의 합계가 16이하인지 판정한다.
    - [ ] 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
    - [ ] 17점 이상이면 추가로 받을 수 없다.
- [x] 플레이어보다 숫자 합계가 높을 경우 승리
- [x] 플레이어보다 숫자 합계가 낮거나 21 초과일 경우 패배
- [x] 같을 경우 무승부

### 카드

- [x] 카드는 4개의 문양과 13개의 숫자 조합으로 구성된다.
- [ ] 게임 내에서 같은 카드는 존재하지 않는다.
- [X] 게임 시작 시, 모든 카드를 섞는다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.
- [X] Ace는 1 또는 11로 계산할 수 있다
    - [X] Ace를 11로 계산할 수 있을 경우(나머지 카드의 합계가 10 이하) Ace를 11로 계산한다.
    - [X] Ace를 11로 계산할 수 없을 경우(나머지 카드의 합계가 10 초과) Ace를 1로 계산한다.

### 블랙잭 규칙

- 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- [x] 플레이어와 딜러 모두 21일 경우 카드의 장수가 2장인 사람이 이긴다. (모두 두장이면 무승부)

## 입출력 요구사항

- [x] 게임에 참여할 사람의 이름을 입력받는다.
    - [x] 이름은 쉼표(,)로 구분한다.
    - [x] 참가자 수는 최소 1명, 최대 5명으로 제한한다.
    - [x] 같은 이름의 플레이어는 허용하지 않는다.
- [x] 플레이어에게 카드를 더 받을지 여부(y/n)를 입력받는다.
- [X] 딜러가 카드를 받았는지 출력한다.
- [X] 게임이 종료하면 결과를 출력한다.
    - [X] 플레이어, 딜러가 갖고있는 카드를 출력한다.
    - [X] 카드의 숫자 합계를 출력한다.
- [ ] 딜러와 플레이어의 승, 패를 출력한다.

~~~
실행 결과

게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승
jason: 패
~~~

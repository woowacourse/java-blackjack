# java-blackjack

## 블랙잭 게임 순서

1. 먼저 딜러를 정한 뒤, 베팅을 한다.
2. 딜러가 자신을 포함한 참가자 전원에게 카드 두 장을 나누어주는데, 딜러의 카드 한 장은 상대에게 보이지 않는다.
3. 카드의 합이 딜러보다 먼저 21이 되거나 딜러보다 21에 가깝게 되면 이기고, 카드를 더 받았는데 21을 초과하면 버스트(Bust)된다.
4. 먼저 받은 카드 두 장의 합이 21에 못 미치면 히트(Hit)라고 말한 뒤 한 장씩 더 받을 수 있고, 멈추려면 스탠드(Stand)라고 말한다.
5. 딜러는 카드의 합이 16 이하면 무조건 한 장을 더 받아야 하고, 17 이상의 경우에는 멈추어야 한다.
6. 딜러의 카드와 합이 같으면 비긴 것이 된다.
7. 에이스 카드는 1이나 11로 취급할 수 있고, 10, J, Q, K는 모두 10으로 계산한다.
8. 처음 받은 카드 두 장이 에이스와 10, J, Q, K 중의 하나로 합이 21이 되면 블랙잭(Blackjack)이 되고, 베팅한 금액의 1.5배의 돈을 받는다.

---

## 기능 요구 사항

### Controller

- [x] 덱의 카드 섞은 후 모든 플레이어들에게 카드 두 장씩 분배
- [ ] 배팅 금액들 생성
-
- 플레이어 턴
    - [x] 플레이어가 Hit 가능한 경우 계속 뽑을 수 있다.

- 딜러 턴
    - [x] 항상 마지막에 카드를 뽑는다.
    - [x] 딜러가 Hit 가능한 경우 계속 카드를 뽑는다.

### Result

- 플레이어와 결과를 가진다.

### ResultStatus

- 플레이어의 승, 무, 패를 표현한다.

### CardSuit

- [x] 하트, 클로버, 다이아몬드, 스페이드 4종류를 가진다.

### CardNumber

- [x] Ace부터 10, J, Q, K 까지 13장이 있다.
- [x] Ace의 값은 1 또는 11이고, J, Q, K의 값은 10이다.

### Card

- [x] 카드는 상징과 숫자를 가진다.

### Hand

- 카드들을 가진다.
- [ ] 카드들의 숫자를 계산하여 Score 를 반환한다.

### Score

- 점수를 가진다.
- [ ] 블랙잭 인지 알 수 있다.
- [ ] Hit 인지 알 수 있다.
- [ ] Bust 인지 알 수 있다.

### CardFactory

- [x] 서로 다른 카드 52장을 반환한다.

### Deck

- 서로 다른 카드들 52장을 가진다.
- 52장의 카드 순서를 섞는다.
- [x] 카드를 뽑으면 해당 카드를 제거하고 반환한다.
    - [x] 덱에 카드가 없으면 예외가 발생한다.

### Player

- 플레이어는 핸드를 갖는다.
- [x] 생성될 때 두 장의 카드를 지급 받는다.
- [x] 자신의 핸드에 카드를 추가할 수 있다.
- [ ] hand 의 Score 를 반환한다.

### Players

- 플레이어들을 가지는 일급 컬렉션.
- [x] 플레이어의 수는 1 ~ 4명 이어야 한다.
    - 플레이어의 수가 올바르지 않다면 예외가 발생한다.
- [ ] 플레이어들의 Score 를 반환한다.

### Dealer

- Player를 상속받는다.
- Deck을 가진다.
- [x] 덱에서 카드를 한 장 반환한다.
- [x] 초기 핸드에 입력할 두 개의 카드를 리스트로 반환한다.
- [x] 최종 점수를 계산하여 플레이어들의 배팅 결과를 반환한다.
    - 딜러와 플레이어 중 블랙잭이거나, 21에 가장 가까운 숫자를 가지는 쪽이 이긴다.
        - [x] 플레이어가 버스트 되면 배팅 금액을 모두 잃는다.
        - [ ] 플레이어만 블랙잭이라면 1.5배의 배팅 금액을 얻는다.
        - [ ] 둘 다 블랙잭인 경우 배팅 금액을 돌려받는다.
        - [x] 딜러가 버스트 되거나 플레이어 점수가 더 높으면 배팅 금액을 받는다.
        - [x] 딜러 보다 점수가 낮다면 배팅 금액을 모두 잃는다.
        - [x] 딜러와 동점인 경우 배팅 금액을 돌려받는다.

### InputView

- [x] 플레이어의 이름을 입력받는다.
    - 비어있는 이름이 입력되면 예외가 발생한다.
- [ ] 플레이어의 배팅 금액을 입력받는다.
    - 정수로 변환이 불가능하다면 예외가 발생한다.
- [x] 플레이어에게 카드를 더 받을지 입력받는다.
    - [x] 'y' 또는 'n' 이 아니라면 예외가 발생한다.

### OutputView

- [x] 플레이어들의 이름과 카드를 출력한다.
    - [x] 딜러의 카드 한 장은 상대에게 보이지 않는다.

- [ ] 플레이어가 카드를 뽑기 전 블랙잭이라면 메세지를 출력한다.
- [x] 플레이어가 카드를 뽑은 후 버스트 되면 메세지를 출력한다.
- [x] 딜러가 카드를 더 받으면 메세지를 출력한다.
    - "딜러는 16이하라 한장의 카드를 더 받았습니다."
- [x] 게임을 완료한 후 각 플레이어별로 핸드와 점수를 출력한다.
- [ ] 게임을 완료한 후 각 플레이어별로 수익을 출력한다.

## 실행 결과 예시

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



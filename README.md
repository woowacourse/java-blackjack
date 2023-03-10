# java-blackjack
블랙잭 미션 저장소

# 기능 요구 사항
- [ ] 게임에 참가할 사람의 이름을 입력한다.
- [ ] 참가자들의 배팅 금액을 입력 받는다.
- [ ] 게임을 시작한다.
  - [ ] 딜러와 참가자들에게 2장의 카드를 나눠준다.
  - [ ] 딜러는 1장의 카드를, 참가자들은 2장의 카드를 모두 출력한다.
  - [ ] 입력한 참가자의 순서대로 Hit와 Stand를 물어본다.
    - [ ] Stand를 한 경우 아무 행위를 하지 않고 다음 참가자로 넘어간다.
    - [ ] Hit를 한 경우 해당 참가자에게 1장의 카드를 준다.
  - [ ] 딜러의 점수가 16점을 초과할 때까지 카드를 받는다.
- [ ] 게임 결과를 출력한다.
  - [ ] 딜러와 참가자들의 카드와 총점을 출력한다.
  - [ ] 최종 수익을 출력한다.

# 주요 객체의 속성과 역할
## BlackjackGame
- [x] `Dealer`, `Players`를 가진다.
- [ ] 게임을 진행한다.

---

## Participant
### Participant
- [x] `Hand`와 `Money`를 가진다.
- [x] 카드를 한 장 받는다.
- [x] 돈을 받는다.
- [x] 자신의 총점을 구한다.
- [x] 자신이 버스트인지 확인한다.
- [x] 자신이 블랙잭인지 확인한다.
- [x] 첫 `Hand`를 받는다.

### Dealer
- [x] `Participant`를 상속한다.
- [x] 딜러라는 이름을 가진다.
- [x] `Bank`와 `Deck`을 가진다.
- [x] `Players`에게 2장의 카드를 세팅한다.
- [x] 본인에게 2장의 카드를 세팅한다.
- [x] 1장의 카드를 반환한다.
- [x] 본인의 점수가 16점 이하라면, 초과할 때까지 카드를 뽑는다.

### Players
- [x] `Player`들을 가진다.
  - [x] 최소 2명에서 최대 8명만 가질 수 있다.
- [ ] `Player`들에게 카드를 2장씩 나눠준다.
  - [ ] 받은 `Hand`와 `Players`의 수가 다르면 예외를 발생한다.

### Player
- [x] `Participant`를 상속한다.
- [x] 이름을 가진다.

---
## Card
### Deck
- [x] 52장의 트럼프 카드를 가진다.
- [x] 가지고 있는 카드를 한 장 뽑아서 준다.
- [x] 가지고 있는 카드를 두 장 뽑는다.

### Hand
- [x] `Card`들을 가진다.
- [x] 가지고 있는 `Card`들의 총점을 계산할 수 있다.
  - [x] `Card`가 Ace라면 1 또는 11로 계산할 수 있다.
- [x] `Card`를 추가로 가질 수 있다.
- [x] 버스트인지 확인한다.
- [x] 블랙잭인지 확인한다.
- [x] 상대방의 카드와 비교하여 결과를 반환한다.

### Card
- [x] `Denomination`을 가진다.
- [x] `Suit`를 가진다.
- [x] 점수를 반환한다.

### Denomination
- [x] 카드의 숫자를 가진다.
- [x] 각 숫자별 점수를 가진다.
- [x] Ace 카드인지 알려준다.
- [x] 숫자의 이름을 가진다.

### Suit
- [x] 카드의 모양을 가진다.
- [x] 모양의 이름을 가진다.

---

## Bank
- [x] 각 플레이어의 배팅 금액을 가진다.
- [x] 플레이어의 배팅 금액을 반환한다.

## Result
- [x] 승, 무, 패를 가진다.

## Money
- [x] 돈을 가진다.
- [x] 돈을 더 받을 수 있다.
- [x] 돈을 지출할 수 있다.

# 궁금했던 점
1. `Dealer`의 `settingCards(Players players)`를 구현하기 위해 `players.distributeHands()`를 구현했습니다. 또, `players.distributeHands()`를 구현하기 위해 `player.receiveHand(Card card)`를 구현했습니다. 그러다보니 추상화 계층 하위에 있는 메서드를 모두 테스트해야하나? 라는 고민이 생겼습니다. 추상화 계층 상위에 있는 메서드 하나만을 테스트하면 되는 것인지, 혹은 하위 계층에 있는 작은 단위의 메서드부터 상위까지 모든 메서드를 테스트 해야 하는지 궁금합니다! 

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
- [ ] Bank와 Deck을 가진다.
- [ ] 게임을 진행한다.
---
## Participant
### Participant
- [ ] Hand와 Money를 가진다.
- [ ] 카드를 받을 수 있다.
- [ ] 돈을 받을 수 있다.

### Dealer
- [ ] 딜러라는 이름을 가진다.
- [ ] `Players`를 가진다.

### Player
- [ ] 이름을 가진다.
- [ ] Hit 를 할 수 있다.
- [ ] Stand를 할 수 있다.
---
## Card
### Deck
- [ ] 52장의 트럼프 카드를 가진다.
- [ ] 가지고 있는 카드를 한 장 뽑아서 준다.

### Hand
- [ ] `Card`들을 가진다.
- [ ] 가지고 있는 `Card`들의 총점을 계산할 수 있다.
  - [ ] Card가 Ace라면 1 또는 11로 계산할 수 있다.

### Card
- [ ] `Denomination`을 가진다.
- [ ] `Suit`를 가진다.

### Denomination
- [ ] 카드의 숫자를 가진다.
- [ ] 각 숫자별 점수를 가진다.

### Suit
- [x] 카드의 모양을 가진다.
---
## Bank
- [ ] 각 플레이어의 배팅 금액을 가진다.
- [ ] 딜러나 플레이어에게 배팅 금액을 전달한다.

## Result
- [ ] 승, 무, 패를 가진다.
- [ ] 딜러의 카드와, 플레이어의 카드로 승패를 결정한다.

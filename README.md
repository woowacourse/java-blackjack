# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## Class Diagram

```mermaid
classDiagram
  BlackJackGame <|-- Gamblers
  BlackJackGame <|-- Deck
  BlackJackGame <|-- Players
  BlackJackGame <|-- Dealer
  Controller <|-- BlackJackGame
  Controller <|-- InputView
  Controller <|-- OutputView
  Deck <.. DeckGenerator
  <<interface>>DeckGenerator
  DeckGenerator <|.. RandomDeckGenerator
  Players <-- Player
  Player <-- Name
  Player <-- Hand
  Dealer <-- Name
  Dealer <-- Hand
  Hand <-- Card
  Card <-- Pattern
  Card <-- Number
```

## ✨기능 구현 목록

- [x] 플레이어의 이름을 입력 받는다.
  - [x] 이름은 공백제외 1글자 이상 5글자 이하로 한다.
  - [x] 플레이어의 이름은 '딜러'일 수 없다.
    - [x] 이 경우 예외처리
  - [x] 중복된 이름은 사용할 수 없다.
  - [ ] 플레이어는 최대 6명이다.

- [x] 플레이어는 베팅 금액을 입력받을 수 있다.
- [x] 플레이어는 배팅 금액을 가질 수 있다.
  - [x] 배팅금액은 1만원이상 10만원 이하까지가능하다.
- [x] 플레이어가 블랙잭인지 알 수 있다.


- [x] 덱을 만든다.
  - [x] 덱은 패턴과 숫자를 조합해 52장을 만든다.
  - [x] 덱을 랜덤으로 섞는다.

- [x] 카드를 나눠준다.
  - [x] 딜러와 참여자에게 2장씩 나눠준다.
  - [x] 나눠준 카드를 이름과 함께 출력한다.
    - [x] 딜러는 1장만 출력한다.

- [ ] 최종 수익을 계산할 수 있다.
  - [ ] 최종 수익은 1000원 단위이다.
  - [ ] 딜러가 처음 받은 카드가 블랙잭일 경우 블랙잭인 플레이어를 제외하고 베팅한 금액을 잃는다.
  - 플레이어가 블랙잭인 경우
    - 딜러도 블랙잭이면,
      - [ ] 플레이어는 베팅한 금액을 돌려받는다
    - 플레이어만 블랙잭이면,
      - [ ] 처음 두 장의 카드 합이 21일 경우 베팅 금액의 1.5 배를 딜러에게 받는다.
      - [ ] 그렇지 않을 경우 베팅 금액을 받는다.
  - [ ] 플레이어가 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
  - 추가 카드를 받은 딜러가 버스트가 되면
    - [ ] 버스트가 아닌 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

- [x] 플레이어마다 카드를 추가로 받을 지 입력받는다.
  - [x] y 면 카드를 추가로 받고
  - [x] n 이면 카드를 받지 않는다.
  - [x] y또는 n이 아닐경우 예외발생

- [x] 딜러가 카드를 추가로 받을 지 결정한다.
  - [x] 딜러의 카드 합이 16이하면 카드를 추가로 받는다.
  - [x] 딜러의 카드 합이 17 이상이면 카드를 받지 않는다.

- [x] 딜러와 플레이어의 카드를 출력한다.

- [x] 카드의 점수를 계산한다.
  - [x] 점수는 음수일 수 없다.
  - 에이스가 포함되어있을 때
  - [x] 점수 합계가 11 이하이면 10점을 더한다.
  - [x] 점수 합계가 11 초과이면 유지한다.

- [x] 승패를 계산한다.
  - [x] 딜러의 카드합이 높으면 딜러승
  - [x] 딜러보다 플레이어의 카드합이 높으면 플레이어 승
  - [x] 딜러와 플레이어의 카드합이 같으면 무승부
  - [x] 딜러의 카드합이 모두 21 초과면 플레이어 패

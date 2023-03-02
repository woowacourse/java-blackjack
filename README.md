# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## Class Diagram

```mermaid
classDiagram
    BlackJackGame <|-- Gamblers
    BlackJackGame <|-- Deck
    Controller <|-- BlackJackGame
    Controller <|-- InputView
    Controller <|-- OutputView
    Deck <.. DeckGenerator
    <<interface>>DeckGenerator
    DeckGenerator <|.. RandomDeckGenerator
    Gamblers <-- Gambler
    <<interface>> Gambler
    Gambler <|.. Player
    Gambler <|.. Dealer
    Player <-- Name
    Player <-- Hand
    Dealer <-- Name
    Dealer <-- Hand
    Hand <-- Card
    Card <-- Pattern
    Card <-- Number
```

## ✨기능 구현 목록

- [ ] 게임 참여자의 이름을 입력 받는다.
    - [ ] 이름은 공백제외 1글자 이상 5글자 이하로 한다.
    - [ ] 중복된 이름은 사용할 수 없다.


- [ ] 덱을 만든다.
    - [ ] 덱은 패턴과 숫자를 조합해 52장을 만든다.
    - [ ] 덱을 랜덤으로 섞는다.


- [ ] 카드를 나눠준다.
    - [ ] 딜러와 참여자에게 2장씩 나눠준다.


- [ ] 나눠준 카드를 이름과 함께 출력한다.
    - [ ] 딜러는 1장만 출력한다.


- [ ] 플레이어마다 카드를 추가로 받을 지 입력받는다.
    - [ ] y 면 카드를 추가로 받고
    - [ ] n 이면 카드를 받지 않는다.

- [ ] 딜러가 카드를 추가로 받을 지 결정한다.
    - [ ] 딜러의 카드 합이 16이하면 카드를 추가로 받는다.
    - [ ] 딜러의 카드 합이 17 이상이면 카드를 받지 않는다.

- [ ] 딜러와 플레이어의 카드를 출력한다.


- [ ] 승패를 계산한다.
    - [ ] 딜러의 카드합이 높으면 딜러승
    - [ ] 딜러보다 플레이어의 카드합이 높으면 플레이어 승
    - [ ] 딜러와 플레이어의 카드합이 같으면 무승부
    - [ ] 딜러와 플레이어의 카드합이 모두 21 초과면 무승부

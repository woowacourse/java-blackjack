# java-blackjack

## BlackJackGame
- [ ] Player에게 카드를 준다.
- [ ] 승패를 계산한다.

## Player <Interface>
- [ ] 보유하고 있는 카드를 반환한다.
- [ ] 카드를 받는다. 

### Dealer
- [ ] Cards
- [ ] 보유하고 있는 카드를 반환한다. - 카드 한 장을 숨기고 반환한다.
- [ ] 카드를 받는다 - 카드가 16 이하 일 때.

### Gamer
- [ ] Cards
- [ ] name
- [ ] 보유하고 있는 카드를 반환한다. - 모든 카드를 반환한다.
- [ ] 카드를 받는다 - 카드가 21 이하일 때
- [ ] [Error] 동일한 이름이 들어오면 예외를 발생시킨다. - input 검증
- [ ] [Error] 이름이 들어오지 않는 경우 예외를 발생시킨다. - input 검증
- [ ] [Error] 이름에 공백이 포함된 경우 예외를 발생시킨다. - input 검증
- [ ] [Error] 이름이 "딜러"인경우 예외를 발생시킨다. - 생성자 검증


## Deck
- [x] 52장의 카드를 갖고 있다.
- [x] 카드를 뽑는다.
- [x] [Error] 카드가 0장이면 예외를 발생시킨다.

## Card
- [x] Enum - Denomination
- [x] Enum - Suit

## Yes / No
- [ ] [Error] y/ n 중 입력이 없으면 예외를 발생시킨다.
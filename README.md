# 🐰 기능 목록

<hr>

## Deck

- [X] 생성될 때 Card 52장을 초기화해서 셔플한다.
- [X] Card 한장을 뽑는다. (Player에게 Card 배분)

## Card

- [X] 필드로 Suit을 가진다.
- [X] 필드로 Denomination을 가진다.

## Suit (enum)

- [X] 필드로 이름을 가진다.

## Denomination (enum)

- [X] 해당 숫자와 점수를 가진다.

## Cards

- [X] 컬렉션의 점수를 계산한다.
- [X] A는 1점 혹은 11점을 선택한다.
- 
### Player

- [X] 필드로 PlayerName을 가진다.
- [X] 필드로 Cards를 가진다.

### Dealer (Player 상속) 

- [X] 16이하면 카드를 뽑고 17이상이면 카드를 뽑지 않는다.

#### PlayerName

- [X] 플레이어의 이름을 검증한다.
  - [X] blank면 예외 처리

## Players

- [X] Player들의 이름이 중복되는지 검증한다.  
- [X] Player는 5명까지 참여 가능하다.

## Score

- [X] 21점을 초과할 경우 isBusted가 true가 된다.

## InputView 

- [X] 입력 메시지를 출력한다.
- [X] 이름을 "," 기준으로 입력받는다.
- [X] 카드를 더 뽑을지 입력받는다.

## OutputView

- [X] 각 Gambler가 가진 카드를 출력한다.
- [X] 각 Gambler가 가진 카드와 점수를 출력한다.
- [X] 결과(최종 승패)를 출력한다.
  - [X] Dealer의 최종 승패를 출력한다.
  - [X] Player들의 승패를 출력한다.
- [X] 게임 초반 2장 배부 가이드 메세지를 출력한다. 

## CardParser

- [X] Suit와 Denomination의 출력 메시지를 결정한다.
- [X] enumMap 사용

# 추가 요구 사항

## 베팅

- [X] player는 승리할 경우 베팅 금액을 받는다.
- [X] player가 블랙잭일 경우 베팅금액의 1.5배를 받는다.
- [X] player가 버스트되면 베팅 금액을 모두 잃는다.
- [X] dealer가 버스트되면 버스트되지 않은 플레이어들은 베팅금액을 받는다.

### Bet

- [X] 0 이하의 value를 가질 수 없다.
- [X] 100_000 초과의 value를 가질 수 없다.
- [X] 결과에 따라 상금을 반환한다.

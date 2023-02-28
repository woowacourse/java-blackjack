# 🐰 기능 목록

<hr>

## Deck

- [ ] 시작할 때 Card 52장을 초기화해서 셔플한다.
- [ ] Card 한장을 뽑는다.

## Card

- [ ] 필드로 Suit을 가진다.
- [ ] 필드로 Numbers를 가진다.

## Suit (enum)

- [ ] 필드로 이름을 가진다.

## Denomination (enum)

- [ ] 해당 숫자와 점수를 가진다.

## Cards

- [ ] 컬렉션의 점수를 계산한다.

## Gambler (interface)

- [ ] 카드를 뽑는다.

### Player

- [ ] 필드로 PlayerName을 가진다.
- [ ] 필드로 Cards를 가진다.

#### PlayerName
 
- [ ] 플레이어의 이름을 검증한다.
  - [ ] blank면 예외 처리
  - [ ] null이면 예외 처리

### Dealer (플레이어 상속) 

- [ ] 필드로 Cards를 가진다.
- [ ] 16이하면 카드를 뽑고 17이상이면 카드를 뽑지 않는다.

## Players

- [ ] Player들의 이름이 중복되는지 검증한다.  

## InputView 

- [ ] 입력 메시지를 출력한다.
- [ ] 이름을 "," 기준으로 입력받는다.
- [ ] 카드를 더 뽑을지 입력받는다.

## OutputView

- [ ] 각 Gambler가 가진 카드를 출력한다.
- [ ] 결과(최종 승패)를 출력한다.
  - [ ] Dealer의 최종 승패를 출력한다.
  - [ ] Player들의 승패를 출력한다.

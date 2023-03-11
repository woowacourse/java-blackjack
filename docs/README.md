# 🐰 기능 목록

<hr>

## Deck

- [X] 시작할 때 Card 52장을 초기화해서 셔플한다.
- [X] Card 한장을 뽑는다. (Gambler에게 Card 배분)

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

## Gambler (interface)

- [X] 카드를 뽑는다. (자신의 Cards에 뽑은 Card 넣기)

### Player (Gambler 상속)

- [X] 필드로 PlayerName을 가진다.
- [X] 필드로 Cards를 가진다.

#### PlayerName

- [X] 플레이어의 이름을 검증한다.
    - [X] blank면 예외 처리

### Dealer (Gambler 상속)

- [X] 필드로 Cards를 가진다.
- [X] 16이하면 카드를 뽑고 17이상이면 카드를 뽑지 않는다.

## Players

- [X] Player들의 이름이 중복되는지 검증한다.
- [X] Player는 5명까지 참여 가능하다.

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

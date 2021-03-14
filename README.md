# java-blackjack

블랙잭 게임 미션 저장소

## 기능 요구 사항

- Player
- [x] 구현 : 덱을 가지고 있어야 한다.
- [x] 구현 : 덱에 카드를 추가할 수 있다.
- [x] 구현 : 덱의 점수를 계산한다.
- [x] 예외 : 중복되는 카드를 가질 수 없다.
    - Dealer
        - [x] 구현 : 자신의 점수가 16 이하인 지 확인
- [x] 구현 : 플레이어는 배팅금액을 입력한다.
    - [x] 예외 : 숫자가 아닌 입력은 예외 


- Card
- [x] 구현 : Symbol(enum) 과 CardNumber(enum) 의 값을 가진다.
- [x] 구현 : CardNumber에서 점수를 가져올 수 있다.

- Cards
- [x] 구현 : 셔플할 수 있다. (생성자)
- [x] 예외 : 동일한 카드를 넣을 수 없다.

- CardsFactory
- [x] 구현 : 생성자에서 모든 카드의 수를 초기화한다.

- Game
- [x] 구현 : 시작 시 두 장 배분 -> 초기화
- [x] 구현 : 이름을 받아 그 이름에 해당하는 플레이어에게 카드 배분 후 각 플레이어의 playerState(status, deck)를 리턴한다.
    - [x] 예외 : 존재하지 않는 사용자의 이름을 받을 시 PlayerNotFoundException 발생


- GameResult
- [x] 구현 : 최종 승패 결과를 반환한다.
- [x] 구현 : 플레이어의 이익을 반환한다.

- Status
- [x] 구현 : 점수를 받아 BURST, BLACKJACK, HIT 를 계산 후 반환

- Gamers
- [x] 구현 : 게이머들을 담는 일급컬렉션 구현
- [x] 예외 : 동명이인이 존재할 경우 예외

## 우아한테크코스 코드리뷰

* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
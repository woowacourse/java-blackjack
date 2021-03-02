# java-blackjack
블랙잭 게임 미션 저장소

## 기능 요구 사항
- Player
- [ ] 구현 : 덱을 가지고 있어야 한다.
- [ ] 구현 : 덱에 카드를 추가할 수 있다.
- [ ] 구현 : 덱의 점수를 계산한다.
- [ ] 예외 : 중복되는 카드를 가질 수 없다.
  - Dealer
    - [ ] 구현 : 자신의 점수가 16 이하인 지 확인

- Card
- [ ] 구현 : Symbol(enum) 과 CardNumber(enum) 의 값을 가진다.
- [ ] 구현 : CardNumber에서 점수를 가져올 수 있다.

- Cards
- [ ] 구현 : 셔플할 수 있다. (생성자)  
- [ ] 예외 : 동일한 카드를 넣을 수 없다.

- CardsFactory
- [ ] 구현 : 생성자에서 모든 카드의 수를 초기화한다.  

- Game
- [ ] 구현 : 시작 시 두 장 배분 -> 초기화
- [ ] 구현 : 이름을 받아 그 이름에 해당하는 플레이어에게 카드 배분 후 
  각 플레이어의 playerState(status, deck)를 리턴한다.
- [ ] 예외 : 동명이인은 없는 걸루.
- [ ] 구현 : 최종 승패 결과를 반환한다.

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
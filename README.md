# java-blackjack
블랙잭 게임 미션 저장소

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 구현 목록

- [x] 참여할 사람의 이름을 받아 플레이어를 생성한다.
- [X] 카드 덱을 구성한다. (플레잉 카드 규칙에 의거)
- [x] 각 카드는 점수를 가지고 있다.
- [x] 각 카드는 문양을 가지고 있다.
- [x] 각 유저(플레이어, 딜)은 시작 시 2장의 카드를 받는다.
- [x] 블랙잭인지 판별 할 수 있어야 한다.  
  - [분기1] 딜러가 블랙잭인 경우
    - [x] 그 자리에서 모두와 승패를 가리고 게임이 종료된다.
  - [분기2] 딜러가 블랙잭이 아닌 경우
    - [x] 블랙잭인 플레이어는 히트/스테이를 선택하지 않고 무조건 승리한다.
    - [ ] 블랙잭이 아닌 플레이어는 히트/스테이를 선택할 수 있다.
     - [ ] 히트를 선택한 후, 21을 넘어가면 버스트가 되며 게임에서 패배한다.
     - [ ] 그렇지 않은 경우, 스테이를 선택 할 때까지 계속 카드를 받는다.
    - [ ] 딜러는 16이하일 시 무조건 한 장을 더 받는다.
      - [ ] 이 때, 딜러가 버스트가 되는 경우 그 시점까지 살아남은 모든 플레이어는 승리하며, 버스트된 플레이어는 진다.
    - [ ] 모든 플레이어가 스테이하며, 딜러 카드의 합이 17 이상인 경우 한 번의 게임이 끝난다.
- [x] 각 유저마다 점수를 계산할 수 있어야 한다.
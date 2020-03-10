# java-blackjack
블랙잭 게임 미션 저장소
---
###기능 요구사항
* 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
* 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
* 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 기능 구현 목록
* 게임에 참여할 사람의 이름을 입력받는 기능
    * (예외 처리) 공백인 이름일 경우
* 기본 2장의 카드를 나누어주는 기능
* 플레이어별로 추가 카드를 받는 기능
    * (예외 처리) y 또는 n이 아닐 경우
* 딜러가 16이하일 경우 추가 카드를 받는 기능
* 모든 플레이어의 결과를 출력하는 기능
* 게임 최종 승패를 출력하는 기능

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
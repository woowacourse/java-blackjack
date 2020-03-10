# java-blackjack
블랙잭 게임 미션 저장소

## 게임 소개
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 기능 요구 사항

### Domain

- [x] 입력 받은 이름으로 유저들을 생성한다. (유저만)
    - [x] 입력 받은 문자열이 null 또는 공백인지 검증
- 딜러와 유저에게 두장의 카드를 분배한다.
- 추가 지급을 원하는 지 물어본다.
    - 유저는 현재 카드의 총합이 21 미만인 경우
    - 딜러는 16이하인 경우에 받고 17이상인 경우는 받지 않는다 (자동)
- 플레이어들의 결과를 계산한다. 
    - ACE 계산
    - 딜러와 유저 비교
        - 딜러 >= 유저 : 딜러 1승추가 그리고 패배
        - 딜러 < 유저 : 딜러 1패추가 그리고 승리     

### View

- 이름을 입력 받는다. (쉼표로 분리)
- 분배 받은 카드를 각각 출력한다.
- Y / N 을 입력 받는다.
- 추가 분배를 받는 경우 다시 카드 목록을 출력한다. (사용자, 딜러 둘다)
- 모든 카드 및 합을 출력하고 최종 승패를 출력한다.


## 구현 순서 (TDD)

- [x] Symbol
- [x] Type
- [x] Card
- [x] Cards
- [x] Player
- [ ] Dealer
- [ ] Participant
- [ ] Results

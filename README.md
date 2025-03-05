# java-blackjack

블랙잭 미션 저장소

## 입력

- [ ] 게임에 참여할 사람의 이름을 입력받는다.
    - [ ] 이름은 쉼표로 구분한다.
    - [ ] 이름이 공백이면 예외처리 한다.
    - [ ] 이름은 중복되지 않는다.
- [ ] 플레이어는 한장의 카드를 더 받을지 선택할 수 있다.
    - [ ] Y 라면 한장을 더 받는다.
    - [ ] N 라면 한장을 더 받지 않는다.

## 도메인

- [ ] 딜러와 플레이어에게 카드를 2장씩 나누어준다.
    - [x] 딜러, 플레이어의 점수는 곧 카드뭉치의 카드들의 점수의 합이다.
    - [x] 카드 뭉치를 통해 카드를 관리할 수 있다.
        - [x] 플레이어, 딜러의 카드 뭉치에 카드를 추가할 수 있다
        - [x] 카드뭉치에 카드를 뽑을 수 있다.
    - [ ] 카드 뭉치의 카드는 중복되지 않는다.
    - [ ] 카드 뭉치의 카드 순서는 랜덤이다.
    - [x] 플레이어는 이름으로 구별된다.
    - [ ] 게임 시작 시 플레이어는 2장의 카드를 받는다.
    - [ ] 게임 시작 시 딜러는 2장의 카드를 받는다.

    - [x] 카드의 종류는 4가지다.
    - [x] 카드는 문양과 숫자를 가진다.
    - [x] 카드는 1부터 k까지 13개의 숫자를 가진다.
    - [x] 카드는 1부터 10까지의 점수를 가진다.
    - [x] J, Q, K 는 각각 10으로 계산한다.
    - [x] 카드의 숫자 계산은 카드의 기본 숫자로 한다.
    - [ ] Ace는 1또는 11로 계산할 수 있다.
    - [ ] Ace가 있는 경우 21을 초과한 경우 1로 간주한다.
        - [ ] Ace가 있는 경우 21을 초과하지 않은 경우 11로 간주한다.

- [x] 플레이어는 카드 뭉치에서 카드를 한장 뽑을 수 있다.
- [ ] 플레이어는 한장의 카드를 더 받을지 선택할 수 있다.
    - [ ] Y 라면 한장을 더 받는다.
    - [ ] N 라면 한장을 더 받지 않는다.
    - [ ] 21이 초과되었다면 버스트된다.
    - [ ] 21이 초과되지 않았다면 카드를 더 받을지 선택할 수 있다.
    - [ ] 플레이어의 점수는 가지고 있는 카드의 총 합계이다.

- [ ] 딜러는 처음에 받은 2장의 합계가 16이하면 반드시 1장의 카드를 추가로 받는다.
    - [ ] 처음 받는 2장의 합계가 17 이상이라면 추가로 받을 수 없다.

- [ ] 버스트 되면 패배 처리 한다.

- [ ] 합계가 21을 초과하지 않고, 21에 더 가까운 사람이 승리한다.

- [ ] 카드의 합계가 같을 경우 무승부다.

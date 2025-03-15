# java-blackjack

블랙잭 미션 저장소

## 입력

- [x] 게임에 참여할 사람의 이름을 입력받는다.
    - [x] 이름은 쉼표로 구분한다.
    - [x] 이름이 공백이면 예외처리 한다.
- [x] 플레이어는 한장의 카드를 더 받을지 선택할 수 있다.
    - [x] Y 라면 한장을 더 받는다.
    - [x] N 라면 한장을 더 받지 않는다.

## 도메인

- [x] 딜러와 플레이어에게 카드를 2장씩 나누어준다.
    - [x] 딜러, 플레이어의 점수는 곧 카드뭉치의 카드들의 점수의 합이다.
    - [x] 카드 뭉치를 통해 카드를 관리할 수 있다.
        - [x] 플레이어, 딜러의 카드 뭉치에 카드를 추가할 수 있다
        - [x] 카드뭉치에 카드를 뽑을 수 있다.
    - [x] 카드 뭉치의 카드는 중복되지 않는다.
    - [x] 카드 뭉치의 카드 순서는 랜덤이다.
    - [x] 플레이어는 이름으로 구별된다.
    - [x] 게임 시작 시 플레이어는 2장의 카드를 받는다.
    - [x] 게임 시작 시 딜러는 2장의 카드를 받는다.

    - [x] 카드의 종류는 4가지다.
    - [x] 카드는 문양과 숫자를 가진다.
    - [x] 카드는 1부터 k까지 13개의 숫자를 가진다.
    - [x] 카드는 1부터 10까지의 점수를 가진다.
    - [x] J, Q, K 는 각각 10으로 계산한다.
    - [x] 카드의 숫자 계산은 카드의 기본 숫자로 한다.
    - [x] Ace는 1또는 11로 계산할 수 있다.
    - [x] Ace가 있는 경우 21을 초과한 경우 1로 간주한다.
        - [x] Ace가 있는 경우 21을 초과하지 않은 경우 11로 간주한다.

- [x] 플레이어는 카드 뭉치에서 카드를 한장 뽑을 수 있다.
- [x] 플레이어는 한장의 카드를 더 받을지 선택할 수 있다.
    - [x] Y 라면 한장을 더 받는다.
    - [x] N 라면 한장을 더 받지 않는다.
    - [x] 21이 초과되었다면 버스트된다.
    - [x] 21이 초과되지 않았다면 카드를 더 받을지 선택할 수 있다.
    - [x] 플레이어의 점수는 가지고 있는 카드의 총 합계이다.

- [x] 딜러는 처음에 받은 2장의 합계가 16이하면 반드시 1장의 카드를 추가로 받는다.
    - [x] 처음 받는 2장의 합계가 17 이상이라면 추가로 받을 수 없다.

- [x] 버스트 되면 패배 처리 한다.

- [x] 합계가 21을 초과하지 않고, 21에 더 가까운 사람이 승리한다.

- [x] 카드의 합계가 같을 경우 무승부다.

### step2

- [x] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
- [x] 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
- [ ] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
- [ ] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관없이 승리해 베팅 금액을 받는다.

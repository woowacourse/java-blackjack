# java-blackjack

블랙잭 미션 저장소

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- [x] 카드의 숫자 계산은 카드 문양이 아닌 카드 숫자의 합으로 한다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.
- [x] 예외로 Ace는 카드의 합이 21을 초과하기 전까지는 11로 계산하고, 그 외에는 1로 계산한다.
- [x] 덱은 중복되지 않는 임의의 카드를 반환한다.
- [x] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
- [x] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- [ ] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
- [ ] 17점 이상이면 카드를 추가로 받지 않는다.
- [ ] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합보다 크다면 이긴다.
- [ ] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합과 같다면 비긴다.
- [ ] 두 장의 카드 숫자를 합쳐 21을 초과하거나 딜러의 카드 숫자의 합보다 작다면 패배한다.
- [ ] 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

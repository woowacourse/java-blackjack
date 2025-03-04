# java-blackjack

블랙잭 미션 저장소

### 도메인 요구사항

블랙잭 기본 규칙 정의

- [x] 카드 덱에는 4종류의 모양이 13장씩있다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.
- [x] Ace는 1 또는 11로 계산할 수 있다.
- [ ] 블랙잭이란 (Ace, 10), (Ace, Queen), (Ace, King), (Ace, Jack) 쌍을 의미한다.
- [x] 플레이어수가 딜러를 제외한 최대 10명을 넘으면 안된다.
- [x] 플레이어수가 딜러를 제외한 최소 2명을 넘지 않아야 한다.
- [ ] 게임이 시작되면 카드를 랜덤하게 섞는다.
- [x] 딜러는 게임 시작시 덱에서 2장의 카드를 뽑는다.
- [x] 딜러가 플레이어게 카드를 2장씩 나누어준다.
- [x] 플레이어는 추가로 카드를 받을 수 있다.
- [x] 한 플레이어가 가질 수 있는 카드 합의 최대는 21이다.
    - [x] 카드 합이 21이 넘는 경우 카드를 더 받지 못한다.
- [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- [x] 플레이어는 자신이 가진 카드로 21에 최대한 가깝게 점수를 계산할 수 있다
- [x] 딜러의 점수와 같으면 무승부다.
- [ ] 딜러의 점수보다 낮다면 패배이다.
- [ ] 딜러의 점수보다 높다면 승리이다.
- [ ] 딜러가 21을 초과한다면 남아있는 플레이어들은 승리한다.
- [ ] 딜러가 블랙잭일 경우, 딜러의 승리이다.
    - [ ] 단, 플레이어 중 블랙잭이 있을 경우, 해당 플레이어는 무승부이다.

### 입출력 요구사항

- [ ] 게임에 참여할 사람의 이름을 입력받는다.
    - [ ] 구분자 쉼표가 아닌경우 예외를 발생시킨다.
- [ ] 각 플레이어별로 자신이 받은 카드 정보를 출력한다. 단, 딜러는 1장만 공개한다.
- [ ] 각 플레이어별로 추가 카드를 받을지에 대한 여부를 입력받는다.
    - [ ] y 또는 n이 아닌 다른 문자일경우 예외를 발생시킨다.
- [ ] 딜러의 카드 숫자합이 16이하일 경우 16을 넘을때까지 카드를 받는다고 출력한다.
- [ ] 게임을 실행한 결과를 출력한다.
- [ ] 게임의 최종 승패를 출력한다. 

# java-blackjack

블랙잭 미션 저장소

### 공통

- [ ] 잘못된 입력 시, 예외를 발생하고 프로그램을 종료한다.

### 사용자 입력 받기

- [x] 게임에 참여할 사람의 이름을 입력 받는다.
    - [x] 쉼표 기준으로 입력을 받는다.
    - [x] 5명 이하의 이름을 입력 받는다.
- [ ] 각 플레이어 별 베팅 금액을 입력 받는다.
    - [ ] 21억을 넘지 않는 숫자를 입력 받는다.

### 기본 카드 나눠주기

- [x] 플레이어부터 카드를 한 장씩 2 사이클 나눠준다.
- [ ] 플레이어가 가진 2장의 카드 합이 21일 경우, 베팅 금액의 1.5배를 딜러에게 받는다.
- [x] 딜러의 2번 째 카드는 비공개한다.

### 카드 추가 배부하기

- [x] 플레이어에게 y 또는 n를 입력 받는다.
- [x] y를 입력 받을 시, 카드 한 장을 배부한 후 플레이어의 카드들을 출력한다.
    - [ ] 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃는다.
- [x] n을 입력 받을 시, 플레이어의 카드들을 출력하고 다음 플레이어로 넘어간다.
- [x] 딜러는 카드 점수 합산이 17 이상일 때까지 카드를 반복해서 받는다.
    - [ ] 딜러의 카드 점수 합산이 21 초과일 경우, 해당 시점에 남아있던 플레이어들은 베팅 금액을 받는다.

### 점수 출력하기

- [x] 각 플레이어 별 카드 점수 합산을 계산한 후 결과를 출력한다.
    - Ace는 1 또는 11로, 플레이어에게 유리한 방향으로 계산한다.
    - King, Queen, Jack은 각각 10으로 계산한다.

### 최종 수익 출력하기

- [x] 딜러와 플레이어들의 최종 수익 결과를 출력한다.

# java-blackjack

# 기능구현목록

### 플레이어
- [x] 사람은 이름을 가진다.
- [x] [Error] 사람의 이름에 null이 들어오면 예외를 발생시킨다.
- [x] [Error] 사람의 이름에 공백이 들어오면 예외를 발생시킨다.
- [x] [Error] 사람의 이름들 간에 중복이 있으면 예외를 발생시킨다.

### 배팅 금액
- [x] [Error] 배팅 금액이 자연수가 아니면 예외를 발생시킨다.
- [x] [Error] 배팅 금액에 null이 들어오면 예외를 발생시킨다.
- [ ] [Error] 배팅 금액에 공백이 들어오면 예외를 발생시킨다.

### 배팅
- [ ] 승리하면 배팅 금액을 얻는다.
  - [ ] 블랙잭으로 승리하면 배팅 금액의 1.5배를 얻는다.
- [ ] 패배하면 배팅 금액을 잃는다.
  - [ ] 상대가 블랙잭인 경우, 패배하면 배팅 금액의 1.5배를 잃는다.
- [ ] 무승부라면 금액을 얻지도 잃지도 않는다.

### 카드
- [x] 카드는 문양과 번호를 가진다.
- [x] A는 현재 번호의 +10 스페셜 점수를 반환할 수 있다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.

### 카드덱
- [x] 카드 2장을 덱에서 꺼내서 반환할 수 있다.
- [x] 카드 1장을 덱에서 꺼내서 반환할 수 있다.
- [x] [Error] 남은 카드의 수보다 많은 카드의 수를 반환하려고 하면 예외를 발생시킨다.

### 게임
- [x] 첫 턴에 딜러와 참가자 모두는 카드를 2장씩 받는다.
- [x] [Error] 모든 턴이 종료되었을 때 카드를 유저에게 주려하면 예외를 발생시킨다.

### 턴(공통)
- [x] 카드덱에서 카드를 받을 수 있다.
- [x] 카드의 합이 21 초과이면 턴이 종료된다.
- [x] 드로우 가능 여부를 확인할 수 있다.
- [x] [Error] 드로우 할 수 없는데 카드를 받으려하면 예외를 발생시킨다.

### 플레이어 턴
- [x] 본인의 턴에서 카드를 더 받을지 말지를 결정할 수 있다.
- [x] 카드를 더 받지 않는다고 하면 턴이 종료된다.

### 딜러 턴
- [x] 딜러는 카드의 합이 16이하면 카드를 한 장 더 받는다.
- [x] 딜러의 드로우 가능 여부를 확인할 수 있다.
- [x] 딜러는 카드의 합이 17이상이면 턴이 종료된다.

### 카드 합 계산
- [x] 카드의 숫자의 합을 반환한다.
- [x] [Error] 턴이 종료되지 않은 사람의 카드 합을 계산하려 할 경우 예외를 발생시킨다.
- [x] A가 포함될 경우,
    - [x] 버스트가 아닌 21에 가장 가까운 합을 반환한다.
    - [x] 모든 상황이 버스트인 경우, 가장 작은 합을 반환한다.
- [x] K, Q J은 10으로 계산한다.

### 승패 결과
- [x] 딜러가 Bust인 경우
    - [x] 플레이어가 Bust면 무승부한다.
    - [x] 플레이어가 Bust가 아니면 플레이어가 승리한다.
- [x] 딜러가 Bust가 아닌 경우
    - [x] 플레이어가 Bust면 딜러가 승리한다.
    - [x] 플레이어와 딜러의 카드 합이 같으면 무승부한다.
    - [x] 플레이어가 Bust가 아니면, 21에 가까운 카드 합을 가진 사람이 승리한다.
- [x] BlackJack
    - [x] 플레이어와 딜러가 모두 블랙잭이면 무승부한다.
    - [x] 블랙잭인 사람이 승리한다.
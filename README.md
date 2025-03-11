# java-blackjack

블랙잭 미션 저장소


### 카드
- [X] ACE = 1 또는 11
- [X] 킹, 잭, 퀸 = 10
- [X] 나머지는 숫자대로
- [x] 본인 및 플레이어에게 카드를 배분한다.

### 손패
- [x] 카드의 합을 구한다.

### 딜러
- [x] 카드의 합이 16이하면 반드시 카드를 뽑습니다.
- [x] 카드의 합이 17이상이면 절대로 카드를 뽑을 수 없습니다.

### 플레이어
- [x] Hit 한다면 카드를 추가로 한 장 더 받는다.
- [x] Stand 한다면 카드를 받지 않는다.
- [x] 만약 ACE카드를 받은경우 플레이어가 2장을 받은 후에 1또는 11로 선택한다
  - [x] 그 이후로는 곧바로 1 또는 11로 선택한다.

### 승패
- [X] 플레이어의 경우 받은 카드들의 숫자를 합하여 결과값이 21 초과면 즉시 패배
- [X] 딜러의 카드 숫자 합 보다 클 경우 승리
- [X] 딜러의 카드 숫자 합 보다 작을 경우 패배
- [X] 딜러의 카드 숫자 합과 같을 경우 무승부
- [X] 딜러의 경우 카드들의 숫자의 합이 21초과면 남은 플레이어에게 패배
- [X] 플레이어의 카드 숫자 합 보다 클 경우 승리
- [X] 플레이어의 카드 숫자 합 보다 작을 경우 패배
- [X] 플레이어의 카드 숫자 합과 같을 경우 무승부

### 베팅
- [X] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
- [] Surrender 플레이어가 포기한다. 이 때 최초 베팅액의 1/2을 잃게 된다.
- [] Insurance 딜러의 오픈 카드가 Ace일 경우 베팅액의 1/2범위 내에서 보험금 걸 수 있음
  - [] 딜러가 블랙잭이면 보험금의 두 배를 받는다.
  - [] 딜러가 블랙잭이 아닐 경우 보험금을 잃게 된다.
- [] Double Down 플레이어의 요청 시 한 장의 추가 카드만 받는다는 조건으로 현재 베팅 금액 내에서 추가 베팅이 가능해짐
- [X] -블랙잭으로 승리할 경우 1.5배 수익이다 (이 때 딜러와 플레이어 모두 블랙잭인 경우 베팅한 금액만 받아간다)
- [X] -승리한다면 배팅한 금액만큼의 수익을 얻는다.
- [X] -패배한다면 베팅한 금액을 잃는다
- [X] -무승부라면 베팅한 금액을 그래도 돌려받는다.




### 예외
- [x] 쉼표 기준으로 분리하지 않은 경우
- [x] 중복 이름 
- [x] y,n 선택 예외
- [X] 한 참가자가 가진 카드 간에 중복이 있는 경우
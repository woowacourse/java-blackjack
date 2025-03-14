# java-blackjack

블랙잭 미션 저장소


### 카드
- [X] ACE = 1 또는 11 (소프트핸드)
- [X] 킹, 잭, 퀸 = 10 (하드핸드)
- [X] 나머지는 숫자대로
- [x] 본인 및 플레이어에게 카드를 배분한다.

### 손패
- [x] 카드의 합을 구한다.

### 딜러
- [ ] 플레이어들이 카드를 뽑게 한다.
- [x] 히트 : 카드의 합이 16이하면 반드시 카드를 뽑습니다.
- [x] 스탠드 : 카드의 합이 17이상이면 카드를 뽑지 않는다.'
- [x] 업카드 : 최초 카드 분배 시 맨처음 카드 1장만 공개한다

### 플레이어
- [ ] 배팅 : 배팅 금액을 건다.
- [x] 히트 : 원한다면 카드를 추가로 한 장 더 받는다.
- [x] 스탠드 : 원한다면 카드를 받지 않고 멈춘다.
- [x] 버스트인 경우 카드를 더이상 뽑지 못한다.
- [x] 만약 ACE카드를 받은 경우 플레이어가 2장을 받은 후에 1또는 11로 선택한다
  - [x] 그 이후로는 곧바로 1 또는 11로 선택한다.

### 승패
- [ ] 블랙잭 : 플레이어만 블랙잭이면 플레이어가 승리한다.
- [ ] 블랙잭 : 딜러만 블랙잭이면 모든 플레이어에 대해 딜러가 승리한다.
- [ ] 블랙잭 : 플레이어와 딜러가 모두 블랙잭이면 무승부이다.
- [x] 버스트 : 플레이어가 버스트(카드 합 21 초과)면 플레이어 패배
- [x] 버스트 : 딜러가 버스트(카드 합 21 초과)면 남은 플레이어들 승리
- [x] 플레이어 카드 숫자 합이 딜러의 카드 숫자 합 보다 클 경우 플레이어 승리
- [x] 플레이어 카드 숫자 합이 딜러의 카드 숫자 합 보다 작을 경우 플레이어 패배
- [x] 푸쉬 : 플레이어 카드 숫자 합이 딜러의 카드 숫자 합과 같을 경우 플레이어 무승부

### 배팅
- [ ] 딜러가 플레이어의 배팅 금액을 모두 가지고 있다.
- [ ] 플레이어만 블랙잭이어서 승리하면 배팅 금액의 1.5배를 플레이어에게 준다.
- [ ] 플레이어가 승리할 경우 플레이어가 배팅한 금액을 딜러가 플레이어에게 준다.
- [ ] 딜러가 승리할 경우 플레이어가 배팅한 금액을 그대로 갖는다.
- [ ] 무승부일 경우 플레이어가 배팅한 금액을 딜러가 플레이어에게 준다.

### 예외
- [x] 쉼표 기준으로 분리하지 않은 경우
- [x] 중복 이름
- [x] y,n 선택 예외
- [ ] 한 참가자가 가진 카드 간에 중복이 있는 경우
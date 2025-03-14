# java-blackjack
블랙잭 미션 저장소
## 입력
- [X] 플레이어 이름들
- [X] 의사 요구
- [ ] 배팅 금액 입력

## 출력
- [X] 플레이어들 카드 분배 결과 문구
    - [X] 이름 / 카드 내용들
- [X] 딜러 카드 재분배 과정 문구
- [X] 결과 문구
    - [X] 이름 / 카드 내용들 / 점수
  - [ ] 최종 수익 출력
    - [ ] 이름 / 수익  

## 기능
- 게임 룰
    - [X] 딜러 점수가 17이상이면 stop
    - [X] 딜러 점수가 17미만이면 카드 다시 분배
    - [X] 21 초과하면 패
    - [X] 딜러와 플레이어를 비교하여 21과 가까운 사람이 승
- 점수 계산
   - [X] 딜러와 플레이어가 보유한 카드를 기준으로 점수 계산 
   - [X] 에이스인 경우 게임 점수에 유리한 점수 선택 (1 or 11)
   - [X] 카드의 숫자 계산은 카드 숫자를 기본으로 하며, K,Q,J은 각 10으로 계산
   - [X] 점수 결과를 출력

- 배팅
1. 블랙잭인지 판단해서 배팅 결과를 산출한다.
  - [ ] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되며 배팅 금액의 1.5배를 딜러에게 받는다.
  - [ ] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 배팅한 금액을 돌려 받는다.
    
2. 블랙잭이 아닌 경우 게임 점수를 기준으로 승패를 판단해서 배팅 결과를 산출한다.
  - [ ] 딜러와 플레이어가 동시에 bust인 경우 딜러가 승리하고 플레이어는 배팅 금액을 잃는다.
  - [ ] 딜러가 버스트인 경우 남아 있던 플레이어들은 가지고 있는 패와 상관없이 승리해 배팅 금액을 받는다.
  - [ ] 딜러와 플레이어가 모두 버스트가 아닌 경우 점수를 기준으로 승패를 판단하고 승리한 쪽은 배팅 금액을 받고 패배한 쪽은 배팅 금액을 잃는다.

### 상세 정보
- 카드(52장)
    - [X] 수트(4 종류): 에이스, 다이아몬드, 하트, 스페이드
    - [X] 랭크(13 종류): 2 ~ 10 (9개), K, J, Q, A (4개)
    - [X] 카드 숫자는 숫자가 점수, 인물은 10점
    - [X] 에이스의 경우 1 또는 11

- 배팅 금액
    - [] 1000원 이상부터 배팅 가능
    - [] 1000원 단위로 배팅 가능


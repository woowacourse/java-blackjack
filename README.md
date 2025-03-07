# java-blackjack
블랙잭 미션 저장소
## 입력
- [X] 플레이어 이름들
- [X] 의사 요구
## 출력
- [X] 플레이어들 카드 분배 결과 문구
    - [X] 이름 / 카드 내용들
- [X] 딜러 카드 재분배 과정 문구
- [X] 결과 문구
    - [X] 이름 / 카드 내용들 / 점수
- [X] 승패 결과
    - [X] 이름  / 승패
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
### 상세 정보
- 카드(52장)
    - [X] 수트(4 종류): 에이스, 다이아몬드, 하트, 스페이드
    - [X] 랭크(13 종류): 2 ~ 10 (9개), K, J, Q, A (4개)
    - [X] 카드 숫자는 숫자가 점수, 인물은 10점
    - [X] 에이스의 경우 1 또는 11

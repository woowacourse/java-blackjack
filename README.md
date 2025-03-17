# java-blackjack

블랙잭 미션 저장소
 
---

- [x] 카드
  - [x] 필드로 숫자와 문양을 가진다.
  - [x] A의 숫자는 기본으로 11로 간주한다. 

- [x] 카드들
  - [x] 총합 계산 메서드를 가진다.
    - [x] 총합이 21을 넘길 경우 만약 A가 있다면, A를 11대신 1로 간주한다.
  - [x] 카드를 추가한 뒤, 합계가 21을 초과하는지 확인한다.

- [x] 트럼프 숫자
  - [x] 2 ~ 10, K(10), Q(10), J(10), A_ELEVEN(11), A_ONE(1)  
  
- [x] 트럼프 문양
  - [x] 하트, 다이아, 스페이드, 클로버 네 종류의 문양을 가진다.
  
- [x] 덱
  - [x] 52장의 카드들로 섞여진 초기 덱을 생성한다.
  - [x] 딜러와 플레이어에게 각각 2장식 나눠준다. 
  - [x] 플레이어나 딜러가 원하면, 각각 한장식 나눠준다.
  - [x] 덱 내부의 카드 순서를 섞는다.
    
- [x] 딜러
  - [x] 초기 카드 중, 한 장을 보여준다.
  - [x] 카드 한장을 추가로 받아 카드 리스트에 추가한다. 
  - [x] 총합이 16 이하일 시, 17이 넘을 때까지 한장을 추가로 받는다.
  - [x] 카드들의 총합을 계산한다.

- [x] 플레이어
  - [x] 카드 두 장을 받아 플레이어를 생성한다.
  - [x] 카드 한장을 추가로 받아 카드 리스트에 추가한다.
  - [x] 딜러 카드와의 숫자를 비교한다.
  - [x] 카드들의 총합을 계산한다.

- [x] 플레이어들
  - [x] 플레이어들의 카드를 딜러와 비교해서 승패를 도출한다.

- [x] 게임 결과 상태 
  - [x] 승리(WIN), 무승부(PUSH), 패배(LOSE) 를 가진다.

- [x] 예외
  - [x] 플레이어 입력시, 공백이나 띄어쓰기 등 빈 값을 입력받는 경우
  - [x] 플레이어의 닉네임이 10자를 넘는 경우
  - [x] y, n 이외의 값이 들어오는 경우
  - [x] 플레이어 생성시, 초기 카드가 두 장이 아닌경우

---

- 2단계 블랙잭 베팅 추가 요구사항
  - [x] 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다
  - [x] 카드를 추가로 뽑아, 21을 초과할 경우 베팅 금액을 모두 잃는다.
  - [x] 딜러와 플레이어가 동시에 블랙잭인 경우, 플레이어는 베팅 금액을 다시 돌려 받는다.
  - [x] 플레이어만 블랙잭인 경우, 베팅 금액의 1.5배를 받는다.
  - [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가진 패에 상관 없이 승리하고, 베팅 금액을 받는다.

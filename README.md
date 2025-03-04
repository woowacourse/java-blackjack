# java-blackjack

블랙잭 미션 저장소
 
---

- [ ] 카드
  - [ ] 필드로 숫자와 문양을 가진다.
  - [ ] 정적 팩터리 메서드로 숫자와 문양을 받아서 객체를 생성한다.
  - [ ] A의 숫자는 기본으로 11로 간주한다.
    - [ ] 총합이 21을 넘길 경우 1로 간주한다.

- [ ] 카드들
  - [ ] 필드로 카드를 가지는 일급 컬렉션
  - [ ] 총합 계산 메서드를 가진다.

- [ ] 트럼프 숫자
  - [ ] 2 ~ 10, K(10), Q(10), J(10), A_ELEVEN(11), A_ONE(1)  
  
- [ ] 트럼프 문양
  - [ ] 하트, 다이아, 스페이드, 클로버 네 종류의 문양을 가진다.
  
- [ ] 덱
  - [ ] 52장의 카드들로 섞여진 초기 덱을 생성한다.
  - [ ] 딜러와 플레이어에게 각각 2장식 나눠준다. 
  - [ ] 플레이어나 딜러가 원하면, 각각 한장식 나눠준다.
  - [ ] 덱 내부의 카드 순서를 섞는다. Collections.shuffle() 

- [ ] 참가자 (Interface) 
  - [ ] 덱을 인자로 받아 뽑은 카드를 갖는다.
  - [ ] 카드들의 총합을 계산한다.

- [ ] 딜러 implements 참가자
  - [ ] 카드 리스트를 가진다.
  - [ ] 한장을 보여준다.
  - [ ] 카드 한장을 추가로 받아 카드 리스트에 추가한다.
  - [ ] 총합이 16 이하일 시, 한장을 추가로 받는다.

- [ ] 플레이어 implements 참가자
  - [ ] 카드 리스트와 딜러와의 승부 결과를 가진다.
  - [ ] 카드 두 장을 받아 플레이어를 생성한다.
  - [ ] 카드 한장을 추가로 받아 카드 리스트에 추가한다.
  - [ ] 딜러 카드와의 숫자를 비교한다.

- [ ] 플레이어들
  - [ ] 플레이어들을 모은 일급 컬렉션 List<Player> 
  - [ ] 플레이어들의 카드를 딜러와 비교해서 승패를 도출한다.

- [ ] 게임 결과 상태 
  - [ ] 승리(WIN), 무승부(PUSH), 패배(LOSE) 를 가진다.

- [ ] 예외
  - [ ] 플레이어 수가 6명을 넘는 경우
  - [ ] 플레이어 입력시, 공백이나 띄어쓰기 등 빈 값을 입력받는 경우
  - [ ] y, n 이외의 값이 들어오는 경우
  - [ ] 잘못된 입력이 들어온 경우, 다시 입력받는다. 
   

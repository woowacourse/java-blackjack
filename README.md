## 📝 기능 요구 사항

### 카드
- 카드 모양
  - [x] 카드 모양은 4가지이다.
      - 하트, 클로버, 스페이드, 다이아몬드
- 카드 숫자
  - [x] 카드 숫자는 총 13가지이다.
      - ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10, JACK, QUEEN, KING

### 카드 생성기
- [x] 52장의 카드를 생성할 수 있다.

### 카드 덱
- [x] 처음 카드 덱의 총 카드 수는 52장이다.
- [x] 카드를 한 장 뽑을 수 있다.

### 카드 패
- [x] 카드를 받을 수 있다.
- [x] 카드 패의 총 점수를 계산할 수 있다.
  - [x] A는 1 또는 11로 계산한다.
  - [x] J, Q, K는 10으로 계산한다

### 딜러
- 상태
  - Blackjack, hit, stay, bust
- [x] 딜러는 카드를 셔플할 수 있다. 
- [x] 카드 덱에서 카드를 한 장 뽑을 수 있다.
  - [x] 기존 카드 덱의 카드를 모두 사용하였을 경우, 새로운 카드 덱에서 카드를 뽑는다.
- [x] 딜러 카드의 총 점수를 계산할 수 있다.
- [x] 딜러는 카드를 받을 수 있다.
- [x] 딜러가 카드를 더 받아야 하는지 판별할 수 있다.
  - [x] 딜러의 카드의 총 합이 16 이하라면 카드를 한 장 받아야 한다.

### 플레이어
- 이름
  - [x] 플레이어의 이름을 알 수 있다.
  - [x] **[예외 처리]** 플레이어 이름의 길이가 2보다 작거나 5보다 크다면 예외가 발생한다. 
- 상태
  - Blackjack, hit, stay, bust
- [x] 플레이어 카드의 총 점수를 계산할 수 있다.
- [x] 플레이어는 카드를 받을 수 있다.
- [x] 플레이어가 카드를 더 받아야 하는지 판별할 수 있다.
  - [x] 플레이어의 상태가 블랙잭이라면 카드를 받을 수 없다.
  - [x] 플레이어의 상태가 버스트라면 카드를 받을 수 없다.
  - [x] 플레이어의 상태가 스테이라면 카드를 받을 수 없다.
  - [x] 플레이어 카드의 총 점수가 21보다 작다면 카드를 받을 수 있다.

### 플레이어들
- [x] 블랙잭 게임에 참여하는 플레이어들 이름을 알 수 있다.
  - [x] **[예외 처리]** 중복된 이름을 가지면 예외가 발생한다.
  - [x] **[예외 처리]** 게임에 참여하는 플레이어의 수가 1보다 작거나 6보다 크다면 예외가 발생한다.

### 배팅 금액
- [x] 플레이어의 배팅 금액을 알 수 있다.
  - [x] **[예외 처리]** 배팅 금액이 10,000원보다 작거나 1,000,000원보다 크다면 예외가 발생한다.
  - [x] **[예외 처리]** 배팅 금액이 1,000원 단위가 아니라면 예외가 발생한다.

### 배팅 금액 저장소
- [x] 플레이어 별 배팅 금액을 기록할 수 있다.
- [x] 플레이어 별 배팅 금액을 찾을 수 있다.

### 블랙잭 게임
- [x] 블랙잭 게임을 시작할 수 있다.
  - [x] 게임이 시작하면 모든 플레이어와 딜러는 카드를 2장씩 갖는다
    - 카드 돌리기 순서 : 딜러(뒤집힌 카드) -> 플레이어 1 ... n(보이는 카드) -> 딜러(보이는 카드) -> 플레이어 1 ... n(보이는 카드)
- [x] 각 플레이어별 승, 무, 패 여부를 판단할 수 있다.
  - 플레이어 패
    - [x] 플레이어보다 카드의 합이 더 클 때 (21에 가깝다)
    - [x] 플레이어 21 초과 (버스트)
    - [x] 플레이어, 딜러 둘 다 21 초과 (버스트)
    - [x] 딜러가 블랙잭이고, 플레이어는 블랙잭이 아닐 때
  - 플레이어 승리
    - [x] 딜러보다 카드의 합이 더 클 때 (21에 가깝다)
    - [x] 딜러 21 초과 (버스트)
    - [x] 플레이어가 블랙잭이고, 딜러는 블랙잭이 아닐 때
  - 무승부
    - [x] 딜러와 플레이어의 합이 같을 때
    - [x] 딜러와 플레이어가 동시에 블랙잭일 때
- [x] 플레이어에게 카드를 추가로 지급할 수 있다.
- [x] 딜러에게 카드를 추가로 지급할 수 있다.

### 수익 계산기
- [x] 플레이어의 수익을 계산할 수 있다.
  - [x] 플레이어가 블랙잭이라면 배팅 금액의 1.5배를 받는다. (x1.5)
  - [x] 플레이어가 블랙잭이이고, 딜러가 블랙잭이면 배팅 금액을 돌려받는다. (x0)
  - [x] 플레이어가 이겼다면 배팅 금액을 얻는다. (x1)
  - [x] 플레이어가 졌다면 배팅 금액을 잃는다. (x-1)
  - [x] 플레이어가 딜러와 비겼다면 배팅 금액을 돌려받는다. (x0)
    - [x] 플레이어의 수익을 통해 딜러의 수익을 계산할 수 있다.

### 블랙잭 명령어 (y, n)
- [x] y, n의 값을 통해 명령어를 찾을 수 있다.

### 변환기
- [x] 콤마로 구분된 문자열을 리스트로 변환할 수 있다.

### 입력
- [x] **[공통 예외 사항]** 입력이 공백이면 예외가 발생한다.
- [x] 게임 참가자 이름을 입력받을 수 있다.
  - [x] **[예외 처리]** 게임 참가자 이름 중 "딜러", "y", "n" 이라는 이름을 가지면 예외가 발생한다.
- [x] 플레이어 별 배팅 금액을 입력받을 수 있다.
  - [x] **[예외 처리]** 배팅 금액에 대한 입력이 숫자가 아니라면 예외가 발생한다.
- [x] 카드를 더 받을지 여부를 입력받을 수 있다.
  - [x] **[예외 처리]** 카드 추가 지급 여부에 대한 입력이 "y" 또는 "n"이 아니라면 예외가 발생한다.

### 출력
- [x] 2장씩 카드를 배분한 결과를 출력한다.
  - [x] 딜러의 카드 중 한 장만 출력한다.
- [ ] 딜러의 카드 합이 16 이하일 때 카드를 한 장 더 받았는지 여부를 출력한다.
- [ ] 카드 합 결과를 출력한다.
- [ ] 플레이어와 딜러의 최종 수익을 출력한다.

---

### 체크 리스트
- [ ] indent는 1까지만 허용한다
- [ ] 3항 연산자를 쓰지 않는다
- [ ] else 예약어를 쓰지 않는다
- [ ] TDD로 구현하여 모든 기능에 대한 단위 테스트가 존재해야 한다
- [ ] 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- [ ] 배열 대신 컬렉션을 사용한다.
- [ ] 모든 원시 값과 문자열을 포장한다
- [ ] 일급 컬렉션을 쓴다.
- [ ] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

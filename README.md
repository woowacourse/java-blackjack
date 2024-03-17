# java-blackjack

블랙잭 미션 저장소

## 기능 요구사항 해석

### 요구사항

- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 추가된 요구사항
- **플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.**
- **카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.**
- **처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.**
- **딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.**

### 해석

- 플레이어 이름은 공백으로 입력할 수 없다.
- 플레이어는 입력 순서대로 출력한다.
- 플레이어는 최소1명, 최대 8명이다.
- 플레이어의 카드 합계가 21을 넘는 경우 카드를 받을 수 없다.
- 딜러와 플레이어의 카드 합계가 같을 경우 무승부가 되어 베팅 금액을 돌려받는다. 
- 베팅 금액은 자연수이다. 

## 게임 참가자

- 이름
  - [x] 게임에 참여할 사람의 이름 생성
    - [x] 공백이 입력된 경우 예외 발생
- 카드 패
  - [x] 카드를 패에 넣는다.
  - [x] 카드의 합을 구한다.
    - [x] Ace를 1 또는 11로 계산해서 21에 더 가까운 값으로 결과를 정한다.

### 플레이어

- [x] 플레이어는 최소1명, 최대 8명이다.
  - [x] 플레이어가 최소 1명, 최대 8명이 아닌 경우 예외 발생
- [x] 카드의 합계로 `Bust`를 판단한다.
- [x] 카드의 합계로 `Blackjack`를 판단한다.
- [x] 베팅 금액을 가진다.

### 베팅 금액

- [x] 베팅 금액이 1000 이하일 경우 예외를 던진다. 
- [x] 베팅 금액이 1000으로 나누어떨어지지 않을 경우 예외를 던진다. 

### 수익

- [x] 값을 뺀다.
- [x] 값을 수익률과 곱한다.

### 딜러

- 카드 덱
  - [x] 카드를 카드덱에서 뽑는다.
  - [x] 카드를 섞는다.
- [x] 게임 참가자에게 카드를 1장씩 나누어준다.
- [x] 딜러의 카드 합계가 16 이하면 카드를 한 장 더 받는다.
- [x] 플레이어와 자신의 카드를 비교해 승패무를 정한다.

## 블랙잭 게임

- [x] 게임 시작 시 딜러와 플레이어에게 카드를 2장씩 나누어준다.
- [x] 딜러와 플레이어의 최종 승패를 도출한다. 
- [x] 딜러와 플레이어의 베팅 금액 수익을 계산한다. 

## 카드

- [x] 카드를 맨 위에서 뽑는다.
- [x] 카드를 섞는다.
- [x] 카드의 합을 구한다.
- [x] 에이스가 포함되었는지 알 수 있다. 

## 입력

- [x] 게임에 참여할 사람의 이름 입력 (쉼표를 기준으로 분리)
  - [x] 공백이 입력된 경우 예외 발생
- [x] 플레이어에게 카드를 더 받을지에 대해 입력을 받는다.
  - [x] 'y'를 선택한 플레이어에게 카드를 주고, 다시 물어본다.
- [x] 플레이어의 베팅 금액을 입력한다.
  - [x] 1000 미만인 경우 예외를 발생한다.
  - [x] 1000으로 나누어떨어지지 않을 경우 예외를 발생한다.

## 출력

- [x] 카드를 나눈 결과를 출력한다.
  - [x] 딜러의 카드 두 장 중 한 장만 공개한다.
- [x] 카드를 받을 때마다 해당 플레이어의 카드를 모두 출력한다.
- [x] 딜러의 추가 카드를 받았다면 해당 내용을 출력한다.
- [x] 카드 합계 계산 결과를 출력한다.
- [x] ~~최종 승패를 출력한다.~~
- [x] 최종 수익을 출력한다. 

## 리팩토링
- [x] Cards 에서 카드가 비어 있을 경우 메서드 사용 시 예외 발생 테스트
  - [x] draw()
  - [x] peek()
  - [x] shuffle()
  - [x] hasAce()
  - [x] sum()
- [x] BlackjackViewParser의 default 메서드 재고
- [x] Participant 생성 로직 변경
  - Name 객체를 생성자의 인자로 받는 대신, String을 받도록 변경
- [x] Participant.isLose() if문과 얼리 리턴을 활용해서 가독성 개선
- [x] DealerResult.put() { counts.put() -> counts.merge() }
- [x] CardMachine 불필요한 메서드 삭제
- [x] InputView.askPlayers()의 Player와 Name생성 로직을 Players 로 이동
- [x] Cards 정적 팩토리 메서드 개선
- [x] Cards 클래스 분리 -> Deck, Hand
  - [x] Deck
  - [x] Hand
- [ ] 가짜중복으로 인한 BlackjackViewParser의 View 공통 메서드 분리
- [ ] NPE 처리
- [ ] Deck 생성 로직 이동 CardMachine -> Deck
  - [ ] EnumSet 대신 values() 사용 [참고](https://github.com/woowacourse/java-blackjack/pull/652#discussion_r1518824698)
- [x] Name NPE 예외 처리
- [ ] 상태 패턴 적용(Optional)
  - [ ] Dealer.hasSoftAce 필드 삭제


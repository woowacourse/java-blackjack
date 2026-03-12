# java-blackjack
블랙잭 미션 저장소 

## 기능 요구사항

---
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.  
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.  
처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.  
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.   
딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

### Card
#### field
- CardNumber(Enum class)
- CardPattern(Enum class)
#### method
- 숫자 검증
- 카드 패턴 검증
---
### Hand(1급 컬렉션)
#### field
- List Card
#### method
- appendCard() 카드 추가
- calculateTotalValue() 손 안의 카드 값 총합 계산
- hasAce() ace를 가지고 있는지 판단
---
### Deck
### field
queue 전체 카드
#### method
- init() 카드 초기화
- draw() 카드 뽑기
- makeCard() 카드 만들기
---
### Member(추상 클래스)
#### field
- Hand
- Name

#### method
- showFirstCards() 처음 카드를 분배 받고 결과 반환
- isDealer() 딜러인지 확인
- getBettingAmount() 배팅 금액 조회
- applyBlackjackBonus() 블랙잭 보너스인 1.5배 적용
- handValue() 가지고 있는 카드의 총합
- receiveCard() 카드 뽑기
- handCards() 손에 들고 있는 카드 조회
---
### Dealer(Member 상속)
#### method
Member의 메서드 상속해서 구현
isDealer()
showFirstCards() - 한장의 카드만 조회
getBettingAmount() - player의 기능 지원하지 않음을 예외처리
applyBlackjackBonus() - player의 기능 지원하지 않음을 예외처리
---
### Player
#### field
BettingAmount 배팅 금액
#### method
isDealer()
showFirstCards() 처음 받은 2장의 카드 조회
getBettingAmount() 배팅 금액 조회
applyBlackjackBonus() 보너스 적용
---
### BettingAmount
#### method
applyBonus 보너스 적용
getAmount 배팅 금액 반환
---
### GameTable
#### field
- Members(일급 컬렉션)

#### method
- draw(memberName, card) 카드 뽑기
- canDealerDraw() 딜러가 카드를 더 뽑을 수 있는지 확인
- checkBust(memberName) 카드 총합이 버스트인지 확인
- checkBlackjack(playerName) 블랙잭인지 확인
- applyBlackjackBonus(playerName) 블랙잭 보너스 적용
- checkGameResult() 게임 결과 출력
---
### BlackjackGame
#### field
- GameTable
- Deck
#### method
- isNotDealer(memberName) 플레이어만을 구분하기 위한 검증
- initialDeal() 딜러와 플레이어 모두에게 카드 2장씩 나누어주는 기능
- drawPlayer() 플레이어가 카드 뽑는 기능
- getFirstCardNames(memberName) 처음 나누어준 카드들을 보여주는 기능
- canDealerDraw() 딜러가 16이하인지 확인하는 검증
- drawDealer() 딜러가 카드 뽑는 기능
- getGameResults() 승부결과 조회 기능
- isContinuable(playerName) 버스트인지 확인하여, 계속 게임을 할 수 있는지 확인하는 기능
- hasBlackjack(playerName) 블랙잭을 가지고 있는지 검증
- applyBlackjackBonus(playerName) 블랙잭 보너스를 적용하는 기능
---
```
딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```
- finalResult()
```
  ## 최종 수익
  딜러: 10000
  pobi: 10000 
  jason: -20000
 ```
---

## 실행 결과

---
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
pobi카드: 2하트, 8스페이드, A클로버
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

## 프로그래밍 요구 사항

---
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- 기본적으로 Java Style Guide을 원칙으로 한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다. 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.  
힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다. else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.  
힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
- UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 컬렉션을 사용한다.
- 모든 원시 값과 문자열을 포장한다.
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.

## 추가된 요구 사항

---
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

## 미션 중 할 일
---
토론 활동에서 정한 규칙을 의식하며 코드 작성  
규칙 때문에 코드를 변경한 곳 기록
막히는 순간 기록
## 미션 중 기록
---
필수 기록:  
[ ] 규칙을 적용해서 변경한 코드 1곳 이상  
[ ] 테스트 작성이 어려웠던 코드 1곳 이상  
[ ] 막힌 순간 1회 이상

## 미션 완료 조건
---
[ ] 요구사항 구현  
[ ] 규칙에 의한 코드 변경 1회 이상  
[ ] 미션 중 기록 작성  

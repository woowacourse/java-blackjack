# 블랙잭 1단계: 1차 피드백 TODOS

## 리팩토링 리스트

src/main/java/blackjack/utils/RandomCardDeck.java
- [x] forEach가 중첩되어서 가독성이 많이 떨어지는데요, flatMap을 사용하거나, 아니면 하나씩 순차적으로 반복문

src/main/java/blackjack/domain/Card.java
- [x] static 필드는 상수로 관리

src/main/java/blackjack/domain/Card.java
- [x] 캐싱한 카드를 String 타입인 이름 기준으로 뽑아오고 있는데, 타입이 String이라 이 이름이 denomination + suit 라는걸 외부에서 알아야 할 것 같아요.
  더 명확한 캐싱 및 조회 방법은?
- [x] 테스트 코드 수정

src/main/java/blackjack/domain/Playable.java
- [x] 점수 계산은 Cards에서 충분히 할 수 있지 않을까요? getter를 지양
- [x] 점수계산로직에서 int를 반환하는 이유는? 승패관련 Enum에서 처리 가능하지 않을까? 휴먼에러로 인한 장애 가능성 있음
명확하게 승페계산 로직 변경
- [x] 상수의 위치는 ACE가 있는 자리가 아닐까?

src/main/java/blackjack/domain/Participants.java
- [x] View의 역할을 가진 내용은 View로 옮기기(책임과 역할을 분명히 하자)
- [x] instanceof 말고 다른 방법은?
- [x] stream 구문은 한줄에 하나씩 작성

src/main/java/blackjack/Application.java
- [x] takeCard 관리부분:  deck을 관리하는 객체를 만들어 player에게 카드를 줄지 말지 판단할 수 있도록 설계해볼 수도 있다
- [x] view로 인한 controller 역할 커짐: Controller에 있는 로직을 최대한 더 Model로 뽑을 수 있는 여지는 없을까? 

src/main/java/blackjack/utils/RandomCardDeck.java
- [x] 카드뭉치 생성의 책임과 카드 Queue를 분리?   
  (카드묶음을 생성하는 객체를 인터페이스로 추상화해서 지금처럼 shuffle 유무를 나누도록 하고,
  카드 Queue를 가지고 있는 객체는 정말 Deck으로만 기능하도록 생성 시에 외부에서 카드 묶음을 받도록 하면, 테스트에서도 원하는 테스트를 위해 별도의 가공 과정을 거치지 않아도 될 것 같아요)

## 추가 self 리팩토링
- [x] domain의 testCoverage 90%이상

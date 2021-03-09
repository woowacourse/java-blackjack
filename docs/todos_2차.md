# 블랙잭 1단계: 1차 피드백 TODOS

## 리팩토링 리스트
src/main/java/blackjack/domain/card/Card.java
- [ ] Card를 캐싱하는 객체를 분리 (Card는 카드 자체에 대한 관리만 진행. 객체의 무게 줄이기)

src/main/java/blackjack/domain/GameTable.java
- [ ]  Queue를 꺼내지 말고 그냥 GameTable이 CardDeck을 가지고 있는게 좋아보여요 :)

src/main/java/blackjack/domain/Game.java
- [ ] Game 객체의 역할이 모호하다. 
  생성자에서도 사용자가 보기에 쉽게 이해할 수 없는 String 타입의 무언가를 받아서 Game 이 만들어지고 있네요 (Game이라는 이름도 너무 추상적이구요)
  Game이 명확한 역할로 정의될 수 있는 객체가 아니라면, Controller에서 GameTable, Dealer 등등의 다른 객체들을 생성해도 충분할 것 같아요.

src/main/java/blackjack/domain/gamer/Players.java
- [ ] Players 일급컬렉션은 Player List를 생성자로 받아서 단순하게 생성하는 것이 좋아보입니다.
  `,`으로 String을 분리하고 Player를 생성하는 역할은 외부에서 진행

src/main/java/blackjack/domain/gamer/Dealer.java
- [ ] int 타입을 받아서 진행하니, 어떤 int인지, 또 외부에서 추가로 적절한 int를 넣어줘야만 하겠네요.
  Player를 받으면 어떨까??
  
- [ ] 점수계산 로직을 더 깔끔하게 리팩토링 해보자! (중복조건 제거)

src/test/java/blackjack/DealerTest.java
- [ ] 테스트들도 프로덕션 코드와 동일한 패키지 경로를 가지고 있어야 합니다.
  확인하시고 올바르게 배치

src/test/java/blackjack/CardDeckTest.java 
- [ ] 카트가 아니라 카드...죠? ㅋㅋ
  테스트 클래스 이름도 GameTableTest로 바꿔야겠네요

src/main/java/blackjack/dto/Participants.java
- [ ] View와 관련한 로직이 여전히 섞여있다. names(), finalResult()
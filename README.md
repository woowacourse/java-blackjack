# java-blackjack

블랙잭 미션 저장소

## 기능 구현 목록

## 정한 규칙

- 덱은 1개로 제한한다. (카드 52장 중복 x)
- 첫 카드합이 21이면 블랙잭이 되었음을 알린다. -> 미구현(요구사항 x)
- 21이 될경우 추가로 카드를 받지 않는다. -> 문제 요구사항으로 인해 21 이어도 추가로 받을 수 있다.
- A는 1 or 11 버스트를 넘지 않는 선에서 플레이어에게 유리하게 계산된다.
    - 3개 이상일 경우 테스트 케이스 생각해봐야함.
- 플레이어가 모두 버스트라면 딜러는 추가로 카드를 받지 않는다.

### 유저

-[x] 이름을 저장한다.
    - 2~7자이다.
-[x] 블랙잭 핸드를 관리한다.

### 딜러

-[x] 이름과, 블랙잭 핸드를 관리한다.
-[x] 핸드 값이 16 이하라면 카드를 받아야 함을 알린다.(자동)

### 핸드

-[x] 받은 카드를 저장한다.
-[x] 카드의 총합을 반환한다.
    - A 포함의 경우: 우선 A는 1로 취급한다.
        - 11 이하라면 +10을 해서 전달한다.
        - 11 초과한다면 그냥 전달한다.
    - A 미포함의 경우: FaceCard(J,Q,K)는 10으로 나머지는 숫자에 맞게 더해서 전달한다.

-[x] 버스트인지 확인하다 (A 값처리 후에 21을 초과하는지 확인한다.)

### 덱

-[x] 52장의 카드를 저장한다.
    - 랜덤으로 셔플된 카드들을 주입받아 카드를 저장한다.
    - 중복된 카드가 있으면 예외를 반환한다.
-[x] 첫번째 카드를 전달한다.
    - 카드는 삭제된다. Deque 관리하기 때문에 O(1)

### 카드 생성기

-[x] ENUM(문양), ENUM(숫자, J/K/Q, A) 두 ENUM 각각을 조합해서 56개의 카드를 생성한다.

### 카드

-[x] 문양, 숫자
-[x] ENUM(문양), ENUM(숫자, JKQ, A)로 각각 저장한다.
-[x] 카드의 스코어를 반환한다.
-[x] 카드가 ACE인지 확인한다.
-[x] 카드가 FACE 카드인지 확인한다.

### 상태(Hit, Stay, Bust)

- 플레이어 패의 상태를 저장한다.
- 각 상태별로 카드를 뽑을 수 있는지 확인 할 수 있다.
    - Bust, Stay: false
    - Hit: 힛 전략에 따라 다르다. 플레이어는 **버스트** 날때까지 **원한다면**, 딜러는 **16이하라면 무조건**
- 상태를 객체로 저장해서 객체가 일을 하도록 시킨다.

### 입출력

- 플레이어 이름은 `,`로 구분한다.

# 페어프로그래밍 기록

- 테스트에서 자주 사용되는 값을 TestFixture 통해서 관리한다.
  이유: 다양한 클래스에서 자주 사용하기 때문에.

- 랜덤값 테스트가 어려워서 원래 설계에서 변경(덱셔플 -> 덱의 idx 값을 가져오도록 변경)
    - 추후 idx 값을 주입받는다.

- 동일한 호출이 하위 클래스에서 상위 클래스로 반복될것 같다면 캡슐화가 깨졌는지 확인하자.
  ex) Card에서 domain.card.vo.Rank.equals()를 통해 FaceCard(J/Q/K) 확인 -> 해당 값을 가장 잘 아는 Rank에게 책임 맡김
- domain.participants.Hand 에서 카드 값의 총합을 반환하는 메서드를 만들려고 했다.
    - 하나의 카드 값을 반환하는 메서드를 만들었다.
    - domain.participants.Hand <- domain.card.vo.Card <- domain.card.vo.Rank 순으로 값을 포장만 하고 넘기는 케이스가 발생
    - domain.card.vo.Card 값자체를 가장 잘 아는 Card에게 책임을 넘기고 domain.participants.Hand 는 함수를 사용했다.
- 테스트 주도 개발에서 테스트를 안한 케이스
  enum과 같이 get만을 이용한 상수는 테스트를 진행하지 않았다.
  이유: 상수이기 때문에 테스트 할 필요 없다. (로직이 아니다.)
- 아이디어가 떠올라서 막 구현하다보니 단위테스트가 제대로 진행되지 않았다.(domain.participants.Hand)
    - 설계 구조가 바뀌면서 테스트를 진행하기보다 구조가 명확해진 후에 테스트를 진행하려고 했다.

- 내비게이터가 TODO 리스트 작성하는것 좋은것 같다.

---

# 사이클 2

추가된 요구사항

- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
  딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.

## 기능 목록

### 블랙잭

- State 추상 객체를 상속해서 구현한다.
- 블랙잭이 나오면 더이상 패를 뽑지 않는다.

### 금액

- 금액을 관리한다.
- Result 객체에서 결과에 따라 profit을 반환한다.

# 나만의 기록

- 비즈니스 로직을 Domain 에서 관리함으로써 Controller의 책임이 줄어들었다.(단일책임)
    - Stay, Bust, Hit 과 같은 상태를 도메인으로 관리한다.
- OCP 위반. 다른 도메인의 상태를 다 알고 있다.

```java
public static Result getResult(State dealerState, State playerState) {
    if (playerState instanceof Bust) {
        return Result.LOSE;
    }
    if (playerState instanceof BlackJack) {
        return Result.BLACKJACK;
    }
    if (dealerState instanceof Bust || playerState.getScore() > dealerState.getScore()) {
        return Result.WIN;
    }
    if (dealerState.getScore() > playerState.getScore()) {
        return LOSE;
    }
    if (dealerState.getScore().equals(playerState.getScore())) {
        return Result.DRAW;
    }
    throw new IllegalArgumentException("Result 뭔가 잘못된거같아...");
}
```

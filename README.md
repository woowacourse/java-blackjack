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

### 상태

| 유형             | State <- Started    | State <- Started |
|----------------|---------------------|------------------|
| abstract class | Finished            | Running          |
| 구현체            | Blackjack,Bust,Stay | Hit              |

위와 같은 구조로 상속하고 있다.

- Runnung -> 패의 상태에 따라 다음 상태를 반환한다.
- 각 상태별로 카드를 뽑을 수 있는지 확인 할 수 있다.
- Hit: 힛 전략에 따라 다르다. 플레이어는 **버스트** 날때까지 **원한다면**, 딜러는 **16이하라면 무조건**
- 상태를 객체로 저장해서 객체가 일을 하도록 시킨다.

### 입출력

- 플레이어 이름은 `,`로 구분한다.

# 나만의 기록

- 비즈니스 로직을 Domain 에서 관리함으로써 Controller의 책임이 줄어들었다.(단일책임)
    - Stay, Bust, Hit 과 같은 상태를 도메인으로 관리한다.
- OCP 위반. 다른 도메인의 상태를 다 알고 있다.

```java
public static Result getResult(State dealerRunning, State playerRunning) {
    if (playerRunning instanceof Bust) {
        return Result.LOSE;
    }
    if (playerRunning instanceof BlackJack) {
        return Result.BLACKJACK;
    }
    if (dealerRunning instanceof Bust || playerRunning.getScore() > dealerRunning.getScore()) {
        return Result.WIN;
    }
    if (dealerRunning.getScore() > playerRunning.getScore()) {
        return LOSE;
    }
    if (dealerRunning.getScore().equals(playerRunning.getScore())) {
        return Result.DRAW;
    }
    throw new IllegalArgumentException("Result 뭔가 잘못된거같아...");
}
```

- 모든 원시값을 포장하라는 말은 무슨말일까?
    1. Wrapper로 감싸라는 말일까?
    2. 의미있는 객체를 사용하라는 걸까? -> Score를 객체로 감싸야 하나? -> 그런데 Score를 구현하면 Result와 다를게 없다. 반드시 객체를 참조해야 하고 OCP를 위배하게 된다.
       -> Score 객체는 의미있는 역할을 하기 힘들다.

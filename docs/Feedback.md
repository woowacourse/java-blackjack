# ️블랙잭 피드백

---

## 1단계 - 블랙잭

### 📋 1차 피드백 수정 필요 항목

- [x] 상수의 의미를 생각하여 네이밍
    - GameController 내의 FIRST_QUESTION_FLAG
    - `에단 피드백`
        1. 상수의 이름만 보고 <u>어떤 의미인지 이해할 수 있는지</u> 주위 사람들에게 물어보기
        2. 상수 자체가 정말 복잡한 의미를 갖는다면, <u>상수가 필요 없는 해결책</u>은 없는지 고민
            - 이름이 도저히 떠오르지 않는다면 상수를 지우고 문제를 어떻게 해결할 지에 대한 고민은 도움이 된다.
- [x] GameResult 내의 `WIN`을 나타내는 문자열은 어느 영역의 관심사인가
    - 다른 언어권에게 제공해야한다면 어떻게 배워야할까?
    - GameResult 내 결과를 의미하는 필드 제거 및 OutputView에 문자열 변환하는 함수 구현
- [x] `MAX_POINT`라는 상수가 여러 곳에 선언되어 있다.(최대 점수가 21의 의미하는 숫자로 사용함.)
    - 사용 클래스
        - Cards : MAX_POINT
        - Player : AVAILABLE_POINT_FOR_ADD_CARD
        - Statistic : MAX_POINT
    - `Cards` 클래스 내에서 현재 포인트가 MAX_POINT(21)이 넘는지 계산하는 함수 `isOverThanMaxPoint()`를 구현하여 중복 제거
- [x] `Statistic` 클래스에서 팩터리 메서드 외의 필드 및 멤버 함수도 static으로 설정한 이유
- [x] `Card` 구현 시에 캐싱 적용
    - `List<Card> CACHE` 필드를 생성하여 52장의 카드를 담고 있는 List 생성
- [x] `Card`에서 toString의 구현이 바뀌게 된다면 View의 출력에 미치는 영향
    - getter를 이용하여 Denomination, Symbol 정보 반환
    - 출력 로직 view에 구현 및 getter 사용하여 정보 반환
- [x] `giveCard()`메서드의 의미에 대해 (누가 누구에게 주는 것인가.)
    - Players : giveCard -> distributeCard
    - Card : giveCard -> draw
- [x] `CardDeck`에서 `size()`메서드는 테스트에서만 사용됨.
    - 미사용 메서드 제거 및 테스트 코드 제거
- [x] `CardDeckGenerator`에서 카드를 생성할 때 어떤 규칙을 사용하는가.
- [x] `Denomination`에서 `예외`를 던질 때 메세지를 담아서 보내기
    - orElseThrow()에서 IllegalArgumentException 발생하여 메세지 전달 하도록 수정
- [x] `Name` 객체 생성 시에 검증하는 과정에서 적절한 예외 클래스 사용
    - 기존 RuntimeException에서 IllegalArgumentException으로 예외 클래수 수정
- [x] **사용하지 않는 import 구문은 삭제하기**
    - IntelliJ에서 포맷팅 시 자동으로 import를 지워주니 실행하는 습관.
    - `Mac OS : ^(Control) + ⌥(Option) + O`
- [x] `추상클래스(Human)`에서 자식클래스 모두가 같은 구현을 가질 때 추상메서드로 남겨두어야 하는가
    - `Player`와 `Dealer`에 중복되는 메서드를 추상클래스 내에서 구현

### ⁉️ 1차 피드백 Q&A

- [ ] 상속이 캡슐화를 깨트린다고 설명이 되는 이유
    - 캡슐화 : 외부에서 내부의 정보를 들여다 보지 못하게 하는 것
    - `Override`, 오버라이딩을 통해 메소드 재선언을 하는 경우 부모 객체는 캡슐화가 유지되지 못한다.
        - 부모 객체의 메서드를 가져다 쓰기만 하는 것이 아닌 재정의하고 바꾸기 때문에
        - 상위 클래스의 구현이 하위 클래스에 노출되기 때문에
- [ ] 결과 출력이 ~~Player~~ Gambler의 책임인가
    - domain 에서는 값을 전달해주는 역할을 하고 출력에 대한 책임은 View에서
    - Ex) JSON 형태로 값을 전달하여 해당 데이터를 통해 View에서 뿌려주는 것과 같지 않을까?
    - Gamblers에서 이름 목록을 String으로 변환하여 보내는 것이 아닌 List<String> 형태로 전달하여 view에서 조작하여 출력할 수 있도록 하는 것

### 1차 추가 수정

- 클래스 네이밍 수정
    - Player -> Gambler
    - Human -> Player
- CardDeck 내 카드목록 Stack으로 구현
    - 기존 List<Card> -> Stack<Card>
    - 리스트의 크기 - 1을 반환하는 메서드에서 stack의 pop을 이용한 메서드로 수정
- 추상 클래스 Player 중복 필드 및 메서드 구현
    - Name 필드, getName() 메서드 중복
    - 추상 클래스 내 구현
- 패키지 이름 변경 : Human -> Player

### 📋 2차 피드백 수정 필요 항목

- [ ] IntelliJ에서 포맷팅 시 자동으로 import를 지워주니 실행하는 습관.
    - `Mac OS : ^(Control) + ⌥(Option) + O`
- [x] **함수 및 변수의 네이밍에 대한 수정 필요**
    - [x] 블랙잭 규칙에 따른 용어에 맞춰 메서드명을 수정
        - 버스트(bust) : 카드를 더 받았는데 21이 초과하는 경우
        - 히트(hit) : 먼저 받은 카드 두장의 합이 21에 미치지 못했을 때, 카드를 더 받고자 할 경우
        - 스탠드(stand) : 먼저 받은 카드 두장의 합이 21에 미치지 못했을 때, 카드를 더 받는 것을 멈출 경우
        - 블랙잭(blackjack) : 처음 받은 카드의 합이 21인 경우
    - [x] `Cards` 클래스 `getAceCount` -> `countAce`
    - [x] `Cards` 클래스 `isOverThanMaxPoint` -> `isBust`
    - [x] `GameController`의 `checkFirstQuestion()`
        - 특정 조건을 검사하는 것만이 아닌 조건에 따라 View에 출력하는 기능을 포함.
        - 이를 나타낼 수 있는 이름으로 표현
    - [x] `Card` 클래스의 `of`메서드 안에서 "getCard"라는 변수명을 사용
        - 메서드명과 헷갈릴 수 있으므로 적절한 이름으로 수정
        - 반환하는 카드의 이름을 card -> findCard 수정
        - filter() 내부에서 사용하는 변수명을 getCard -> card 수정
- [ ] `GameResult`에서 `getDealerResult()` 메서드를 호출하는 경우에 대한 고민
- [x] `Statistic`의 gamblerResult의 타입을 `Map`에서 `LinkedHashMap`으로 구체화하게된 이유
    - Gambler 목록의 순서대로 게임 결과를 출력하고자 `LinkedHashMap`으로 구현
    - Statistic의 사용할 때 `Player`, `GameResult`의 값을 통한 반복문을 사용하기때문에 불필요한 구체화로 생각되어 다시 `Map`으로 변경
- [x] `Map` 객체가 여러 메서드에 전달되며 어떤 값이 추가되는지 확인해야한다.
    - 각 메서드에서 결과 계산과 집계를 하고 Map에 담는 역할을 나누는 것은 어떨지
    - 메서드 별로 Map을 생성하여 값을 담고 반환하는 형태로 수정
- [x] `Card`에서 `if(Objects.isNull())` 조건식의 사용
    - `Optional.get`은 무엇을 반환하는가
        - 객체를 반환하지만 값이 없을 경우 `NoSuchElementException` 발생
        - `orElseThrow` 사용
- [ ] `Cards`의 `getCards` 메서드가 사용하는 필드 자체를 반환
    - 새 컬렉션에 담아 반환하면 어떤지
    - 새 컬렉션에 담아 반환하는 이유, 장점 알아보기

### 2차 피드백 학습

- Optional.get()
    - Optional 객체에서 값을 꺼낼 수 있는 가장 간단한 메서드
    - Wrapping된 값이 없다면 NoSuchElementException 발생
      ```java
      public T get() {
          if (value == null) {
              throw new NoSuchElementException("No value present");
          }
          return value;
      }```
    - 위와 같이 구현되어있기때문에 반드시 객체에 값이 있는 것으로 판단되는 경우에만 사용한다.
- Optional.get 이외 메서드
    - 기본 값 설정하기, orElse
        - orEsle를 통해 비어있는 Optional 객체의 기본값을 설정할 수 있다.
        - Optional의 값이 있든 없든 **항상** 실행
    - 기본 값 설정하기 2, orElseGett
        - orElse와 유사하지만 인자로 Supplier를 받는다는 차이점이 존재
        - 또한, Optional에 **값이 없을 경우**에만 실행
    - 예외 던지기, orElseThrow
        - 객체에 래핑된 값이 없는 경우 예외를 발생한다.
        - get과는 다르게 예외를 직접 선택할 수 있다.

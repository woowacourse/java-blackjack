## 블랙잭 피드백

---

### 1단계 - 블랙잭

#### 1차 피드백 수정 필요 항목

- [x] 상수의 의미를 생각하여 네이밍
    - GameController 내의 FIRST_QUESTION_FLAG
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
- [x] `추상클래스(Human)`에서 자식클래스 모두가 같은 구현을 가질 때 추상메서드로 남겨두어야 하는가
    - `Player`와 `Dealer`에 중복되는 메서드를 추상클래스 내에서 구현

#### 1차 피드백 Q&A

- [ ] 상속이 캡슐화를 깨트린다고 설명이 되는 이유
    - 캡슐화 : 외부에서 내부의 정보를 들여다 보지 못하게 하는 것
    - `Override`, 오버라이딩을 통해 메소드 재선언을 하는 경우 부모 객체는 캡슐화가 유지되지 못한다.
        - 부모 객체의 메서드를 가져다 쓰기만 하는 것이 아닌 재정의하고 바꾸기 때문에
        - 상위 클래스의 구현이 하위 클래스에 노출되기 때문에
- [ ] 결과 출력이 ~~Player~~ Gambler의 책임인가
    - domain 에서는 값을 전달해주는 역할을 하고 출력에 대한 책임은 View에서
    - Ex) JSON 형태로 값을 전달하여 해당 데이터를 통해 View에서 뿌려주는 것과 같지 않을까?
    - Gamblers에서 이름 목록을 String으로 변환하여 보내는 것이 아닌 List<String> 형태로 전달하여 view에서 조작하여 출력할 수 있도록 하는 것

#### 1차 추가 수정

- 클래스 네이밍 수정
    - Player -> Gambler
    - Human -> Player
- CardDeck 내 카드목록 Stack으로 구현
    - 기존 List<Card> -> Stack<Card>
    - 리스트의 크기 - 1을 반환하는 메서드에서 stack의 pop을 이용한 메서드로 수정
- 추상 클래스 Player 중복 필드 및 메서드 구현
    - Name 필드, getName() 메서드 중복
    - 추상 클래스 내 구현

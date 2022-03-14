## 블랙잭 피드백

---

### 1단계 - 블랙잭

#### 1차 피드백 수정 필요 항목

- [x] 상수의 의미를 생각하여 네이밍
    - GameController 내의 FIRST_QUESTION_FLAG
- [ ] GameResult 내의 `WIN`을 나타내는 문자열은 어느 영역의 관심사인가
    - 다른 언어권에게 제공해야한다면 어떻게 배워야할까?
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
- [ ] `CardDeck`에서 `size()`메서드는 테스트에서만 사용됨.
- [ ] `CardDeckGenerator`에서 카드를 생성할 때 어떤 규칙을 사용하는가.
- [x] `Denomination`에서 `예외`를 던질 때 메세지를 담아서 보내기
    - orElseThrow()에서 IllegalArgumentException 발생하여 메세지 전달 하도록 수정
- [x] `Name` 객체 생성 시에 검증하는 과정에서 적절한 예외 클래스 사용
    - 기존 RuntimeException에서 IllegalArgumentException으로 예외 클래수 수정
- [ ] **사용하지 않는 import 구문은 삭제하기**
- [ ] `추상클래스(Human)`에서 자식클래스 모두가 같은 구현을 가질 때 추상메서드로 남겨두어야 하는가

#### 1차 피드백 Q&A

- [ ] View의 출력 형식을 어디서 담당하는 것이 적절한가.
- [ ] 상속이 캡슐화를 깨트린다고 설명이 되는 이유
- [ ] 결과 출력이 Player의 책임인가


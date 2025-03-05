# java-blackjack

블랙잭 미션 저장소

## 기능 요구사항 정리

블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며,
    - 예외로 Ace는 1 또는 11로 계산할 수 있다.
    - King, Queen, Jack은 각각 10으로 계산한다.


- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
    - 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
    - 딜러가 처음에 받는 2장의 카드의 합이 21이라면, 플레이어가 블랙잭이 아닌 경우 패배한다.
        - 단, 블랙잭일 경우는 무승부이다.


- 플레이어는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    - 단, 플레이어가 21점을 초과하는 순간 무조건 패배한다.


- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.


- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.
    - 단, 점수가 같다면 무승부이다.
    - 플레이어가 21점을 초과하는 순간 무조건 패배한다.

## 프로그래밍 요구사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 배열 대신 컬렉션을 사용한다.
- Java Enum을 적용한다.
- 모든 원시 값과 문자열을 포장한다.
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

## 구현해야하는 기능 목록

- [x] 블랙잭에 필요한 카드 추가
- [x] 블랙잭에 필요한 덱 추가
- [x] 덱에서 카드를 뽑는 기능 추가
- [x] 블랙잭에 필요한 심판 추가

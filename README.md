# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 연료 주입

- [x] 회사를 생성한다
    - [x] 차량 별 상태를 반환한다
- [x] 차를 생성한다
    - [x] 차가 이동할 거리를 인스턴스로 받는다
    - [x] 차종의 이름, 여행하려는 거리, 연비를 저장한다
    - [x] 주입해야 할 연료량을 구한다

## 1단계 - 블랙잭

### 입력

- [x] 참여자 이름 입력 받는 기능
    - [x] [예외] 빈 문자열을 입력받은 경우
    - [x] [예외] 문자열 앞뒤에 구분자가 있는 경우
- [x] 참여자의 배팅금액 입력 받는 기능
    - [x] [예외] 숫자가 아닌 경우
- [x] 한장 더 받는지 여부 확인 기능
    - [x] [예외] y 또는 n이 아닌 문자를 입력받은 경우

### 블랙잭 게임 진행

- [x] 카드묶음 생성
- [x] 카드 생성
- [x] 플레이어 모음 객체 생성
- [x] 플레이어 객체 생성
    - [x] [예외] 중복된 이름이 입력된 경우
- [x] 숫자, 모양 이넘객체 생성
- [x] 배팅금액 객체 생성
    - [x] [예외] 0 이하일 경우
- [x] 이름 객체 생성
    - [x] [예외] 쉼표 기준으로 입력받지 않을 경우
- [x] 딜러, 참여자에게 두 장의 카드를 지급
- [x] 카드 숫자 계산
    - [x] 21을 초과 시 패배
    - [x] Ace는 1 또는 11로 계산
    - [x] King, Queen, Jack은 각각 10으로 계산
- [x] 카드 추가 기능
    - [x] 참여자 : 숫자를 합쳐 21 미만이면 한장 더 받는지 묻기
    - [x] 딜러 : 2장의 합계가 16 이하이면 반드시 1장의 카드 추가
- [x] 승패 계산 기능
    - [x] 카드 숫자를 합쳐 21을 초과하지 않거나 21에 가깝게 만들면 이긴다
    - [x] 플레이어 첫 두장이 블랙잭
        - [x] 딜러 20이하/버스트 : 플레이어 승리, 배팅금액 1.5배
        - [x] 딜러 블랙잭 : 무승부
    - [x] 딜러가 버스트
        - [x] 플레이어 모두 승리
    - [x] 딜러가 21 이하
        - [x] 플레이어 21 이하 : 더 높은 점수인 쪽이 승리
        - [x] 플레이어 21 초과 : 딜러 승리
- [x] 최종 돈 반환 기능

### 출력

- [x] 카드 목록 및 점수 출력
- [x] 최종 딴 금액 결과 출력

## 피드백

2단계 2차 피드백

- [x] Java 의 Stack 대신 Deque을 한번 읽어 보세요~
- [x] value에 값을 할당하는 로직도 init() 안에 넣어도 될 것 같네요.
- [x] popCards 검증로직 개선
- [x] INIT_NUMBER라는 이름만으로는 의미를 온전하게 전달하지 못하는 것 같아요.
- [x] human이라는 단어는 어디서 온 걸까요? participant와 혼용될 필요가 있나요?
- [x] Dealer draw() 속 메서드 변경
- [x] 마이너스 대신 -1을 곱하기
- [x] 사이즈 꺼내서 비교하는 대신 물어보기
- [x] raw라는 단어 변환
- [x] state의 draw()라는 메서드 이름 addCard로 변경
- [x] getRawPoint -> getAceNotConsideredPoint로 이름 변경
- [x] isSameDenomination() -> isAce() 변경
- [x] getCopy() 를 get()으로 변경
- [x] isPrint 인자를 isDealerHit으로 변경
- [x] 잘못 쓴 Integer int로 변경
- [x] Finished 상수 이름 변경(PROFIT 추가)
- [x] abstract끼리 모아두기
- [x] State 클래스들 이름 변경
- [x] test 코드 내부에서 카드가 중복되지 않게 수정
- [x] 동일성과 동등성 : 동일성은 같은 객체인지, 동등성은 객체 내부 값이 같은지(동등한지)

2단계 피드백

- [x] BlackjackGame -> 매직넘버 INIT_CARD_COUNT 를 popCards(2) 에서도 사용
- [x] BlackjackGame -> endGame 이름 출력하는 일 하는 느낌으로 변경
- [x] Players -> 상수와 인스턴스 변수 사이 개행
- [x] 테스트 코드에서만 사용되는 메소드 제거
    - [x] fromNames(), fromText()
    - [x] 정적 팩터리 메서드가 생성자만 호출하는 경우 삭제
    - [x] 테스트 코드의 간편화를 위해 fixture 클래스를 테스트코드 내부에 생성
- [x] raw... 변수명 변경
- [x] players -> 사용되지 않는 calculateBettingMoney 메서드 제거
- [x] player
    - [x] calculateResult() -> 삭제
    - [x] Player.is.... -> 문법적으로 어색
- [x] Result 삭제
- [x] BlackjackGame 로직 다른 곳으로 이동
- [x] PrideCalculator ->
    - [x] [hashCode() 메서드 용도 공부](https://www.baeldung.com/java-hashcode) : 해시코드는 객체의 아이디-> 클라이언트 전달 용도로는 부적절
- [x] betting
    - [x] getMultiple()이름 수정
    - [x] percentage 라는 단어 수정
- [x] InputView
    - [x] Name의 존재를 모르도록 수정
    - [x] 이름은 한자 이상 입력 검증로직 수정
        - [x] "a,,a" 같은 입력을 인풋뷰에서 예외처리
- [x] Point
    - [x] point > 21 같은 원시적 방식으로 변경
- [x] isBlackjack -> 카드가 두장인 조건 추가
- [x] getMultiple() 매직넘버 상수화

## TODO

### 목표

- [x] extends 상속의 이점에 맞게 상속구조 수정!
- 좋은 객체의 7가지 덕목
    - [x] 싱글턴, 팩터리는 좋은 객체가 아니다.
    - [x] 유틸리티 클래스는 “클래스”라고 부를 수조차 없다.
    - [x] 객체의 이름은 이 객체가 무엇인지 말해야 하고 무슨 일을 하는지 말해서는 안 된다.
        - [x] “-er”로 끝나는 이름은 피하라.
    - [x] 객체의 클래스는 final 이나 abstract
- [x] 주 생성자/부 생성자(주 생성자를 호출하게 됨) 분리

- [x] Consumer 적용해 getter 삭제
- [x] 정적 팩터리 메소드 다이어트!
- [x] fixture 적용해 테스트코드 간소화!
- [x] 객체 지향의 다형성을 이용
    - [x] 수업에서 배운 state... 적용해보기!
    - [x] 힛(Hit): 처음 2장의 상태에서 카드를 더 뽑는 것
    - [x] 스테이(Stay): 카드를 더 뽑지 않고 차례를 마치는 것
    - [x] 블랙잭(Blackjack): 처음 두 장의 카드 합이 21인 경우, 베팅 금액의 1.5배
    - [x] 버스트(Bust): 카드 총합이 21을 넘는 경우. 배당금을 잃는다.
    - [x] 현재 상태에서 다음 상태의 객체를 생성하는 역할을 현재 상태가 담당하도록 한다.


- [x] player 초기화 시 카드 두장 리스트 받아오게 변경
- [x] BlackjackGame 코드 효율화
    - [x] 플레이어들 결과 계산 로직 players 로 이동

- [x] 패키지 구조 여러개-낱개-구성요소 순으로 정렬 변경
- [x] 테스트 코드 검사

- [x] cards.add 불변처리
- [x] players test 추가
- [x] final class 로 상속안함 표시
- [x] cards, table 클래스 제외 불변화
- [x] moneyStatistic 로직 수정
- [x] Point 생성자 인자를 Cards로 변경 및 메소드 Cards로 이동

- [x] final 적용
- [x] moneyStatistic indent 줄이기 & test코드 수정
- [x] 상수화
- [x] 테이블 생성
- [x] 딜러 승패 맵 생성
- [x] 컨트롤러 정리
- [x] 변수명 수정
- [x] ace 포인트 메서드 추출
- [x] indent 1로 수정
- [x] Deck을 Stack으로 변경
- [x] 테스트 코드 리팩토링

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
    - [x] [예외] 중복된 이름이 입력된 경우
- [x] 한장 더 받는지 여부 확인 기능
    - [x] [예외] y 또는 n이 아닌 문자를 입력받은 경우

### 블랙잭 게임 진행

- [x] 카드묶음 생성
- [x] 카드 생성
- [x] 플레이어 모음 객체 생성
- [x] 플레이어 객체 생성
- [x] 숫자, 모양 이넘객체 생성

- [x] 이름 객체 생성
    - [x] [예외] 쉼표 기준으로 입력받지 않을 경우
- [x] 딜러, 참여자에게 두 장의 카드를 지급
- [x] 카드 숫자 계산
    - [x] 21을 초과 시 패배
    - [x] Ace는 1 또는 11로 계산
    - [x] King, Queen, Jack은 각각 10으로 계산
- [x] 카드 추가 기능
    - [x] 참여자 : 숫자를 합쳐 21을 초과하지 않으면 한장 더 받는지 묻기
    - [x] 딜러 : 2장의 합계가 16이하이면 반드시 1장의 카드 추가
- [x] 승패 계산 기능
    - [x] 카드 숫자를 합쳐 21을 초과하지 않거나 21에 가깝게 만들면 이긴다
    - [x] 딜러가 21 초과
        - [X] 플레이어가 모두 21 이하 : 플레이어 모두 승리
        - [x] 플레이어 중 21 초과 존재 : 21 이하인 플레이어만 승리
        - [x] 플레이어 모두 21 초과 : 딜러 승리
    - [x] 딜러가 21 이하
        - [x] 플레이어 모두 21 이하 : 딜러보다 21에 가까운 플레이어들이 승리 ,같은 값이면 무승부
        - [x] 플레이어 중 21 초과 존재 : 초과는 패배 , 딜러보다 21에 가까운 플레이어들이 승리
        - [x] 플레이어 모두 21 초과 : 딜러가 모두 승리 ㅇ

### 출력

- [x] 카드 목록 및 점수 출력
- [x] 최종 승패 결과 출력

## 피드백

1단계 피드백-2

- [x] 혹시 여기에 있는 xml 파일을 임포트하여 사용하고 있으신가요?
    - [x] 빈 줄에는 공백이 있으면 안 되는데 전체적으로 정리 부탁드립니다.
- [x] 상수 이동
    - [x] 유틸이라 함은 비즈니스 로직과 관련이 없으면서 범용적으로 사용할 수 있는 도구들 -> 유틸 제거
- [x] Repository 이름 변경 및 역할 고민
  - [x] 참여자들이 있는 Participant 클래스로 변경
  - [x] CardDeck 필드에서 제거, 메소드 인자로 변경
  - [x] human 패키지 안으로 이동
- [ ] 패키지 이름은 도메인 용어
- [ ] 메서드 배치 순서를 정하는 기준
- [ ] 인풋 예외처리 확실히 하기
    - [ ] 이름 공백일 때
- [ ] 입력에서 사용되는 ,는 어떤가요? -> 역시 view에서 처리?
- [ ] , 다음에 띄어쓰기하는 경우도 입력 허용해보자~
- [ ] 이름 중복 예외 처리가 의도한 대로 동작하고 있나요?
- [ ] toString()의 본래 용도에 대해 꼭 학습해 보고, 지금 사용한 방식이 적절한지도 고민
- [ ] 테스트 코드 역시 유지보수성, 가독성이 정말 중요
    - [ ] 어떤 건 인스턴스 변수이고 어떤 건 로컬 변수
    - [ ] Exception.class의 인스턴스인지 확인하는 것은 별로 의미가 없는 것 같아요.
    - [ ] 메서드 간의 개행
    - [ ] 테스트를 위한 준비 부분과 단언문 사이에 개행
- [ ] 메서드 이름이 잘못된 것 같아요.
    - [ ] stringOf()라는 이름은 String 값을 리턴할 것을 기대
- [ ] Cards -> getter가 연쇄적으로 호출
- [ ] 단순히 생성자를 호출할 뿐인데, 이런 방식의 구조와 이름을 사용하신 이유
    - [ ] 싱글턴 장단점 학습
- [ ] 상속
    - [ ] "상위 클래스와 하위 클래스를 모두 같은 프로그래머가 통제하는 패키지 안에서라면 상속도 안전한 방법이다."
    - [ ] 현재의 구현 : 상위 클래스와 하위 클래스가 매우 밀접하게 연결 -> 상위에서 하위로 영향 미치는 것이 당연
- [ ] 불변성이 왜 좋은지, 불변성을 지킬 수 있다면 무조건 좋은지 고민
    - [ ] 그리고 현실적으로 게임 진행을 위해 컨트롤러에서 개별 플레이어를 알아야 하는 상황에서,
    - 현재 플레이어는 BlackjackRepository가 가지고 있기 때문에 이렇게 플레이어를 달라고 요청하고 있는데,
    - 그렇다면 과연 BlackjackRepository가 하는 역할은 무엇인지도 고민할 가치가 있는 것 같아요.
- [ ] printHumanHand 비롯한 아웃풋뷰 메소드 객체지향적으로 수정
- [ ] 이 메서드를 호출하면 player.isTwoCard()와 같은 모양이 되는데, 이렇게 보았을 때 메서드 이름이 적절한가요?

1단계 피드백-1

- [x] 파일 끝에 개행문자 추가
    - [x] insert_final_newline 옵션을 true로 설정
- [x] 인풋 뷰 검증로직을 Players 클래스 안으로 이동, 정적팩토리메서드 추가
- [x] `GameController` : 10 라인 넘어가는 `questAddCard` 메서드 로직 효율화
- [x] suit 클래스 속 이름 중복 제거
- [x] 상수와 인스턴스 변수 사이 개행
- [x] cards 속 정적 팩토리 메소드 제거 및 생성자 public 화
    - [x] 상속불가 클래스에 `final` 추가
    - [x] 정적 팩토리 메서드 네이밍 컨벤션에 맞게 수정
- [x] PLAYER_NAME_DELIMITER 제거 및 로직 변경
- [x] 패키지 명명규칙에 맞게 이름 수정
- [x] 테스트코드
    - [x] 테스트코드 컨벤션에 맞게 수정
    - [x] 테스트코드 when-given-then 적용
    - [x] `@DisplayName` 변경
- [x] 코드 리포맷 세팅
- [x] 출력화면에 맞게 OutputView 메서드 로직 수정
- [x] 패키지에 위치한 클래스들 하위에 패키지를 더 나누기
- [x] Table 클래스 이름 변경, 클래스 역할 고민 -> repository 화

## 궁금증

1.

`public int getPoint() { return cards.getPoint(); }`
여기서 getPoint()를 내부 변수를 가져와 재정의해야 하는데 위 방식이 맞을까?

2.

```java
/*public class Item46 {
    Card card1 = new Card(10, spade);
    Cards cards = new Cards();
    cards.add(card1);
    
    card1 =new
    
    Card(4,spade);
}*/
//->cards 안의 값이 변경되는가?

```

cards.add() 에서 불변처리 하자

## TODO

- [ ] 이름들 변경
- [ ] 테스트 코드 검사
- [ ] 응집도를 높이는 방향으로 리팩토링
    - [ ] statistic map도 따로 클래스로 구현해보자, 아웃풋에 구현된 클래스를 넘겨줘 보자
        - [ ] statistic, point 객체 수정

- [x] cards.add 불변처리
- [x] players test 추가
- [x] final class 로 상속안함 표시
- [x] cards, table 클래스 제외 불변화
- [x] resultStatistic 로직 수정
- [x] Point 생성자 인자를 Cards로 변경 및 메소드 Cards로 이동

- [x] final 적용
- [x] resultStatistic indent 줄이기 & test코드 수정
- [x] 상수화
- [x] 테이블 생성
- [x] 딜러 승패 맵 생성
- [x] 컨트롤러 정리
- [x] 변수명 수정
- [x] ace 포인트 메서드 추출
- [x] indent 1로 수정
- [x] Deck을 Stack으로 변경
- [x] 테스트 코드 리팩토링

# java-blackjack

블랙잭 미션 저장소

# 요구사항 분석
## 주요 객체의 속성, 역할
### Participant
- [ ] 이름을 가진다.
- [x] `Cards`를 가진다.
- [x] `Card`를 받는다.

### Dealer
- [ ] "딜러" 라는 이름을 가진다.
- [x] `Players`를 가진다.
- [x] `Deck`을 가진다.
- [ ] `Participant`를 가진다.
- [x] 초기 게임 세팅을 한다.
- [x] `Player`에게 카드를 한 장 준다.
- [x] 최종 승패를 결정한다.
- [x] 점수가 16점 초과일 때까지, 본인의 카드를 한 장 더 뽑는다.

### Player
- [x] 이름의 길이는 최소 1글자에서 최대 5글자이다.
- [ ] `Participant`를 가진다.

### Players
- [x] 입력된 이름들로 `Player`를 만들어 가진다.
  - [X] 최소 2명에서 최대 8명의 `Players`를 생성한다.

### Card
- [x] `Number`와 `Pattern`을 가진다.

### Cards
- [x] `Card`들을 가진다.
- [x] 총점을 계산한다.
- [x] 블랙잭인지 계산한다.
- [x] 버스트인지 계산한다.
- [x] 16점 이하인지 계산한다.

### Deck
- [x] 52장의 카드를 가진다.
- [x] 무작위의 카드를 한 장 뽑아준다.

### Number (Enum)
- [x] 숫자를 가진다. (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K)
- [x] 숫자를 점수로 변환해준다.

### Pattern (Enum)
- [x] 문양을 가진다. (하트, 스페이드, 클로버, 다이아몬드)
  - [x] 문양에 맞는 이름을 가진다.

### Result (Enum)
- [x] 결과를 가진다. (승, 무, 패)
- [x] 딜러의 점수와 Player들의 점수를 토대로 게임 결과를 반환한다.

## 입출력 요구사항
### 입력 요구사항
- [x] 참가자의 이름을 입력받는다. (','로 구분짓는다.)
  - [x] 공백을 입력했는지 검증한다.
- [x] 카드를 더 받을지 입력받는다.
  - [x] 'y' 또는 'n' 을 입력했는지 검증한다.

### 출력 요구사항
- [x] 딜러와 참가자들에게 나누어준 카드를 출력한다.
  - [x] 딜러는 한 장의 카드만 출력한다.
- [x] 참가자의 현재 가진 카드들을 출력한다.
- [x] 딜러의 점수가 16점 이하인 경우, 딜러가 한 장의 카드를 더 받았다고 출력한다.
- [x] 참가자와 딜러가 가진 카드들을 모두 출력하고, 총점도 같이 출력한다.
- [x] 최종 승패를 출력한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 미션을 진행하며 궁금했던 점
1. 일급 컬렉션을 쓰다보니 컨트롤러에서 `dealer.getPlayers().getPlayers()` 로 쓰이게 됩니다. 그래서 아래와 같은 방법을 생각해봤습니다.
```java
public class Dealer {
  // ...
  public List<Player> getPlayers() {
      return this.players.getPlayers();
  }
}
```
그런데, 아무래도 캡슐화가 깨지는 느낌을 받아서 가독성 측면에서 보기 안좋지만 `dealer.getPlayers().getPlayers()` 처럼 하게 됐습니다. 더 나은 방법이 있을까요?

2. 저희는 `Controller`에서 도메인으로부터 값을 받아 `View`에게 도메인 객체를 넘기지 않고, 필요한 정보를 재가공하여 전달하는 방식을 사용했습니다. 이렇게 하니, `OutputView`는 단순히 print만 하는 클래스가 되는 느낌이었고, `Controller`의 코드가 복잡해졌습니다.<br>
이유는, `View`가 `Domain`을 직접적으로 아는 듯하여 의존성을 약하게 하기 위함입니다.<br>
`OutputView`에서 `Domain` 객체를 직접 가공하는 것은 MVC 패턴에 위배되는 것 같은데, 저희의 방법이 적절한가요?

3. `Enum` 객체에서 출력을 위한 상태를 가져도 괜찮은 지 궁금합니다.

4. TDD를 적용하여 구현하려고 했습니다. 하지만 개발 초기에는 생성자 또는 Java API를 사용하는 클래스가 거의 대부분이더라구요. 그래서 이 부분은 테스트 할 필요성을 느끼지 못해 하지 않았습니다. 이래도 괜찮을까요?

5. PlayersFactory는 페어의 의견으로 생성된 팩터리 객체입니다. 사용자로부터 플레이어의 이름을 입력받고, Players 객체를 생성하기 전에 인원을 검증하고 싶다는 생각으로 생성되었습니다. 당시 설득되어 사용했지만, 개인적으로 Players가 충분히 생성자를 통해 입력값을 받아 검증하고 생성해줘도 좋겠다고 생각이 듭니다.

6. 2번 질문과 관련된 질문입니다. 프로그래밍 요구사항에 `핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다. UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.` 가 명시되어 있었습니다. 때문에 InputView, OutputView 를 그대로 적용해야 했습니다.<br><br>
2번 질문에 대한 제 결론은, 현재 콘솔 환경이며 프로그램 규모를 고려하여 지금 단계에서는 **Domain 객체가 바로 View에 전달되어 View에서 렌더링을 하는 방식**으로 결정했습니다.<br>
가장 최근 미션의 피드백 중, `지금같은 간단한 코드에서는 도메인을 넘겨줘서 view에서 처리해주는것이 코드가 깔끔해지고, 불필요한 작업이 없어서 좋아보일 수 있습니다. 하지만, view로 도메인이 객체가 나가게 되면 이전에 사용하던 데이터 때문에, 상위호환성을 지켜야해서 비지니스 요구사항이 변경되어도 도메인영역을 변경해야할 때, 쉽게 할 수가 없게됩니다.` 라는 말 덕분인데요.<br>
때문에 추후 서비스 규모가 커지거나 도메인이 변경될 가능성이 많은 상황에서는 이 둘의 관계를 느슨하게 해주는 방식으로 하려고 합니다. 혹시 이에 대해 어떻게 생각하시는지 의견을 듣고 싶습니다!

7. 상속이 가지는 많은 문제점들 때문에 조합으로 구현해보았습니다. 하지만, 막상 구현하고 보니 요구사항 중에 `딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.`를 지키지 못하는 것 같았어요. 그리고 코드 재사용으로 상속을 사용하게 되면 부모와 자식 클래스 간의 강한 결합도가 생겨서 변경 및 확장성이 매우 안좋아지기에 조합을 쓴다고 공부했는데요. 현재 코드에서 조합으로 구현해도 이 부분에 대한 이점을 느끼지 못했습니다. 여기에 대해서 페어와 많은 이야기를 나눠봤지만, 페어도 어떤 점이 좋아서 조합을 쓰라고 하는 지 모르겠다고 합니다. 혹시 알려주실 수 있으실까요 😢

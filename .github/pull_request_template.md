## 체크 리스트

- [x] 미션의 필수 요구사항을 모두 구현했나요?
- [x] Gradle `test`를 실행했을 때, 모든 테스트가 정상적으로 통과했나요?
- [x] 애플리케이션이 정상적으로 실행되나요?
- [x] [프롤로그](https://prolog.techcourse.co.kr/studylogs/4021)에 셀프 체크를 작성했나요?

## 객체지향 생활체조 요구사항을 얼마나 잘 충족했다고 생각하시나요?

### 1~5점 중에서 선택해주세요.

- [ ] 1 (전혀 충족하지 못함)
- [ ] 2
- [ ] 3 (보통)
- [x] 4
- [ ] 5 (완벽하게 충족)

### 선택한 점수의 이유를 적어주세요.

BlackjackScore 객체를 생성하여 블랙잭 게임의 점수를 계산하는 행위와 관련된 메서드들을 분리했다.
Dealer, Player 역할을 분리하여 각자 본인이 지녀야 할 책임들만 지니도록 메서드를 작성했다.

## 어떤 부분에 집중하여 리뷰해야 할까요?

### BlackjackScore 객체 생성

변경 전: Score 객체에서 dfs 알고리즘을 이용해 플레이어와 딜러의 카드 점수 계산
변경 후: BlackjackScore 객체를 불변객체로 변경 후, 점수 계산 관련 메서드 생성하고 Cards 클래스에서 점수 계산

record 불변 객체를 사용했는데, 인자로 `카드점수`, `카드 개수`를 전달합니다.

```java
public record BlackjackScore(int value, int cardSize) {

}
```

처음에는 `카드점수`만 넘겨주도록 설계했는데, 이후에 점수를 계산하는 WinningResult에서 `블랙잭임을 확인`해야할 때, 카드 개수가 2개인지 확인할 방법이 없었습니다.
아래의 코드에서 mainScore, subScore만 넘겨주어 계산하고 싶은데, `mainCards.isBlackjack()`메서드를 사용해야 해서 이 부분을 Score 객체를 통해 계산할 수 있도록 리팩토링하고
싶었습니다.

```java
public enum WinningResult {
    DRAW((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore.isBust() && subScore.isBust()) {
            return true;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return false;
        }
        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return false;
        }
        return mainScore.value() == subScore.value();
    }),
  ...
}
```

해결방법으로 떠오른 것은

1. 그냥 이대로 사용하기
2. Score 객체에 매개변수로 카드 개수를 받도록 수정하고, 클래스 네이밍 수정하기 (Score->BlackjackScore)

2번 방법으로 수정하면 블랙잭인지 판단하는 메서드를 BlackjackScore 에서 처리할 수 있고, 코드를 읽기에도 직관적일 것 같아서 수정하게 되었습니다.
혹시 더 좋은 다른 방법이 있는지 궁금해요!

### BettingResult 클래스

BettingResult 클래스는 플레이어와 딜러의 최종 수익을 계산하고 반환합니다.

제가 생각할 때는 GameResult, BettingResult는 서로 비슷하게 dto 역할을 해주는 것 같은데, 이렇게 `단순 계산만 하는` 계산 클래스를 두개씩 두는게 맞을까요?

오히려 dealer와 player 각각에게 계산 책임을 위임하는 방식이 더 나을까요?

블랙잭 게임의 구조에서는 DTO 사용이 필요하지 않다고 생각해서 여쭤봅니다.

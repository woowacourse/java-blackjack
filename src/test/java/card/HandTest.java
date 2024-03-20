package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("해당 라운드에서 카드 점수의 최솟값이 21을 넘는지 확인한다.")
    @Test
    void countAceCard() {
        Hand hand = new Hand(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(hand.countCard()).isEqualTo(2);
    }

    @DisplayName("Result로 낼 수 있는 가장 큰 결과 값을 return 하는지 확인한다.")
    @Test
    void getMaxCardScoreResult() {
        Hand hand = new Hand(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN),
                new Card(CardNumber.EIGHT, CardPattern.SPADE_PATTERN)));

        Assertions.assertThat(hand.countMaxScore()).isEqualTo(20);
    }

    @DisplayName("추가 ACE 점수가 더해질 때 Bust 일 경우 ACE가 1인 경우를 계산한 값을 return 한다.")
    @Test
    void notConvertToAdditionalAceCardScore() {
        Hand hand = new Hand(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.KING, CardPattern.DIA_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.DIA_PATTERN)));

        int matchScore = hand.countMaxScore();

        Assertions.assertThat(hand.countMaxScore()).isEqualTo(matchScore);
    }

    @DisplayName("추가 ACE 점수가 더해질 때 Bust가 아닐 경우 ACE가 11인 경우를 계산한 값을 return 한다.")
    @Test
    void convertToAdditionalAceCardScore() {
        Hand hand = new Hand(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.FOUR, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(hand.countMaxScore()).isEqualTo(15);
    }

    @DisplayName("가진 카드들의 모습을 List로 만들어 반환한다.")
    @Test
    void getCardsFeature() {
        Hand hand = new Hand(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.KING, CardPattern.DIA_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.DIA_PATTERN)));

        List<String> expectedResult = List.of("A클로버", "K다이아몬드", "Q다이아몬드");

        Assertions.assertThat(hand.getCardsFeatures()).isEqualTo(expectedResult);
    }

    @DisplayName("첫번째 카드를 반환한다.")
    @Test
    void getFirstCard() {
        Card expectedCard = new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN);

        Hand hand = new Hand(List.of(expectedCard,
                new Card(CardNumber.FOUR, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(hand.getFirstCard()).isEqualTo(expectedCard);
    }
}

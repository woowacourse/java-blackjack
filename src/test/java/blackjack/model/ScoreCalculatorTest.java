package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    private final ScoreCalculator calculator = new ScoreCalculator(new AceAdjustPolicy(new BustPolicyImpl()));

    @Test
    @DisplayName("카드 점수의 합을 계산한다")
    void calculateTotalScore() {
        // given
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.CLUB),
                new Card(Rank.THREE, Suit.CLUB)
        );
        int expectedScore = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        // when
        int actualScore = calculator.calculateScore(cards);

        // then
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}

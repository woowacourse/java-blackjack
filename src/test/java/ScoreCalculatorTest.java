import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {

    @Test
    void 카드_점수를_합산한다() {
        ScoreCalculator calculator = new ScoreCalculator();
        List<Card> hand = List.of(new Card(Rank.JACK, Suit.SPADE), new Card(Rank.TEN, Suit.SPADE));

        int currentScore = calculator.sumScore(hand);

        assertThat(currentScore).isEqualTo(20);
    }
}

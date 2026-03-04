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

    @Test
    void 핸드에_A가_있고_합산점수가_11점_이하면_10점을_추가한다() {
        ScoreCalculator calculator = new ScoreCalculator();
        List<Card> hand = List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.SPADE));

        int currentScore = calculator.sumScore(hand);
        int finalScore = calculator.checkAce(currentScore, hand);

        assertThat(currentScore).isEqualTo(21);
    }
}

package domain;

import domain.constant.Rank;
import domain.constant.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {

    @Test
    void 카드_점수를_합산한다() {
        ScoreCalculator calculator = new ScoreCalculator();
        List<Card> hand = List.of(new Card(Rank.JACK, Suit.SPADE), new Card(Rank.TEN, Suit.SPADE));

        int score = calculator.calculateScore(hand);

        assertThat(score).isEqualTo(20);
    }

    @Test
    void 핸드에_A가_있고_합산점수가_11점_이하면_10점을_추가한다() {
        ScoreCalculator calculator = new ScoreCalculator();
        List<Card> hand = List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.SPADE));

        int score = calculator.calculateScore(hand);

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 핸드에_A가_있고_합산점수가_11점_초과이면_원래점수를_사용한다() {
        ScoreCalculator calculator = new ScoreCalculator();
        List<Card> hand = List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.ACE, Suit.HEART),
                new Card(Rank.KING, Suit.HEART));

        int score = calculator.calculateScore(hand);

        assertThat(score).isEqualTo(12);
    }

    @Test
    void 핸드가_21점이_넘으면_버스트다() {
        ScoreCalculator calculator = new ScoreCalculator();
        boolean result = calculator.isBust(22);

        assertThat(result).isTrue();
    }
}

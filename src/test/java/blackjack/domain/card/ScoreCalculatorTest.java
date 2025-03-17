package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    void 카드_합이_21을_초과하지_않는_경우가_존재한다면_21보다_작은_수_중_최대값을_반환한다() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE)
        );

        //when
        Score score = scoreCalculator.calculateMaxScore(cards);

        //then
        assertThat(score).isEqualTo(new Score(20));
    }

    @Test
    void 카드_합이_21을_초과하는_경우만_있다면_21과_가장_가까운_수를_반환한다() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE)
        );

        //when
        Score score = scoreCalculator.calculateMaxScore(cards);

        //then
        assertThat(score).isEqualTo(new Score(22));
    }

    @Test
    void 가능한_최소_점수를_계산한다() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT)
        );

        //when
        Score score = scoreCalculator.calculateMinScore(cards);

        //then
        assertThat(score).isEqualTo(new Score(12));
    }
}

package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ScoreCalculatorTest {

    @ParameterizedTest
    @EnumSource(value = Rank.class)
    void 버스트가_아닌_경우_ace를_11로_조정한_총_점수를_반환한다(Rank rank) {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.CLOVER),
                new Card(rank, Suit.CLOVER)
        );
        int sum = Rank.ACE.getValue() + rank.getValue();
        // when
        Score adjustedScore = scoreCalculator.calculate(cards);
        // then
        assertThat(adjustedScore).isEqualTo(sum + 10);
    }

}

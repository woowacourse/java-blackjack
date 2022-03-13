package blackjack.model.score;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.player.Cards;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MaxScoreCalculatorTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card NINE = new Card(Rank.NINE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);

    @ParameterizedTest
    @MethodSource("provideMaxCards")
    @DisplayName("최대 카드 점수 계산")
    void maxScore(Cards cards, int expect) {
        assertThat(new MaxScoreCalculator().calculate(cards)).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMaxCards() {
        return Stream.of(
            Arguments.of(new Cards(ACE, JACK), 21),
            Arguments.of(new Cards(ACE, JACK, KING), 31),
            Arguments.of(new Cards(ACE, ACE, NINE), 31)
        );
    }

}

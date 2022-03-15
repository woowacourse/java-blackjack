package blackjack.model.cards;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BestScoreCalculatorTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("최고의 카드 점수 계산")
    void bestScore(Cards cards, int expect) {
        BestScoreCalculator calculator = new BestScoreCalculator();
        assertThat(calculator.score(cards)).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideCards() {
        return Stream.of(
            Arguments.of(new Cards(ACE, JACK), 21),
            Arguments.of(new Cards(ACE, JACK, KING), 21),
            Arguments.of(new Cards(ACE, ACE, ACE, ACE), 14),
            Arguments.of(new Cards(QUEEN, JACK, KING), 30),
            Arguments.of(new Cards(THREE, TWO), 5)
        );
    }

}

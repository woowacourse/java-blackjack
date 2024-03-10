package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    private static final List<Card> TWO_ACE = CardTest.TWO_ACE;
    private static final List<Card> SCORE_13_WITH_ACE = CardTest.SCORE_13_WITH_ACE;
    private static final List<Card> CARDS_SCORE_16 = CardTest.CARDS_SCORE_16;
    private static final List<Card> CARDS_SCORE_21 = CardTest.CARDS_SCORE_21;
    private static final List<Card> BLACKJACK = CardTest.BLACKJACK;
    private static final List<Card> CARDS_SCORE_22 = CardTest.CARDS_SCORE_22;

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("cardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore()).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.of(BLACKJACK, 21),
                Arguments.of(TWO_ACE, 12),
                Arguments.of(SCORE_13_WITH_ACE, 13),
                Arguments.of(CARDS_SCORE_16, 16)
        );
    }

}

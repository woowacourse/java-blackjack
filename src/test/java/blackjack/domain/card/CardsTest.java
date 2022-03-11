package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("")
    @ParameterizedTest
    @MethodSource("provideForParameterizedTest")
    void calculateScoreTest(final List<Card> initializedCards, final int expectedScore, final boolean expectedBursted) {
        final Cards cards = new Cards();
        initializedCards.forEach(cards::addCard);

        final int actualScore = cards.calculateScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("")
    @ParameterizedTest
    @MethodSource("provideForParameterizedTest")
    void isBurstTest(final List<Card> initializedCards, final int expectedScore, final boolean expectedBurst) {
        final Cards cards = new Cards();
        initializedCards.forEach(cards::addCard);

        final boolean actualBurst = cards.isBurst();
        assertThat(actualBurst).isEqualTo(expectedBurst);
    }

    private static Stream<Arguments> provideForParameterizedTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.HEART),
                                new Card(CardNumber.ACE, CardPattern.CLOVER),
                                new Card(CardNumber.ACE, CardPattern.DIAMOND)
                        ), 14, false
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART)
                        ), 21, false
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.HEART),
                                new Card(CardNumber.KING, CardPattern.HEART)
                        ), 12, false
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        ), 30, true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.TWO, CardPattern.CLOVER)
                        ), 22, true
                )
        );
    }

}

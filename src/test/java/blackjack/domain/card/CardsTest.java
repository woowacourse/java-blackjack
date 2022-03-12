package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("카드의 합계가 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 기댓값 : {1}, 카드 : {0}")
    @MethodSource("provideForParameterizedTest")
    void calculateScoreTest(final List<Card> initializedCards, final int expectedScore, final boolean expectedBusted) {
        final Cards cards = new Cards();
        initializedCards.forEach(cards::addCard);

        final int actualScore = cards.calculateScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("카드의 합계가 21을 넘으면 버스트된다.")
    @ParameterizedTest(name = "[{index}] 기댓값 : {2}, 카드 : {0}")
    @MethodSource("provideForParameterizedTest")
    void isBustTest(final List<Card> initializedCards, final int expectedScore, final boolean expectedBust) {
        final Cards cards = new Cards();
        initializedCards.forEach(cards::addCard);

        final boolean actualBust = cards.isBust();
        assertThat(actualBust).isEqualTo(expectedBust);
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

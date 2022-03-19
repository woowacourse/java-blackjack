package blackjack.domain.card;

import static blackjack.Fixture.CLOVER_ACE;
import static blackjack.Fixture.CLOVER_TWO;
import static blackjack.Fixture.DIAMOND_KING;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.HEART_KING;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardHandsTest {

    @DisplayName("카드의 합계는 기댓값과 같아야 한다.")
    @ParameterizedTest(name = "[{index}] 기댓값 : {1}, 카드 : {0}")
    @MethodSource("provideForParameterizedTest")
    void calculateScoreTest(final List<Card> initializedCards, final int expectedScore) {
        final CardHands cards = new CardHands();
        initializedCards.forEach(cards::addCard);

        final int actualScore = cards.calculateScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideForParameterizedTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                HEART_ACE,
                                CLOVER_ACE,
                                DIAMOND_KING
                        ), 14
                ),
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                HEART_KING
                        ), 21
                ),
                Arguments.of(
                        List.of(
                                SPADE_ACE,
                                HEART_ACE,
                                HEART_KING
                        ), 12
                ),
                Arguments.of(
                        List.of(
                                SPADE_KING,
                                HEART_KING,
                                DIAMOND_KING
                        ), 30
                ),
                Arguments.of(
                        List.of(
                                SPADE_KING,
                                HEART_KING,
                                CLOVER_TWO
                        ), 22
                )
        );
    }

}

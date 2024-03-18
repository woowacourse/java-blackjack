package model.card;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.NINE;
import static model.card.CardNumber.QUEEN;
import static model.card.CardNumber.THREE;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("카드 합이 21 또는 21에 가깝게 스코어를 계산한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedScore")
    void testCalculateScore(List<Card> cardValues, int expectedScore) {
        Cards cards = new Cards(cardValues);
        assertThat(cards.score()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideCardsAndExpectedScore() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), 21
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), 13
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)), 22
            )
        );
    }

    @DisplayName("블랙잭이면 isBlackjack true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedIsBlackjack")
    void testIsBlackjack(List<Card> cardValues, boolean expected) {
        Cards cards = new Cards(cardValues);
        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedIsBlackjack() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), true
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), false
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)),
                false
            )
        );
    }

    @DisplayName("21점을 넘기면 isBurst true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedIsBurst")
    void testIsBurst(List<Card> cardValues, boolean expected) {
        Cards cards = new Cards(cardValues);
        assertThat(cards.isBurst()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedIsBurst() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), false
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), false
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)), true
            )
        );
    }
}

package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setup() {
        hand = new Hand();
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("카드의 합")
    void sum(List<Card> cards, int expected) {
        for (Card card : cards) {
            hand.add(card);
        }
        assertThat(hand.getSum()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.FIVE)),
                        15),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.FIVE),
                                new Card(Shape.HEART, Symbol.EIGHT)),
                        23),
                Arguments.of(
                        List.of(
                                new Card(Shape.CLOVER, Symbol.ACE),
                                new Card(Shape.SPADE, Symbol.ACE),
                                new Card(Shape.HEART, Symbol.ACE),
                                new Card(Shape.CLOVER, Symbol.FIVE)),
                        18)
        );
    }
}

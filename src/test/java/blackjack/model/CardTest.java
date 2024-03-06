package blackjack.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {
    @ParameterizedTest
    @MethodSource("provideDenominationAndSuit")
    @DisplayName("카드는 끗수와 슈트의 조합이다")
    void generateCardTest(Denomination denomination, Suit suit) {
        Card card = new Card() {
            @Override
            protected Denomination generateDenomination() {
                return denomination;
            }

            @Override
            protected Suit generateSuit() {
                return suit;
            }
        };

        assertThat(card.getDenomination()).isEqualTo(denomination);
        assertThat(card.getSuit()).isEqualTo(suit);
    }

    private static Stream<Arguments> provideDenominationAndSuit() {
        return Stream.of(
                Arguments.arguments(Denomination.TWO, Suit.SPADE),
                Arguments.arguments(Denomination.THREE, Suit.SPADE),
                Arguments.arguments(Denomination.QUEEN, Suit.HEART)
        );
    }
}

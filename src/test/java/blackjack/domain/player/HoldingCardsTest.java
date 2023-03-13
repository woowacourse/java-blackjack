package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardFixture;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HoldingCardsTest {
    private HoldingCards holdingCards;

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_FIVE), 15),
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_FIVE, CardFixture.HEART_EIGHT), 23),
                Arguments.of(List.of(CardFixture.CLOVER_ACE, CardFixture.SPADE_ACE, CardFixture.HEART_ACE,
                        CardFixture.CLOVER_FIVE), 18)
        );
    }

    @BeforeEach
    void setup() {
        holdingCards = new HoldingCards();
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("카드의 합")
    void sum(List<Card> cards, int expected) {
        for (Card card : cards) {
            holdingCards.add(card);
        }
        assertThat(holdingCards.getSum()).isEqualTo(expected);
    }
}

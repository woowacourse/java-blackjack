package domain.blackjack;

import static domain.card.CardName.ACE;
import static domain.card.CardName.FIVE;
import static domain.card.CardName.JACK;
import static domain.card.CardName.QUEEN;
import static domain.card.CardName.SIX;
import static domain.card.CardType.HEART;

import domain.card.Card;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerCardDrawStrategyTest {
    private static final String DEALER_NAME = "딜러";

    public static Stream<Arguments> canDrawParameters() {
        return Stream.of(
                Arguments.of(HoldingCards.of(new Card(ACE, HEART), new Card(SIX, HEART)), false),
                Arguments.of(HoldingCards.of(new Card(ACE, HEART), new Card(FIVE, HEART)), true),
                Arguments.of(HoldingCards.of(new Card(JACK, HEART), new Card(QUEEN, HEART)), false)
        );
    }

    @ParameterizedTest
    @MethodSource("canDrawParameters")
    @DisplayName("딜러의 드로우 여부가 제대로 판단되는지 검증")
    void canDraw(HoldingCards holdingCards, boolean expected) {
        Gamer dealer = new Gamer(DEALER_NAME, holdingCards);
        Assertions.assertThat(new DealerRandomCardDrawStrategy(dealer).canDraw())
                .isEqualTo(expected);
    }
}

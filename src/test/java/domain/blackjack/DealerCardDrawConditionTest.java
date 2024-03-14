package domain.blackjack;


import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.FIVE_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.SEVEN_HEART;
import static domain.card.TestCards.SIX_HEART;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerCardDrawConditionTest {

    public static Stream<Arguments> canDrawParameters() {
        return Stream.of(
                Arguments.of(HoldingCards.of(ACE_HEART, SIX_HEART), false),
                Arguments.of(HoldingCards.of(ACE_HEART, FIVE_HEART), true),
                Arguments.of(HoldingCards.of(JACK_HEART, SEVEN_HEART), false),
                Arguments.of(HoldingCards.of(JACK_HEART, SIX_HEART), true)
        );
    }

    @ParameterizedTest
    @MethodSource("canDrawParameters")
    @DisplayName("딜러의 드로우 여부가 제대로 판단되는지 검증")
    void canDraw(HoldingCards holdingCards, boolean expected) {
        BlackJackGameMachine dealerMachine = new BlackJackGameMachine(holdingCards);
        Assertions.assertThat(new DealerCardDrawCondition(dealerMachine).canDraw())
                .isEqualTo(expected);
    }
}

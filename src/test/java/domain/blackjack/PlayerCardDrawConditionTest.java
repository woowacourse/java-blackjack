package domain.blackjack;


import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.TWO_HEART;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerCardDrawConditionTest {

    public static Stream<Arguments> canDrawParameters() {
        return Stream.of(
                Arguments.of(HoldingCards.of(ACE_HEART, JACK_HEART), true),
                Arguments.of(HoldingCards.of(TWO_HEART, JACK_HEART, QUEEN_HEART), false),
                Arguments.of(HoldingCards.of(JACK_HEART, QUEEN_HEART), true)
        );
    }

    @ParameterizedTest
    @MethodSource("canDrawParameters")
    @DisplayName("플레이어의 드로우 여부가 제대로 판단되는지 검증")
    void canDraw(HoldingCards holdingCards, boolean expected) {
        BlackJackGameMachine player = new BlackJackGameMachine(holdingCards);
        Assertions.assertThat(new PlayerCardDrawCondition(player).canDraw())
                .isEqualTo(expected);
    }
}

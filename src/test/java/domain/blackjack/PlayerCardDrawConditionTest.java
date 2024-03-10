package domain.blackjack;

import static domain.card.CardName.ACE;
import static domain.card.CardName.JACK;
import static domain.card.CardName.QUEEN;
import static domain.card.CardName.TWO;
import static domain.card.CardType.HEART;

import domain.card.Card;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerCardDrawConditionTest {

    public static Stream<Arguments> canDrawParameters() {
        return Stream.of(
                Arguments.of(HoldingCards.of(new Card(ACE, HEART), new Card(JACK, HEART)), true),
                Arguments.of(HoldingCards.of(new Card(TWO, HEART), new Card(JACK, HEART), new Card(QUEEN, HEART)),
                        false),
                Arguments.of(HoldingCards.of(new Card(JACK, HEART), new Card(QUEEN, HEART)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("canDrawParameters")
    @DisplayName("플레이어의 드로우 여부가 제대로 판단되는지 검증")
    void canDraw(HoldingCards holdingCards, boolean expected) {
        Gamer player = new Gamer("플레이어", holdingCards);
        Assertions.assertThat(new PlayerCardDrawCondition(player).canDraw())
                .isEqualTo(expected);
    }
}

package domain;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerCardDrawStrategyTest {

    public static Stream<Arguments> canDrawParameters() {
        return Stream.of(
                Arguments.of(
                        HoldingCards.of(new Card(CardName.ACE, CardType.HEART), new Card(CardName.SIX, CardType.HEART)),
                        false),
                Arguments.of(
                        HoldingCards.of(new Card(CardName.ACE, CardType.HEART),
                                new Card(CardName.FIVE, CardType.HEART)),
                        true),
                Arguments.of(
                        HoldingCards.of(new Card(CardName.JACK, CardType.HEART),
                                new Card(CardName.QUEEN, CardType.HEART)),
                        false)
        );
    }

    @ParameterizedTest
    @MethodSource("canDrawParameters")
    @DisplayName("딜러의 드로우 여부가 제대로 판단되는지 검증")
    void canDraw(HoldingCards holdingCards, boolean expected) {
        Gamer dealer = new Gamer("딜러", holdingCards);
        Assertions.assertThat(new DealerRandomCardDrawStrategy(dealer).canDraw())
                .isEqualTo(expected);
    }
}

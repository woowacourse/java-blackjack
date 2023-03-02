package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("가진 카드의 합이 16 초과인지 확인한다")
    void checking_sum_is_over_16(List<Card> cards, boolean expected) {
        Player player = new Dealer();
        for (Card card : cards) {
            player.pick(card);
        }

        assertThat(player.canPick()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.FOUR)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.SEVEN)),
                        false)
        );
    }
}

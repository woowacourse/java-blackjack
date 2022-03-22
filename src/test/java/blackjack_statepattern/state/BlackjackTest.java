package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;
import blackjack_statepattern.card.Denomination;
import blackjack_statepattern.card.Suit;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackTest {

    @Test
    @DisplayName("블랙잭인 상태에서 카드를 받지 못한다.")
    void blackjackDraw() {
        Blackjack blackjack = new Blackjack(new Cards(SPADES_ACE, SPADES_JACK));
        assertThatThrownBy(
                () -> blackjack.draw(Card.of(Suit.SPADES, Denomination.TEN))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("블랙잭일 때 딜러가 블랙잭이면 수익은 없고, 딜러가 블랙잭이 아니면 1.5배 수익이다.")
    @MethodSource("blackjackProvider")
    void profit(State state, double earningRate) {
        Blackjack blackjack = new Blackjack(new Cards(SPADES_JACK, SPADES_ACE));
        int money = 1000;
        double profit = blackjack.profit(state, money);
        Assertions.assertThat(profit).isEqualTo(money * earningRate);
    }

    public static Stream<Arguments> blackjackProvider() {
        return Stream.of(
                Arguments.arguments(new Blackjack(new Cards(SPADES_ACE, SPADES_JACK)), 0),
                Arguments.arguments(new Bust(new Cards(SPADES_ACE, SPADES_TEN, SPADES_TWO)), 1.5)
        );
    }
}

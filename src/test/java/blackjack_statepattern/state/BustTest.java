package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack_statepattern.card.Cards;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BustTest {

    @Test
    @DisplayName("버스트인 상태에서 카드를 받지 못한다.")
    void bustDraw() {
        Bust bust = new Bust(new Cards(SPADES_JACK, SPADES_TWO, SPADES_TEN));
        assertThatThrownBy(
                () -> bust.draw(SPADES_ACE)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("버스트면 플레이어는 항상 돈을 잃는다.")
    @MethodSource("bustCardProvider")
    void profit(State state, double earningRate) {
        Bust bust = new Bust(new Cards(SPADES_JACK, SPADES_TEN, SPADES_TWO));
        int money = 1000;
        double profit = bust.profit(state, money);
        assertThat(profit).isEqualTo(money * earningRate);
    }

    public static Stream<Arguments> bustCardProvider() {
        return Stream.of(
                Arguments.arguments(new Blackjack(new Cards(SPADES_JACK, SPADES_ACE)), -1),
                Arguments.arguments(new Stay(new Cards(SPADES_JACK, SPADES_TEN)), -1),
                Arguments.arguments(new Bust(new Cards(SPADES_JACK, SPADES_TEN, SPADES_TWO)), -1)

        );
    }
}

package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack_statepattern.card.Cards;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StayTest {

    @Test
    @DisplayName("스테이 상태에서 스테이 할 수 없다.")
    void stay() {
        State state = new Stay(new Cards(SPADES_ACE, SPADES_TWO));

        assertThrows(IllegalArgumentException.class, state::stay);
    }

    @Test
    @DisplayName("스테이 상태에서 드로우 할 수 없다.")
    void draw() {
        State state = new Stay(new Cards(SPADES_ACE, SPADES_TWO));
        assertThrows(IllegalArgumentException.class, () -> state.draw(SPADES_JACK));
    }

    @ParameterizedTest
    @DisplayName("스테이 상태에서는 딜러가 블랙잭이거나 플레이어보다 점수가 높을 경우 돈을 잃고, 플레이어가 딜러보다 점수가 높으면 돈을 얻는다.")
    @MethodSource("stayCardProvider")
    void profit(Cards cards, double earningRate) {
        Stay stay = new Stay(new Cards(SPADES_JACK, SPADES_TEN));
        int money = 1000;
        double profit = stay.profit(cards, money);
        assertThat(profit).isEqualTo(money * earningRate);
    }

    public static Stream<Arguments> stayCardProvider() {
        return Stream.of(
                Arguments.arguments(new Cards(SPADES_JACK, SPADES_ACE), -1),
                Arguments.arguments(new Cards(SPADES_JACK, SPADES_TEN), 0),
                Arguments.arguments(new Cards(SPADES_TEN, SPADES_TWO), 1)

        );
    }
}

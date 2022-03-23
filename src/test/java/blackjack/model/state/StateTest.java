package blackjack.model.state;

import static blackjack.model.state.CardFixture.CLOVER_ACE;
import static blackjack.model.state.CardFixture.CLOVER_EIGHT;
import static blackjack.model.state.CardFixture.CLOVER_JACK;
import static blackjack.model.state.CardFixture.CLOVER_KING;
import static blackjack.model.state.CardFixture.CLOVER_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.state.finished.Blackjack;
import blackjack.model.state.running.Playing;
import blackjack.model.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

    @DisplayName("카드를 한장만 가지고 있으면 Ready이다.")
    @Test
    void ready() {
        State state = new Ready().add(CLOVER_EIGHT);

        assertThat(state).isInstanceOf(Ready.class);
    }

    @DisplayName("카드를 두장 가지고 있을때 카드 숫자 합이 21이 아니면 Hit이다.")
    @Test
    void hit() {
        State state = new Ready().add(CLOVER_EIGHT).add(CLOVER_TWO);

        assertThat(state).isInstanceOf(Playing.class);
    }

    @DisplayName("카드를 두장 가지고 있을때 카드 숫자 합이 21이면 Blackjack이다.")
    @Test
    void blackjack() {
        State state = new Ready().add(CLOVER_JACK).add(CLOVER_ACE);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("Blackjack일때 카드를 추가하면 예외를 발생한다.")
    @Test
    void blackjack_add_error() {
        State state = new Ready().add(CLOVER_JACK).add(CLOVER_ACE);

        assertThatThrownBy(() -> state.add(CLOVER_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 추가 할 수 없습니다.");
    }

    @DisplayName("Stay일때 카드를 추가하면 예외를 발생한다.")
    @Test
    void stay_add_error() {
        State state = new Ready().add(CLOVER_JACK).add(CLOVER_KING).add(CLOVER_ACE);

        assertThatThrownBy(() -> state.add(CLOVER_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 추가 할 수 없습니다.");
    }

    @DisplayName("Bust일때 카드를 추가하면 예외를 발생한다.")
    @Test
    void bust_add_error() {
        State state = new Ready().add(CLOVER_JACK).add(CLOVER_EIGHT).add(CLOVER_KING);

        assertThatThrownBy(() -> state.add(CLOVER_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 추가 할 수 없습니다.");
    }
}

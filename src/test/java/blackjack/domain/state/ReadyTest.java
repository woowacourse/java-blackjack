package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    @Test
    @DisplayName("ready에서 한번 draw하면 ready가 생성된다.")
    void ready() {
        State state = new Ready()
                .draw(SPADE_ACE);

        assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("ready에서 stay를 하면 에러가 발생한다.")
    void stay() {
        State state = new Ready();

        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready에서 한번 draw하고 stay하면 에러가 발생한다.")
    void ready_stay() {
        State state = new Ready()
                .draw(SPADE_ACE);

        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready에서 두번 draw하고 카드 합이 21이 되면 blackjack이 된다.")
    void ready_blackjack() {
        State state = new Ready()
                .draw(SPADE_ACE)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Blackjack.class);
    }
}

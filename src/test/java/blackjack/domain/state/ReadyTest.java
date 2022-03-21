package blackjack.domain.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    @Test
    @DisplayName("Ready 상태에서 한장만 입력했을 경우")
    void ready() {
        final State state = new Ready().draw(SPADE_JACK);

        assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("Ready 상태에서 두장 입력 결과가 Hit일 경우")
    void hit() {
        final State state = new Ready().draw(SPADE_TWO)
            .draw(SPADE_JACK);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Ready 상태에서 두장 입력 결과가 Hit일 경우 - 추가 draw 결과 Hit")
    void hitAndHit() {
        final State state = new Ready().draw(SPADE_TWO)
            .draw(SPADE_JACK)
            .draw(SPADE_ACE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Ready 상태에서 2장 입력 결과가 Hit일 경우 - 추가 draw 결과 Bust")
    void hitAndBust() {
        final State state = new Ready().draw(SPADE_TWO)
            .draw(SPADE_JACK)
            .draw(SPADE_JACK);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Bust 상태에서 추가 draw 시 예외 발생")
    void bustDraw() {
        final State state = new Ready().draw(SPADE_TWO)
            .draw(SPADE_JACK)
            .draw(SPADE_JACK);

        assertThatThrownBy(() -> state.draw(SPADE_TWO))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Ready 상태에서 2장 입력 결과가 BlackJack인 경우")
    void blackJack() {
        final State state = new Ready().draw(SPADE_ACE)
            .draw(SPADE_JACK);

        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("BlackJack에서 draw 시 예외 발생")
    void blackJackDraw() {
        final State state = new Ready().draw(SPADE_ACE)
            .draw(SPADE_JACK);

        assertThatThrownBy(() -> state.draw(SPADE_JACK))
            .isInstanceOf(IllegalStateException.class);
    }
}

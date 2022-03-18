package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 한 번 실행하여 Ready 상태가 되는 것을 확인한다.")
    @Test
    void draw_ready() {
        Ready ready = new Ready();
        ready.bet(betting);

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Ready.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        Ready ready = new Ready();
        ready.bet(betting);

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))
                .draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Blackjack 상태가 되는 것을 확인한다.")
    @Test
    void draw_blackjack() {
        Ready ready = new Ready();
        ready.bet(betting);

        assertThat(ready.draw(Card.of(Denomination.ACE, Suit.SPADE))
                .draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Blackjack.class);
    }

    @DisplayName("draw 시 베팅 금액을 입력하지 않았을 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Ready ready = new Ready();

        assertThatThrownBy(() -> ready.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("베팅 금액을 입력 해야 합니다.");
    }

    @DisplayName("stay 를 실행하여 Stay 상태가 되는 것을 확인한다.")
    @Test
    void stay() {
        Ready ready = new Ready();
        ready.bet(betting);

        State state = ready.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("stay 시 베팅 금액을 입력하지 않았을 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Ready ready = new Ready();

        assertThatThrownBy(ready::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("베팅 금액을 입력 해야 합니다.");
    }
}

package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @DisplayName("베팅 금액에 숫자가 아닌 값을 입력했을 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void betting_not_number() {
        Ready ready = new Ready();

        assertThatThrownBy(() -> ready.bet("asdf"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자를 입력해주세요.");
    }

    @DisplayName("draw 를 한 번 실행하여 Ready 상태가 되는 것을 확인한다.")
    @Test
    void draw_ready() {
        Ready ready = new Ready();
        ready.bet("1000");

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Ready.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        Ready ready = new Ready();
        ready.bet("1000");

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))
                .draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Blackjack 상태가 되는 것을 확인한다.")
    @Test
    void draw_blackjack() {
        Ready ready = new Ready();
        ready.bet("1000");

        assertThat(ready.draw(Card.of(Denomination.ACE, Suit.SPADE))
                .draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(Blackjack.class);
    }

    @DisplayName("stay 를 실행하여 Stay 상태가 되는 것을 확인한다.")
    @Test
    void stay() {
        Ready ready = new Ready();
        ready.bet("1000");

        State state = ready.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Ready ready = new Ready();

        assertThat(ready.isRunning()).isTrue();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Ready ready = new Ready();

        assertThat(ready.isFinished()).isFalse();
    }

    @DisplayName("수익률을 변경할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void decide_rate() {
        Ready ready = new Ready();

        assertThatThrownBy(() -> ready.decideRate(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 변경할 수 없습니다.");
    }

    @DisplayName("수익률 계산 시 예외가 발생하는 것을 확인한다.")
    @Test
    void earning_exception() {
        Ready ready = new Ready();

        assertThatThrownBy(ready::getEarning)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익을 계산할 수 없습니다.");
    }

    @DisplayName("카드를 추가하지 않은 상태에서 카드 총합을 확인한다.")
    @Test
    void card_total_zero() {
        Ready ready = new Ready();

        assertThat(ready.cardTotal()).isEqualTo(0);
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        Ready ready = new Ready();
        ready.bet("1000");

        ready.draw(Card.of(Denomination.KING, Suit.SPADE));
        ready.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(ready.cardTotal()).isEqualTo(20);
    }
}

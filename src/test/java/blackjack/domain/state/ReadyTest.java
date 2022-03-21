package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.Fixture.ACE;
import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @DisplayName("draw 를 한 번 실행하여 Ready 상태가 되는 것을 확인한다.")
    @Test
    void draw_ready() {
        Ready ready = new Ready();

        assertThat(ready.draw(TEN))
                .isInstanceOf(Ready.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        Ready ready = new Ready();

        assertThat(ready.draw(TEN)
                .draw(TEN))
                .isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Blackjack 상태가 되는 것을 확인한다.")
    @Test
    void draw_blackjack() {
        Ready ready = new Ready();

        assertThat(ready.draw(ACE)
                .draw(TEN))
                .isInstanceOf(Blackjack.class);
    }

    @DisplayName("stay 를 실행하여 Stay 상태가 되는 것을 확인한다.")
    @Test
    void stay() {
        Ready ready = new Ready();

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

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        Ready ready = new Ready();

        assertThat(ready.isBlackjack()).isFalse();
    }

    @DisplayName("버스트인지 확인한다.")
    @Test
    void is_bust() {
        Ready ready = new Ready();

        assertThat(ready.isBust()).isFalse();
    }

    @DisplayName("수익률을 구할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void get_earning_rate_exception() {
        Ready ready = new Ready();

        assertThatThrownBy(ready::getEarningRate)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 구할 수 없습니다.");
    }

    @DisplayName("수익률을 변경할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void decide_rate_exception() {
        Ready ready = new Ready();

        assertThatThrownBy(() -> ready.decideRate(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 변경할 수 없습니다.");
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

        ready.draw(TEN);
        ready.draw(TEN);

        assertThat(ready.cardTotal()).isEqualTo(20);
    }
}

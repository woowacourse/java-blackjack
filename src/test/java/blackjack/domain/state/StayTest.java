package blackjack.domain.state;

import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.KING_SPACE;
import static blackjack.domain.CardFixtures.THREE_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    @DisplayName("스테이 상태에서 추가적인 카드 뽑기는 불가능하다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 스테이_카드_뽑기_예외() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        assertThatThrownBy(() -> stay.draw(TWO_SPACE))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("스테이 상태에서 stay할 수 없다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 스테이_스테이_불가능() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        assertThatThrownBy(() -> stay.stay())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("스테이 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다.")
    @Test
    void 스테이_준비상태() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        boolean result = stay.isRunning();

        assertThat(result).isFalse();
    }

    @DisplayName("스테이 상태의 끝난 상태 유무는 true이다.")
    @Test
    void 스테이_끝난상태() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        boolean result = stay.isFinished();

        assertThat(result).isTrue();
    }

    @DisplayName("상대 상태가 버스트인 경우 승이므로 earning rate는 1이다.")
    @Test
    void 스테이_상대_버스트() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        State bust = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .draw(THREE_SPACE);

        double result = stay.earningRate(bust);

        assertThat(result).isEqualTo(1);
    }

    @DisplayName("상대 상태가 버스트가 아닐 때 본인의 점수가 더 크면 승이므로 earning rate는 1이다.")
    @Test
    void 스테이_승() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        State otherStay = new Ready()
                .draw(KING_SPACE)
                .draw(THREE_SPACE)
                .stay();

        double result = stay.earningRate(otherStay);

        assertThat(result).isEqualTo(1);
    }

    @DisplayName("상대 상태가 버스트가 아닐 때 점수가 같으면 무승부이므로 earning rate는 0이다.")
    @Test
    void 스테이_무승부() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        State otherStay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        double result = stay.earningRate(otherStay);

        assertThat(result).isZero();
    }

    @DisplayName("상대 상태가 버스트가 아닐 때 점수가 더 낮으면 패이므로 earning rate는 -1이다.")
    @Test
    void 스테이_패() {
        State stay = new Ready()
                .draw(KING_SPACE)
                .draw(THREE_SPACE)
                .stay();

        State otherStay = new Ready()
                .draw(KING_SPACE)
                .draw(JACK_SPACE)
                .stay();

        double result = stay.earningRate(otherStay);

        assertThat(result).isEqualTo(-1);
    }
}
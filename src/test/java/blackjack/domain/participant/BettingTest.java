package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @DisplayName("0 으로 베팅을 생성한다")
    @Test
    void testCreate1() {
        assertThatCode(() -> new Betting(0)).doesNotThrowAnyException();
    }

    @DisplayName("음수로 베팅을 생성하면 예외가 발생한다")
    @Test
    void testCreate2() {
        assertThatCode(() -> new Betting(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅할 금액은 0 보다 커야 합니다.");
    }


}

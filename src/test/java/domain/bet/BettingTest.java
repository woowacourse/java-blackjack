package domain.bet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("new(): 1000원 단위가 아니라면 예외를 반환한다.")
    void amount() {
        assertThatThrownBy(() -> new Betting(1001))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    @DisplayName("배팅 금액이 음수이면 예외가 발생한다")
    @Test
    void exception_negative() {
        assertThatThrownBy(() -> Bet.from(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 음수일 수 없습니다.");
    }

    @DisplayName("배팅 금액의 단위가 1000이 아니면 예외가 발생한다")
    @Test
    void exception_unit() {
        assertThatThrownBy(() -> Bet.from(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1000원 단위여야 합니다.");
    }
}

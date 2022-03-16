package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("배팅 금액이 음수이면 예외가 발생한다")
    @Test
    void exception_negative() {
        assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 음수일 수 없습니다.");
    }

    @DisplayName("배팅 금액의 단위가 1000이 아니면 예외가 발생한다")
    @Test
    void exception_unit() {
        assertThatThrownBy(() -> new Money(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 1000원 단위여야 합니다.");
    }
}

package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountTest {
    @Test
    @DisplayName("베팅 금액은 최소 1000이다")
    void invalidBetAmountTest() {
        assertThatThrownBy(() -> new BetAmount(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 최소 1000 이상이여야 합니다: 500");
    }
}

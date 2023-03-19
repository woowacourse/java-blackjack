package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class BettingAmountTest {
    @DisplayName("음수인 경우 검증")
    @Test
    void 음수인_경우() {
        assertThatThrownBy(() -> new BettingAmount(-10000));
    }

    @DisplayName("0인 경우 검증")
    @Test
    void ZERO인_경우() {
        assertDoesNotThrow(() -> new BettingAmount(0));
    }

    @DisplayName("자연수인 경우 검증")
    @Test
    void 자연수인_경우() {
        assertDoesNotThrow(() -> new BettingAmount(10000));
    }
}

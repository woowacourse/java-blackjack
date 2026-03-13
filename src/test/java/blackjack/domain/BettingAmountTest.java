package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingAmountTest {

    @Test
    @DisplayName("배팅 금액이 양수인지 검증")
    void validate_plus() {
        // given
        BigDecimal input = new BigDecimal("-10000");

        // when & then
        assertThatThrownBy(() -> new BettingAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }

    @Test
    @DisplayName("배팅 금액이 0원인지 검증")
    void validate_zero() {
        // given
        BigDecimal input = BigDecimal.ZERO;

        // when & then
        assertThatThrownBy(() -> new BettingAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0");
    }
}

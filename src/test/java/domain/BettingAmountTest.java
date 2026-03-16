package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    private final BigDecimal amount = new BigDecimal("10000");

    @Test
    @DisplayName("배팅 금액 1.5배 증가")
    void bettingAmount_one_and_half_increase() {
        BettingAmount bettingAmount = BettingAmount.of(amount);

        bettingAmount.applyBlackjackBonus();

        assertThat(bettingAmount.value()).isEqualTo(new BigDecimal("15000"));
    }

    @Test
    @DisplayName("배팅 금액 잃음 (현 배팅 금액에서 음수로)")
    void bettingAmount_lose() {
        BettingAmount bettingAmount = BettingAmount.of(amount);

        bettingAmount.applyLoseAmount();

        assertThat(bettingAmount.value()).isEqualTo(new BigDecimal(-10_000));
    }

}
package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    private final BigDecimal amount = new BigDecimal(10_000);

    @Test
    @DisplayName("배팅 금액 1.5배 증가")
    void bettingAmount_one_and_half_increase() {
        BettingAmount bettingAmount = BettingAmount.of(amount);

        bettingAmount.applyBlackjackBonus();

        assertThat(bettingAmount.value().intValueExact()).isEqualTo(15_000);
    }

    @Test
    @DisplayName("배팅 금액 잃음 (현 배팅 금액에서 음수로)")
    void bettingAmount_lose() {
        BettingAmount bettingAmount = BettingAmount.of(amount);

        bettingAmount.applyLoseAmount();

        assertThat(bettingAmount.value().intValueExact()).isEqualTo(-10_000);
    }

    @Test
    @DisplayName("무승부 시 배팅 금액 0으로 조정")
    void bettingAmount_draw_isZero() {
        BettingAmount bettingAmount = BettingAmount.of(amount);

        bettingAmount.applyDrawAmount();

        assertThat(bettingAmount.value()).isZero();
    }


}
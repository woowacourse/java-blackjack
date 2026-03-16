package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    @DisplayName("배팅 금액 1.5배 증가")
    void bettingAmount_one_and_half_increase() {
        BettingAmount bettingAmount = BettingAmount.of(10000);

        bettingAmount.applyBlackjackBonus();

        assertThat(bettingAmount.value()).isEqualTo(15000);
    }

}
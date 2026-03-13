package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("양수 수익의 getAmount는 해당 값을 반환한다")
    void getAmount_returnsPositiveValue() {
        // given & when
        Profit profit = new Profit(10000);

        // then
        assertThat(profit.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("음수 수익의 getAmount는 음수 값을 반환한다")
    void getAmount_returnsNegativeValue() {
        // given & when
        Profit profit = new Profit(-10000);

        // then
        assertThat(profit.getAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("negate는 수익의 부호를 반전한다")
    void negate_returnsNegatedProfit() {
        // given
        Profit profit = new Profit(10000);

        // when
        Profit negated = profit.negate();

        // then
        assertThat(negated.getAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("0 수익을 negate하면 0이다")
    void negate_returnsZero_whenAmountIsZero() {
        // given
        Profit profit = new Profit(0);

        // when
        Profit negated = profit.negate();

        // then
        assertThat(negated.getAmount()).isZero();
    }
}

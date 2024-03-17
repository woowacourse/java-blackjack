package domain.manager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("전달된 값을 곱하여 수익을 계산 후 반환한다.")
    @Test
    void calculateProfitByMultiplication() {
        Profit profit = new Profit(10);
        assertThat(profit.multiply(1.5).getValue()).isEqualTo(15);
    }
}

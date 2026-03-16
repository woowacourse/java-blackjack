package domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @DisplayName("금액이 같으면 같은 객체로 취급한다 (VO)")
    @Test
    void equals_SameAmount_ReturnTrue() {
        assertThat(new Money(10_000)).isEqualTo(new Money(10_000));
    }

    @DisplayName("베팅 금액이 0원 이하이면 예외가 발생한다")
    @Test
    void constructor_UnderZero_ThrowException() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수익률을 곱해 최종 수익금을 계산한다")
    @Test
    void multiply_EarningRate_ReturnProfit() {
        Money bet = new Money(10_000);
        double earningRate = 1.5;

        int profit = bet.calculateProfit(earningRate);

        assertThat(profit).isEqualTo(15_000);
    }
}

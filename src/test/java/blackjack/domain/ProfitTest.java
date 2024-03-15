package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("숫자를 통해서 수익을 생성한다.")
    void Profit_Instance_create_with_Integer() {
        assertThatCode(() -> {
            new Profit(10000);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("가지고 있는 수익에서 다른 사람의 수익만큼 빼서 새로운 수익을 생성한다.")
    void Profit_Subtract_with_other_profit() {
        Profit profit = new Profit(10000);
        var sut = new Profit(0);

        var result = sut.subtractProfit(profit);

        assertThat(result).isEqualTo(new Profit(-10000));
    }
}

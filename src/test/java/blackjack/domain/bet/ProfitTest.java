package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("수익 금액 테스트")
class ProfitTest {

    @DisplayName("수익에 다른 수익을 더할 수 있다")
    @Test
    void testAdd() {
        Profit profit1 = new Profit(1000);
        Profit profit2 = new Profit(-1000);
        assertThat(profit1.add(profit2).getValue()).isEqualTo(0);
    }

    @DisplayName("수익을 반전시킨 결과를 볼 수 있다")
    @Test
    void testInverse() {
        Profit profit = new Profit(1000);
        assertThat(profit.inverse().getValue()).isEqualTo(-1000);
    }
}

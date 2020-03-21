package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @Test
    @DisplayName("int를 통해서 생성")
    void factoryMethodFromInt() {
        Profit profit = Profit.from(10);
        assertThat(profit.getProfit()).isEqualTo(10);
    }

    @Test
    @DisplayName("double로 생성 가능")
    void factoryMethodFromDouble() {
        Profit profit = Profit.from(10d);
        assertThat(profit.getProfit()).isEqualTo(10);
    }

    @Test
    @DisplayName("minus를 하면 새로운 객체 반환")
    void minus() {
        Profit ten = Profit.from(10);
        Profit three = Profit.from(3);
        Profit result = ten.minus(three);
        assertThat(result.getProfit()).isEqualTo(7);
    }
}
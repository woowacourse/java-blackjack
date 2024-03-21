package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("수익에 값을 곱한다.")
    @Test
    void testMultiply() {
        // given
        Profit profit = new Profit(new BigDecimal(1000));

        // when
        Profit actual = profit.multiply(1.5);

        // then
        assertThat(actual).isEqualTo(new Profit(new BigDecimal(1500)));
    }

    @DisplayName("수익끼리 더한다.")
    @Test
    void testAdd() {
        // given
        Profit profit1 = new Profit(new BigDecimal(1000));
        Profit profit2 = new Profit(new BigDecimal(1000));

        // when
        Profit actual = profit1.add(profit2);

        // then
        assertThat(actual).isEqualTo(new Profit(new BigDecimal(2000)));
    }

    @DisplayName("수익의 부호를 바꾼다.")
    @Test
    void reverseSign() {
        // given
        Profit profit = new Profit(new BigDecimal(1000));

        // when
        Profit actual = profit.reverseSign();

        // then
        assertThat(actual).isEqualTo(new Profit(new BigDecimal(-1000)));
    }
}

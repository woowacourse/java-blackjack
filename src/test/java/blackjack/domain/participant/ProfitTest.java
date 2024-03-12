package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("수익")
class ProfitTest {

    @DisplayName("수익 추가에 성공한다.")
    @Test
    void earn() {
        //given
        Profit profit = Profit.initProfit();
        BigDecimal addAmount = new BigDecimal(1000);

        //when
        profit.earn(addAmount);

        //then
        assertThat(profit.toString()).isEqualTo("1000");
    }

    @DisplayName("수익 감소에 성공한다.")
    @Test
    void lose() {
        //given
        Profit profit = Profit.initProfit();
        BigDecimal loseAmount = new BigDecimal(1000);

        //when
        profit.lose(loseAmount);

        //then
        assertThat(profit.toString()).isEqualTo("-1000");
    }
}

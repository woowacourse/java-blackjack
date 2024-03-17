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

    @DisplayName("정수일 경우, 정수 부분만 출력한다.")
    @Test
    void toStringTest() {
        //given
        Profit integerProfit = Profit.initProfit();
        Profit decimalProfit = Profit.initProfit();

        BigDecimal integerAmount = new BigDecimal("1000");
        BigDecimal decimalAmount = new BigDecimal("11000");
        BigDecimal blackjackAmount = new BigDecimal("1000");

        //when
        integerProfit.earn(integerAmount);
        decimalProfit.earnBlackjack(decimalAmount, blackjackAmount);

        //then
        assertThat(integerProfit.toString()).isEqualTo("1000");
        assertThat(decimalProfit.toString()).isEqualTo("10000");
    }
}

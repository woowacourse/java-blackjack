package blackjack.model.player.matcher;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Money;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultStatusTest {

    @ParameterizedTest
    @DisplayName("이익 테스트")
    @CsvSource({"WIN,1000,1000", "LOSS,1000,-1000", "DRAW,1000,0", "BLACKJACK,1000,1500.0"})
    void profit(ResultStatus result, String amount, String expectMoney) {
        Money money = new Money(new BigDecimal(amount));
        Money profit = result.profit(money);
        Money expect = new Money(new BigDecimal(expectMoney));

        assertThat(profit.amount()).isEqualTo(expect.amount());
    }

}

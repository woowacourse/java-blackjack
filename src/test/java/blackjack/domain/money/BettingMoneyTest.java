package blackjack.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
    
    @Test
    void 배팅_금액을_곱할_수_있다() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        double factor = 1.5;
        Money profit = new Money(15000);
        
        // expected
        Assertions.assertThat(bettingMoney.multiply(factor)).isEqualTo(profit);
    }
}

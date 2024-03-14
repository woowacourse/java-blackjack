package domain.money;

import domain.participant.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    @DisplayName("딜러는 10000 만큼 수익을 얻는다.")
    @Test
    void earnProfit() {
        Dealer dealer = new Dealer();
        dealer.earn(new Money(10000));
        Assertions.assertThat(dealer.totalProfit()).isEqualTo(10000);
    }
}

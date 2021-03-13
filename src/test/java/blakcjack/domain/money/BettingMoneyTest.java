package blakcjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        assertThat(bettingMoney).isEqualTo(new BettingMoney(10000));
    }
}

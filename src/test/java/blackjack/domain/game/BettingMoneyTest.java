package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("베팅 금액을 생성한다")
    void createBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        assertThat(bettingMoney.getValue()).isEqualTo(10000);
    }
}

package blackjack.domain;

import blackjack.domain.participant.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingMoneyTest {

    @Test
    @DisplayName("베팅값을 추가")
    void betMoney() {
        BettingMoney bettingMoney = new BettingMoney();
        bettingMoney.betMoney(1000);

        assertThat(bettingMoney.getValue()).isEqualTo(1000);
    }
}

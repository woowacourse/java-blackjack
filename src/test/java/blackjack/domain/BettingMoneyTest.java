package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("배팅 금액 생성 ")
    @Test
    void create() {
        BettingMoney bettingMoney = new BettingMoney(1000);

        assertThat(bettingMoney).isEqualTo(new BettingMoney(1000));
    }
}
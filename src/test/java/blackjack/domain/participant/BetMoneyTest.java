package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetMoneyTest {

    @Test
    @DisplayName("베팅금액을 음수로 받았을 때 예외처리")
    void negativeBetMoney() {
        int moneyAmount = -1;
        Assertions.assertThatThrownBy(() -> new BetMoney(moneyAmount)).isInstanceOf(IllegalArgumentException.class);
    }
}

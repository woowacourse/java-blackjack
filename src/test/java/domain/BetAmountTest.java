package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @DisplayName("베팅 금액을 저장한다.")
    @Test
    void saveAmount() {
        // given
        BetAmount betAmount = new BetAmount();
        Gamer gamer = new Gamer("pobi");
        int amount = 10000;

        // when
        betAmount.saveAmount(gamer, amount);

        // then
        assertThat(betAmount.getAmount(gamer)).isEqualTo(amount);
    }
}

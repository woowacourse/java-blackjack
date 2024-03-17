package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @DisplayName("베팅 금액을 저장한다.")
    @Test
    void saveAmounts() {
        // given
        BetAmount betAmount = new BetAmount();
        Gamer gamer = new Gamer("pobi");
        int amount = 10000;

        Map<Gamer, Integer> bettingAmounts = new HashMap<>();
        bettingAmounts.put(gamer, amount);

        // when
        betAmount.save(bettingAmounts);

        // then
        assertThat(betAmount.getAmount(gamer)).isEqualTo(amount);
    }
}

package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountTest {
    @Test
    @DisplayName("베팅 금액을 입력한 배수로 곱한다.")
    void betAmountTest() {
        BetAmount betAmount = new BetAmount(10000);

        BetAmount newAmount = betAmount.times(1.5);

        assertThat(newAmount.value()).isEqualTo(15000);
    }
}

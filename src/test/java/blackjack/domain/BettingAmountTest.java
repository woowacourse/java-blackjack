package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingAmountTest {

    @Test
    @DisplayName("배팅 금액은 음수이면 안된다.")
    void constructor_throwNegative() {
        Assertions.assertThatThrownBy(() -> new BettingAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수이면 안됩니다.");
    }
}

package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @DisplayName("베팅 금액을 생성한다.")
    @Test
    void createSetMoneyAmount() {
        // given
        long money = 10_000;

        // when & then
        assertThatCode(() -> new BetAmount(money)).doesNotThrowAnyException();
    }

}

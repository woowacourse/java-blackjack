package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class BetAmountTest {

    @DisplayName("100원 부터 배팅 가능")
    @Test
    void minimumBetAmount() {
        assertThatCode(() -> new BetAmount(99)).isInstanceOf(IllegalArgumentException.class);
    }
}

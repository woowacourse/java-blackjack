package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitAmountTest {

    @DisplayName("수익이 음수일 경우 예외를 발생시킨다.")
    @Test
    void createProfitAmount() {
        //given
        int profitAmount = -1;

        //when, then
        assertThatThrownBy(() -> new ProfitAmount(profitAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

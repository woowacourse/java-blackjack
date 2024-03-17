package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @DisplayName("배팅 금액이 100원 미만일 경우 예외를 발생시킨다.")
    @Test
    void createBetAmount() {
        //given
        int betAmount = 10;

        //when, then
        assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 10원 단위가 아닐 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByOutOfUnit() {
        //given
        int betAmount = 15;

        //when, then
        assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

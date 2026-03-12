package blackjack.domain.bet;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.result.GameOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    private BetAmount betAmount;

    @BeforeEach
    void setup() {
        betAmount = new BetAmount(1000);
    }

    @Test
    @DisplayName("올바른 금액이면 Bet 객체 생성")
    void test_success_when_valid_amount() {
        assertDoesNotThrow(() -> new BetAmount(1000));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("0원 이하로는 베팅 불가")
    void test_fail_when_invalid_amount(int betAmount) { //Non-positive
        assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("블랙잭일 경우 베팅 1.5배")
    void test_blackjack_payoutRate() {

        assertThat(betAmount.calculateProfit(GameOutcome.BLACKJACK_WIN.getPayoutRate())).isEqualTo(1500);
    }

    @Test
    @DisplayName("승리할 경우 베팅 1배")
    void test_win_payoutRate() {

        assertThat(betAmount.calculateProfit(GameOutcome.WIN.getPayoutRate())).isEqualTo(1000);
    }

    @Test
    @DisplayName("무승부일 경우 베팅 0배")
    void test_draw_payoutRate() {

        assertThat(betAmount.calculateProfit(GameOutcome.DRAW.getPayoutRate())).isEqualTo(0);
    }

    @Test
    @DisplayName("패배일 경우 베팅 -1배")
    void test_lose_payoutRate() {

        assertThat(betAmount.calculateProfit(GameOutcome.LOSE.getPayoutRate())).isEqualTo(-1000);
    }
}

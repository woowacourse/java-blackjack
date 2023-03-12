package blackjackgame.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    private Bet bet;

    @BeforeEach
    void setUp() {
        bet = new Bet(10_000);
    }

    @DisplayName("베팅 금액만큼의 수익을 유지한다.")
    @Test
    void profit_win() {
        Profit profit = new Profit(bet);

        profit.keep();

        assertThat(profit.getAmount()).isEqualTo(bet.getAmount());
    }

    @DisplayName("수익이 없어진다.")
    @Test
    void profit_lose() {
        Profit profit = new Profit(bet);

        profit.lose();

        assertThat(profit.getAmount()).isEqualTo(0);
    }

    @DisplayName("베팅 금액만큼의 손해가 발생한다.")
    @Test
    void profit_becomeNegative() {
        Profit profit = new Profit(bet);

        profit.becomeNegative();

        assertThat(profit.getAmount()).isEqualTo(-bet.getAmount());
    }

    @DisplayName("베팅 금액 1.5배만큼의 수익이 발생한다.")
    @Test
    void profit_win_applyBonus() {
        Profit profit = new Profit(bet);
        double bonusPercentage = 1.5;

        profit.applyBonus(bonusPercentage);

        assertThat(profit.getAmount()).isEqualTo((int) (bet.getAmount() * bonusPercentage));
    }
}

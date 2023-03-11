package blackjackgame.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    private int betAmount;

    @BeforeEach
    void setUp() {
        betAmount = 10_000;
    }

    @DisplayName("블랙잭으로 이기면 수익을 베팅 금액의 1.5배로 계산한다.")
    @Test
    void win_blackjack() {
        assertThat(Result.WIN_BLACKJACK.calculateProfit(betAmount)).isEqualTo((int) (betAmount * 1.5));
    }

    @DisplayName("블랙잭이 아닌 점수로 이기면 수익을 베팅 금액 그대로 계산한다.")
    @Test
    void win() {
        assertThat(Result.WIN.calculateProfit(betAmount)).isEqualTo(betAmount);
    }

    @DisplayName("비기면 수익을 0원으로 계산한다.")
    @Test
    void draw() {
        assertThat(Result.DRAW.calculateProfit(betAmount)).isEqualTo(0);
    }

    @DisplayName("지면 베팅 금액만큼 잃는 것으로 계산한다.")
    @Test
    void lose() {
        assertThat(Result.LOSE.calculateProfit(betAmount)).isEqualTo(-betAmount);
    }
}

package domain.user;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountTest {
    @Test
    @DisplayName("승리 시, 베팅 금액만큼 금액을 얻는다.")
    void betAmountTimesTest() {
        BetAmount betAmount = new BetAmount(10000);

        BetAmount newAmount = betAmount.change(WIN);

        assertThat(newAmount.value()).isEqualTo(10000);
    }

    @Test
    @DisplayName("무승부 시, 0원이 된다.")
    void betAmountDrawTest() {
        BetAmount betAmount = new BetAmount(10000);

        BetAmount newAmount = betAmount.change(DRAW);

        assertThat(newAmount.value()).isEqualTo(0);
    }

    @Test
    @DisplayName("패배 시, 베팅 금액을 잃는다.")
    void betAmountLoseTest() {
        BetAmount betAmount = new BetAmount(10000);

        BetAmount newAmount = betAmount.change(LOSE);

        assertThat(newAmount.value()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("블랙잭 배수만큼 곱한다.")
    void changeByBlackJackTest() {
        BetAmount betAmount = new BetAmount(10000);

        BetAmount newAmount = betAmount.changeByBlackJack();

        assertThat(newAmount.value()).isEqualTo(15000);
    }
}

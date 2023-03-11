package blackjackgame.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import blackjackgame.domain.game.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @DisplayName("0원 이하의 금액으로 베팅하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -5000, -10000})
    void vetAmountLessThanZero(int amount) {
        assertThrows(IllegalArgumentException.class, () -> new Bet(amount));
    }

    @DisplayName("내기에서 지면 베팅한 금액만큼 수익을 잃는다.")
    @Test
    void lose() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.applyResult(Result.LOSE);

        Assertions.assertThat(bet.profit()).isEqualTo(-amount);
    }

    @DisplayName("내기에서 이기면 베팅한 금액만큼의 수익을 얻는다.")
    @Test
    void win() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.applyResult(Result.WIN);

        Assertions.assertThat(bet.profit()).isEqualTo(amount);
    }

    @DisplayName("내기에서 비기면 베팅한 금액을 돌려받아 수익이 0원이다.")
    @Test
    void draw() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.applyResult(Result.DRAW);

        Assertions.assertThat(bet.profit()).isEqualTo(0);
    }

    @DisplayName("내기에서 블랙잭으로 이기면 베팅한 금액의 1.5배만큼의 수익을 얻는다.")
    @Test
    void win_blackjack() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.applyResult(Result.WIN_BLACKJACK);

        Assertions.assertThat(bet.profit()).isEqualTo(15_000);
    }

}

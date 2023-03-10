package blackjackgame.domain.user;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("베팅한 금액만큼 수익을 잃는다.")
    @Test
    void lose() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.lose();

        Assertions.assertThat(bet.profit()).isEqualTo(-amount);
    }

    @DisplayName("베팅한 금액만큼의 수익을 얻는다.")
    @Test
    void win() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.win();

        Assertions.assertThat(bet.profit()).isEqualTo(amount);
    }

    @DisplayName("베팅한 금액을 돌려받아 수익이 0원이다.")
    @Test
    void draw() {
        int amount = 10_000;
        Bet bet = new Bet(amount);

        bet.draw();

        Assertions.assertThat(bet.profit()).isEqualTo(0);
    }

}

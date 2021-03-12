package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetMoneyTest {
    @Test
    @DisplayName("-1원으로 배팅머니를 만들면, 예외가 발생한다.")
    void negativeBetMoney() {
        assertThatThrownBy(() -> new BetMoney(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000원으로 배팅머니를 만들고 get 하면, 1000원을 가져온다.")
    void getBetMoney() {
        BetMoney betMoney = new BetMoney(1000);
        assertThat(betMoney.toInt()).isEqualTo(1000);
    }
}
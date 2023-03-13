package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BetTest {
    @Test
    @DisplayName("주어진 베팅액으로 Bet 객체를 만들 수 있다.")
    void generatingBet() {
        assertDoesNotThrow(() -> Bet.from(3000));
    }

    @Test
    @DisplayName("주어진 베팅액은 양수여야 한다.")
    void invalidNegativeBet() {
        assertThatThrownBy(() -> Bet.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 베팅액은 1,000원 단위로 입력해야 한다.")
    void invalidUnitBet() {
        assertThatThrownBy(() -> Bet.from(3500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅액이 0인 베팅액 객체를 만들 수 있다.")
    void zeroBet() {
        Bet bet = Bet.zero();
        assertThat(bet.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("주어진 배팅액을 음수로 역전한 배팅액 객체를 만들 수 있다.")
    void negativeBet() {
        Bet bet = Bet.from(3000);
        Bet negativeBet = bet.toNegative();
        assertThat(negativeBet.getValue()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("주어진 배팅액을 주어진 배수만큼 곱할 수 있다. 소수도 가능하다.")
    void multiplyBet() {
        Bet bet = Bet.from(3000);
        assertThat(bet.multiply(4).getValue()).isEqualTo(12000);
        assertThat(bet.multiply(-1).getValue()).isEqualTo(-3000);
        assertThat(bet.multiply(1.5f).getValue()).isEqualTo(4500);
    }
}
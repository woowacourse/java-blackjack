package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void negativeBet() {
        assertThatThrownBy(() -> Bet.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 베팅액은 1,000원 단위로 입력해야 한다.")
    void invalidUnitBet() {
        assertThatThrownBy(() -> Bet.from(3500))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
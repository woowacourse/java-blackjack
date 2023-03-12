package blackjackgame.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @DisplayName("0원 이하의 금액으로 베팅하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -5000, -10000})
    void vetAmountLessThanZero(int amount) {
        assertThrows(IllegalArgumentException.class, () -> new Bet(amount));
    }
}

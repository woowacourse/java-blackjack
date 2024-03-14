package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameMoneyTest {

    @DisplayName("배팅 금액은 0보다 커야한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void validateGameMoneyOverZero(int gameMoney) {
        assertThatThrownBy(() -> new GameMoney(gameMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

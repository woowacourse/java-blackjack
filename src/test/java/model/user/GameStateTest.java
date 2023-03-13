package model.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {

    @Test
    @DisplayName("계산 방식을 검사한다.")
    void calculateMoney() {
        assertAll(
                () -> assertThat(GameState.BLACKJACK.calculateMoney(10_000)).isEqualTo(15_000),
                () -> assertThat(GameState.WIN.calculateMoney(10_000)).isEqualTo(10_000),
                () -> assertThat(GameState.LOSE.calculateMoney(10_000)).isEqualTo(-10_000),
                () -> assertThat(GameState.TIE.calculateMoney(10_000)).isEqualTo(0)
        );
    }
}

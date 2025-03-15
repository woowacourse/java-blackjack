package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetSystemTest {

    @DisplayName("플레이어는 베팅을 할 수 있다.")
    @Test
    void playerBet() {
        //given
        Player player = new Player("도기");
        int betAmount = 100;

        BetSystem betSystem = new BetSystem();

        //when //then
        assertThatCode(() -> betSystem.betting(player, betAmount))
                .doesNotThrowAnyException();
    }

    @DisplayName("베팅 금액이 0원 이하인 경우 예외가 발생한다.")
    @Test
    void validateBetAmount() {
        //given
        Player player = new Player("도기");
        int betAmount = -1;

        BetSystem betSystem = new BetSystem();

        //when //then
        assertThatThrownBy(() -> betSystem.betting(player, betAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}

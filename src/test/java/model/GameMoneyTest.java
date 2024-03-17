package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameMoneyTest {

    @DisplayName("배팅 금액은 1000보다 작을 경우 예외가 발생한다.")
    @Test
    void validateGameMoneyOverZero() {
        assertThatThrownBy(() -> new GameMoney(999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 상태가 블랙잭이고 승리했을 경우, 1.5배의 수익을 얻는다.")
    @Test
    void calculateRevenueBlackJackWin() {
        int revenue = new GameMoney(1000).calculateRevenue(Outcome.WIN, BlackJackState.BLACKJACK);
        assertThat(revenue).isEqualTo(1500);
    }

    @DisplayName("승리했을 경우, 1배의 수익을 얻는다.")
    @Test
    void calculateRevenueWin() {
        int revenue = new GameMoney(1000).calculateRevenue(Outcome.WIN, BlackJackState.STAND);
        assertThat(revenue).isEqualTo(1000);
    }

    @DisplayName("패했을 경우, -1배의 수익을 얻는다.")
    @Test
    void calculateRevenueLose() {
        int revenue = new GameMoney(1000).calculateRevenue(Outcome.LOSE, BlackJackState.STAND);
        assertThat(revenue).isEqualTo(-1000);
    }

    @DisplayName("무했을 경우, 0배의 수익을 얻는다.")
    @Test
    void calculateRevenueBlackJack() {
        int revenue = new GameMoney(1000).calculateRevenue(Outcome.DRAW, BlackJackState.STAND);
        assertThat(revenue).isEqualTo(0);
    }
}

package domain.bet;

import domain.result.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BetTest {

    @DisplayName("최초의 배팅금액이 0이하이면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-100, 0, -1})
    void constructBetTest(final int money) {
        assertThatThrownBy(() -> new Bet(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 배팅금액은 0원 초과여야 합니다.");
    }

    @DisplayName("블랙잭으로 이겼을 때 배팅금액의 1.5배를 받는다.")
    @Test
    void calculateBetByOutcomeWinWithBlackJackTest() {
        Bet bet = new Bet(1000);
        int expectedMoney = (int) (bet.getMoney() * Bet.BLACKJACK_WIN_RATIO);
        bet.calculateBetByOutcome(Outcome.WIN, true);

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("블랙잭이 아닌데 이겼을 때 배팅금액을 받는다.")
    @Test
    void calculateBetByOutcomeWinWithoutBlackJackTest() {
        Bet bet = new Bet(1000);
        int expectedMoney = bet.getMoney();
        bet.calculateBetByOutcome(Outcome.WIN, false);

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("비겼을 때 배팅금액을 돌려받는다.")
    @Test
    void calculateBetByOutcomeDraw() {
        Bet bet = new Bet(1000);
        int expectedMoney = (int) (bet.getMoney() * Bet.DRAW_RATIO);
        bet.calculateBetByOutcome(Outcome.DRAW, false);

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("졌을 때 배팅금액을 잃는다.")
    @Test
    void calculateBetByOutcomeLose() {
        Bet bet = new Bet(1000);
        int expectedMoney = (int) (bet.getMoney() * Bet.LOSE_RATIO);
        bet.calculateBetByOutcome(Outcome.LOSE, false);

        assertEquals(expectedMoney, bet.getMoney());
    }
}

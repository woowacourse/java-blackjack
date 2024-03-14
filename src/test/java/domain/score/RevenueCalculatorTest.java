package domain.score;

import domain.player.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.score.RevenueCalculator.*;
import static org.assertj.core.api.Assertions.assertThat;

class RevenueCalculatorTest {

    @Test
    @DisplayName("블랙잭이라면 수익은 베팅금액의 1.5배다.")
    void calculate_BlackjackEarn_isOneAndHalfTimesOfBet() {
        Bet bet = new Bet(20000);

        Revenue revenue = BLACKJACK_EARN.calculate(bet);

        assertThat(revenue).isEqualTo(new Revenue(30000));
    }

    @Test
    @DisplayName("돈을 벌면 수익은 베팅금액의 1배다.")
    void calculate_Earn_isSameWithBet() {
        Bet bet = new Bet(20000);

        Revenue revenue = EARN.calculate(bet);

        assertThat(revenue).isEqualTo(new Revenue(20000));
    }

    @Test
    @DisplayName("잃으면 수익은 베팅금액의 -1배이다.")
    void calculate_Lose_isMinus() {
        Bet bet = new Bet(20000);

        Revenue revenue = LOSE.calculate(bet);

        assertThat(revenue).isEqualTo(new Revenue(-20000));
    }

    @Test
    @DisplayName("돌려받으면 수익은 0이다.")
    void calculate_Return_isZero() {
        Bet bet = new Bet(20000);

        Revenue revenue = RETURN.calculate(bet);

        assertThat(revenue).isEqualTo(new Revenue(0));
    }
}
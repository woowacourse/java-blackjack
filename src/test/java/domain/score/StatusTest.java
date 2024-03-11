package domain.score;

import domain.player.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @Test
    @DisplayName("블랙잭이라면 수익은 베팅의 1.5배다.")
    void calculateRevenue_BlackJack_isOneAndHalfTimesOfBet() {
        Bet bet = new Bet(20000);
        Bet revenue = Status.BLACKJACK.calculateRevenue(bet);

        assertThat(revenue).isEqualTo(new Bet(30000));
    }

    @Test
    @DisplayName("이기면 수익은 베팅만큼이다.")
    void calculateRevenue_Win_isSameWithBet() {
        Bet bet = new Bet(20000);
        Bet revenue = Status.WIN.calculateRevenue(bet);

        assertThat(revenue).isEqualTo(bet);
    }

    @Test
    @DisplayName("지면 손실은 베팅만큼이다.")
    void calculateRevenue_Lose_isMinus() {
        Bet bet = new Bet(20000);
        Bet revenue = Status.LOSE.calculateRevenue(bet);

        assertThat(revenue).isEqualTo(new Bet(-20000));
    }

    @Test
    @DisplayName("비기면 수익이 0이다.")
    void calculateRevenue_Tie_isZero() {
        Bet bet = new Bet(20000);
        Bet revenue = Status.TIE.calculateRevenue(bet);

        assertThat(revenue).isEqualTo(new Bet(0));
    }
}

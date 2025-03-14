package domain.gamer;

import domain.bet.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetTest {
    @DisplayName("블랙잭인 경우 베팅 금액의 1.5배를 준다")
    @Test
    void isBlackJackBetTest() {
        Bet bet = new Bet(10);

        int blackJackBet = bet.getBlackJackPrize();

        assertThat(blackJackBet).isEqualTo(15);
    }

    @DisplayName("승리한 경우 베팅한 금액을 받는다")
    @Test
    void winningBetTest() {
        Bet bet = new Bet(10);

        int winningBet = bet.getWinningPrize();

        assertThat(winningBet).isEqualTo(10);
    }

    @DisplayName("패배한 경우 베팅한 금액을 잃는다")
    @Test
    void losingBetTest() {
        Bet bet = new Bet(10);

        int losingBet = bet.getLosingPrize();

        assertThat(losingBet).isEqualTo(-10);
    }

    @DisplayName("무승부 경우 금액을 돌려받는다")
    @Test
    void drawingBetTest() {
        Bet bet = new Bet(10);

        int losingBet = bet.getDrawPrize();

        assertThat(losingBet).isEqualTo(0);
    }
}
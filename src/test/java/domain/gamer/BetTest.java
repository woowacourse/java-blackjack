package domain.gamer;

import domain.bet.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BetTest {
    @DisplayName("블랙잭인 경우 베팅 금액의 1.5배를 준다")
    @Test
    void isBlackJackBetTest(){
        Bet bet = new Bet(10);

        Bet blackJackBet = bet.getBlackJackPrize();

        assertThat(blackJackBet.getBettingCount()).isEqualTo(15);
    }

    @DisplayName("승리한 경우 베팅한 금액을 받는다")
    @Test
    void winningBetTest(){
        Bet bet = new Bet(10);

        Bet winningBet = bet.getWinningPrize();

        assertThat(winningBet.getBettingCount()).isEqualTo(10);
    }

    @DisplayName("패배한 경우 베팅한 금액을 잃는다")
    @Test
    void losingBetTest(){
        Bet bet = new Bet(10);

        Bet losingBet = bet.getLosingPrize();

        assertThat(losingBet.getBettingCount()).isEqualTo(-10);
    }

    @DisplayName("무승부 경우 금액을 돌려받는다")
    @Test
    void drawingBetTest(){
        Bet bet = new Bet(10);

        Bet losingBet = bet.getDrawPrize();

        assertThat(losingBet.getBettingCount()).isEqualTo(0);
    }
}
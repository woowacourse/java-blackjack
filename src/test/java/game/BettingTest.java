package game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void 배팅_금액을_정한다() {
        int money = 10000;

        Betting betting = new Betting(money);

        assertThat(betting.getMoney()).isEqualTo(money);
    }
    
    @Test
    void 승리하면_배팅_금액을_받는다() {
        GameResult result = GameResult.WIN;
        int money = 10000;
        Betting betting = new Betting(money);

        int profit = betting.evaluate(result);

        assertThat(profit).isEqualTo(money);
    }

    @Test
    void 무승부이면_수익이_없다() {
        GameResult result = GameResult.DRAW;
        int money = 10000;
        Betting betting = new Betting(money);

        int profit = betting.evaluate(result);

        assertThat(profit).isEqualTo(0);
    }

    @Test
    void 패배하면_배팅_금액을_잃는다() {
        GameResult result = GameResult.LOSE;
        int money = 10000;
        Betting betting = new Betting(money);

        int profit = betting.evaluate(result);

        assertThat(profit).isEqualTo(-1 * money);
    }

    @Test
    void 블랙잭이면_1_5배를_받는다() {
        GameResult result = GameResult.BLACKJACK;
        int money = 10000;
        Betting betting = new Betting(money);

        int profit = betting.evaluate(result);

        assertThat(profit).isEqualTo((int) (money * 1.5));
    }


}

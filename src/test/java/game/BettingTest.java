package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void 배팅_금액을_정한다() {
        int money = 10000;

        Betting betting = new Betting(money);

        assertThat(betting.getBetting()).isEqualTo(money);
    }

    @Test
    void 베팅_금액이_음수일_경우_예외를_던진다() {
        int money = -1;

        assertThatThrownBy(() -> new Betting(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 승리하면_배팅_금액을_받는다() {
        GameResult result = GameResult.WIN;
        Betting betting = new Betting(10000);

        int earned = betting.evaluate(result);

        assertThat(earned).isEqualTo(10000);
    }

    @Test
    void 무승부이면_수익이_없다() {
        GameResult result = GameResult.DRAW;
        Betting betting = new Betting(10000);

        int earned = betting.evaluate(result);

        assertThat(earned).isEqualTo(0);
    }

    @Test
    void 패배하면_배팅_금액을_잃는다() {
        GameResult result = GameResult.LOSE;
        Betting betting = new Betting(10000);

        int earned = betting.evaluate(result);

        assertThat(earned).isEqualTo(-10000);
    }

    @Test
    void 블랙잭이면_1_5배를_받는다() {
        GameResult result = GameResult.BLACKJACK;
        Betting betting = new Betting(10000);

        int earned = betting.evaluate(result);

        assertThat(earned).isEqualTo(15000);
    }


}

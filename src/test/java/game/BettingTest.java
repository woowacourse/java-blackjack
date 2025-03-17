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

}

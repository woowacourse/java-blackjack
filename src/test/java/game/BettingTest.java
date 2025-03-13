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
}

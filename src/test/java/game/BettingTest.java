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
    void 배팅_금액을_모두_잃는다() {
        int money = 10000;
        Betting betting = new Betting(money);

        betting.lose();

        assertThat(betting.getMoney()).isEqualTo(0);
    }

    @Test
    void 배팅_금액을_받는다() {
        int money = 10000;
        Betting betting = new Betting(money);

        int earned = betting.earn();

        assertThat(earned).isEqualTo(money);
    }

    @Test
    void 배팅_금액의_1_5배를_받는다() {
        int money = 10000;
        Betting betting = new Betting(money);

        int earned = betting.earnedWhenBlackJack();

        assertThat(earned).isEqualTo((int) (money * 1.5));
    }
}

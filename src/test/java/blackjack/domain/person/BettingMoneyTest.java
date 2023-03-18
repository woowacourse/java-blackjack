package blackjack.domain.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {

    @Test
    @DisplayName("블랙잭일 때 수익은 배팅금액의 1.5배이다.")
    void calculateBlackjackProfit() {
        // given
        Player player = new Player(new Name("user"));
        BettingMoney bettingMoney = new BettingMoney(Map.of(player, 1000));

        // when
        double profit = bettingMoney.getBlackjackProfit(player);

        // then
        assertThat(profit)
                .isEqualTo(1500);
    }

    @Test
    @DisplayName("승리했을 때 수익은 배팅금액이다.")
    void calculateWinProfit() {
        // given
        Player player = new Player(new Name("user"));
        BettingMoney bettingMoney = new BettingMoney(Map.of(player, 1000));

        // when
        double profit = bettingMoney.getWinProfit(player);

        // then
        assertThat(profit)
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("무승부일 때 수익은 0이다.")
    void calculateDrawProfit() {
        // given
        Player player = new Player(new Name("user"));
        BettingMoney bettingMoney = new BettingMoney(Map.of(player, 1000));

        // when
        double profit = bettingMoney.getDrawProfit(player);

        // then
        assertThat(profit)
                .isEqualTo(0);
    }

    @Test
    @DisplayName("패배했을 때 수익은 배팅금액의 음수이다.")
    void calculateLoseProfit() {
        // given
        Player player = new Player(new Name("user"));
        BettingMoney bettingMoney = new BettingMoney(Map.of(player, 1000));

        // when
        double profit = bettingMoney.getLoseProfit(player);

        // then
        assertThat(profit)
                .isEqualTo(-1000);
    }
}
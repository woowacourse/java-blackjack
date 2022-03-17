package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    GameResult gameResult;
    Player player;

    @BeforeEach
    void init() {
        gameResult = new GameResult();
        player = new Player("pobi");
    }

    @DisplayName("플레이어가 블랙잭으로 이겼을 때 수익금이 올바르게 계산되는지 확인한다.")
    @Test
    void win_blackjack_profit() {
        gameResult.putResult(player, "블랙잭");
        Money money = gameResult.calculateProfit(player, Money.of("5000"));

        assertThat(money.getMoney()).isEqualTo(7500);
    }

    @DisplayName("플레이어가 이겼을 때 수익금이 올바르게 계산되는지 확인한다.")
    @Test
    void win_profit() {
        gameResult.putResult(player, "승");
        Money money = gameResult.calculateProfit(player, Money.of("5000"));

        assertThat(money.getMoney()).isEqualTo(5000);
    }

    @DisplayName("플레이어가 졌을 때 수익금이 올바르게 계산되는지 확인한다.")
    @Test
    void lose_profit() {
        gameResult.putResult(player, "패");
        Money money = gameResult.calculateProfit(player, Money.of("5000"));

        assertThat(money.getMoney()).isEqualTo(-5000);
    }

    @DisplayName("플레이어가 비겼을 때 수익금이 올바르게 계산되는지 확인한다.")
    @Test
    void draw_profit() {
        gameResult.putResult(player, "무");
        Money money = gameResult.calculateProfit(player, Money.of("5000"));

        assertThat(money.getMoney()).isEqualTo(0);
    }
}

package model.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.user.money.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("player의 결과에 따라 수익계산")
    void multiplyBettingMoney_Test() {
        Player player = new Player("tiger", new BettingMoney("100"));
        assertThat(player.multiplyBettingMoney(1.5)).isEqualTo(150);
    }
}
package blackjack.model.bettings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WagerTest {

    @DisplayName("딜러의 배팅 금액을 갱신한다.")
    @Test
    void wager_Dealer() {
        Wager dealerWager = new Wager(1000);

        dealerWager = dealerWager.updateDealerWager(1, 1000);

        assertThat(dealerWager.getWager()).isEqualTo(2000);
    }

    @DisplayName("플레이어의 배팅 금액을 갱신한다.")
    @Test
    void wager_Player() {
        Wager playerWager = new Wager(1000);

        playerWager = playerWager.updatePlayerWager(1.5);

        assertThat(playerWager.getWager()).isEqualTo(2500);
    }
}

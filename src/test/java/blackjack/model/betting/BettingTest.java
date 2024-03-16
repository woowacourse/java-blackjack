package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @Test
    @DisplayName("플레이어 이름으로 플레이어의 배팅 금액을 등록한다")
    void addPlayerBettingMoneyTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);

        // when
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // then
        assertThat(betting.findBettingMoneyByPlayerName(playerName)).isEqualTo(bettingMoney);
    }
}

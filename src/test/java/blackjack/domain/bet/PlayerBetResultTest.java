package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.PlayerBetResult;
import blackjack.domain.player.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetResultTest {

    @DisplayName("플레이어의 수익 합산의 반대로 딜러의 수익을 계산한다.")
    @Test
    void calculateDealerRevenue() {
        // given
        Map<PlayerName, BetRevenue> playersBetRevenu = Map.of(new PlayerName("pobi"), new BetRevenue(1000),
                new PlayerName("kirby"), new BetRevenue(2000),
                new PlayerName("json"), new BetRevenue(-1000));

        PlayerBetResult playerBetResult = new PlayerBetResult(playersBetRevenu);

        // when
        BetRevenue dealerRevenue = playerBetResult.calculateDealerRevenue();

        // then
        assertThat(dealerRevenue).isEqualTo(new BetRevenue(-2000));
    }
}

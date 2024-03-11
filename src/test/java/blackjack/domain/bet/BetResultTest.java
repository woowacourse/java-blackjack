package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.BetResult;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.bet.BetRevenue;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetResultTest {

    @DisplayName("플레이어의 수익 합산의 반대로 딜러의 수익을 계산한다.")
    @Test
    void calculateDealerRevenue() {
        // given
        Map<PlayerName, BetRevenue> playersBetRevenu = Map.of(new PlayerName("pobi"), new BetRevenue(1000),
                new PlayerName("kirby"), new BetRevenue(2000),
                new PlayerName("json"), new BetRevenue(-1000));

        BetResult betResult = new BetResult(playersBetRevenu);

        // when
        BetRevenue dealerRevenue = betResult.calculateDealerRevenue();

        // then
        assertThat(dealerRevenue).isEqualTo(new BetRevenue(-2000));
    }
}

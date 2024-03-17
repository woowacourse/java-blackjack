package blackjack.domain.profit;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import fixture.DealerFixture;
import fixture.PlayerFixture;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersProfitTest {

    @DisplayName("배팅 금액으로 초기 플레이어 수익을 생성한다.")
    @Test
    void testCreateInitial() {
        // given
        Map<Player, BetAmount> rawProfits = new HashMap<>();
        rawProfits.put(PlayerFixture.createPobi(), new BetAmount(10000));
        rawProfits.put(PlayerFixture.createJason(), new BetAmount(20000));

        // when
        PlayersProfit2 profits = PlayersProfit2.createInitial(rawProfits);

        // then
        assertThat(profits.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(10000),
                PlayerFixture.createJason(), new Profit(20000)
        ));
    }

    @DisplayName("배팅 금액과 결과에 따라 결정된 배팅 배수를 곱해 수익을 계산한다.")
    @Test
    void testCalculatePlayersProfit() {
        // given
        Map<Player, Profit> profits = new HashMap<>();
        profits.put(PlayerFixture.createPobi(), new Profit(10000));
        profits.put(PlayerFixture.createJason(), new Profit(20000));
        PlayersProfit2 playersProfit = new PlayersProfit2(profits);

        Dealer dealer = DealerFixture.createDealer();

        // when
        playersProfit.calculate(dealer);

        // then
        assertThat(playersProfit.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(10000),
                PlayerFixture.createJason(), new Profit(-20000)
        ));
    }
}

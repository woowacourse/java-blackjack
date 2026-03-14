package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.Bets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("플레이어들의 수익금 합산의 정반대 부호를 딜러의 수익금으로 반환한다.")
    void getDealerProfit_ReturnsZeroSumOfPlayerProfits() {
        Players players = Players.fromPlayerNicknames(List.of("pobi", "jason"));
        Bets bets = new Bets();
        Gamblers gamblers = new Gamblers(players, bets);
        Player pobi = players.getPlayers().get(0);
        Player jason = players.getPlayers().get(1);
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        playerProfits.put(pobi, 15000);
        playerProfits.put(jason, -10000);

        long dealerProfit = gamblers.getDealerProfit(playerProfits);

        assertThat(dealerProfit).isEqualTo(-5000L);
    }

    @Test
    @DisplayName("플레이어들의 수익금 합산이 0일 때 딜러의 수익금도 정확히 0이다.")
    void getDealerProfit_ReturnsZero_WhenPlayerProfitsSumIsZero() {
        Players players = Players.fromPlayerNicknames(List.of("pobi", "jason"));
        Bets bets = new Bets();
        Gamblers gamblers = new Gamblers(players, bets);
        Player pobi = players.getPlayers().get(0);
        Player jason = players.getPlayers().get(1);
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        playerProfits.put(pobi, 10000);
        playerProfits.put(jason, -10000);

        long dealerProfit = gamblers.getDealerProfit(playerProfits);

        assertThat(dealerProfit).isEqualTo(0L);
    }

    @Test
    @DisplayName("정산 내역이 없을 경우 딜러의 수익금은 0이다.")
    void getDealerProfit_ReturnsZero_WhenEmpty() {
        Players players = Players.fromPlayerNicknames(List.of("pobi", "jason"));
        Bets bets = new Bets();
        Gamblers gamblers = new Gamblers(players, bets);
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();

        long dealerProfit = gamblers.getDealerProfit(playerProfits);

        assertThat(dealerProfit).isEqualTo(0L);
    }
}

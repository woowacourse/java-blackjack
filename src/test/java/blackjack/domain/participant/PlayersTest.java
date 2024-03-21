package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.profit.PlayersProfit;
import blackjack.domain.profit.Profit;
import fixture.PlayerFixture;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("모든 플레이어가 배팅한다.")
    @Test
    void testBet() {
        // given
        Player pobi = PlayerFixture.createPobi();
        Player jason = PlayerFixture.createJason();
        Players players = new Players(List.of(pobi, jason));

        Function<Player, String> betByPlayer = player -> getBetAmount(player, pobi, jason);

        // when
        PlayersProfit profits = players.bet(betByPlayer);

        // then
        assertThat(profits.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)),
                PlayerFixture.createJason(), new Profit(new BigDecimal(20000))
        ));
    }

    private String getBetAmount(Player player, Player pobi, Player jason) {
        if (player.equals(pobi)) {
            return "10000";
        }
        if (player.equals(jason)) {
            return "20000";
        }
        throw new IllegalArgumentException();
    }
}

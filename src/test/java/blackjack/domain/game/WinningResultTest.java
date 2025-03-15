package blackjack.domain.game;

import static blackjack.domain.game.WinningType.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningResultTest {
    @DisplayName("우승_정보에_따라_플레이어의_수익금을_반환한다")
    @CsvSource(value = {"BLACKJACK_WIN:15_000", "WIN:10_000", "DRAW:0", "DEFEAT:-10_000"}, delimiterString = ":")
    @ParameterizedTest
    void calculatePlayersProfit(WinningType winningType, int expected) {
        // given
        Player player = new Player(new Name("레오"));
        player.bet(new BettingAmount(10_000));
        WinningResult winningResult = new WinningResult(Map.of(player, winningType));

        // when
        Map<Player, Integer> result = winningResult.calculatePlayersProfit();

        // then
        assertThat(result.get(player)).isEqualTo(expected);
    }

    @DisplayName("플레이어의_우승_정보에_따라_딜러의_수익금을_반환한다")
    @CsvSource(value = {"BLACKJACK_WIN:-25_000", "WIN:-20_000", "DRAW:-10_000", "DEFEAT:0"}, delimiterString = ":")
    @ParameterizedTest
    void calculateDealerProfit(WinningType winningType, int expected) {
        // given
        Player player1 = new Player(new Name("레오"));
        player1.bet(new BettingAmount(10_000));
        Player player2 = new Player(new Name("라젤"));
        player2.bet(new BettingAmount(10_000));
        WinningResult winningResult = new WinningResult(Map.of(player1, WIN, player2, winningType));

        // when
        int result = winningResult.calculateDealerProfit();

        // then
        assertThat(result).isEqualTo(expected);
    }
}

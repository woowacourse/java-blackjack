package blackjack.model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BetsTest {

    @Nested
    @DisplayName("수익률 계산")
    class calculateProfit {
        private Bets bets;
        private Player player;


        @BeforeEach
        void init() {
            bets = new Bets();
            player = new Player("리브");
        }

        @ParameterizedTest
        @CsvSource(value = {"WIN,10_000", "LOSE,-10_000", "DRAW,10_000", "BLACK_JACK_WIN,15_000"})
        @DisplayName("플레이어의 수익을 계산한다.")
        void calculatePlayerProfit(ResultCommand resultCommand, int expected) {
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = new LinkedHashMap<>(Map.of(player, resultCommand));
            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Profit(expected)));
        }

        @ParameterizedTest
        @CsvSource(value = {"WIN,-10_000", "LOSE,10_000", "DRAW,-10_000", "BLACK_JACK_WIN,-15_000"})
        @DisplayName("딜러의 수익을 계산한다.")
        void calculateDealerProfit(ResultCommand resultCommand, int expected) {
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = new LinkedHashMap<>(Map.of(player, resultCommand));
            assertThat(bets.calculateDealerProfit(playerResultCommand)).isEqualTo(new Profit(expected));
        }
    }
}

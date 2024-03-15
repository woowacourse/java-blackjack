package blackjack.model.money;

import static blackjack.model.result.ResultCommand.BLACK_JACK_WIN;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.LOSE;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BetsTest {
    @Nested
    @DisplayName("플레이어 수익률 계산 시")
    class PlayerProfit {

        @Test
        @DisplayName("승리하면 베팅한 금액을 모두 돌려받는다.")
        void calculateProfitWhenWin() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, WIN);

            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Profit(10_000)));
        }

        @Test
        @DisplayName("패배하면 베팅한 금액을 모두 잃는다.")
        void calculateProfitWhenLose() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, LOSE);

            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Profit(-10_000)));
        }

        @Test
        @DisplayName("무승부이면 베팅한 금액을 모두 돌려받는다.")
        void calculateProfitWhenDraw() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, DRAW);

            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Profit(10_000)));
        }

        @Test
        @DisplayName("블랙잭으로 승리하면 베팅한 금액의 1.5배를 받는다.")
        void calculateProfitWhenBlackJackWin() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, BLACK_JACK_WIN);

            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Profit(15_000)));
        }
    }

    @Nested
    @DisplayName("딜러 수익률 계산 시")
    class DealerProfit {

        @Test
        @DisplayName("플레이어가 승리하면 베팅 금액을 모두 플레이어에게 준다.")
        void calculateProfitWhenPlayerWin() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, WIN);

            assertThat(bets.calculateDealerProfit(playerResultCommand))
                    .isEqualTo(new Profit(-10_000));
        }

        @Test
        @DisplayName("플레이어가 패배하면 플레이어의 베팅 금액을 받는다.")
        void calculateProfitWhenPlayerLose() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, LOSE);

            assertThat(bets.calculateDealerProfit(playerResultCommand))
                    .isEqualTo(new Profit(10_000));
        }

        @Test
        @DisplayName("무승부이면 베팅 금액을 플레이어에게 모두 준다.")
        void calculateProfitWhenDraw() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, DRAW);

            assertThat(bets.calculateDealerProfit(playerResultCommand))
                    .isEqualTo(new Profit(-10_000));
        }

        @Test
        @DisplayName("플레이어가 블랙잭으로 승리하면 플레이어 베팅 금액의 1.5배를 플레이어에게 준다.")
        void calculateProfitWhenPlayerBlackJackWin() {
            Player player = Fixtures.createPlayer();
            Bets bets = Fixtures.createBets();
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = Fixtures.createPlayerResultCommand(player, BLACK_JACK_WIN);

            assertThat(bets.calculateDealerProfit(playerResultCommand))
                    .isEqualTo(new Profit(-15_000));
        }
    }

    static class Fixtures {
        static Bets createBets() {
            return new Bets();
        }

        static Player createPlayer() {
            return new Player("리브");
        }

        static Map<Player, ResultCommand> createPlayerResultCommand(Player player, ResultCommand resultCommand) {
            return new LinkedHashMap<>(Map.of(player, resultCommand));
        }
    }
}

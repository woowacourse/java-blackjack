import bet.BetAmount;
import bet.BetCenter;
import io.ConsoleInput;
import io.ConsoleOutput;
import participant.Player;
import strategy.winning.BlackJackWinStrategy;
import strategy.winning.DealerBlackJackStrategy;
import strategy.winning.DrawStrategy;
import strategy.winning.LoseStrategy;
import strategy.winning.NormalWinStrategy;
import strategy.winning.WinningStrategy;

import java.util.List;
import java.util.Map;

public class Application {
    private static final List<WinningStrategy> strategies = List.of(
            new BlackJackWinStrategy(),
            new DealerBlackJackStrategy(),
            new NormalWinStrategy(),
            new DrawStrategy(),
            new LoseStrategy()
    );

    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();

        BlackJackManager manager = new BlackJackManager(
                input.readParticipantsNames(),
                players -> {
            Map<Player, BetAmount> playerBets = input.readPlayerBetAmounts(players);
            return new BetCenter(playerBets, strategies);
        });

        output.printInitialGameSettings(manager.getPlayers(), manager.getDealer());
        manager.performPlayerTurn(
                player -> input.readShouldHit(player.getNickname()),
                output::printPlayerCards
        );

        manager.performDealerTurn(
                output::printDealerHitMessage
        );

        output.printGameResults(manager.getPlayers(), manager.getDealer());
        output.printFinalProfit(manager.getBetCenter(), manager.getDealer());
    }
}

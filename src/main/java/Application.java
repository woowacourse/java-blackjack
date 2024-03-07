import domain.Blackjack;
import domain.BlackjackResultDTO;
import domain.Player;
import domain.Players;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Blackjack blackjack = createBlackjack();
        OutputView.printPlayersStatus(blackjack.getPlayers());

        playGame(blackjack);
        OutputView.printResults(blackjack.getPlayers());

        BlackjackResultDTO blackjackResult = blackjack.finishGame();
        OutputView.printGameResults(blackjackResult);
    }

    private static void playGame(final Blackjack blackjack) {
        for (var player : blackjack.getParticipants()) {
            drawCardDuringPlayerTurn(player, blackjack);
        }

        Player dealer = blackjack.getDealer();

        if (dealer.alive()) {
            blackjack.dealCard(dealer);
            OutputView.printDealerStatus();
        }
    }

    private static Blackjack createBlackjack() {
        String[] names = InputView.inputPlayers();
        Players players = Players.from(names);
        return new Blackjack(players);
    }

    private static void drawCardDuringPlayerTurn(final Player player, final Blackjack blackjack) {
        while (player.alive() && InputView.tryPoll(player.getName())) {
            blackjack.dealCard(player);
        }
        OutputView.printPlayerStatus(player);
    }
}

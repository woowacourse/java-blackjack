import domain.Blackjack;
import domain.BlackjackResult;
import domain.Player;
import domain.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(final String[] args) {
        final Blackjack blackjack = createBlackjack();
        OutputView.printPlayersStatus(blackjack.getPlayers());

        playGame(blackjack);
        OutputView.printResults(blackjack.getPlayers());

        final BlackjackResult blackjackResult = blackjack.finishGame();
        OutputView.printBlackjackResults(blackjackResult);
    }

    private static void playGame(final Blackjack blackjack) {
        for (final var player : blackjack.getParticipants()) {
            drawCardDuringPlayerTurn(player, blackjack);
        }

        final Player dealer = blackjack.getDealer();

        if (dealer.isNotBust()) {
            blackjack.dealCard(dealer);
            OutputView.printDealerHitMessage();
        }
    }

    private static Blackjack createBlackjack() {
        final List<String> inputtedNames = InputView.inputNames();
        final Players players = Players.from(inputtedNames);
        return new Blackjack(players);
    }

    private static void drawCardDuringPlayerTurn(final Player player, final Blackjack blackjack) {
        while (player.isNotBust() && InputView.tryHit(player.getName())) {
            blackjack.dealCard(player);
        }
        OutputView.printPlayerStatus(player);
    }
}

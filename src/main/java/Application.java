import domain.Blackjack;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(final String[] args) {
        final List<String> names = InputView.inputPlayers();
        final List<Integer> betAmounts = names.stream().map(InputView::inputBettingAmount).toList();
        final Blackjack blackjack = Blackjack.of(names, betAmounts);

        OutputView.printInitialStatus(blackjack.toParticipantsResponse());

        playGame(blackjack, names);

        OutputView.printAfterStatus(blackjack.toParticipantsResponse());
        OutputView.printProfit(blackjack.toGameResult());
    }

    private static void playGame(final Blackjack blackjack, final List<String> names) {
        names.forEach(name -> tryHit(blackjack, name));

        blackjack.dealerHit(OutputView::printDealerHitMessage);
        blackjack.dealerStand();
    }

    private static void tryHit(final Blackjack blackjack, final String name) {
        while (blackjack.canPlayerHit(name)) {
            if (InputView.tryHit(name)) {
                blackjack.playerHit(name);
                OutputView.printStatus(name, blackjack.toPlayerResponse(name).cardResponse());
                System.out.println();
                continue;
            }
            blackjack.playerStand(name);
        }
    }
}

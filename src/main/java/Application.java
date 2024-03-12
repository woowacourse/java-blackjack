import domain.Blackjack;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(final String[] args) {
        final List<String> names = InputView.inputPlayers();
        final Blackjack blackjack = Blackjack.fromPlayerNamesWithInitialization(names);
        OutputView.printInitialStatus(blackjack.getDealer(), blackjack.getPlayers());

        playGame(blackjack, names);

        OutputView.printResults(blackjack.getDealer(), blackjack.getPlayers());
        OutputView.printWinOrLose(blackjack.finishGame());
    }

    private static void playGame(final Blackjack blackjack, final List<String> names) {
        names.forEach(name -> tryHit(blackjack, name));

        while (blackjack.canDealerHit()) {
            blackjack.dealerHit();
            OutputView.printDealerHitMessage();
        }
    }

    private static void tryHit(final Blackjack blackjack, final String name) {
        while (blackjack.canPlayerHit(name) && InputView.tryHit(name)) {
            blackjack.playerHit(name);
            OutputView.printStatus(blackjack.getPlayer(name));
        }
    }

}

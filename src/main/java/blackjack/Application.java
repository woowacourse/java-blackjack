package blackjack;

import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        List<String> names = inputView.askEntryNames();

        Game game = new Game(names);
        game.start();

        final ResultView resultView = new ResultView();
        resultView.printDeckInitialized(game.getEntryNames());
        resultView.printInitializedDecks(game.getNames(), game.getDecksToString());

        do {
            game.toNextEntry();
            playTurn(inputView, game, resultView);
        } while (game.hasNextEntry());

        game.hitDealer();
        if (game.countCardsAddedToDealer() > 0) {
            resultView.printDealerHitCount(game.countCardsAddedToDealer());
        }
    }

    private static void playTurn(InputView inputView, Game game, ResultView resultView) {
        if (game.isCurrentEntryBust()) {
            resultView.printBustMessage(game.getCurrentEntryName());
            return;
        }
        if (!inputView.askForHit(game.getCurrentEntryName())) {
            resultView.printDeck(game.getCurrentEntryName(), game.getCurrentDeckToString());
            return;
        }
        game.hitCurrentEntry();
        resultView.printDeck(game.getCurrentEntryName(), game.getCurrentDeckToString());
        playTurn(inputView, game, resultView);
    }
}

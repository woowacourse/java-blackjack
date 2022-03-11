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
            inputView.askForHit(game.getCurrentEntryName());
        } while (game.hasNextEntry());
    }
}

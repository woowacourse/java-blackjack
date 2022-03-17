package blackjack;

import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.view.BlackjackView;
import blackjack.view.input.InputView;
import blackjack.view.input.reader.ConsoleReader;
import blackjack.view.output.OutputView;

public class MainApplication {

    public static void main(String[] args) {
        final MainApplication mainApplication = new MainApplication();
        mainApplication.run();
    }

    private void run() {
        final ConsoleReader reader = new ConsoleReader();
        final InputView inputView = new InputView(reader);
        final OutputView outputView = new OutputView();
        final BlackjackView blackjackView = new BlackjackView(inputView, outputView);
        final DeckGenerator deckGenerator = new RandomDeckGenerator();
        final BlackjackGame blackjackGame = new BlackjackGame(deckGenerator);
        final BlackjackApplication blackjackApplication = new BlackjackApplication(blackjackGame, blackjackView);

        blackjackApplication.run();
    }

}

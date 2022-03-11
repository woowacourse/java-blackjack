package blackjack;

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
        final BlackjackApplication blackjackApplication = new BlackjackApplication(blackjackView);
        blackjackApplication.run();
    }
}

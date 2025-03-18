package blackjack;

import blackjack.controller.Controller;
import blackjack.domain.BlackjackGameFactory;
import blackjack.domain.card.Deck;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        Controller controller = makeController();
        BlackjackGameFactory blackjackGameFactory = new BlackjackGameFactory(Deck.shuffled());
        controller.startGame(blackjackGameFactory);
    }

    private static Controller makeController() {
        final InputView inputView = new InputView(new Scanner(System.in));
        final ResultView resultView = new ResultView();
        return new Controller(inputView, resultView);
    }
}

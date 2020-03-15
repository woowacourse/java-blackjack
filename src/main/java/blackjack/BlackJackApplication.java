package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.console.ConsoleInputView;
import blackjack.view.console.ConsoleOutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        InputView consoleInputView = new ConsoleInputView();
        OutputView consoleOutputView = new ConsoleOutputView();
        BlackjackController blackjackController = new BlackjackController(consoleInputView, consoleOutputView);

        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        blackjackController.run(dealer, deck);
    }
}

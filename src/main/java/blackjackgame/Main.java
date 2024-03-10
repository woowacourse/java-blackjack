package blackjackgame;

import blackjackgame.controller.BlackjackController;
import blackjackgame.domain.blackjack.Gamer;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.card.Deck;
import java.util.List;
import blackjackgame.view.NameInputView;
import blackjackgame.view.OutputView;

public class Main {
    private static final String DEALER_NAME = "딜러";

    public static void main(String[] args) {
        Gamer dealer = new Gamer(DEALER_NAME, HoldingCards.of());
        OutputView.printInputNamesMessage();
        List<Gamer> players = NameInputView.getNames().stream()
                .map(name -> new Gamer(name, HoldingCards.of()))
                .toList();

        BlackjackController blackjackController = new BlackjackController(dealer, players);
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}

package blackjackgame;

import controller.BlackjackController;
import domain.blackjack.Gamer;
import domain.blackjack.HoldingCards;
import domain.card.Deck;
import java.util.List;
import view.NameInputView;
import view.OutputView;

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

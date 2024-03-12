package blackjackgame;

import blackjackgame.controller.BlackjackController;
import blackjackgame.domain.blackjack.CardHolderGamer;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.blackjack.Gamers;
import blackjackgame.domain.card.Deck;
import blackjackgame.view.NameInputView;
import blackjackgame.view.OutputView;
import java.util.List;

public class Main {
    private static final String DEALER_NAME = "딜러";

    public static void main(String[] args) {
        CardHolderGamer blackjackDealer = new CardHolderGamer(DEALER_NAME, HoldingCards.of());
        OutputView.printInputNamesMessage();
        List<CardHolderGamer> players = NameInputView.getNames().stream()
                .map(name -> new CardHolderGamer(name, HoldingCards.of()))
                .toList();
        Gamers blackjackPlayers = new Gamers(players);

        BlackjackController blackjackController = new BlackjackController(blackjackDealer, blackjackPlayers);
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}

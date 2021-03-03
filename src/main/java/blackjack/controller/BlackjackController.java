package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamers;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        final Deck deck = Deck.create();
        final Gamers gamers = deck.initiateGamers(InputView.receiveNames());
        OutputView.printGameStartMsg(gamers);
    }
}

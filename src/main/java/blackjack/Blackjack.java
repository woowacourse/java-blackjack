package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        Players players = Players.of(playerNames);
        User dealer = Dealer.create();
        Deck deck = Deck.create();

        dealer.giveCards(deck.draw(), deck.draw());
        for (int i = 0; i < players.memberSize(); i++) {
            players.giveCards(i, deck.draw(), deck.draw());
        }

        OutputView.printInitialInfo(dealer, players);
    }
}

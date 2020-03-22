package blackjack.domain.rule;

import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class HandInitializer {

    public static final int INITIAL_HAND_SIZE = 2;

    public static void initialize(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < INITIAL_HAND_SIZE; i++) {
            drawCard(dealer, players, deck);
        }
    }

    private static void drawCard(Dealer dealer, Players players, Deck deck) {
        dealer.draw(deck.pick());
        for (Player player : players) {
            player.draw(deck.pick());
        }
    }
}

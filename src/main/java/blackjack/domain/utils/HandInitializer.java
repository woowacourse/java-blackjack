package blackjack.domain.utils;

import blackjack.domain.gamer.Players;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class HandInitializer {

    public static void init(Deck deck, Dealer dealer, Players players) {
        dealer.initHands(deck.makeInitialHands());
        for (Player player : players) {
            player.initHands(deck.makeInitialHands());
        }
    }
}

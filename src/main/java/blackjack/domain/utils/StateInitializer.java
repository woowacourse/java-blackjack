package blackjack.domain.utils;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class StateInitializer {

//    public static void init(Deck deck, Dealer dealer, Players players) {
//        dealer.initHands(deck.makeInitialHands());
//        for (Player player : players) {
//            player.initHands(deck.makeInitialHands());
//        }
//    }

    public static void init2(Deck deck, Dealer dealer, Players players) {
        dealer.initState(deck.makeInitialHands());
        for (Player player : players) {
            player.initState(deck.makeInitialHands());
        }
    }
}

package blackjack.domain.player.state;

import blackjack.domain.card.Deck;

public class StateFactory {

    public static State start(Deck deck) {
        if (deck.isBlackJack()) {
            return new BlackJack(deck);
        }
        return new Hit(deck);
    }
}

package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> playerCards;

    public Hand(List<Card> cards) {
        this.playerCards = cards;
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(List.copyOf(playerCards));
        newCards.add(card);
        return new Hand(List.copyOf(newCards));
    }

    public List<Card> getHand() {
        return this.playerCards;
    }
}

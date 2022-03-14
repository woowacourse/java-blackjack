package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;

public class Table {

    private final Deck deck;

    public Table(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void giveCard(Player player) {
        player.receiveCard(drawCard());
    }
}

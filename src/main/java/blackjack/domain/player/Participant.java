package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Participant {

    private final String name;
    private final Cards cards = new Cards();

    public Participant(final List<Card> cards, final String name) {
        this.cards.addCards(cards);
        this.name = name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}

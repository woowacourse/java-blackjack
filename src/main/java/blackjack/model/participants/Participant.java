package blackjack.model.participants;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import java.util.List;

public abstract class Participant {
    protected final Cards cards;

    protected Participant() {
        this.cards = new Cards();
    }

    public abstract boolean checkDrawCardState();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cardToAdd) {
        cards.add(cardToAdd);
    }

    public Cards getCards() {
        return cards;
    }
}

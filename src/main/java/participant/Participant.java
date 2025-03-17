package participant;

import game.Card;
import game.Cards;
import game.Deck;

import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    public Participant(Deck deck) {
        this.cards = deck.drawInitialCards();
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}

package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {

    protected Name name;
    protected Cards cards;

    public Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public void drawCard(List<Card> providedCards) {
        cards.addCards(providedCards);
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public int getTotalNumberSum() {
        return cards.calculateTotalCardNumber();
    }

    public int calculateDifferenceFromTwentyOne() {
        return cards.calculateDifferenceFromBurst();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }
}

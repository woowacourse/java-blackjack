package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;
import java.util.function.Function;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalSum() {
        return cards.getTotalSum();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCards(Function<Integer, List<Card>> getCardsFunc, int quantity) {
        cards.addAll(getCardsFunc.apply(quantity));
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

}

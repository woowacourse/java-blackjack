package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCard(Supplier<Card> getCard) {
        cards.add(getCard);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCards(Function<Integer, List<Card>> getCardsFunc, int quantity) {
        cards.addAll(getCardsFunc, quantity);
    }

    public Score getTotalSum() {
        return cards.getTotalSum();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

}

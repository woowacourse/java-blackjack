package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Hand;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCard(Supplier<Card> getCard) {
        hand.add(getCard);
    }

    public void addCards(List<Card> cards) {
        this.hand.addAll(cards);
    }

    public void addCards(Function<Integer, List<Card>> getCardsFunc, int quantity) {
        hand.addAll(getCardsFunc, quantity);
    }

    public Score getTotalSum() {
        return hand.getTotalSum();
    }

    public Name getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }

}

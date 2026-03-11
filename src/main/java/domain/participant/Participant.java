package domain.participant;

import domain.Rank;
import domain.Score;
import domain.card.Card;
import domain.card.Hand;

import java.util.List;

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

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public Score getTotalSum() {
        return Rank.totalSum(hand.getAceAmount(), hand.getSumWithoutAce());
    }

    public Name getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }

}

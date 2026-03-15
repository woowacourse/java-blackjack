package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

import static domain.BlackjackRule.INITIAL_CARDS_COUNT;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    protected Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void receiveInitialCards(Cards cards) {
        if (cards.size() != INITIAL_CARDS_COUNT) {
            throw new IllegalArgumentException();
        }

        cards.cards().forEach(this::receive);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void receive(Card card) {
        hand.add(card);
    }

    public int handSize() {
        return hand.size();
    }

    public int score() {
        return hand.score();
    }

    public String name() {
        return name.name();
    }

    public Card card() {
        return hand.peek();
    }

    public List<Card> cards() {
        return hand.cards();
    }

    public abstract boolean canDraw();
}

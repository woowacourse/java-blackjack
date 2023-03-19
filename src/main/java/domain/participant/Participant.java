package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK = 21;

    private final String name;
    private final Hand hand;

    Participant(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.getScore();
    }

    public void initHand(List<Card> twoCards) {
        twoCards.forEach(hand::addCard);
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.toList());
    }
}

package domain.member;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Member {

    private final Hand hand;
    private final Name name;

    public Member(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public boolean hasBust() {
        return hand.isBust();
    }

    public boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    public void initDraw(Deck deck) {
        receiveCard(deck.draw());
        receiveCard(deck.draw());
    }

    public int handValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> handCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}

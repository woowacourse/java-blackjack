package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private final Deck deck;
    private final Hand hand;

    public Dealer(Deck deck) {
        this.deck = deck;
        this.hand = new Hand(new ArrayList<>());
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void receiveHand(Card card) {
        hand.receiveHand(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateHandTotal() {
        return hand.calculateHandTotal();
    }

    public boolean hitDealer() {
        if (hand.calculateHandTotal() <= DEALER_HIT_THRESHOLD) {
            receiveHand(drawCard());
            return true;
        }
        return false;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getHand());
    }

    public Card getVisibleCard() {
        return hand.getFirstHand();
    }
}

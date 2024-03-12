package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Hand;

public class Dealer {

    private static final int HIT_CONDITION = 17;

    private Hand hand;

    public Dealer() {
        this(new Hand(List.of()));
    }

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public boolean isPossibleHit() {
        int totalNumbers = hand.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public void hitCard(Card card) {
        hand = hand.add(card);
    }

    public void hitCards(List<Card> cards) {
        hand = hand.addAll(cards);
    }

    public Card getFirstCard() {
        return hand.getCards().get(0);
    }

    public int handSize() {
        return hand.size();
    }

    public Hand getHand() {
        return hand;
    }
}

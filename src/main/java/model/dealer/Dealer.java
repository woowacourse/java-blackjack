package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Hand;

public class Dealer {

    private static final int HIT_CONDITION = 17;

    private final Hand hand;

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

    public Dealer hitCard(Card card) {
        Hand addedCards = hand.add(card);
        return new Dealer(addedCards);
    }

    public Dealer hitCards(List<Card> cards) {
        Hand addedCards = this.hand.addAll(cards);
        return new Dealer(addedCards);
    }

    public int cardsSize() {
        return hand.size();
    }

    public Hand getHand() {
        return hand;
    }
}

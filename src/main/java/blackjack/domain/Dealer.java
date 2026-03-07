package blackjack.domain;

import java.util.List;

public class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Hand hand;

    private Dealer(Hand hand) {
        this.hand = hand;
    }

    public static Dealer of() {
        return new Dealer(Hand.init());
    }

    public Dealer receiveCards(List<TrumpCard> trumpCards) {
        Hand newHand = this.hand;
        for (TrumpCard card : trumpCards) {
            newHand = newHand.receive(card);
        }
        return new Dealer(newHand);
    }

    public Dealer receive(TrumpCard card) {
        Hand newHand = this.hand.receive(card);
        return new Dealer(newHand);
    }

    public int score() {
        return hand.calculateScore();
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }

    public boolean shouldHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }

    public TrumpCard getOpenCard() {
        return hand.getCards().getFirst();
    }
}

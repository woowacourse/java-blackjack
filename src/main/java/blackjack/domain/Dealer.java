package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public List<Integer> getPossibleSums() {
        return hand.calculatePossibleSums();
    }

    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public Card revealFirstCard() {
        return hand.getAllCards().getFirst();
    }

    public Hand getCardHolder() {
        return hand;
    }
}

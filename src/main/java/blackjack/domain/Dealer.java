package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer implements Participant {

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean tracksWinLossCount() {
        return true;
    }

    @Override
    public boolean shouldRevealSingleCard() {
        return true;
    }

    @Override
    public boolean doesHaveName() {
        return false;
    }

    @Override
    public List<Card> getCards() {
        return hand.getAllCards();
    }

    @Override
    public List<Integer> getPossibleSums() {
        return hand.calculatePossibleSums();
    }

    @Override
    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    @Override
    public int getOptimisticValue() {
        return hand.getOptimisticValue();
    }
}

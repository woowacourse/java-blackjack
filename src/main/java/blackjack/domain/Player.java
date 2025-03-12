package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player implements Participant {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public boolean tracksWinLossCount() {
        return false;
    }

    @Override
    public boolean shouldRevealSingleCard() {
        return false;
    }

    @Override
    public boolean doesHaveName() {
        return true;
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

    public String getName() {
        return name;
    }
}

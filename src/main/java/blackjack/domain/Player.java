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
    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    @Override
    public int getOptimisticValue() {
        return hand.getOptimisticValue();
    }

    // TODO: isOverLimit 과 합쳐야 할까?
    // TODO: 21 을 내부에서 알아도 될까?
    @Override
    public boolean ableToTakeMoreCards() {
        return hand.calculatePossibleSums().stream()
                .allMatch(sum -> sum <= 21);
    }

    @Override
    public boolean canDecideToTakeMoreCard() {
        return true;
    }

    @Override
    public boolean isOverLimit(int limit) {
        return hand.calculatePossibleSums().stream()
                .allMatch(sum -> sum <= limit);
    }

    public String getName() {
        return name;
    }
}

package domain;

import java.util.List;

public class Dealer extends Gamer {

    private static final int ACE_ADDITIONAL_NUMBER = 10;

    public Dealer(final Nickname nickname) {
        super(nickname);
    }

    public boolean canHit(final int threshold) {
        final Hand hand = getHand();
        final int sumOfRank = hand.getSumOfRank();
        if (hand.hasAce()) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER <= threshold;
        }
        return sumOfRank <= threshold;
    }

    @Override
    public int calculateSumOfRank() {
        final Hand hand = getHand();
        final int sumOfRank = hand.getSumOfRank();
        if (hand.hasAce()) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER;
        }
        return sumOfRank;
    }

    public Card getFirstCard() {
        final Hand hand = getHand();
        final List<Card> cards = hand.getCards();
        return cards.getFirst();
    }
}

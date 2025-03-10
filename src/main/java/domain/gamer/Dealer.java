package domain.gamer;

import domain.deck.Card;
import java.util.List;

public class Dealer extends Gamer {

    private static final int ACE_ADDITIONAL_NUMBER = 10;

    public Dealer(final Nickname nickname) {
        super(nickname);
    }

    public boolean canHit(final int threshold) {
        final int sumOfRank = getSumOfRank();
        if (hasAce()) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER <= threshold;
        }
        return sumOfRank <= threshold;
    }

    @Override
    public int calculateSumOfRank() {
        final int sumOfRank = getSumOfRank();
        if (hasAce()) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER;
        }
        return sumOfRank;
    }

    public Card getFirstCard() {
        final List<Card> cards = getCards();
        return cards.getFirst();
    }
}

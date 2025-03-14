package domain.gamer;

import static domain.BlackJackConstants.THRESHOLD;

import domain.deck.Card;
import java.util.List;

public class Dealer extends Gamer {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Nickname(DEALER_NAME));
    }

    public boolean canHit() {
        final int sumOfRank = getSumOfRank();
        if (hasAce()) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER <= THRESHOLD;
        }
        return sumOfRank <= THRESHOLD;
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

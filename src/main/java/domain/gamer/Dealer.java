package domain.gamer;

import domain.deck.Card;
import java.util.List;

public class Dealer extends Gamer {

    public static final int THRESHOLD = 16;

    public Dealer(final Nickname nickname) {
        super(nickname);
    }

    public boolean canHit() {
        final Hand hand = getHand();
        final int sumOfRank = hand.calculateSumOfRank();
        if (hand.hasAce()) {
            return hand.calculateSumOfRankConsideredAce() <= THRESHOLD;
        }
        return sumOfRank <= THRESHOLD;
    }

    @Override
    public List<Card> getVisibleCardsAtStart() {
        final Hand hand = getHand();
        final Card firstCard = hand.getCards().getFirst();
        return List.of(firstCard);
    }

    @Override
    public int calculateSumOfRank() {
        final Hand hand = getHand();
        if (hand.hasAce()) {
            return hand.calculateSumOfRankConsideredAce();
        }
        return hand.calculateSumOfRank();
    }
}

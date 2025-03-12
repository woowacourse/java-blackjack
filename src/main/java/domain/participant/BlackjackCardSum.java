package domain.participant;

import domain.blackjackgame.TrumpCard;
import java.util.ArrayList;
import java.util.List;

public class BlackjackCardSum {

    private static final int BUST_STANDARD = 21;
    private static final int BLACKJACK_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private static final int BLACKJACK_CARD_COUNT_STANDARD = 2;

    private final List<TrumpCard> cardHands;
    private final int cardSum;

    public BlackjackCardSum(List<TrumpCard> cardHands) {
        this.cardHands = new ArrayList<>(cardHands);
        cardSum = calculateCardSum();
    }

    public int calculateCardSum() {
        int sum = cardHands.stream().mapToInt(TrumpCard::cardNumberValue)
                .sum();
        int aceCount = (int) cardHands.stream()
                .filter(TrumpCard::isAce)
                .count();
        if (aceCount != 0) {
            return calculateAceIncludeSum(aceCount, sum);
        }
        return sum;
    }

    private int calculateAceIncludeSum(int aceCount, int sum) {
        while (isBust(sum) && aceCount != 0) {
            sum = sum - ACE_DIFF;
            aceCount--;
        }
        return sum;
    }

    private boolean isBust(int sum) {
        return BUST_STANDARD < sum;
    }

    public boolean isBust() {
        return BUST_STANDARD < cardSum;
    }

    public boolean isBlackjack() {
        int drawCardCount = cardHands.size();
        return drawCardCount == BLACKJACK_CARD_COUNT_STANDARD && BLACKJACK_STANDARD == cardSum;
    }
}

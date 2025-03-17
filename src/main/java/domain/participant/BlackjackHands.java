package domain.participant;

import domain.blackjackgame.TrumpCard;
import domain.except.BlackJackStateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackHands {

    private static final int BUST_STANDARD = 21;
    private static final int BLACKJACK_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private static final int BLACKJACK_CARD_COUNT_STANDARD = 2;
    private static final String INVALID_HANDS_STATE = "아직 카드를 받지 않은 참여자입니다.";

    private final List<TrumpCard> cardHands;
    private final int cardSum;

    public BlackjackHands(List<TrumpCard> cardHands) {
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
        return cardHands.size() == BLACKJACK_CARD_COUNT_STANDARD && BLACKJACK_STANDARD == cardSum;
    }

    public BlackjackHands addCard(TrumpCard trumpCard) {
        cardHands.add(trumpCard);
        return new BlackjackHands(this.cardHands);
    }

    public TrumpCard getFirst() {
        if (cardHands.isEmpty()) {
            throw new BlackJackStateException(INVALID_HANDS_STATE);
        }
        return cardHands.getFirst();
    }

    public List<TrumpCard> cards() {
        return Collections.unmodifiableList(cardHands);
    }
}

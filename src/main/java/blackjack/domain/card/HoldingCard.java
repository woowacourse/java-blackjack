package blackjack.domain.card;

import static blackjack.domain.BlackjackBoard.INIT_CARD_COUNT;

import java.util.ArrayList;
import java.util.List;

public class HoldingCard {
    public static final int BLACK_JACK_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> holdingCard;

    public HoldingCard() {
        this.holdingCard = new ArrayList<>();
    }

    public void add(Card card) {
        holdingCard.add(card);
    }

    public boolean isBust() {
        return computeTotalScore() > BLACK_JACK_SCORE;
    }

    public boolean isBlackjack() {
        return computeTotalScore() == BLACK_JACK_SCORE && holdingCard.size() == INIT_CARD_COUNT;
    }

    public int computeTotalScore() {
        int sum = sum();
        if (hasAce() && sum <= BLACK_JACK_SCORE - ACE_BONUS_SCORE) {
            sum += ACE_BONUS_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        return holdingCard.stream()
                .anyMatch(Card::isAce);
    }

    private int sum() {
        return holdingCard.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();
    }

    public List<Card> getHoldingCard() {
        return holdingCard;
    }
}

package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int WIN_MAX_VALUE = 21;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> receivedCards;

    public Hand() {
        this.receivedCards = new ArrayList<>();
    }

    public void hitCard(Card card) {
        receivedCards.add(card);
    }

    public boolean hasAceCard() {
        return receivedCards.stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    public int calculateCardNumber() {
        int sum = calculateCardNumberAceCardValueOne();
        if (hasAceCard() && sum <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return sum + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return receivedCards.size() == BLACKJACK_SIZE && calculateCardNumber() == WIN_MAX_VALUE;
    }

    private int calculateCardNumberAceCardValueOne() {
        return receivedCards.stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }

    public List<Card> getReceivedCards() {
        return receivedCards;
    }
}

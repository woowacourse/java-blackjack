package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int CHANGEABLE_SCORE_FOR_ACE_CARD = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalScore = getTotalScore();
        if (containsAce() && isRangeScore(totalScore)) {
            return totalScore + CHANGEABLE_SCORE_FOR_ACE_CARD;
        }
        return totalScore;
    }

    private int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.ACE);
    }

    private boolean isRangeScore(int totalScore) {
        return totalScore + CHANGEABLE_SCORE_FOR_ACE_CARD <= BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

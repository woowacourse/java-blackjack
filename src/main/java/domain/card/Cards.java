package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BONUS_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumAllCards() {
        int score = sumCardNumber();
        if (hasAce()) {
            return sumBonusScore(score);
        }
        return score;
    }

    private int sumCardNumber() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int sumBonusScore(int score) {
        int plussedBonusScore = score + BONUS_SCORE;
        if (plussedBonusScore <= BLACKJACK_SCORE) {
            return plussedBonusScore;
        }
        return score;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && sumAllCards() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return sumAllCards() > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}

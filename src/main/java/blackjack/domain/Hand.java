package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private static final int BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int hardHandScore = calculateHardHandScore();
        int softHandScore = hardHandScore + BONUS_SCORE;

        if (hasAce() && softHandScore <= BLACKJACK_SCORE) {
            return softHandScore;
        }

        return hardHandScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getCardRank)
                .anyMatch(CardRank::isAce);
    }

    private int calculateHardHandScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }
}

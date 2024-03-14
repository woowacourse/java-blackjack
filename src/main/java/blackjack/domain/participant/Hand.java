package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;

class Hand {

    private static final int BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        int score = calculateScore();

        return score > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return isBlackJackScore() && isBlackJackCardSize();
    }

    public boolean isBlackJackScore() {
        int score = calculateScore();

        return score == BLACKJACK_SCORE;
    }

    private boolean isBlackJackCardSize() {
        int size = cards.size();

        return size == BLACKJACK_CARD_SIZE;
    }

    public int calculateScore() {
        int hardHandScore = calculateHardHandScore();
        int softHandScore = hardHandScore + BONUS_SCORE;

        if (hasAce() && softHandScore <= BLACKJACK_SCORE) {
            return softHandScore;
        }

        return hardHandScore;
    }

    private int calculateHardHandScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getCardRank)
                .anyMatch(CardRank::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}

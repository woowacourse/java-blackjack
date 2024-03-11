package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_MAX_SCORE = 21;

    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public static PlayerCards empty() {
        return new PlayerCards(new ArrayList<>());
    }

    public void append(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int scoreValue = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (isBlackJackStatus(scoreValue)) {
            return Score.blackJackScore();
        }
        Score score = Score.of(scoreValue);
        int currentAceAmount = getAceCount();
        if (currentAceAmount > 0 && score.isBusted()) {
            return score.convertToSmallAce(currentAceAmount);
        }

        return score;
    }

    private boolean isBlackJackStatus(int score) {
        boolean matchedCardSize = (cards.size() == BLACKJACK_CARD_COUNT);
        boolean matchedScore = (score == BLACKJACK_MAX_SCORE);
        return matchedCardSize && matchedScore;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBusted() {
        Score score = calculateScore();
        return score.isBusted();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
